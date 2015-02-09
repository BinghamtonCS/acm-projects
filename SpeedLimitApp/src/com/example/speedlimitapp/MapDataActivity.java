package com.example.speedlimitapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapActivity;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.Overlay;

public class MapDataActivity extends MapActivity {
	
	protected MapView myMap;
	private SnappableMyLocationOverlay myLocationOverlay;
	
	TextView userData;
	TextView roadData;
	
	double lastLat;
	double lastLon;
	double lastTime;
	
	double testChange = 0.01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		
		userData = (TextView)findViewById(R.id.userData);
		roadData = (TextView)findViewById(R.id.roadData);
		
		//Initialize time and coordinate values
		lastLat = 0;
		lastLon = 0;
		lastTime = 0;
		
        this.setupMapView();
        this.setupMyLocation();
        
        getUserData();
		
	}
	
	 /**
     * This will set up a MapQuest map with a MyLocation Overlay
     */
	private void setupMapView() {
		this.myMap = (MapView) findViewById(R.id.map);
		
		// enable the zoom controls
		myMap.setBuiltInZoomControls(true);
        myMap.displayZoomControls(true);
	}
	
	private void setupMyLocation() {
		this.myLocationOverlay = new SnappableMyLocationOverlay(this, myMap);
		
		myLocationOverlay.enableMyLocation();
		
		myLocationOverlay.runOnFirstFix(new Runnable() {
			@Override
			public void run() {
				GeoPoint currentLocation = myLocationOverlay.getMyLocation(); 
				myMap.getController().animateTo(currentLocation);
				myMap.getController().setZoom(14);
				myMap.getOverlays().add(myLocationOverlay);
			}
		});
	}
	
	@Override
	protected void onResume() {
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableCompass();
		myLocationOverlay.disableMyLocation();
		this.myLocationOverlay = null;
	}
	
	/*@Override
	public void onBackPressed() {
		this.myLocationOverlay = null;
	    super.onBackPressed();
	    if ( myLocationOverlay != null ){
	    	this.myLocationOverlay.destroy();
	    }
	}*/

	@Override
	public boolean isRouteDisplayed() {
		return false;
	}
	

	/**
	 * This is a simple example of subclassing the MyLocation Overlay that implements the Overlay.Snappable interface.
	 * When implementing Overlay.Snappable you can focus on a POI where the users is attempting to pinch zoom in or out.  this 
	 * allows you to be a bit more accurate with the zoom behavoir when you are expecting a zoom into a poi for the user to focus on
	 * @author jsypher
	 *
	 */
	public class SnappableMyLocationOverlay extends MyLocationOverlay implements Overlay.Snappable {

		public SnappableMyLocationOverlay(Context context, MapView mapView) {
			super(context, mapView);
		}

		@Override
		public boolean onSnapToItem(int x, int y, Point snapPoint, MapView mapView) { 
			//we are always assuming the center 
			mapView.getProjection().toPixels(myLocationOverlay.getMyLocation(), snapPoint);
			return true;
		}
		
	}
	
	public void getUserData()
	{
    	final LocationManager locationManager = (LocationManager) MapDataActivity.this.getSystemService(Context.LOCATION_SERVICE);

    	// Define a listener that responds to location updates
    	LocationListener locationListener = new LocationListener() {
    	    public void onLocationChanged(Location location) {
    	    	
    	    	userData.setText(Html.fromHtml("<font color='#999999'>Current Speed:</font>"));
    	    	userData.append("\n");
    	    	
    		      // Called when a new location is found by the network location provider.
    	    	
    	    	String locationProvider = LocationManager.NETWORK_PROVIDER;
    	    	Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
    	    	//Log.v("OUTPUT", lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude());
    	    	
    	  
    	    	double lat = lastKnownLocation.getLatitude();
    	    	double lon = lastKnownLocation.getLongitude();
    	    	double time = lastKnownLocation.getTime();
    	    	
    	    	Log.e("VALUES-TIME", String.valueOf(time-lastTime));
    	    	
    	    	//lat+=testChange;
    	    	//lon+=testChange;
    	    	//testChange = testChange*1.1
    	    	//Log.e("VALUE-TEST", String.valueOf(testChange));
    	    	
    	    	getSpeedData(lastLat, lastLon, lastTime, lat, lon, time); 
 
    	    	//setupMyLocation();
    	    	if ( myLocationOverlay != null){
        	    	myLocationOverlay.setFollowing(true);
    	    	}
    	    	
    	    	String query = constructQuery(lat, lon);
    	    	new RoadDataTask().execute(query);
    	    	
    	    	lastLat = lat;
    	    	lastLon = lon;
    	    	lastTime = time;
    	    	
    	    	Log.e("VALUES", String.valueOf(lat));
    	    	Log.e("VALUES", String.valueOf(lon));
    	    	Log.e("VALUES", String.valueOf(time));
    	    }

    	    public void onStatusChanged(String provider, int status, Bundle extras) {}

    	    public void onProviderEnabled(String provider) {}

    	    public void onProviderDisabled(String provider) {}
    	  };

	    // Register the listener with the Location Manager to receive location updates
	    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    	
	}
	
	public double getNodeDistance(double lat1, double lon1, double lat2, double lon2){
		double radius = 6378.1;
		
		double dLat = toRadians(lat2-lat1);  
		double dLon = toRadians(lon2-lon1); 
		double a =  Math.sin(dLat/2) * Math.sin(dLat/2) + 
			Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) * 
		    Math.sin(dLon/2) * Math.sin(dLon/2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = radius * c; 
		
		return d;
	}
	
	
	public void getSpeedData(double lat1, double lon1, double time1, double lat2, double lon2, double time2){
		double speed;
		
		if ( time1 == 0 )
		{
			speed = 0;
		}
		else{
			double d = getNodeDistance(lat1, lon1, lat2, lon2);
			speed = d/(time2/1000 - time1/1000);
			
			//Round to two decimal points of precision
			speed = (double)Math.round(speed * 100) / 100;
		}
		userData.append(Html.fromHtml("<h1><font color='#000000'>"+speed+" mph</font></h1>"));
	}
	
	public double toRadians(double lat2){
		return lat2 * (Math.PI/180);
	}
	
	/*public void getRoadData(double lat, double lon) throws IOException{
		String query = constructQuery(lat,lon);
		Document doc = Jsoup.connect("http://www.overpass-api.de/api/interpreter")
				.data("data", query).post();
		Log.e("lol", "hmm");

	}*/
	
	public String constructQuery(double lat, double lon){
		String query = "<osm-script><query type=\"way\"><bbox-query e=\"";
		query = query+(lon+0.0003);
		query = query+"\" n=\"";
		query = query+(lat+0.0003);
		query = query+"\" s=\"";
		query = query+(lat-0.0003);
		query = query+"\" w=\"";
		query = query+(lon-0.0003);
		query = query+"\"/><has-kv k=\"highway\"/></query><union><item/><recurse type=\"down\"/></union><print/></osm-script>";
	
		return query;
	}
	
	public void addToRoadDataView(String text, boolean header, boolean lowCertainty){
		String html = "";
		if ( header ){
			html+="<font color='#999999'>"+text+":</font>";
		}
		else{
			if ( lowCertainty ){
				html+="<h1><font color='#FF0000'>"+text+"</font></h1>";
			}
			else{

				html+="<h1><font color='#000000'>"+text+"</font></h1>";
			}
		}
		roadData.append(Html.fromHtml(html));
		roadData.append("\n");
	}
	
	class RoadDataTask extends AsyncTask<String, Void, Map<String, String>> {

	    private Exception exception;

	    protected Map<String, String> doInBackground(String... urls) {
	    	
	    	Map<String, String> results = new HashMap<String, String>();
	    	
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://www.overpass-api.de/api/interpreter");

	        try {
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	            nameValuePairs.add(new BasicNameValuePair("data", urls[0]));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	            // Execute HTTP Post Request
	            HttpResponse response = httpclient.execute(httppost);
	            Log.e("ok", "start");
	            
	            String html = "";
	            InputStream in = response.getEntity().getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	            StringBuilder str = new StringBuilder();
	            String line = null;
	            while((line = reader.readLine()) != null)
	            {
	                str.append(line);
	            }
	            in.close();
	            html = str.toString();
	            
	            Document doc = Jsoup.parse(html);
	            
	            Elements nodes = doc.select("node");
	            Element closestNode = null;
	            double closestDistance = 0 ;
	            
	            for ( int i = 0; i < nodes.size(); i++){
	            	if ( i == 0 ){
	            		closestNode = nodes.get(i);
	            		closestDistance = getNodeDistance(lastLat, lastLon, Double.parseDouble(closestNode.attr("lat")), Double.parseDouble(closestNode.attr("lon")));
	            		
	            	}
	            	else{
	            		if ( closestDistance >  getNodeDistance(lastLat, lastLon, Double.parseDouble(nodes.get(i).attr("lat")), Double.parseDouble(nodes.get(i).attr("lon"))) ){
	            			 closestNode = nodes.get(i);
	            			 closestDistance = getNodeDistance(lastLat, lastLon, Double.parseDouble(nodes.get(i).attr("lat")), Double.parseDouble(nodes.get(i).attr("lon")));
	            		}	
	            	}
	            }
	            

	            Elements ways = doc.select("way");
	            Element containingWay = null; 
	            boolean containingWayFound = false;
	            
	            //Log.e("ID", closestNode.attr("id") );
	            for ( int i = 0; (i < ways.size())&&!containingWayFound; i++){
	            	Log.e("ok", "where is containing way");
	            	//Log.e("count", String.valueOf(i));
	            	Element tempWay = ways.get(i);
	            	Elements nds = tempWay.select("nd");
	            	
	            	for ( int j = 0; (j < nds.size())&&!containingWayFound; j++){
	            		//Log.e("count2", String.valueOf(j));
	            		//Log.e("ref", nds.get(j).attr("ref"));
	            		if ( nds.get(j).attr("ref").equals(closestNode.attr("id"))){
	            			containingWayFound = true;
	            			containingWay = tempWay;
	            			Log.e("FOUND!", containingWay.toString());
	            		}
	            	}
	            }
	            if ( containingWay!= null ){
	            	Elements tags = containingWay.select("tag");
	            	for ( int i = 0; i < tags.size(); i++ ){
	            		String attrK = tags.get(i).attr("k");
	            		String attrV = tags.get(i).attr("v");
	            		if ( attrK.equals("name") || attrK.equals("highway") || attrK.equals("lanes") ||
	            				attrK.equals("maxspeed") || attrK.equals("oneway")){
	            			results.put(tags.get(i).attr("k"), tags.get(i).attr("v"));
	            		}

	            	}
	            }
           
	            Log.e("ok", html);

	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        }
			Log.e("ok", "end");
	    	
	    	return results;
	    }

	    protected void onPostExecute(Map<String, String> results) {
			roadData.setText("");
	        if ( results.containsKey("name") ){
	        	addToRoadDataView("Name", true, false );
	        	addToRoadDataView(results.get("name"), false, false );
	        }
	        
	        addToRoadDataView("Speed Limit", true, false );
	        if ( results.containsKey("maxspeed")  ){
	        	addToRoadDataView(results.get("maxspeed"), false, false );
	        }
	        else if (results.containsKey("highway")) {
	        	String highwayType = results.get("highway");
	        	
	        	//Assumptions based on road type
	        	//Figures obtained from http://wiki.openstreetmap.org/wiki/OSM_tags_for_routing/Maxspeed#United_States_of_America
	        	//Appears as bright red in text view, as values are not necessarily accurate
	        	if ( highwayType.equals("motorway") ){
		        	addToRoadDataView("~65 mph", false, true );
	        	}
	        	else if ( highwayType.equals("trunk") ){
		        	addToRoadDataView("~55 mph", false, true );
	        	}
	        	else if ( highwayType.equals("primary") ){
		        	addToRoadDataView("~55 mph", false, true );
	        	}
	        	else if ( highwayType.equals("secondary") ){
		        	addToRoadDataView("~55 mph", false, true );
	        	}
	        	else if ( highwayType.equals("tertiary") ){
		        	addToRoadDataView("~55 mph", false, true );
	        	}
	        	else if ( highwayType.equals("unclassified") ){
		        	addToRoadDataView("~55 mph", false, true );
	        	}
	        	else if ( highwayType.equals("residential") ){
		        	addToRoadDataView("~30 mph", false, true );
	        	}
	        	else{
	        		addToRoadDataView("N/A", false, false );
	        	}
	        }
	        else {
	        	addToRoadDataView("N/A", false, false );
	        }
	        
	        if ( results.containsKey("lanes") ){
	        	addToRoadDataView("Lanes: ", true, false );
	        	String lanesFormatted = results.get("lanes").substring(0,1).toUpperCase()+results.get("lanes").substring(1);
	        	addToRoadDataView(lanesFormatted, false, false );
	        }
	        
	        if ( results.containsKey("oneway") ){
	        	addToRoadDataView("Oneway", true, false );
	        	String onewayFormatted = results.get("oneway").substring(0,1).toUpperCase()+results.get("oneway").substring(1);
	        	addToRoadDataView(onewayFormatted, false, false );
	        }
	    }
	}

}
