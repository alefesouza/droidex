package aloogle.pokedex.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Locale;
import aloogle.pokedex.R;
import aloogle.pokedex.fragment.PokemonName;
import aloogle.pokedex.other.Other;
import aloogle.pokedex.other.Other.pokemonInterface;
import android.content.DialogInterface;

public class ActivityMain extends FragmentActivity implements pokemonInterface {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private String[]mDrawerTitles;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Other.ActionBarColor(this);
		Other.ActionBarColorIcons(this, getString(R.string.app_name));
		Other.ActionBarIcon(this);
		Other.SystemBarColor(this, true);

		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			PokemonName pokemonName = new PokemonName();
			getSupportFragmentManager()
			.beginTransaction()
			.add(R.id.fragment_list_container, pokemonName)
			.commit();
		}

		mDrawerTitles = getResources().getStringArray(R.array.drawer_array);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList = (ListView)findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		mDrawerList.setAdapter(new ArrayAdapter < String > (this, R.layout.drawer_list_item, mDrawerTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView <  ?  > parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		final ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Activity.CONNECTIVITY_SERVICE);
		switch (position) {
		case 0:
			if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
				Intent update = new Intent(ActivityMain.this, ActivityWebView.class);
				update.putExtra(Other.WebViewValue, 3);
				startActivity(update);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		case 1:
			final WebView webview = new WebView(this);
			webview.loadData(getString(R.string.rateappawarning), "text/html; charset=utf-8", null);
			webview.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					if (url.contains("?aloogleapp=activityhelp")) {
						if (Locale.getDefault().getLanguage().equals("pt")) {
							Intent help = new Intent(ActivityMain.this, ActivityAboutChangelogHelp.class);
							help.putExtra(Other.WebViewValue, 5);
							startActivity(help);
						} else {
							Intent help = new Intent(ActivityMain.this, ActivityAboutChangelogHelp.class);
							help.putExtra(Other.WebViewValue, 2);
							startActivity(help);
						}
					} else if (url.contains("?aloogleapp=activityverifyupdate")) {
						if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
							Intent update = new Intent(ActivityMain.this, ActivityWebView.class);
							update.putExtra(Other.WebViewValue, 3);
							startActivity(update);
						} else {
							Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
							toast.show();
						}
					}
					return true;
				}

				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
				}

				@Override
				public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
					super.onReceivedError(view, errorCode, description, failingUrl);
				}
			});

			final AlertDialog ratealert = new
				AlertDialog.Builder(this)
				.setView(webview)
				.setTitle(R.string.rateapp)
				.setPositiveButton(R.string.rate, null)
				.create();

			ratealert.setOnShowListener(new
				DialogInterface.OnShowListener() {
				@Override
				public void onShow(DialogInterface dialog) {
					Button b = ratealert.getButton(AlertDialog.BUTTON_POSITIVE);
					b.setOnClickListener(new
						View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Intent i = new Intent(Intent.ACTION_VIEW);
							i.setData(Uri.parse("market://details?id=aloogle.pokedex"));
							startActivity(i);
						}
					});
				}
			});
			ratealert.show();
			break;
		case 2:
			Intent settings = new Intent(ActivityMain.this, ActivitySettings.class);
			startActivity(settings);
			break;
		case 3:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent help = new Intent(ActivityMain.this, ActivityAboutChangelogHelp.class);
				help.putExtra(Other.WebViewValue, 5);
				startActivity(help);
			} else {
				Intent help = new Intent(ActivityMain.this, ActivityAboutChangelogHelp.class);
				help.putExtra(Other.WebViewValue, 2);
				startActivity(help);
			}
			break;
		case 4:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent change = new Intent(ActivityMain.this, ActivityAboutChangelogHelp.class);
				change.putExtra(Other.WebViewValue, 3);
				startActivity(change);
			} else {
				Intent change = new Intent(ActivityMain.this, ActivityAboutChangelogHelp.class);
				change.putExtra(Other.WebViewValue, 0);
				startActivity(change);
			}
			break;
		case 5:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent about = new Intent(ActivityMain.this, ActivityAboutChangelogHelp.class);
				about.putExtra(Other.WebViewValue, 4);
				startActivity(about);
			} else {
				Intent about = new Intent(ActivityMain.this, ActivityAboutChangelogHelp.class);
				about.putExtra(Other.WebViewValue, 1);
				startActivity(about);
			}
			break;
		default:
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.menu_rateapp).setVisible(false);
		menu.findItem(R.id.menu_update).setVisible(false);
		menu.findItem(R.id.menu_settings).setVisible(false);
		menu.findItem(R.id.menu_help).setVisible(false);
		menu.findItem(R.id.menu_changelog).setVisible(false);
		menu.findItem(R.id.menu_about).setVisible(false);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String userColorIcons = preferences.getString("prefColorIcons", "white");
		if (userColorIcons.equals("black")) {
			menu.findItem(R.id.action_search).setIcon(R.drawable.ic_search_dark);
			menu.findItem(R.id.action_filter).setIcon(R.drawable.ic_filter_dark_aloogle);
			menu.findItem(R.id.menu_news).setIcon(R.drawable.ic_news_dark_aloogle);
		}
		if (Locale.getDefault().getLanguage().equals("pt")) {
			menu.findItem(R.id.menu_news).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			menu.findItem(R.id.menu_translate).setVisible(true);
		} else {
			menu.findItem(R.id.menu_news).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
			menu.findItem(R.id.menu_translate).setVisible(false);
		}
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_search).setVisible(!drawerOpen);
		menu.findItem(R.id.action_filter).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Activity.CONNECTIVITY_SERVICE);
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.menu_share:
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharecontent));
			shareIntent.setType("text/plain");
			startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.app_name)));
			return true;
		case R.id.menu_news:
			if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
				Intent news = new Intent(ActivityMain.this, ActivityWebView.class);
				news.putExtra(Other.WebViewValue, 0);
				startActivity(news);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
				toast.show();
			}
			return true;
		case R.id.menu_translate:
			if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
				Intent translate = new Intent(ActivityMain.this, ActivityWebView.class);
				translate.putExtra(Other.WebViewValue, 4);
				startActivity(translate);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
				toast.show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == Other.PokemonFilterCode) {
			String gen = data.getStringExtra(Other.PokemonFilterGeneration);
			String type = data.getStringExtra(Other.PokemonFilterType);
			String color = data.getStringExtra(Other.PokemonFilterColor);
			boolean isBaby = data.getBooleanExtra(Other.PokemonFilterBaby, false);
			boolean hasGender = data.getBooleanExtra(Other.PokemonFilterGender, false);

			PokemonName pokemonName = (PokemonName)
			getSupportFragmentManager().findFragmentById(R.id.fragment_list_container);
			pokemonName.makeFilter(gen, type, color, isBaby, hasGender);
		}
	}

	@Override
	public void pokemonSelected(String id) {
		Intent intent = new Intent(this, ActivityDetails.class);
		intent.putExtra(Other.PokemonId, id);
		this.startActivity(intent);
	}

	@Override
	public void formSelected(String id, String img_id, String name, boolean formSwitchable) {}

	public void onResume() {
		super.onResume();
		Other.ActionBarColor(this);
		Other.ActionBarColorIcons(this, getString(R.string.app_name));
		Other.ActionBarIcon(this);
		Other.SystemBarColor(this, true);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean prefCache = preferences.getBoolean("prefCache", false);
		if (prefCache) {
			Editor editor = preferences.edit();
			boolean savecachewarning = preferences.getBoolean("savecachewarning", false);
			if (savecachewarning) {}
			else {
				AlertDialog dialogsave = new
					AlertDialog.Builder(this)
					.setTitle(R.string.pref_cache)
					.setMessage(R.string.storagecachedialog)
					.setPositiveButton("OK", null)
					.create();
				dialogsave.show();
				editor.putBoolean("savecachewarning", true);
				editor.commit();
			}
		}
	}
}
