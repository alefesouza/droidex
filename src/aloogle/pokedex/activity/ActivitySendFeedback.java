package aloogle.pokedex.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import aloogle.pokedex.R;

public class ActivitySendFeedback extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		WebView webView = new WebView(this);
		setContentView(webView);
		webView.loadUrl("http://aloogle.tumblr.com/droidex/feedback");
   webView.getSettings().setJavaScriptEnabled(true);
	}
}