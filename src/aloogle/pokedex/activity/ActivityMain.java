package aloogle.pokedex.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Locale;
import aloogle.pokedex.R;
import aloogle.pokedex.fragment.PokemonName;
import aloogle.pokedex.other.Other;
import aloogle.pokedex.other.Other.pokemonInterface;

public class ActivityMain extends FragmentActivity implements pokemonInterface {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[]mDrawerTitles;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(false);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String userColor = preferences.getString("prefColor", "droidexblue");
		if (userColor.equals("red"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffff0000));
		else if (userColor.equals("green"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff00ff00));
		else if (userColor.equals("blue"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xff0000ff));
		else if (userColor.equals("yellow"))
			getActionBar().setBackgroundDrawable(new ColorDrawable(0xffffff00));
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

		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			PokemonName pokemonName = new PokemonName();
			getSupportFragmentManager()
			.beginTransaction()
			.add(R.id.fragment_list_container, pokemonName)
			.commit();
		}

		mTitle = mDrawerTitle = getTitle();
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
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
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
		ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Activity.CONNECTIVITY_SERVICE);
		switch (position) {
		case 0:
			if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
				Intent update = new Intent(ActivityMain.this, ActivityVerifyUpdate.class);
				startActivity(update);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		case 1:
			if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
				Intent feedback = new Intent(ActivityMain.this, ActivitySendFeedback.class);
				startActivity(feedback);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.needinternet), Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		case 2:
			Intent settings = new Intent(ActivityMain.this, ActivitySettings.class);
			startActivity(settings);
			//For user do not need restart on changes
			ActivityMain.this.finish();
			break;
		case 3:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent intent = new Intent(ActivityMain.this, aloogle.pokedex.activity.pt.ActivityHelp.class);
				startActivity(intent);
				ActivityMain.this.finish();
			} else {
				Intent intent = new Intent(ActivityMain.this, ActivityHelp.class);
				startActivity(intent);
				ActivityMain.this.finish();
			}
			break;
		case 4:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent change = new Intent(ActivityMain.this, aloogle.pokedex.activity.pt.ActivityAboutChangelog.class);
				change.putExtra(Other.AboutOrChange, 0);
				startActivity(change);
			} else {
				Intent change = new Intent(ActivityMain.this, ActivityAboutChangelog.class);
				change.putExtra(Other.AboutOrChange, 0);
				startActivity(change);
			}
			break;
		case 5:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent change = new Intent(ActivityMain.this, aloogle.pokedex.activity.pt.ActivityAboutChangelog.class);
				change.putExtra(Other.AboutOrChange, 1);
				startActivity(change);
			} else {
				Intent change = new Intent(ActivityMain.this, ActivityAboutChangelog.class);
				change.putExtra(Other.AboutOrChange, 1);
				startActivity(change);
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
		menu.findItem(R.id.menu_feedback).setVisible(false);
		menu.findItem(R.id.menu_update).setVisible(false);
		menu.findItem(R.id.menu_settings).setVisible(false);
		menu.findItem(R.id.menu_help).setVisible(false);
		menu.findItem(R.id.menu_changelog).setVisible(false);
		menu.findItem(R.id.menu_about).setVisible(false);
		if (Locale.getDefault().getLanguage().equals("pt")) {
			menu.findItem(R.id.menu_news).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		} else {
			menu.findItem(R.id.menu_news).setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		};
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
				Intent news = new Intent(ActivityMain.this, ActivityNews.class);
				startActivity(news);
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
	public void formSelected(String id, String img_id, String name, boolean formSwitchable) {
	}
}
