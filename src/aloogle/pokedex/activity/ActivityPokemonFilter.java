package aloogle.pokedex.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Other;

public class ActivityPokemonFilter extends Activity {
	private TextView txtFilterGeneration;
	private SparseArray < CheckBox > listChkGeneration;
	private boolean genVisible = false;

	private TextView txtFilterType;
	private SparseArray < CheckBox > listChkType;
	private boolean typeVisible = false;

	private TextView txtFilterColor;
	private SparseArray < CheckBox > listChkColor;
	private boolean colorVisible = false;

	private CheckBox chkBaby,
	chkGenderDiff;
	private TextView txtClearFilter;

	private String generation,
	type,
	color;
	private boolean isBaby,
	hasGenderDiff;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pokemon_filter);
		getArgument();
		setComponentName();
		restorePreviousFilter();
		setCLickListener();
		
		Other.setTranslucentStatus(false, this, false);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

		finish();
	}

	@Override
	public void finish() {
		String generation = setFilterText(listChkGeneration);
		String type = setFilterText(listChkType);
		String color = setFilterText(listChkColor);
		Boolean isBaby = chkBaby.isChecked();
		Boolean hasGenderDiff = chkGenderDiff.isChecked();

		if (this.generation.equals(generation) &&
			this.type.equals(type) &&
			this.color.equals(color) &&
			this.isBaby == isBaby &&
			this.hasGenderDiff == hasGenderDiff)
			setResult(RESULT_CANCELED);
		else {
			Intent dataFilter = new Intent();
			dataFilter.putExtra(Other.PokemonFilterGeneration, generation);
			dataFilter.putExtra(Other.PokemonFilterType, type);
			dataFilter.putExtra(Other.PokemonFilterColor, color);
			dataFilter.putExtra(Other.PokemonFilterBaby, isBaby);
			dataFilter.putExtra(Other.PokemonFilterGender, hasGenderDiff);
			setResult(RESULT_OK, dataFilter);
		}
		super.finish();
	}

	private void getArgument() {
		generation = getIntent().getStringExtra(Other.PokemonFilterGeneration);
		type = getIntent().getStringExtra(Other.PokemonFilterType);
		color = getIntent().getStringExtra(Other.PokemonFilterColor);
		isBaby = getIntent().getBooleanExtra(Other.PokemonFilterBaby, false);
		hasGenderDiff = getIntent().getBooleanExtra(Other.PokemonFilterGender, false);
	}

	private void setComponentName() {
		txtFilterGeneration = (TextView)findViewById(R.id.txtFilterGeneration);
		listChkGeneration = new SparseArray < CheckBox > ();
		listChkGeneration.put(0, (CheckBox)findViewById(R.id.chkGeneration1));
		listChkGeneration.put(1, (CheckBox)findViewById(R.id.chkGeneration2));
		listChkGeneration.put(2, (CheckBox)findViewById(R.id.chkGeneration3));
		listChkGeneration.put(3, (CheckBox)findViewById(R.id.chkGeneration4));
		listChkGeneration.put(4, (CheckBox)findViewById(R.id.chkGeneration5));
		listChkGeneration.put(5, (CheckBox)findViewById(R.id.chkGeneration6));

		txtFilterType = (TextView)findViewById(R.id.txtFilterType);
		listChkType = new SparseArray < CheckBox > ();
		listChkType.put(0, (CheckBox)findViewById(R.id.chkTypeNormal));
		listChkType.put(1, (CheckBox)findViewById(R.id.chkTypeFighting));
		listChkType.put(2, (CheckBox)findViewById(R.id.chkTypeFlying));
		listChkType.put(3, (CheckBox)findViewById(R.id.chkTypePoison));
		listChkType.put(4, (CheckBox)findViewById(R.id.chkTypeGround));
		listChkType.put(5, (CheckBox)findViewById(R.id.chkTypeRock));
		listChkType.put(6, (CheckBox)findViewById(R.id.chkTypeBug));
		listChkType.put(7, (CheckBox)findViewById(R.id.chkTypeGhost));
		listChkType.put(8, (CheckBox)findViewById(R.id.chkTypeSteel));
		listChkType.put(9, (CheckBox)findViewById(R.id.chkTypeFire));
		listChkType.put(10, (CheckBox)findViewById(R.id.chkTypeWater));
		listChkType.put(11, (CheckBox)findViewById(R.id.chkTypeGrass));
		listChkType.put(12, (CheckBox)findViewById(R.id.chkTypeElectric));
		listChkType.put(13, (CheckBox)findViewById(R.id.chkTypePsychic));
		listChkType.put(14, (CheckBox)findViewById(R.id.chkTypeIce));
		listChkType.put(15, (CheckBox)findViewById(R.id.chkTypeDragon));
		listChkType.put(16, (CheckBox)findViewById(R.id.chkTypeDark));
		listChkType.put(17, (CheckBox)findViewById(R.id.chkTypeFairy));

		txtFilterColor = (TextView)findViewById(R.id.txtFilterColor);
		listChkColor = new SparseArray < CheckBox > ();
		listChkColor.put(0, (CheckBox)findViewById(R.id.chkColorBlack));
		listChkColor.put(1, (CheckBox)findViewById(R.id.chkColorBlue));
		listChkColor.put(2, (CheckBox)findViewById(R.id.chkColorBrown));
		listChkColor.put(3, (CheckBox)findViewById(R.id.chkColorGray));
		listChkColor.put(4, (CheckBox)findViewById(R.id.chkColorGreen));
		listChkColor.put(5, (CheckBox)findViewById(R.id.chkColorPink));
		listChkColor.put(6, (CheckBox)findViewById(R.id.chkColorPurple));
		listChkColor.put(7, (CheckBox)findViewById(R.id.chkColorRed));
		listChkColor.put(8, (CheckBox)findViewById(R.id.chkColorWhite));
		listChkColor.put(9, (CheckBox)findViewById(R.id.chkColorYellow));

		chkBaby = (CheckBox)findViewById(R.id.chkIsBaby);
		chkGenderDiff = (CheckBox)findViewById(R.id.chkHasGenderDiff);
		txtClearFilter = (TextView)findViewById(R.id.txtClearFilter);
	}

	private void restorePreviousFilter() {
		if (!this.generation.isEmpty()) {
			String[]gen = this.generation.split(", ");
			for (String aGen : gen)
				listChkGeneration.get(Integer.valueOf(aGen) - 1).setChecked(true);
		}

		if (!this.type.isEmpty()) {
			String[]type = this.type.split(", ");
			for (String aType : type)
				listChkType.get(Integer.valueOf(aType) - 1).setChecked(true);
		}

		if (!this.color.isEmpty()) {
			String[]color = this.color.split(", ");
			for (String aColor : color)
				listChkColor.get(Integer.valueOf(aColor) - 1).setChecked(true);
		}

		chkBaby.setChecked(isBaby);
		chkGenderDiff.setChecked(hasGenderDiff);
	}

	private void setCLickListener() {
		txtFilterGeneration.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toggleVisibility(genVisible, listChkGeneration, txtFilterGeneration);
				genVisible = !genVisible;
			}
		});

		txtFilterType.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toggleVisibility(typeVisible, listChkType, txtFilterType);
				typeVisible = !typeVisible;
			}
		});

		txtFilterColor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toggleVisibility(colorVisible, listChkColor, txtFilterColor);
				colorVisible = !colorVisible;
			}
		});

		txtClearFilter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clearFilter();
			}
		});
	}

	private void toggleVisibility(boolean visibility, SparseArray < CheckBox > listCheckBox, TextView txtFilter) {
		int new_visibility = visibility ? View.GONE : View.VISIBLE;
		int drawable_right = visibility ? R.drawable.ic_max_pokedex : R.drawable.ic_min_pokedex;

		txtFilter.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable_right, 0);
		int nList = listCheckBox.size();
		for (int n = 0; n < nList; n++)
			listCheckBox.get(n).setVisibility(new_visibility);
	}

	private void clearFilter() {
		int nGen = listChkGeneration.size();
		int nType = listChkType.size();
		int nColor = listChkColor.size();

		for (int n = 0; n < nGen; n++)
			listChkGeneration.get(n).setChecked(false);
		for (int n = 0; n < nType; n++)
			listChkType.get(n).setChecked(false);
		for (int n = 0; n < nColor; n++)
			listChkColor.get(n).setChecked(false);

		chkBaby.setChecked(false);
		chkGenderDiff.setChecked(false);
	}

	private String setFilterText(SparseArray < CheckBox > listCheckBox) {
		String out = "";
		String value;
		int nList = listCheckBox.size();

		for (int n = 0; n < nList; n++)
			if (listCheckBox.get(n).isChecked()) {
				value = String.valueOf(n + 1);
				if (out.isEmpty())
					out += value;
				else
					out += ", " + value;
			}

		return out;
	}
}
