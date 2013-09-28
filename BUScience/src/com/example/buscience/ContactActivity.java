package com.example.buscience;

import java.io.*;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactActivity extends Activity
{
	TextView pageTitle;
	LinearLayout contentSec;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);
        
        LinearLayout contentSec = (LinearLayout)findViewById(R.id.content_sec);
        pageTitle = (TextView)findViewById(R.id.pageTitle);
        //new FetchContent().execute("https://docs.google.com/document/preview?hgd=1&id=1ah8eefcuLkjBZ07CPhLgW51N2Ck6osb4E9S4KGR3pIA");
    }
    
    public void addText() {
    	TextView tx = new TextView(this);
    	tx.setId(1234);
    	tx.setText("Your Face");
    	contentSec = (LinearLayout)findViewById(R.id.content_sec);
    	contentSec.addView(tx, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    }
    
    private class FetchContent extends AsyncTask<String, Integer, Double>
    {
        int yearBegin, yearEnd;
    	
		protected Double doInBackground(String... arg0)
		{
			getEBoard(arg0[0]);
			return null;
		}
		
		protected void onPostExecute(Double result) 
		{
        	pageTitle.setText("E-Board " + yearBegin + "-" + yearEnd);
        	addText();
	    }
		
		public void getEBoard(String url)
		{
	        try 
	        {
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(new URI(url));
				HttpResponse response = client.execute(request);
				BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//		        URL website = new URL(url);    
//		        BufferedReader in = new BufferedReader(new InputStreamReader(website.openStream()));
	
		        String inputLine;
		        int count = 0;
		        while ((inputLine = in.readLine()) != null) 
		        {
		        	count++;
					inputLine = inputLine.toLowerCase();
					Log.d("output", inputLine);
					int pos = inputLine.indexOf("board ");
					if ((pos != -1) && (pos + 15 <= inputLine.length())) 
					{
			            try {
			            	yearBegin = Integer.parseInt(inputLine.substring(pos + 6, pos + 10));
			            	yearEnd = Integer.parseInt(inputLine.substring(pos + 11, pos + 15));
			            } catch(NumberFormatException e) {
			            	
			            }
					}
		        }
		        
		        in.close();
	        } catch(Exception e) {
				e.printStackTrace();
	        }
		}
    }
}