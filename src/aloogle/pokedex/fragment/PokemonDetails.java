package aloogle.pokedex.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import aloogle.pokedex.R;
import aloogle.pokedex.adapter.*;
import aloogle.pokedex.object.Pokemon;
import aloogle.pokedex.other.Database;
import aloogle.pokedex.other.Other;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PokemonDetails extends Fragment{
    private Activity activity;
    private String pokemon_id, pokemon_image_id, pokemon_name;
    private String[] pokemonDescription;
    private int nDescription, currentDesc;
    private ScrollView rightScroll;
    private ImageView imgSugimori, imgSpritesFront, imgSpritesBack,
            imgShinyFront, imgShinyBack;
    private LinearLayout boxDescription, boxDexNumber, boxAbility,
            boxEvolution, boxOtherForm, boxTypeEfficacy, boxList;
    private TableLayout boxSprites;
    private RelativeLayout boxData;
    private GridLayout boxStats, boxLocation, boxMove, boxBase;
    private Spinner spinVersion, spinEncounterMethod,
            spinVersionGroup, spinMoveMethod;
    private TextView txtDescVersion, txtDescription, txtSprites,
            txtType1, txtType2, txtHeight, txtWeight,
            txtShape, txtJapanese, txtSpecies, txtHabitat,
            txtGenderRatio, txtEgg, txtHatch, txtGrowth,
            txtCapture, txtBaseExp, txtBaseEffort, txtBaseHappiness,
            txtLblHP, txtLblATK, txtLblDEF, txtLblSA,
            txtLblSD, txtLblSPD, txtHP, txtATK,
            txtDEF, txtSA, txtSD, txtSPD,
            txtLocation, txtReturn, txtMove;
    private ExpandableListView listPokemon;
    private ProgressBar progressPokemon;
    private Database DB;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.pokemon_details, container, false);
        setComponentName(view);
        getArgumentValue();
        DB = new Database(activity);

        makePage make = new makePage();
        make.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pokemon_id);

        return view;
    }

    private class makePage extends AsyncTask<String, Void, Pokemon> {

        @Override
        protected Pokemon doInBackground(String... params) {
            Pokemon pokemon = new Pokemon(activity, params[0]);
            if (pokemon_image_id == null) pokemon_image_id = pokemon_id;
            if (pokemon_name == null) pokemon_name = pokemon.EnglishName;
            return pokemon;
        }

        @Override
        protected void onPostExecute(Pokemon pokemon) {
              activity.getActionBar().setTitle(pokemon_name);

            setDescriptionBox(pokemon);
            setPokemonImage(pokemon);
            setPokemonType(pokemon);
            setPokemonDexNumber(pokemon);
            setPokemonAbility(pokemon);
            setPokemonBody(pokemon);
            setPokemonData(pokemon);
            setPokemonStat(pokemon);
            setPokemonEfficacy(pokemon);
            setPokemonLocation(pokemon);
            setPokemonEvolution(pokemon);
            setPokemonForm(pokemon);
            setPokemonMove(pokemon);

            progressPokemon.setVisibility(View.GONE);
        }
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_activity_details, menu);
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
          case R.id.menu_bulbapedia:
             String urlb = "http://bulbapedia.bulbagarden.net/w/index.php?title=Special%3ASearch&search=" + pokemon_name + "&go=Go";
			   Intent bulbapedia = new Intent(Intent.ACTION_VIEW);
			   bulbapedia.setData(Uri.parse(urlb));
			   startActivity(bulbapedia);
             return true;
		  case R.id.menu_serebii:
			   String urls = "http://www.serebii.net/search.shtml?cx=018410473690156091934%3A6gahkiyodbi&cof=FORID%3A11&q=" + pokemon_name;
			   Intent serebii = new Intent(Intent.ACTION_VIEW);
			   serebii.setData(Uri.parse(urls));
			   startActivity(serebii);
			   return true;
          default:
             return
          super.onOptionsItemSelected(item);
       }
    }
	
    private void setComponentName(View v) {
        rightScroll = (ScrollView) v.findViewById(R.id.leftScroll);

        imgSugimori = (ImageView) v.findViewById(R.id.imgSugimoriArt);
        imgSpritesFront = (ImageView) v.findViewById(R.id.imgSpritesFront);
        imgSpritesBack = (ImageView) v.findViewById(R.id.imgSpritesBack);
        imgShinyFront = (ImageView) v.findViewById(R.id.imgShinyFront);
        imgShinyBack = (ImageView) v.findViewById(R.id.imgShinyBack);

        boxData = (RelativeLayout) v.findViewById(R.id.boxData);
        boxDescription = (LinearLayout) v.findViewById(R.id.boxDescription);
        boxDexNumber = (LinearLayout) v.findViewById(R.id.boxDexNumber);
        boxAbility = (LinearLayout) v.findViewById(R.id.boxAbility);
        boxEvolution = (LinearLayout) v.findViewById(R.id.boxEvolution);
        boxOtherForm = (LinearLayout) v.findViewById(R.id.boxOtherForm);
        boxTypeEfficacy = (LinearLayout) v.findViewById(R.id.boxTypeEfficacy);
        boxList = (LinearLayout) v.findViewById(R.id.boxList);
        boxSprites = (TableLayout) v.findViewById(R.id.boxSprites);
        boxStats = (GridLayout) v.findViewById(R.id.boxStats);
        boxLocation = (GridLayout) v.findViewById(R.id.boxLocation);
        boxMove = (GridLayout) v.findViewById(R.id.boxMove);
        boxBase = (GridLayout) v.findViewById(R.id.boxBase);

        txtDescVersion = (TextView) v.findViewById(R.id.txtVersionName);
        txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        txtSprites = (TextView) v.findViewById(R.id.txtSprites);
        txtType1 = (TextView) v.findViewById(R.id.txtType1);
        txtType2 = (TextView) v.findViewById(R.id.txtType2);
        txtHeight = (TextView) v.findViewById(R.id.txtHeight);
        txtWeight = (TextView) v.findViewById(R.id.txtWeight);
        txtShape = (TextView) v.findViewById(R.id.txtShape);
        txtJapanese = (TextView) v.findViewById(R.id.txtJapanese);
        txtSpecies = (TextView) v.findViewById(R.id.txtSpecies);
        txtHabitat = (TextView) v.findViewById(R.id.txtHabitat);
        txtGenderRatio = (TextView) v.findViewById(R.id.txtGender);
        txtEgg = (TextView) v.findViewById(R.id.txtEgg);
        txtHatch = (TextView) v.findViewById(R.id.txtHatch);
        txtGrowth = (TextView) v.findViewById(R.id.txtGrowth);
        txtCapture = (TextView) v.findViewById(R.id.txtCapture);
        txtBaseExp = (TextView) v.findViewById(R.id.txtBaseExp);
        txtBaseEffort = (TextView) v.findViewById(R.id.txtBaseEffort);
        txtBaseHappiness = (TextView) v.findViewById(R.id.txtBaseHappiness);
        txtLblHP = (TextView) v.findViewById(R.id.txtLblHP);
        txtLblATK = (TextView) v.findViewById(R.id.txtLblATK);
        txtLblDEF = (TextView) v.findViewById(R.id.txtLblDEF);
        txtLblSA = (TextView) v.findViewById(R.id.txtLblSA);
        txtLblSD = (TextView) v.findViewById(R.id.txtLblSD);
        txtLblSPD = (TextView) v.findViewById(R.id.txtLblSPD);
        txtHP = (TextView) v.findViewById(R.id.txtHP);
        txtATK = (TextView) v.findViewById(R.id.txtATK);
        txtDEF = (TextView) v.findViewById(R.id.txtDEF);
        txtSA = (TextView) v.findViewById(R.id.txtSA);
        txtSD = (TextView) v.findViewById(R.id.txtSD);
        txtSPD = (TextView) v.findViewById(R.id.txtSPD);
        txtLocation = (TextView) v.findViewById(R.id.txtLocation);
        txtMove = (TextView) v.findViewById(R.id.txtMove);
        txtReturn = (TextView) v.findViewById(R.id.txtReturn);

        spinVersion = (Spinner) v.findViewById(R.id.spinVersion);
        spinEncounterMethod = (Spinner) v.findViewById(R.id.spinEncounterMethod);
        spinVersionGroup = (Spinner) v.findViewById(R.id.spinVersionGroup);
        spinMoveMethod = (Spinner) v.findViewById(R.id.spinMoveMethod);

        listPokemon = (ExpandableListView) v.findViewById(R.id.listPokemonData);
        progressPokemon = (ProgressBar) v.findViewById(R.id.progressPokemon);
    }

    private void getArgumentValue() {
        pokemon_id = getArguments().getString(Other.PokemonId);
        pokemon_image_id = getArguments().getString(Other.PokemonImageId);
        pokemon_name = getArguments().getString(Other.PokemonName);
    }

    private void setDescriptionBox(Pokemon p) {
        if (p.hasDescription) {
            pokemonDescription = p.Description;
            nDescription = pokemonDescription.length;
            currentDesc = 0;

            setDescriptionText(currentDesc);

            boxDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentDesc++;
                    if (currentDesc == nDescription) currentDesc = 0;
                    setDescriptionText(currentDesc);
                }
            });
        } else boxDescription.setVisibility(View.GONE);
    }

    private void setDescriptionText(int index) {
        String[] desc = pokemonDescription[index].split(Database.SPLIT);
        txtDescVersion.setText("Pokémon " + desc[0]);
        txtDescription.setText(desc[1]);
    }

    private void setPokemonImage(Pokemon p) {
        final String sugimori_art = "art/sa_" + pokemon_image_id + ".png";
        final String normalFront = "sprites/normal/front/nf_" + pokemon_image_id + ".png";
        final String normalBack = "sprites/normal/back/nb_" + pokemon_image_id + ".png";
        final String shinyFront = "sprites/shiny/front/sf_" + pokemon_image_id + ".png";
        final String shinyBack = "sprites/shiny/back/sb_" + pokemon_image_id + ".png";

        Other.setImage(imgSugimori,sugimori_art,R.drawable.unknown_large);
        Other.setImage(imgSpritesFront,normalFront,R.drawable.unknown_small);
        Other.setImage(imgSpritesBack,normalBack,R.drawable.unknown_small);
        Other.setImage(imgShinyFront,shinyFront,R.drawable.unknown_small);
        Other.setImage(imgShinyBack,shinyBack,R.drawable.unknown_small);

        if (p.hasGenderDifferences) {
            txtSprites.setVisibility(View.VISIBLE);
            final String normalFeFront = "sprites/normal/front/nf_" + pokemon_id + "_female.png";
            final String normalFeBack = "sprites/normal/back/nb_" + pokemon_id + "_female.png";
            final String shinyFeFront = "sprites/shiny/front/sf_" + pokemon_id + "_female.png";
            final String shinyFeBack = "sprites/shiny/back/sb_" + pokemon_id + "_female.png";

            boxSprites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (txtSprites.getText().equals("♂")) {
                        txtSprites.setText("♀");
                        Other.setImage(imgSpritesFront,normalFeFront,R.drawable.unknown_small);
                        Other.setImage(imgSpritesBack, normalFeBack, R.drawable.unknown_small);
                        Other.setImage(imgShinyFront, shinyFeFront, R.drawable.unknown_small);
                        Other.setImage(imgShinyBack, shinyFeBack, R.drawable.unknown_small);
                    } else {
                        txtSprites.setText("♂");
                        Other.setImage(imgSpritesFront, normalFront, R.drawable.unknown_small);
                        Other.setImage(imgSpritesBack, normalBack, R.drawable.unknown_small);
                        Other.setImage(imgShinyFront, shinyFront, R.drawable.unknown_small);
                        Other.setImage(imgShinyBack, shinyBack, R.drawable.unknown_small);
                    }
                }
            });
        }
    }

    private void setPokemonType(Pokemon p) {
        int type1 = Integer.valueOf(p.Type1);
        int type2 = Integer.valueOf(p.Type2);

        setTypeText(txtType1, type1);

        if (type2 > 0) {
            txtType2.setVisibility(View.VISIBLE);
            setTypeText(txtType2, type2);
        }
    }

    private void setTypeText(TextView txtType, int type) {
        int typeName = Other.getTypeName(type);
        int typeColor = Other.getTypeColor(type);
        int icon = Other.getTypeImage(type);

        txtType.setText(typeName);
        txtType.setTextColor(typeColor);
        txtType.setCompoundDrawablesWithIntrinsicBounds(icon,0,0,0);
    }

    private void setPokemonDexNumber(Pokemon p) {
        if (p.hasDexNumber) {
            ListDexAdapter dexAdapter = new ListDexAdapter(activity, p.DexNumber);
            int nDex = dexAdapter.getCount();
            for (int n = 0; n < nDex; n++) {
                boxDexNumber.addView(dexAdapter.getView(n,null,boxDexNumber));
            }
        } else boxDexNumber.setVisibility(View.GONE);
    }

    private void setPokemonAbility(Pokemon p) {
        if (p.hasAbility) {
            ListAbilityAdapter abilityAdapter = new ListAbilityAdapter(activity, p.Ability);
            int nAbility = abilityAdapter.getCount();
            for (int n = 0; n < nAbility; n++) {
                boxAbility.addView(abilityAdapter.getView(n,null,boxAbility));
            }
        } else boxAbility.setVisibility(View.GONE);
    }

    private void setPokemonBody(Pokemon p) {
        txtHeight.setText(p.Height);
        txtWeight.setText(p.Weight);
        txtShape.setText(p.BodyShape);
    }

    private void setPokemonData(Pokemon p) {
        if (p.hasData) {
            txtJapanese.setText(p.JapaneseName + " (" + p.RomajiName + ")");
            txtSpecies.setText(p.Species);
            txtHabitat.setText(p.Habitat);
            txtGenderRatio.setText(p.Gender);
            txtEgg.setText(p.EggGroups);
            txtHatch.setText(p.HatchCounter);
            txtGrowth.setText(p.GrowthRate);
            txtCapture.setText(p.CaptureRate);
        } else boxData.setVisibility(View.GONE);

        if (p.hasBaseData) {
            txtBaseExp.setText(p.BaseExperience);
            txtBaseEffort.setText(p.BaseEffort);
            txtBaseHappiness.setText(p.BaseHappiness);
        } else boxBase.setVisibility(View.GONE);
    }

    private void setPokemonStat(Pokemon p) {
        final String[] Stats = p.Stats;
        final String[] maxStats = p.MaxStats;
        setStats(false, Stats);

        boxStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtLblHP.getText().equals("Base HP")) setStats(true, maxStats);
                else setStats(false, Stats);
            }
        });
    }

    private void setStats(boolean max, String[] data) {
        if (max) {
            txtLblHP.setText("Max HP");
            txtLblATK.setText("Max ATK");
            txtLblDEF.setText("Max DEF");
            txtLblSA.setText("Max SA");
            txtLblSD.setText("Max SD");
            txtLblSPD.setText("Max SPD");
        } else {
            txtLblHP.setText("Base HP");
            txtLblATK.setText("Base ATK");
            txtLblDEF.setText("Base DEF");
            txtLblSA.setText("Base SA");
            txtLblSD.setText("Base SD");
            txtLblSPD.setText("Base SPD");
        }

        txtHP.setText(data[0]);
        txtATK.setText(data[1]);
        txtDEF.setText(data[2]);
        txtSA.setText(data[3]);
        txtSD.setText(data[4]);
        txtSPD.setText(data[5]);
    }

    private void setPokemonEfficacy(Pokemon p) {
        if (p.hasEfficacy) {
            ListEfficacyAdapter efficacyAdapter = new ListEfficacyAdapter(activity, p.TypeEfficacy);
            int nEfficacy = efficacyAdapter.getCount();
            for (int n = 0; n < nEfficacy; n++) {
                boxTypeEfficacy.addView(efficacyAdapter.getView(n,null,boxTypeEfficacy));
            }
        } else boxTypeEfficacy.setVisibility(View.GONE);
    }

    private void setPokemonLocation(final Pokemon p) {
        if (p.hasLocation) {
            CustomSpinnerAdapter version_adapter =
                    new CustomSpinnerAdapter(activity,p.Version,"Pokémon");
            CustomSpinnerAdapter encounter_method_adapter =
                    new CustomSpinnerAdapter(activity,p.EncounterMethod,"");

            spinVersion.setAdapter(version_adapter);
            spinEncounterMethod.setAdapter(encounter_method_adapter);

            txtLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightScroll.setVisibility(View.GONE);
                    boxList.setVisibility(View.VISIBLE);
                    makeListLocation();
                }
            });
        } else boxLocation.setVisibility(View.GONE);
    }

    private void setPokemonMove(final Pokemon p) {
        if (p.hasMove) {
            CustomSpinnerAdapter version_adapter =
                    new CustomSpinnerAdapter(activity,p.VersionGroup,"");
            CustomSpinnerAdapter move_method_adapter =
                    new CustomSpinnerAdapter(activity,p.MoveMethod,"");

            spinVersionGroup.setAdapter(version_adapter);
            spinMoveMethod.setAdapter(move_method_adapter);

            txtMove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightScroll.setVisibility(View.GONE);
                    boxList.setVisibility(View.VISIBLE);
                    makeListMove();
                }
            });

            txtReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightScroll.setVisibility(View.VISIBLE);
                    boxList.setVisibility(View.GONE);
                }
            });
        } else boxMove.setVisibility(View.GONE);
    }

    private void setPokemonEvolution(Pokemon p) {
        if (p.hasEvolution) {
            boxEvolution.setVisibility(View.VISIBLE);
            ListEvolutionAdapter evolutionAdapter = new ListEvolutionAdapter(activity, p.Evolution, pokemon_id);
            int nEvolution = evolutionAdapter.getCount();
            for (int n = 0; n < nEvolution; n++) {
                boxEvolution.addView(evolutionAdapter.getView(n,null,boxEvolution));
            }
        }
    }

    private void setPokemonForm(Pokemon p) {
        if (p.hasOtherForm) {
            boxOtherForm.setVisibility(View.VISIBLE);
            ListFormAdapter formAdapter = new ListFormAdapter(activity, p.OtherForm, p.formSwitchable);
            int nForm = formAdapter.getCount();
            for (int n = 0; n < nForm; n++) {
                boxOtherForm.addView(formAdapter.getView(n,null,boxOtherForm));
            }
        }
    }

    private void makeListLocation() {
        SparseArray<String[]> childData = new SparseArray<String[]>();
        String location_id;

        String version_id = spinVersion
                .getSelectedItem().toString().split(Database.SPLIT)[0];
        String method_id = spinEncounterMethod
                .getSelectedItem().toString().split(Database.SPLIT)[0];
        String[] listEncounter = DB.getPokemonLocation(pokemon_id, version_id, method_id);

        int length = listEncounter.length;
        for (int n = 0; n < length; n++) {
            location_id = listEncounter[n].split(Database.SPLIT)[0];
            childData.append(n,DB.getPokemonDetailedLocation(pokemon_id,version_id,location_id,method_id));
        }

        ExpandListEncounterAdapter adapter = new ExpandListEncounterAdapter
                (activity, listEncounter, childData);
        listPokemon.setAdapter(adapter);
    }

    private void makeListMove() {
        String version_id = spinVersionGroup
                .getSelectedItem().toString().split(Database.SPLIT)[0];
        String method_id = spinMoveMethod
                .getSelectedItem().toString().split(Database.SPLIT)[0];
        String[] listMove = DB.getPokemonMoveParentList(pokemon_id, version_id, method_id);
        String[] listChildMove = DB.getPokemonMoveChildList(pokemon_id, version_id, method_id);

        ExpandListMoveAdapter adapter = new ExpandListMoveAdapter(activity, listMove, listChildMove);
        listPokemon.setAdapter(adapter);
    }
}