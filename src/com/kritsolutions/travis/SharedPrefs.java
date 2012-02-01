package com.kritsolutions.travis;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {
	
	EditText sharedData;
	TextView dataResults;
	public final static String FILE_NAME = "MySharedString";
	SharedPreferences someData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedprefs);
		setupVariables();
		someData = getSharedPreferences(FILE_NAME, 0);		
	}

	private void setupVariables() {
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		dataResults = (TextView) findViewById(R.id.tvLoadYourData);
		sharedData = (EditText) findViewById(R.id.etSharedData);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSave:
			String stringData = sharedData.getText().toString();
			SharedPreferences.Editor editor = someData.edit();
			editor.putString("sharedString", stringData);
			editor.commit();
			break;
		case R.id.bLoad:
			someData = getSharedPreferences(FILE_NAME, 0);
			String dataReturned = someData.getString("sharedString", "Couldn't Load Data");
			dataResults.setText(dataReturned);
			break;
		}
	}
}
