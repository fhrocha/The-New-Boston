package com.kritsolutions.travis;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpExample extends Activity {

	private TextView httpLoadingData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexample);
		httpLoadingData = (TextView) findViewById(R.id.tvHttpLoadingData);
		GetMethodExample test = new GetMethodExample();
		try {
			String returned  = test.getInternetData();
			httpLoadingData.setText(returned);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
