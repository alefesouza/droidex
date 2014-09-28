package aloogle.pokedex.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import java.util.Locale;
import aloogle.pokedex.R;
import aloogle.pokedex.fragment.PokemonDetails;
import aloogle.pokedex.fragment.PokemonName;
import aloogle.pokedex.other.Other;
import aloogle.pokedex.other.Other.pokemonInterface;

public class ActivityMainTablet extends FragmentActivity implements pokemonInterface {
	@SuppressWarnings("unused")
	private FrameLayout fragmentContainer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Other.ActionBarIcon(this);
		Other.ActionBarColor(this);
		Other.ActionBarColorIcons(this, getString(R.string.app_name));

		setContentView(R.layout.activity_main);
		fragmentContainer = (FrameLayout)findViewById(R.id.fragment_container);
		if (savedInstanceState == null) {
			PokemonName pokemonName = new PokemonName();
			getSupportFragmentManager()
			.beginTransaction()
			.add(R.id.fragment_list_container, pokemonName)
			.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Activity.CONNECTIVITY_SERVICE);
		switch (item.getItemId()) {
		case R.id.menu_news:
			if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
				Intent news = new Intent(ActivityMainTablet.this, ActivityWebView.class);
				startActivity(news);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
				toast.show();
			}
			return true;
		case R.id.menu_share:
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharecontent));
			shareIntent.setType("text/plain");
			startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.app_name)));
			return true;
		case R.id.menu_update:
			if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
				Intent update = new Intent(ActivityMainTablet.this, ActivityWebView.class);
				update.putExtra(Other.WebViewValue, 3);
				startActivity(update);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
				toast.show();
			}
			return true;
		case R.id.menu_rateapp:
			final WebView webview = new WebView(this);
			webview.loadData(getString(R.string.rateappawarning), "text/html; charset=utf-8", null);
			webview.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					if (url.contains("?aloogleapp=activityhelp")) {
						if (Locale.getDefault().getLanguage().equals("pt")) {
							Intent help = new Intent(ActivityMainTablet.this, ActivityAboutChangelogHelp.class);
							help.putExtra(Other.WebViewValue, 5);
							startActivity(help);
						} else {
							Intent help = new Intent(ActivityMainTablet.this, ActivityAboutChangelogHelp.class);
							help.putExtra(Other.WebViewValue, 2);
							startActivity(help);
						}
					} else if (url.contains("?aloogleapp=activityverifyupdate")) {
						if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
							Intent update = new Intent(ActivityMainTablet.this, ActivityWebView.class);
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
			return true;
		case R.id.menu_settings:
			Intent settings = new Intent(ActivityMainTablet.this, ActivitySettings.class);
			startActivity(settings);
			return true;
		case R.id.menu_help:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent help = new Intent(ActivityMainTablet.this, ActivityAboutChangelogHelp.class);
				help.putExtra(Other.WebViewValue, 5);
				startActivity(help);
			} else {
				Intent help = new Intent(ActivityMainTablet.this, ActivityAboutChangelogHelp.class);
				help.putExtra(Other.WebViewValue, 2);
				startActivity(help);
			}
			return true;
		case R.id.menu_changelog:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent change = new Intent(ActivityMainTablet.this, ActivityAboutChangelogHelp.class);
				change.putExtra(Other.WebViewValue, 3);
				startActivity(change);
			} else {
				Intent change = new Intent(ActivityMainTablet.this, ActivityAboutChangelogHelp.class);
				change.putExtra(Other.WebViewValue, 0);
				startActivity(change);
			}
			return true;
		case R.id.menu_about:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent about = new Intent(ActivityMainTablet.this, ActivityAboutChangelogHelp.class);
				about.putExtra(Other.WebViewValue, 4);
				startActivity(about);
			} else {
				Intent about = new Intent(ActivityMainTablet.this, ActivityAboutChangelogHelp.class);
				about.putExtra(Other.WebViewValue, 1);
				startActivity(about);
			}
			return true;
		case R.id.menu_translate:
			if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
				Intent translate = new Intent(ActivityMainTablet.this, ActivityWebView.class);
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
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (Locale.getDefault().getLanguage().equals("pt")) {
			menu.findItem(R.id.menu_news).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			menu.findItem(R.id.menu_translate).setVisible(true);
		} else {
			menu.findItem(R.id.menu_news).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
			menu.findItem(R.id.menu_translate).setVisible(false);
		}
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String userColorIcons = preferences.getString("prefColorIcons", "white");
		if (userColorIcons.equals("black")) {
			menu.findItem(R.id.action_search).setIcon(R.drawable.ic_search_dark);
			menu.findItem(R.id.action_filter).setIcon(R.drawable.ic_filter_dark_aloogle);
			menu.findItem(R.id.menu_news).setIcon(R.drawable.ic_news_dark_aloogle);
		}
		return super.onPrepareOptionsMenu(menu);
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
		PokemonDetails pokemonDetails = new PokemonDetails();

		Bundle args = new Bundle();
		args.putString(Other.PokemonId, id);
		pokemonDetails.setArguments(args);

		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.fragment_container, pokemonDetails)
		.addToBackStack(null)
		.commit();
	}

	@Override
	public void formSelected(String id, String img_id, String name, boolean formSwitchable) {
		Bundle args = new Bundle();
		args.putString(Other.PokemonId, id);
		args.putString(Other.PokemonImageId, img_id);
		args.putString(Other.PokemonName, name);

		PokemonDetails alternativeForm = new PokemonDetails();
		alternativeForm.setArguments(args);

		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.fragment_container, alternativeForm)
		.addToBackStack(null)
		.commit();
	}

	public void onResume() {
		super.onResume();
		Other.ActionBarColor(this);
		Other.ActionBarColorIcons(this, getString(R.string.app_name));
		Other.ActionBarIcon(this);
		invalidateOptionsMenu();
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
