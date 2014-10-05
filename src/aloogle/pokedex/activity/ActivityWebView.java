package aloogle.pokedex.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;
import java.lang.reflect.InvocationTargetException;
import aloogle.pokedex.R;
import aloogle.pokedex.lib.AnimatedGifImageView;
import aloogle.pokedex.lib.AnimatedGifImageView.TYPE;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import java.io.File;
import android.os.Environment;
import aloogle.pokedex.other.Other;
import android.content.pm.*;

public class ActivityWebView extends Activity {

	WebView web;

	private FrameLayout mTargetView;
	private FrameLayout mContentView;
	private CustomViewCallback mCustomViewCallback;
	private View mCustomView;
	private webChromeClient mClient;

	@SuppressWarnings("unused")
	private long enqueue;

	private AnimatedGifImageView animatedGifImageView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		Other.ActionBarIcon(this);
		Other.setTranslucentStatus(false, this, false);

		animatedGifImageView = ((AnimatedGifImageView)findViewById(R.id.animatedGifImageView));
		animatedGifImageView.setAnimatedGif(R.raw.loading, TYPE.FIT_CENTER);

		web = (WebView)findViewById(R.id.webview01);
		mClient = new webChromeClient();
		web.setWebChromeClient(mClient);
		web.setWebViewClient(new webViewClient());
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setSupportZoom(true);
		web.getSettings().setBuiltInZoomControls(true);
		web.getSettings().setDisplayZoomControls(false);
		int webViewValue = getIntent().getIntExtra(Other.WebViewValue, 0);
		if (webViewValue == 0) {
			Other.ActionBarColor(this);
			Other.ActionBarColorIcons(this, getString(R.string.news));
			try {
				int version = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
				web.loadUrl("http://aloogle.tumblr.com/droidex/news?version=" + version);
			} catch (NameNotFoundException e) {}
		} else if (webViewValue == 1) {
			Other.ActionBarColor(this);
			Other.ActionBarColorIcons(this, getString(R.string.news));
			try {
				int version = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
				web.loadUrl("http://aloogle.tumblr.com/droidex/downloads?version=" + version);
			} catch (NameNotFoundException e) {}
		} else if (webViewValue == 2) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
			boolean prefDetailsColor = preferences.getBoolean("prefDetailsColor", true);
			if (prefDetailsColor) {
				Other.ActionBarDetailsColor(this, getIntent().getStringExtra(Other.PokemonId), getString(R.string.relatedvideos));
			} else {
				Other.ActionBarColor(this);
				Other.ActionBarColorIcons(this, getString(R.string.relatedvideos));
			}
			try {
				int version = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
				web.loadUrl("http://aloogle.tumblr.com/droidex/videos?pokemon=" + getIntent().getStringExtra(Other.PokemonId) + "&version=" + version);
			} catch (NameNotFoundException e) {}
		} else if (webViewValue == 3) {
			Other.ActionBarColor(this);
			Other.SystemBarColor(this, true);
			try {
				PackageInfo version = getPackageManager().getPackageInfo(getPackageName(), 0);
				Other.ActionBarColorIcons(this, getString(R.string.yourversion) + " " + version.versionName);
				web.loadUrl("http://aloogle.tumblr.com/droidex/update?version=" + version.versionCode);
			} catch (NameNotFoundException e) {}
		} else if (webViewValue == 4) {
			Other.ActionBarColor(this);
			Other.ActionBarColorIcons(this, getString(R.string.translate));
			Other.SystemBarColor(this, true);
			try {
				int version = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
				web.loadUrl("http://aloogle.tumblr.com/droidex/translate?version=" + version);
			} catch (NameNotFoundException e) {}
		}
		mContentView = (FrameLayout)findViewById(R.id.main_content);
		mTargetView = (FrameLayout)findViewById(R.id.target_view);
		web.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(final String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				String[]parts = url.split("/");
				String fileName = parts[parts.length - 1];
				if (fileName.startsWith("sa_")) {
					File art = new File(Environment.getExternalStorageDirectory() + "/DroiDex/art/" + fileName);
					if (art.exists()) {
						art.delete();
					}
					DownloadManager dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
					Request request = new Request(Uri.parse(url));
					request.setTitle(fileName);
					request.setDescription(getString(R.string.downloadthing));
					request.setDestinationInExternalPublicDir("DroiDex/art", fileName);
					enqueue = dm.enqueue(request);
				} else if (fileName.startsWith("nf_")) {
					File nf = new File(Environment.getExternalStorageDirectory() + "/DroiDex/sprites/normal/front/" + fileName);
					if (nf.exists()) {
						nf.delete();
					}
					DownloadManager dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
					Request request = new Request(Uri.parse(url));
					request.setTitle(fileName);
					request.setDescription(getString(R.string.downloadthing));
					request.setDestinationInExternalPublicDir("DroiDex/sprites/normal/front", fileName);
					enqueue = dm.enqueue(request);
				} else if (fileName.startsWith("nb_")) {
					File nb = new File(Environment.getExternalStorageDirectory() + "/DroiDex/sprites/normal/back/" + fileName);
					if (nb.exists()) {
						nb.delete();
					}
					DownloadManager dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
					Request request = new Request(Uri.parse(url));
					request.setTitle(fileName);
					request.setDescription(getString(R.string.downloadthing));
					request.setDestinationInExternalPublicDir("DroiDex/sprites/normal/back", fileName);
					enqueue = dm.enqueue(request);
				} else if (fileName.startsWith("sf_")) {
					File sf = new File(Environment.getExternalStorageDirectory() + "/DroiDex/sprites/shiny/front/" + fileName);
					if (sf.exists()) {
						sf.delete();
					}
					DownloadManager dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
					Request request = new Request(Uri.parse(url));
					request.setTitle(fileName);
					request.setDescription(getString(R.string.downloadthing));
					request.setDestinationInExternalPublicDir("DroiDex/sprites/shiny/front", fileName);
					enqueue = dm.enqueue(request);
				} else if (fileName.startsWith("sb_")) {
					File nb = new File(Environment.getExternalStorageDirectory() + "/DroiDex/sprites/normal/back/" + fileName);
					if (nb.exists()) {
						nb.delete();
					}
					DownloadManager dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
					Request request = new Request(Uri.parse(url));
					request.setTitle(fileName);
					request.setDescription(getString(R.string.downloadthing));
					request.setDestinationInExternalPublicDir("DroiDex/sprites/shiny/back", fileName);
					enqueue = dm.enqueue(request);
				} else if (fileName.startsWith("ca_")) {
					File ca = new File(Environment.getExternalStorageDirectory() + "/DroiDex/sound/anime/" + fileName);
					if (ca.exists()) {
						ca.delete();
					}
					DownloadManager dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
					Request request = new Request(Uri.parse(url));
					request.setTitle(fileName);
					request.setDescription(getString(R.string.downloadthing));
					request.setDestinationInExternalPublicDir("DroiDex/sounds/anime", fileName);
					enqueue = dm.enqueue(request);
				} else if (fileName.startsWith("cg_")) {
					File cg = new File(Environment.getExternalStorageDirectory() + "/DroiDex/sounds/game/" + fileName);
					if (cg.exists()) {
						cg.delete();
					}
					DownloadManager dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
					Request request = new Request(Uri.parse(url));
					request.setTitle(fileName);
					request.setDescription(getString(R.string.downloadthing));
					request.setDestinationInExternalPublicDir("DroiDex/sounds/game", fileName);
					enqueue = dm.enqueue(request);
				} else {
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_webview, menu);
		int webViewValue = getIntent().getIntExtra(Other.WebViewValue, 0);
		if (webViewValue == 3) {
			menu.add(1, Menu.FIRST, Menu.FIRST, "Downloads");
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
			String userColorIcons = preferences.getString("prefColorIcons", "white");
			if (userColorIcons.equals("black")) {
				menu.findItem(Menu.FIRST).setIcon(R.drawable.ic_download_dark);
			} else {
				menu.findItem(Menu.FIRST).setIcon(R.drawable.ic_download);
			}
			menu.findItem(Menu.FIRST).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			ActivityWebView.this.finish();
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
			editor.putString("WebViewLastUrl", web.getUrl());
			editor.commit();
			return true;
		case 1:
			Intent news = new Intent(ActivityWebView.this, ActivityWebView.class);
			news.putExtra(Other.WebViewValue, 1);
			startActivity(news);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		int webViewValue = getIntent().getIntExtra(Other.WebViewValue, 0);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String userColorIcons = preferences.getString("prefColorIcons", "white");
		if (webViewValue == 0 || webViewValue == 1) {
			if (userColorIcons.equals("white")) {
				if (web.canGoBack()) {
					menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back);
				} else {
					menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_disabled);
				}

				if (web.canGoForward()) {
					menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward);
				} else {
					menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_disabled);
				}

				menu.findItem(R.id.menu_home).setIcon(R.drawable.ic_home);
			} else {
				if (web.canGoBack()) {
					menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_disabled);
				} else {
					menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_dark);
				}

				if (web.canGoForward()) {
					menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_disabled);
				} else {
					menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_dark);
				}

				menu.findItem(R.id.menu_home).setIcon(R.drawable.ic_home_dark);
			}
		} else if (webViewValue == 2) {
			menu.findItem(R.id.menu_home).setVisible(false);
			menu.findItem(R.id.menu_reload).setVisible(false);
			menu.findItem(R.id.menu_share).setVisible(false);
			String pokemon_image_id = getIntent().getStringExtra(Other.PokemonId);
			boolean prefDetailsColor = preferences.getBoolean("prefDetailsColor", true);
			if (prefDetailsColor) {
				if (pokemon_image_id.matches(Other.PokemonColorWhite) || pokemon_image_id.matches(Other.PokemonColorYellow) || pokemon_image_id.matches(Other.PokemonColorGray)) {
					if (web.canGoBack()) {
						menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_disabled);
					} else {
						menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_dark);
					}

					if (web.canGoForward()) {
						menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_disabled);
					} else {
						menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_dark);
					}
				} else {
					if (web.canGoBack()) {
						menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back);
					} else {
						menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_disabled);
					}

					if (web.canGoForward()) {
						menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward);
					} else {
						menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_disabled);
					}
				}
			} else {
				if (userColorIcons.equals("white")) {
					if (web.canGoBack()) {
						menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back);
					} else {
						menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_disabled);
					}

					if (web.canGoForward()) {
						menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward);
					} else {
						menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_disabled);
					}
				} else {
					if (web.canGoBack()) {
						menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_disabled);
					} else {
						menu.findItem(R.id.menu_back).setIcon(R.drawable.ic_back_dark);
					}

					if (web.canGoForward()) {
						menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_disabled);
					} else {
						menu.findItem(R.id.menu_forward).setIcon(R.drawable.ic_forward_dark);
					}
				}
			}
		} else if (webViewValue == 3 || webViewValue == 4) {
			menu.findItem(R.id.menu_back).setVisible(false);
			menu.findItem(R.id.menu_reload).setVisible(false);
			menu.findItem(R.id.menu_home).setVisible(false);
			menu.findItem(R.id.menu_forward).setVisible(false);
			menu.findItem(R.id.menu_share).setVisible(false);
		}
		return super.onPrepareOptionsMenu(menu);
	}

	public class webViewClient extends WebViewClient {
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			invalidateOptionsMenu();
			super.onPageStarted(view, url, favicon);
		}
		@SuppressWarnings("deprecation")
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			ConnectivityManager cm = (ConnectivityManager)ActivityWebView.this.getSystemService(Activity.CONNECTIVITY_SERVICE);
			if (url.contains("?aloogleapp=open") || url.contains("&aloogleapp=open")) {
				String realurl = url.replace("?aloogleapp=open", "").replace("&aloogleapp=open", "");
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(realurl));
				startActivity(i);
			} else if (url.contains("?aloogleapp=reload") || url.contains("&aloogleapp=reload")) {
				if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
					web.setVisibility(View.GONE);
					web.getSettings().setCacheMode(WebSettings.LOAD_NORMAL);
					web.reload();
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
					toast.show();
				}
			} else if (url.contains("?aloogleapp=share") || url.contains("&aloogleapp=share")) {
				String realurl = url.replace("?aloogleapp=share", "").replace("&aloogleapp=share", "");
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				shareIntent.putExtra(Intent.EXTRA_TEXT, realurl);
				shareIntent.setType("text/plain");
				startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.app_name)));
			} else if (url.contains("?aloogleapp=needinternet") || url.contains("&aloogleapp=needinternet")) {
				if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
					web.getSettings().setCacheMode(WebSettings.LOAD_NORMAL);
					String realurl = url.replace("?aloogleapp=needinternet", "").replace("&aloogleapp=needinternet", "");
					web.loadUrl(realurl);
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
					toast.show();
				}
			} else {
				view.loadUrl(url);
			}
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			animatedGifImageView.setVisibility(View.GONE);
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			boolean prefcache = preferences.getBoolean("prefCache", false);
			if (prefcache) {}
			else {
				web.clearCache(true);
			}
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			web.loadData(getString(R.string.erroroccured) + " " + getString(R.string.connectionerroron), "text/html; charset=UTF-8", null);
		}
	}

	class webChromeClient extends WebChromeClient {

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
		editor.putString("WebViewLastUrl", web.getUrl());
		editor.commit();
	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String lastUrl = preferences.getString("WebViewLastUrl", "http://aloogle.tumblr.com/droidex/news");
		web.loadUrl(lastUrl);
	}
}