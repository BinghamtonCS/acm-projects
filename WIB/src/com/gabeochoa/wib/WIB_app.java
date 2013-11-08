package com.gabeochoa.wib;
/**
 *  Author: Taylor Foxhall
 */

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class WIB_app extends Activity implements OnClickListener 
{
	
	private static final String TAG = "MainActivity";
	private ImageButton imgButton1, imgButton2, imgButton3, imgButton4, imgButton5, imgButton6, imgButton7;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");
		setContentView(R.layout.activity_wib_app);
		initUI();
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
	

	private void initUI() 
	{
		imgButton1 = (ImageButton)findViewById(R.id.imageButton1);
		imgButton2 = (ImageButton)findViewById(R.id.imageButton2);
		imgButton3 = (ImageButton)findViewById(R.id.imageButton3);
		imgButton4 = (ImageButton)findViewById(R.id.imageButton4);
		imgButton5 = (ImageButton)findViewById(R.id.imageButton5);
		imgButton6 = (ImageButton)findViewById(R.id.imageButton6);
		imgButton7 = (ImageButton)findViewById(R.id.imageButton7);
		
		imgButton1.setOnClickListener(this);
		imgButton2.setOnClickListener(this);
		imgButton3.setOnClickListener(this);
		imgButton4.setOnClickListener(this);
		imgButton5.setOnClickListener(this);
		imgButton6.setOnClickListener(this);
		imgButton7.setOnClickListener(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wib_app, menu);
		return true;
	}


	@Override
	public void onClick(View view) 
	{
		Log.d(TAG, "Button " + view.getId() + " selected.");
		Intent i;
		
		switch(view.getId())
		{
			case R.id.imageButton1:
				Toast.makeText(this, "You pressed Button 1", Toast.LENGTH_SHORT).show();
				i = new Intent(this, AboutView.class);
				startActivity(i);
				break;
			case R.id.imageButton2:
				Toast.makeText(this, "You pressed Button 2", Toast.LENGTH_SHORT).show();
				i = new Intent(this, MembersViewActivity.class);
				startActivity(i);
				break;
			case R.id.imageButton3:
				Toast.makeText(this, "You pressed Button 3", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageButton4:
				Toast.makeText(this, "You pressed Button 4", Toast.LENGTH_SHORT).show();
				break;
			case R.id.imageButton5:
				i = new Intent(this, FacebookViewActivity.class);
				startActivity(i);
				break;
			default:break;
		}

	}

}
