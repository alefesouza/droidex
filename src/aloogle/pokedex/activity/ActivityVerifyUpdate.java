package aloogle.pokedex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.content.pm.PackageManager.NameNotFoundException;
import aloogle.pokedex.R;

public class ActivityVerifyUpdate extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		try {
			String version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
			setTitle("Your version is: " + version);
		} catch (NameNotFoundException e) {}

		WebView webView = new WebView(this);
		setContentView(webView);
		webView.loadUrl("http://aloogle.tumblr.com/droidex/update");
		webView.getSettings().setJavaScriptEnabled(true);
	}
}
