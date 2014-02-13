package com.gabeochoa.wib;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;

public class PastEventsViewActivity extends Activity{
	private static final String TAG = "PastEventViewActivity";
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d(TAG, "FacebookViewActivity");
		setContentView(R.layout.activity_past_events_view);
		
		initUI();
	}

	private void initUI()
	{
		webView = (WebView)findViewById(R.id.pastEventsView);
		webView.loadUrl("http://www.binghamtonwib.com/past-events/");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.facebook_view, menu);
		return true;
	}
	/**
	 * May or may not need these, put them in anyway for debugging
	 * purposes.
	 */
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		Log.d(TAG, "onSave()");
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(TAG, "onRestore()");
	}
	@Override
	protected void onStart()
	{
		super.onStart();
		Log.d(TAG, "onStart()");
	}
	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(TAG, "onResume()");
	}
	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(TAG, "onPause()");
	}
	@Override
	public void onStop()
	{
		super.onStop();
		Log.d(TAG, "onStop()");
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}
	

}
