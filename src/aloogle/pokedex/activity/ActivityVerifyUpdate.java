package aloogle.pokedex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.DownloadListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import aloogle.pokedex.R;

public class ActivityVerifyUpdate extends Activity {

	WebView webView;

	 @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		try {
			String version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
			setTitle("Your version is: " + version);
		} catch (NameNotFoundException e) {}

		webView = new WebView(this);
		setContentView(webView);
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);

		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		webView.loadUrl("http://aloogle.tumblr.com/droidex/update");
		webView.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent,
				String contentDisposition, String mimetype,
				long contentLength) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
	}
}
