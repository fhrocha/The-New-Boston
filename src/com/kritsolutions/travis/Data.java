package com.kritsolutions.travis;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener {

	Button start, startFor;
	EditText sendET;
	TextView gotAnswer;
	RelativeLayout rl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();
		// Adding Ad from Java code
		rl = (RelativeLayout) findViewById(R.id.relLayout);
		AdView ad = new AdView(Data.this, AdSize.BANNER, "a14f517188026fa");
		rl.addView(ad);
		ad.loadAd(new AdRequest());
	}

	private void initialize() {
		start = (Button) findViewById(R.id.bSA);
		startFor = (Button) findViewById(R.id.bSAFR);
		sendET = (EditText) findViewById(R.id.etSend);
		gotAnswer = (TextView) findViewById(R.id.tvGot);
		start.setOnClickListener(this);
		startFor.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.bSA:
			String bread = sendET.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("key" , bread);
			Intent intent = new Intent(Data.this, OpenedClass.class);
			intent.putExtras(basket);
			startActivity(intent);
			break;
		case R.id.bSAFR:
			Intent intent2 = new Intent(Data.this, OpenedClass.class);
			startActivityForResult(intent2, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK) {
			Bundle basket = data.getExtras();
			String strAnswer = basket.getString("answer");
			gotAnswer.setText(strAnswer);
		}
	}

}
