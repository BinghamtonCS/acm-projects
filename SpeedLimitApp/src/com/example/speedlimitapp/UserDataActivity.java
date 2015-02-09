package com.example.speedlimitapp;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserDataActivity extends Activity {
	
	LinearLayout dataTable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_layout);
		
		dataTable = (LinearLayout)findViewById(R.id.UserDataContainer);
		
		getData();
	}
	
	public void getData()
	{
    	final LocationManager locationManager = (LocationManager) UserDataActivity.this.getSystemService(Context.LOCATION_SERVICE);

    	// Define a listener that responds to location updates
    	LocationListener locationListener = new LocationListener() {
    	    public void onLocationChanged(Location location) {
    	      // Called when a new location is found by the network location provider.
    	    	
    	    	String locationProvider = LocationManager.NETWORK_PROVIDER;
    	    	Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
    	    	//Log.v("OUTPUT", lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude());
    	    	
    	    	TextView text = new TextView(UserDataActivity.this);
    	    	//text.setText("Latitude: " + String.valueOf(lastKnownLocation.getLatitude()) + " Longitude: " + String.valueOf(lastKnownLocation.getLongitude()));
    	    	String lat = String.valueOf(lastKnownLocation.getLatitude());
    	    	String lon = String.valueOf(lastKnownLocation.getLongitude());
    	    	String time = String.valueOf(lastKnownLocation.getTime());
    	    	text.setText("Latitude: " + lat + "    Longitude: " + lon + "   Time: " + time + "   Test: " + getDistance(51.5072, -0.1275, lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()));
    	    	dataTable.addView(text);
    	      
    	    }

    	    public void onStatusChanged(String provider, int status, Bundle extras) {}

    	    public void onProviderEnabled(String provider) {}

    	    public void onProviderDisabled(String provider) {}
    	  };

	    // Register the listener with the Location Manager to receive location updates
	    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    	
	}
	
	public double getDistance(double lat1, double lon1, double lat2, double lon2){
		
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
	
	public double toRadians(double lat2){
		return lat2 * (Math.PI/180);
	}

}
