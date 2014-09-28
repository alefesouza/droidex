package aloogle.pokedex.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import aloogle.pokedex.R;
import aloogle.pokedex.fragment.PokemonDetails;
import aloogle.pokedex.other.Other;

public class ActivityDetails extends FragmentActivity implements Other.pokemonInterface {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		getWindow().setBackgroundDrawable(null);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		Other.ActionBarColor(this);
		Other.ActionBarColorIcons(this, "DroiDéx");
		Other.ActionBarIcon(this);
		Other.SystemBarColor(this, true);

		if (savedInstanceState == null) {
			PokemonDetails pokemonDetails = new PokemonDetails();
			pokemonDetails.setArguments(getIntent().getExtras());

			getSupportFragmentManager()
			.beginTransaction()
			.add(R.id.fragment_container, pokemonDetails)
			.commit();
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
}
