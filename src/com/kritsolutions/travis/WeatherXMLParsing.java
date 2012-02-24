package com.kritsolutions.travis;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherXMLParsing extends Activity implements OnClickListener {

	private static final String baseURL = "http://www.google.com/ig/api?weather=";
	private TextView tvCurrentWeather;
	private EditText etCity, etState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.weather);
		Button bGetWeather = (Button) findViewById(R.id.bGetWeather);
		tvCurrentWeather = (TextView) findViewById(R.id.tvCurrentWeather);
		etCity = (EditText) findViewById(R.id.etCity);
		etState = (EditText) findViewById(R.id.etState);
		bGetWeather.setOnClickListener(this);
	}

	public void onClick(View v) {
		String city = etCity.getText().toString();
		String state = etState.getText().toString();

		StringBuilder URL = new StringBuilder(baseURL);
		URL.append(city);
		URL.append(",");
		URL.append(state);
		String fullURL = URL.toString().replace(" ", "%20");

		try {
			
			URL website = new URL(fullURL);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			
			HandlingXMLStuff handlingXML = new HandlingXMLStuff();
			xr.setContentHandler(handlingXML);
			
			InputSource inputSource = new InputSource(website.openStream());
			inputSource.setEncoding("ISO-8859-1");
			xr.parse(inputSource);
			
			String information = handlingXML.getInformation();
			tvCurrentWeather.setText(information);
			
		} catch (Exception e) {
			tvCurrentWeather.setText("Error: " + e.getMessage());
		}
	}

}
