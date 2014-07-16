package aloogle.pokedex.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;
import java.lang.reflect.InvocationTargetException;
import aloogle.pokedex.R;
import aloogle.pokedex.lib.AnimatedGifImageView;
import aloogle.pokedex.lib.AnimatedGifImageView.TYPE;

public class ActivityNews extends Activity {

	WebView web;

	private FrameLayout mTargetView;
	private FrameLayout mContentView;
	private CustomViewCallback mCustomViewCallback;
	private View mCustomView;
	private MyChromeClient mClient;

	private AnimatedGifImageView animatedGifImageView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		setTitle(getResources().getText(R.string.news));
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
		animatedGifImageView.setAnimatedGif(R.raw.loading, TYPE.FIT_CENTER);

		web = (WebView)findViewById(R.id.webview01);
		mClient = new MyChromeClient();
		web.setWebChromeClient(mClient);
		web.setWebViewClient(new newsWebClient());
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setSupportZoom(true);
		web.getSettings().setBuiltInZoomControls(true);
		web.getSettings().setDisplayZoomControls(false);
		web.loadUrl("http://aloogle.tumblr.com/droidex/news");
		mContentView = (FrameLayout)findViewById(R.id.main_content);
		mTargetView = (FrameLayout)findViewById(R.id.target_view);
		web.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_news, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			ActivityNews.this.finish();
			return true;
		case R.id.menu_back:
			if (web.canGoBack()) {
				web.goBack();
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.nopages), Toast.LENGTH_LONG);
				toast.show();
			}
			return true;
		case R.id.menu_home:
			web.loadUrl("http://aloogle.tumblr.com/droidex/news");
			return true;
		case R.id.menu_forward:
			if (web.canGoForward()) {
				web.goForward();
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.nopages), Toast.LENGTH_LONG);
				toast.show();
			}
			return true;
		case R.id.menu_reload:
			web.reload();
			return true;
		case R.id.menu_share:
			Intent sharePageIntent = new Intent();
			sharePageIntent.setAction(Intent.ACTION_SEND);
			sharePageIntent.putExtra(Intent.EXTRA_TEXT, web.getUrl());
			sharePageIntent.setType("text/plain");
			startActivity(Intent.createChooser(sharePageIntent, getResources().getText(R.string.app_name)));
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
			Editor editor = preferences.edit();
			editor.putString("DroiDexNewsLastUrl", web.getUrl());
			editor.commit();
			return true;
		case R.id.menu_update:
			Intent update = new Intent(ActivityNews.this, ActivityVerifyUpdate.class);
			startActivity(update);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	public class newsWebClient extends WebViewClient {
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

	class MyChromeClient extends WebChromeClient {

		@Override
		public void onShowCustomView(View view, CustomViewCallback callback) {
			mCustomViewCallback = callback;
			mTargetView.addView(view);
			mCustomView = view;
			mContentView.setVisibility(View.GONE);
			mTargetView.setVisibility(View.VISIBLE);
			mTargetView.bringToFront();
			getActionBar().hide();
		}

		@Override
		public void onHideCustomView() {
			if (mCustomView == null)
				return;

			mCustomView.setVisibility(View.GONE);
			mTargetView.removeView(mCustomView);
			mCustomView = null;
			mTargetView.setVisibility(View.GONE);
			mCustomViewCallback.onCustomViewHidden();
			mContentView.setVisibility(View.VISIBLE);
			getActionBar().show();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mCustomView != null) {
				mClient.onHideCustomView();
				return true;
			} else {
				if (web.canGoBack()) {
					web.goBack();
					return true;
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onPause() {
		super.onPause();
		try {
			Class.forName
			("android.webkit.WebView")
			.getMethod
			("onPause", (Class[])null)
			.invoke
			(web, (Object[])null);
		} catch (ClassNotFoundException cnfe) {}

		catch (NoSuchMethodException nsme) {}

		catch (InvocationTargetException ite) {}

		catch (IllegalAccessException iae) {}
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = preferences.edit();
		editor.putString("DroiDexNewsLastUrl", web.getUrl());
		editor.commit();
	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String lastUrl = preferences.getString("DroiDexNewsLastUrl", "http://aloogle.tumblr.com/droidex/news");
		web.loadUrl(lastUrl);
	}
}
