package com.kritsolutions.travis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {
	
	EditText sharedData;
	TextView dataResults;
	FileOutputStream fos;
	private static final String FILE_NAME = "InternalString";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedprefs);
		setupVariables();
	}

	private void setupVariables() {
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		dataResults = (TextView) findViewById(R.id.tvLoadYourData);
		sharedData = (EditText) findViewById(R.id.etSharedData);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		try {
			fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSave:
			String data = sharedData.getText().toString();
			/*
			File f = new File(FILE_NAME);
			try {
				fos = new FileOutputStream(f);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
			try {
				fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
				fos.write(data.getBytes());	
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case R.id.bLoad:
			new LoadSomeStuff().execute(FILE_NAME);
			break;
		}
	}
	
	public class LoadSomeStuff extends AsyncTask<String, Integer, String> {
		
		ProgressDialog dialog;
		
		protected void onPreExecute() {
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			
			String collected = null;
			FileInputStream fis = null;
			
			for(int i =0; i < 20; i++) {
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			try {
				fis = openFileInput(FILE_NAME);
				byte[] dataArray = new byte[fis.available()];
				while(fis.read(dataArray) != -1) {
					collected = new String(dataArray);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return collected;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		protected void onProgressUpdate(Integer...progress) {
			dialog.incrementProgressBy(progress[0]);
		}
		
		protected void onPostExecute(String result) {			
			dataResults.setText(result);
		}
	}

}
