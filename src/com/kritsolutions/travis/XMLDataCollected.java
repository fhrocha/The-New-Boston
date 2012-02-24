package com.kritsolutions.travis;

public class XMLDataCollected {

	private int temperature;
	private String city;

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String dataToString() {
		return "In " + city + " the current temperature in Celcius is " + temperature + " degrees.";
	}
	
}
