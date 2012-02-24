package com.kritsolutions.travis;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingXMLStuff extends DefaultHandler {

	private XMLDataCollected info;

	public HandlingXMLStuff() {
		info = new XMLDataCollected();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (localName.equals("city")) {
			String city = attributes.getValue("data");
			info.setCity(city);
		} else if (localName.equals("temp_c")) {
			String temp_c = attributes.getValue("data");
			int temperature = 0;
			if (temp_c != null) {
				temperature = Integer.parseInt(temp_c);
			}
			info.setTemperature(temperature);
		}
	}

	public String getInformation() {

		return info.dataToString();
	}

}
