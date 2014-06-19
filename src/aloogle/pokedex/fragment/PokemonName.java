package aloogle.pokedex.fragment;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import aloogle.pokedex.R;
import aloogle.pokedex.activity.ActivityPokemonFilter;
import aloogle.pokedex.adapter.ListNameAdapter;
import aloogle.pokedex.other.Database;
import aloogle.pokedex.other.Other;
import aloogle.pokedex.other.Other.pokemonInterface;
import aloogle.pokedex.other.ZipHelper;

import java.io.File;

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
        searchView.setQueryHint("Pokemon's name");

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_art:
                Other.startDownload(mgr,Other.ArtURL, "Sugimori Art",getString(R.string.downloadwarning), "Art.zip");
                byteBuffer = 16 * 1024;
                zipName = "Art.zip";
                return true;
            case R.id.menu_sprite:
                Other.startDownload(mgr,Other.SpritesURL,"Pokémon Sprites",getString(R.string.downloadwarning),"Sprites.zip");
                byteBuffer = 3 * 1024;
                zipName = "Sprites.zip";
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
            File f = new File(unzipTarget);
            if (f.exists()) f.delete();

            UnzipFile unzip = new UnzipFile();
            String zipLocation = unzipTarget + zipName;
            unzip.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new ZipHelper(zipLocation, unzipTarget));
        }
    };

    public class UnzipFile extends AsyncTask<ZipHelper,Void,Void> {
        String zipLocation;
        NotificationCompat.Builder notif;

        @Override
        protected void onPreExecute() {
            notif = new NotificationCompat.Builder(activity);
            notif.setContentTitle("Extracting " + zipName)
                    .setContentText("Extracting data")
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

            notif.setContentText("Extracting finished")
                    .setProgress(0, 0, false);
            notifManager.notify(0, notif.build());
        }
    }
}
