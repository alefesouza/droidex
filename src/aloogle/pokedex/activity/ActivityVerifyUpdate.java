package aloogle.pokedex.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.DownloadListener;
import java.io.File;
import aloogle.pokedex.R;
import aloogle.pokedex.lib.AnimatedGifImageView;
import aloogle.pokedex.lib.AnimatedGifImageView.TYPE;

public class ActivityVerifyUpdate extends Activity {
	@SuppressWarnings("unused")
	private long enqueue;
	private DownloadManager dm;
	WebView web;
	private AnimatedGifImageView animatedGifImageView;

	 @ SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String userColor = preferences.getString("prefColor", "droidexblue");
		if (userColor.equals("red"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffff0000));
		else if (userColor.equals("green"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff00cc00));
		else if (userColor.equals("blue"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0000ff));
		else if (userColor.equals("yellow"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe5e500));
		else if (userColor.equals("gold"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffdaa520));
		else if (userColor.equals("silver"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffc0c0c0));
		else if (userColor.equals("crystal"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffa1e2ff));
		else if (userColor.equals("ruby"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe0115f));
		else if (userColor.equals("sapphire"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0f52ba));
		else if (userColor.equals("emerald"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff50c878));
		else if (userColor.equals("diamond"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffb9f2ff));
		else if (userColor.equals("pearl"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffeae0c8));
		else if (userColor.equals("platinum"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffe5e4e2));
		else if (userColor.equals("black"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff000000));
		else if (userColor.equals("droidexblue"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0080ff));
		else if (userColor.equals("dexdroidred"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffff4444));

		String userIcon = preferences.getString("prefIcon", "default");
		if (userIcon.equals("default"))
			getActionBar().setIcon(R.drawable.ic_launcher);
		else if (userIcon.equals("red"))
			getActionBar().setIcon(R.drawable.ic_pokedex);
		else if (userIcon.equals("green"))
			getActionBar().setIcon(R.drawable.ic_abilitydex);
		else if (userIcon.equals("blue"))
			getActionBar().setIcon(R.drawable.ic_itemdex);
		else if (userIcon.equals("yellow"))
			getActionBar().setIcon(R.drawable.ic_movedex);

		animatedGifImageView = ((AnimatedGifImageView)findViewById(R.id.animatedGifImageView));
		animatedGifImageView.setAnimatedGif(R.raw.loading,
			TYPE.FIT_CENTER);

		try {
			String version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
			setTitle(getResources().getText(R.string.yourversion) + " " + version);
		} catch (NameNotFoundException e) {}

		web = (WebView)findViewById(R.id.webview01);

		web.setWebViewClient(new updateWebClient());
		web.getSettings().setJavaScriptEnabled(true);
		web.loadUrl("http://aloogle.tumblr.com/droidex/update");
		web.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(final String url, String userAgent,
				String contentDisposition, String mimetype,
				long contentLength) {
				File oldApk = new File(Environment.getExternalStorageDirectory() + "/DroiDex/DroiDex.apk");
				if (oldApk.exists()) {
					oldApk.delete();
				}
				dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
				Request request = new Request(
						Uri.parse(url));
				request.setTitle(getString(R.string.downloadupdate));
				request.setDescription(getString(R.string.downloadwarning));
				request.setDestinationInExternalPublicDir("DroiDex", "DroiDex.apk");
				enqueue = dm.enqueue(request);
			}
		});
	}

	public class updateWebClient extends WebViewClient {
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;

		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			animatedGifImageView.setVisibility(View.GONE);
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			ActivityVerifyUpdate.this.finish();
			return true;
		default:
			return
			super.onOptionsItemSelected(item);
		}
	}
}
