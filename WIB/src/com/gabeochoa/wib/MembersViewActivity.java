package com.gabeochoa.wib;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 *  Author: Gabriel Ochoa
 */

public class MembersViewActivity extends Activity {

	ArrayList<Person> members = null;
	String file = "Members.txt";
	private TextView tV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_members_view);
		// Show the Up button in the action bar.
		setupActionBar();
		
		if(checkDate())
		{
			members = GetMembers.getFromFile(file);
			if(members != null)
			GetMembers.write(file, members, this.getApplicationContext());
		}
		else
		{
			members = GetMembers.get();
		}
		
		//We have to figure out how to best display the names/position/picture
		//its possible we could do just a wall of faces and you click one and
		//it will come up with the name and position of the person
		tV = (TextView) findViewById(R.id.membersText);
		printNames();

	}

	private void printNames() {
		if(members != null)
		for(Person p: members)
		{
			tV.append(p.toString());
		}
	}

	private boolean checkDate() {
		boolean oldFile = true;
		StringBuilder text = new StringBuilder();
		File data = null;
		try {

			File dir = Environment.getRootDirectory();
			data = new File(dir,file);

			DateFormat df = DateFormat.getDateInstance(DateFormat.MONTH_FIELD);

			String curDate = df.format(new Date());

			String last = ""+data.lastModified();
			Date b = df.parse(last);
			String oldDate = b.toString();

			if(curDate.equalsIgnoreCase(oldDate))
				oldFile = true;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return oldFile;
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.members_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}