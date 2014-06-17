package aloogle.pokedex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import aloogle.pokedex.R;
import aloogle.pokedex.fragment.PokemonDetails;
import aloogle.pokedex.fragment.PokemonName;
import aloogle.pokedex.other.Other;
import aloogle.pokedex.other.Other.pokemonInterface;

public class ActivityMain extends FragmentActivity implements pokemonInterface {
	private FrameLayout fragmentContainer;

	 @ Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setBackgroundDrawable(null);

		fragmentContainer = (FrameLayout)findViewById(R.id.fragment_container);
		if (savedInstanceState == null) {
			PokemonName pokemonName = new PokemonName();
			getSupportFragmentManager()
			.beginTransaction()
			.add(R.id.fragment_list_container, pokemonName)
			.commit();
		}
	}

	 @ Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	 @ Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_changelog:
			Intent change = new Intent(ActivityMain.this, ActivityAboutChangelog.class);
			change.putExtra(Other.AboutOrChange, 0);
			startActivity(change);
			return true;
		case R.id.menu_about:
			Intent about = new Intent(ActivityMain.this, ActivityAboutChangelog.class);
			about.putExtra(Other.AboutOrChange, 1);
			startActivity(about);
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
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "See the most updated Pokédex app for Android!! http://aloogle.tumblr.com/droidex/download");
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.app_name)));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	 @ Override
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

	 @ Override
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

	 @ Override
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