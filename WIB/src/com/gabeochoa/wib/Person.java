package com.gabeochoa.wib;


public class Person {

	private String name;
	private String position;
	private String pictureUrl;
	
	public Person()
	{
		name = null;
		position = null;
		pictureUrl = null;
	}

	public Person(String name, String position, String pictureUrl) {
		this.name = name;
		this.position = position;
		this.pictureUrl = pictureUrl;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}
	
	public String toString()
	{
		return "Name: "+name+"\n Position: "+position+"\n URL: "+pictureUrl+" \n";
	}
	
}
