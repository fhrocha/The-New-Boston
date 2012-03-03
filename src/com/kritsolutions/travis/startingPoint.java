package com.kritsolutions.travis;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class startingPoint extends Activity {
	
	int counter;
	Button add, sub;
	TextView display;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        counter = 0;
        add = (Button)findViewById(R.id.bAdd);
        sub = (Button)findViewById(R.id.bSub);
        display = (TextView)findViewById(R.id.tvDisplay);
        
        // Refresh Ad
        AdView ad = (AdView)findViewById(R.id.ad);
        ad.loadAd(new AdRequest());
        
        add.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				counter++;
				display.setText("Your total is " + counter);
			}
		});
        
        sub.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				counter--;
				display.setText("Your total is " + counter);
			}
		});
    }
}