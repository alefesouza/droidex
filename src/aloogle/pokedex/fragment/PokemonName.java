package aloogle.pokedex.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.*;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;
import java.io.File;
import aloogle.pokedex.R;
import aloogle.pokedex.activity.ActivityPokemonFilter;
import aloogle.pokedex.adapter.ListNameAdapter;
import aloogle.pokedex.other.Database;
import aloogle.pokedex.other.Other;
import aloogle.pokedex.other.Other.pokemonInterface;
import aloogle.pokedex.other.ZipHelper;

public class PokemonName extends Fragment {
    private Activity activity;
    private Database DB;

    private GridView gridPokemon;

    private String Name = "", Generation = "", Color = "", Type = "";
    private Boolean isBaby = false, hasGenderDiff = false;

    private pokemonInterface pokemonInterface;

    private String unzipTarget;
    private String zipName;
    private DownloadManager mgr;
    private NotificationManager notifManager;

    private int byteBuffer = 1024;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = getActivity();
        this.mgr = (DownloadManager)activity.getSystemService(Context.DOWNLOAD_SERVICE);
        this.notifManager =
			(NotificationManager)activity.getSystemService(Context.NOTIFICATION_SERVICE);

        activity.registerReceiver(onComplete,
								  new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		DialogDownload("art");
		DialogDownload("sprites");

        setRetainInstance(true);
        unzipTarget = Other.ImageLocation;
        DB = new Database(activity);
        View view = inflater.inflate(R.layout.pokemon_name, container, false);
        setHasOptionsMenu(true);

        gridPokemon = (GridView) view.findViewById(R.id.listPokemon);
        gridPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String[] row = gridPokemon.getItemAtPosition(position).toString().split(Database.SPLIT);
					pokemonInterface.pokemonSelected(row[0]);
				}
			});

        pokemonInterface = (pokemonInterface) activity;
        makeList();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity.unregisterReceiver(onComplete);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pokemon_name, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.searchhint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
				@Override
				public boolean onQueryTextSubmit(String query) {
					return false;
				}

				@Override
				public boolean onQueryTextChange(String txt) {
					Name = txt;
					makeList();
					return false;
				}
			});

    }

    public void DialogDownload(String dialog) {
    	final ConnectivityManager cm = (ConnectivityManager)activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
    	if (dialog.equals("sprites")) {
    		File foldersprites = new File(Environment.getExternalStorageDirectory() + "/DroiDex/sprites/");
    		if (foldersprites.exists()) {}
    		else {
    			final AlertDialog dialogsprites = new AlertDialog.Builder(activity)
    				.setTitle("Sprites")
    				.setMessage(R.string.dialogdownloadsprites)
    				.setPositiveButton(R.string.yes, null)
    				.setNegativeButton(R.string.no, null)
    				.create();

    			dialogsprites.setOnShowListener(new
    				DialogInterface.OnShowListener() {
    				@Override
    				public void onShow(DialogInterface dialog) {
    					Button b = dialogsprites.getButton(AlertDialog.BUTTON_POSITIVE);
    					b.setOnClickListener(new
    						View.OnClickListener() {
    						@Override
    						public void onClick(View view) {
    							if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
    								Other.startDownload(mgr, Other.SpritesURL, "Pokémon Sprites - DroiDéx", getString(R.string.downloadwarning), "Sprites.zip");
    								byteBuffer = 3 * 1024;
    								zipName = "Sprites.zip";
    								DialogDownload("warning");
    							} else {
    								Toast toast = Toast.makeText(activity, getString(R.string.needinternet), Toast.LENGTH_LONG);
    								toast.show();
    							}
    							dialogsprites.dismiss();
    						}
    					});
    				}
    			});
    			dialogsprites.show();
    		}
    	} else if (dialog.equals("art")) {

    		File folderart = new File(Environment.getExternalStorageDirectory() + "/DroiDex/art/");
    		if (folderart.exists()) {}
    		else {
    			final AlertDialog dialogart = new AlertDialog.Builder(activity)
    				.setTitle("Sugimori art")
    				.setMessage(R.string.dialogdownloadart)
    				.setPositiveButton(R.string.yes, null)
    				.setNegativeButton(R.string.no, null)
    				.create();

    			dialogart.setOnShowListener(new
    				DialogInterface.OnShowListener() {
    				@Override
    				public void onShow(DialogInterface dialog) {
    					Button b = dialogart.getButton(AlertDialog.BUTTON_POSITIVE);
    					b.setOnClickListener(new
    						View.OnClickListener() {
    						@Override
    						public void onClick(View view) {
    							if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
    								Other.startDownload(mgr, Other.ArtURL, "Sugimori Art - DroiDéx", getString(R.string.downloadwarning), "Art.zip");
    								byteBuffer = 16 * 1024;
    								zipName = "Art.zip";
    								DialogDownload("warning");
    							} else {
    								Toast toast = Toast.makeText(activity, getString(R.string.needinternet), Toast.LENGTH_LONG);
    								toast.show();
    							}
    							dialogart.dismiss();
    						}
    					});
    				}
    			});
    			dialogart.show();
    		}
    	} else if (dialog.equals("warning")) {
    		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    		boolean dialogawarning = preferences.getBoolean("dialogwarning", false);
    		if (dialogawarning) {}
    		else {
    			final AlertDialog dialogwarning = new AlertDialog.Builder(activity)
    				.setTitle(R.string.read)
    				.setMessage(R.string.dialogwarning)
    				.setPositiveButton(android.R.string.ok, null)
    				.create();
    			dialogwarning.show();
    			Editor editor = preferences.edit();
    			editor.putBoolean("dialogwarning", true);
    			editor.commit();
    		}
    	}
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		final ConnectivityManager cm = (ConnectivityManager)activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
        switch (item.getItemId()) {
            case R.id.menu_art:
				if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
					Other.startDownload(mgr,Other.ArtURL, "Sugimori Art - DroiDéx",getString(R.string.downloadwarning), "Art.zip");
					byteBuffer = 16 * 1024;
					zipName = "Art.zip";
					DialogDownload("warning");
				} else {
					Toast toast = Toast.makeText(activity, getString(R.string.needinternet), Toast.LENGTH_LONG);
					toast.show();
				}
                return true;
            case R.id.menu_sprite:
				if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
					Other.startDownload(mgr,Other.SpritesURL,"Pokémon Sprites - DroiDéx",getString(R.string.downloadwarning),"Sprites.zip");
					byteBuffer = 3 * 1024;
					zipName = "Sprites.zip";
					DialogDownload("warning");
				} else {
					Toast toast = Toast.makeText(activity, getString(R.string.needinternet), Toast.LENGTH_LONG);
					toast.show();
				}
                return true;
			case R.id.menu_downloadanime:
				if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
					Other.startDownload(mgr,Other.SoundAnimeURL, "Pokémon Anime Sounds - DroiDéx",getString(R.string.downloadwarning), "SoundAnime.zip");
					byteBuffer = 16 * 1024;
					zipName = "SoundAnime.zip";
					DialogDownload("warning");
				} else {
					Toast toast = Toast.makeText(activity, getString(R.string.needinternet), Toast.LENGTH_LONG);
					toast.show();
				}
                return true;
			case R.id.menu_downloadgame:
				if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
					Other.startDownload(mgr,Other.SoundGameURL,"Pokémon Game Sounds - DroiDéx",getString(R.string.downloadwarning),"SoundGame.zip");
					byteBuffer =  16 * 1024;
					zipName = "SoundGame.zip";
					DialogDownload("warning");
				} else {
					Toast toast = Toast.makeText(activity, getString(R.string.needinternet), Toast.LENGTH_LONG);
					toast.show();
				}
                return true;
            case R.id.action_filter:
                Intent intent = new Intent(activity, ActivityPokemonFilter.class);
                intent.putExtra(Other.PokemonFilterGeneration, Generation);
                intent.putExtra(Other.PokemonFilterType, Type);
                intent.putExtra(Other.PokemonFilterColor, Color);
                intent.putExtra(Other.PokemonFilterBaby, isBaby);
                intent.putExtra(Other.PokemonFilterGender, hasGenderDiff);
                activity.startActivityForResult(intent, Other.PokemonFilterCode);
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            default:
                return false;
        }
    }

    public void makeFilter(String generation, String type, String color, boolean isBaby, boolean hasGenderDiff) {
        this.Generation = generation;
        this.Type = type;
        this.Color = color;
        this.isBaby = isBaby;
        this.hasGenderDiff = hasGenderDiff;

        makeList();
    }

    private void makeList() {
        ListNameAdapter adapter = new ListNameAdapter(activity,
													  DB.getPokemonList(Name, Generation, Color, Type, isBaby, hasGenderDiff));
        gridPokemon.setAdapter(adapter);
    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
			if(zipName == null) { Toast toast = Toast.makeText(activity, getString(R.string.downloadfinished), Toast.LENGTH_SHORT);
				toast.show(); } else {
				File f = new File(unzipTarget);
				if (f.exists()) f.delete();

				UnzipFile unzip = new UnzipFile();
				String zipLocation = unzipTarget + zipName;
				unzip.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new ZipHelper(zipLocation, unzipTarget));}
        }
    };

    public class UnzipFile extends AsyncTask<ZipHelper,Void,Void> {
        String zipLocation;
        NotificationCompat.Builder notif;

        @Override
        protected void onPreExecute() {
            notif = new NotificationCompat.Builder(activity);
            notif.setContentTitle(getString(R.string.extract) + " " + zipName)
				.setContentText(getString(R.string.extractdata))
				.setSmallIcon(R.drawable.ic_extract)
				.setProgress(0, 0, true);
            notifManager.notify(0, notif.build());
        }

        @Override
        protected Void doInBackground(ZipHelper... zip) {
            zipLocation = zip[0].ZipFileLocation();
            zip[0].unzip(byteBuffer);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            File f = new File(zipLocation);
            if (f.exists()) f.delete();

            notif.setContentText(getString(R.string.extractfinish))
				.setProgress(0, 0, false);
            notifManager.notify(0, notif.build());
        }
	}
}