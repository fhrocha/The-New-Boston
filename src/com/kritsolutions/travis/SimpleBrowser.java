package com.kritsolutions.travis;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {
	
	EditText url;
	WebView ourBrow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		ourBrow = (WebView) findViewById(R.id.wvBrowser);
		
		ourBrow.getSettings().setJavaScriptEnabled(true);
		//ourBrow.getSettings().setBuiltInZoomControls(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		
		ourBrow.setWebViewClient(new OurViewClient());
		ourBrow.loadUrl("http://www.kr-itsolutions.com.br");
		
		Button go = (Button) findViewById(R.id.bGo);
		Button forward = (Button) findViewById(R.id.bFoward);
		Button back = (Button) findViewById(R.id.bBack);
		Button refresh = (Button) findViewById(R.id.bRefresh);
		Button clearHistory = (Button) findViewById(R.id.bHistory);
		url = (EditText) findViewById(R.id.etURL);
		
		go.setOnClickListener(this);
		forward.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.bGo:
			String webSite = url.getText().toString();
			ourBrow.loadUrl(webSite);
			//Hidding keyboard after using an EditText
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			break;
		case R.id.bBack:
			if(ourBrow.canGoBack())
				ourBrow.goBack();
			break;
		case R.id.bFoward:
			if(ourBrow.canGoForward())
				ourBrow.goForward();
			break;
		case R.id.bRefresh:
			ourBrow.reload();
			break;
		case R.id.bHistory:
			ourBrow.clearHistory();
			break;
		}
	}	
}
