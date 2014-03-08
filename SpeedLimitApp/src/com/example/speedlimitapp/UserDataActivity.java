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
		
		Button button = (Button)findViewById(R.id.TheButton);
		
		button.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	getData();

		    }
		    
		});
	}
	
	public void getData()
	{
    	LocationManager locationManager = (LocationManager) UserDataActivity.this.getSystemService(Context.LOCATION_SERVICE);

    	// Define a listener that responds to location updates
    	LocationListener locationListener = new LocationListener() {
    	    public void onLocationChanged(Location location) {
    	      // Called when a new location is found by the network location provider.
    	      
    	    }

    	    public void onStatusChanged(String provider, int status, Bundle extras) {}

    	    public void onProviderEnabled(String provider) {}

    	    public void onProviderDisabled(String provider) {}
    	  };

    	// Register the listener with the Location Manager to receive location updates
    	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    	String locationProvider = LocationManager.NETWORK_PROVIDER;
    	Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
    	//Log.v("OUTPUT", lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude());
    	
    	
    	TextView text = new TextView(this);
    	//text.setText("Latitude: " + String.valueOf(lastKnownLocation.getLatitude()) + " Longitude: " + String.valueOf(lastKnownLocation.getLongitude()));
    	String lat = String.valueOf(lastKnownLocation.getLatitude());
    	String lon = String.valueOf(lastKnownLocation.getLongitude());
    	text.setText("Latitude: " + lat + "    Longitude: " + lon);
    	dataTable.addView(text);
    	
	}

}
