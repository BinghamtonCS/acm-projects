package com.gabeochoa.wib;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 *  Author: Gabriel Ochoa
 */

public class AboutView extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	
	TextView tV;
	
	String aboutData = "Women in Business is a female-oriented business organization at Binghamton University."+
	" Our mission is to instill confidence and drive in female students to pursue challenges and to enrich and diversify"+
	" the workplace. Women in Business members are ambitious, smart, and capable young women or men pursuing an undergraduate"+
	" or graduate degree in Binghamton University."+
	"\nFormed in 2011, Women in Business strives for excellence in the four pillars of leadership, service, scholarship, and friendship.";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_view);

		tV = (TextView) findViewById(R.id.aboutText);
		addAboutData();
		
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	/*
	 * Add the About hardcoded string to About Page 
	 */
	private void addAboutData()
	{
		tV.append(aboutData);
	}
	
	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		
		// Adding child data
		listDataHeader.add("DEVELOP YOUR LEADERSHIP SKILLS");
		listDataHeader.add("ENGAGE IN SERVICE OPPORTUNITIES");
		listDataHeader.add("ENHANCE YOUR SCHOLARSHIP ACHIEVEMENT");
		listDataHeader.add("CREATE LASTING FRIENDSHIPS");

		// Adding child data
		String sOne = ""+
				"Membership in Women in Business will offer you countless opportunities in becoming an effective leader"+
				"and will open doors to leadership skills within yourself that will prepare you for your personal and"+
				"professional lives. Each member is a leader with specific responsibilities to the organization."+
				"Women in Business incorporate all members in implementing new policies, planning events, and developing leaders."
		;
		
		String sTwo = "Women in Business is unique in that community service is an important part of the mission of our organization. Women in Business enables members to give back to the local community of Binghamton and be creative in their approach to make the world a better place.";
		
		String sThree = "Membership in Women in Business will provide you a mentoring network to help maximize your educational achievements. Women in Business has a branch to enhance each member’s professional development.";
		
		String sFour = "Women in Business fosters a very tight knit community and membership in our organization will strengthen relationships with your peers. Being a part of Women in Business is like having a second family";

		
		
		//I am using lists here just in case we want to add extra strings to this in the future -gabe
		List<String> one = new ArrayList<String>();
		one.add(sOne);	 
		List<String> two = new ArrayList<String>();
		two.add(sTwo);
		List<String> three = new ArrayList<String>();
		three.add(sThree);
		List<String> four = new ArrayList<String>();
		four.add(sFour);
		
		listDataChild.put(listDataHeader.get(0), one); // Header, Child data
		listDataChild.put(listDataHeader.get(1), two);
		listDataChild.put(listDataHeader.get(2), three);
		listDataChild.put(listDataHeader.get(3), four);
	}

}
