package aloogle.pokedex.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import java.util.Locale;
import aloogle.pokedex.R;
import aloogle.pokedex.fragment.PokemonDetails;
import aloogle.pokedex.fragment.PokemonName;
import aloogle.pokedex.other.Other;
import aloogle.pokedex.other.Other.pokemonInterface;

public class ActivityMain extends FragmentActivity implements pokemonInterface {
	private FrameLayout fragmentContainer;

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
		switch (item.getItemId()) {
		case R.id.menu_changelog:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent change = new Intent(ActivityMain.this, aloogle.pokedex.activity.pt.ActivityAboutChangelog.class);
				change.putExtra(Other.AboutOrChange, 0);
				startActivity(change);
			} else {
				Intent change = new Intent(ActivityMain.this, ActivityAboutChangelog.class);
				change.putExtra(Other.AboutOrChange, 0);
				startActivity(change);
			}
			return true;
		case R.id.menu_about:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent change = new Intent(ActivityMain.this, aloogle.pokedex.activity.pt.ActivityAboutChangelog.class);
				change.putExtra(Other.AboutOrChange, 1);
				startActivity(change);
			} else {
				Intent change = new Intent(ActivityMain.this, ActivityAboutChangelog.class);
				change.putExtra(Other.AboutOrChange, 1);
				startActivity(change);
			}
			return true;
		case R.id.menu_update:
			Intent update = new Intent(ActivityMain.this, ActivityVerifyUpdate.class);
			startActivity(update);
			return true;
		case R.id.menu_feedback:
			Intent feedback = new Intent(ActivityMain.this, ActivitySendFeedback.class);
			startActivity(feedback);
			return true;
		case R.id.menu_share:
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharecontent));
			shareIntent.setType("text/plain");
			startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.app_name)));
			return true;
		case R.id.menu_settings:
			Intent settings = new Intent(ActivityMain.this, ActivitySettings.class);
			startActivity(settings);
			//For users do not need restart on changes
			ActivityMain.this.finish();
			return true;
		case R.id.menu_news:
			Intent news = new Intent(ActivityMain.this, ActivityNews.class);
			startActivity(news);
			return true;
		case R.id.menu_help:
			if (Locale.getDefault().getLanguage().equals("pt")) {
				Intent intent = new Intent(ActivityMain.this, aloogle.pokedex.activity.pt.ActivityHelp.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(ActivityMain.this, ActivityHelp.class);
				startActivity(intent);
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
		if (fragmentContainer == null) {
			Intent intent = new Intent(this, ActivityDetails.class);
			intent.putExtra(Other.PokemonId, id);
			this.startActivity(intent);
		} else {
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
}
