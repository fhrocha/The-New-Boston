package com.kritsolutions.travis;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OurViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);
		return true;
	}
}
