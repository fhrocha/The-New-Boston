package com.kritsolutions.travis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener {

	TextView question, test;
	Button returnData;
	RadioGroup selectionList;
	String gotBread, setData;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		//Bundle gotBasket = getIntent().getExtras();
		//gotBread = gotBasket.getString("key");
		//question.setText(gotBread);
	}

	private void initialize() {
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.bReturn);
		returnData.setOnClickListener(this);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
		selectionList.setOnCheckedChangeListener(this);
	}

	public void onClick(View v) {
		Intent personIntent = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("answer", setData);
		personIntent.putExtras(backpack);
		setResult(RESULT_OK, personIntent);
		finish();
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rCrazy:
			setData = "Probably right!";
			break;
		case R.id.rSexy:
			setData = "Definitely right!";
			break;			
		case R.id.rBoth:
			setData = "Spot On!";
			break;			
		}
		test.setText(setData);
	}
}
