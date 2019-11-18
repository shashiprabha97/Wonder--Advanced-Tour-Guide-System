package com.apptodia.cityguide;

import com.apptodia.cityguide.extra.AllConstants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FacebookActivity extends Activity {
	/** Called when the activity is first created. */
	private WebView facebookweBview;
	Context con;

	String url = AllConstants.facebookFanPageUrl;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.facebookview);
		con = this;
		 Toast.makeText(FacebookActivity.this, "Loading..",
				 Toast.LENGTH_LONG).show();
		try {
			updateWebView(url);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private class HelloWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && facebookweBview.canGoBack()) {
			facebookweBview.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	private void updateWebView(String url) {
		// TODO Auto-generated method stub

		facebookweBview = (WebView) findViewById(R.id.mapViewface);
		facebookweBview.getSettings().setJavaScriptEnabled(true);
		facebookweBview.getSettings().setDomStorageEnabled(true);
		facebookweBview.loadUrl(url);

		facebookweBview.setWebViewClient(new HelloWebViewClient());

	}
	public void btnHome(View v) {

		Intent next = new Intent(con, CityGuideActivity.class);
		next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(next);

	}

	public void btnBack(View v) {
		finish();

	}
}