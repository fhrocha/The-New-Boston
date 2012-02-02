package com.kritsolutions.travis;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener {
	
	private TextView canWrite, canRead;
	private String state;
	private boolean canW, canR;
	private Spinner spinner;
	private String[] paths = {"Music", "Pictures", "Downloads"};
	private File path = null;
	private EditText saveFile;
	private Button confirm, save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		confirm = (Button) findViewById(R.id.bConfirmSave);
		save = (Button) findViewById(R.id.bSaveFile);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);

		state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)) {
			canWrite.setText("true");
			canRead.setText("true");
			canW = true;
			canR = true;
		} else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;
		} else {
			canWrite.setText("false");
			canRead.setText("false");
			canW = false;
			canR = false;
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View arg0) {
		switch(arg0.getId()) {
			case R.id.bSaveFile:
				
				break;
			case R.id.bConfirmSave:
				save.setVisibility(View.VISIBLE);
				break;
		}		
	}
}
