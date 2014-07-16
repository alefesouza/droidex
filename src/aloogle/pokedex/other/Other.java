package aloogle.pokedex.other;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import java.io.File;
import aloogle.pokedex.R;

public class Other {
    public static final String PokemonId = "POKEMON_ID";
    public static final String PokemonImageId = "POKEMON_IMAGE_ID";
    public static final String PokemonName = "POKEMON_NAME";
    public static final int PokemonFilterCode = 90;
    public static final String PokemonFilterGeneration = "PoKEMON_FILTER_GENERATION";
    public static final String PokemonFilterType = "PoKEMON_FILTER_TYPE";
    public static final String PokemonFilterColor = "PoKEMON_FILTER_COLOR";
    public static final String PokemonFilterBaby = "PoKEMON_FILTER_IS_BABY";
    public static final String PokemonFilterGender = "PoKEMON_FILTER_HAS_GENDER_DIFF";

    public static final String ImageLocation =
            Environment.getExternalStorageDirectory().toString() + "/DroiDex/";
    public static final String ArtURL =
            "https://github.com/alefesouza/aloogle-files/releases/download/1.0/Art.zip";
    public static final String SpritesURL =
            "https://github.com/alefesouza/aloogle-files/releases/download/1.0/Sprites.zip";
	public static final String SoundAnimeURL =
	"https://github.com/alefesouza/aloogle-files/releases/download/1.0/SoundAnime.zip";
    public static final String SoundGameURL =
	"https://github.com/alefesouza/aloogle-files/releases/download/1.0/SoundGame.zip";
    public static final String AboutOrChange = "ABOUT_OR_CHANGE";

    public static void setImage(ImageView img, String imgLocation, int defaultImg) {
        String location = Environment.getExternalStorageDirectory().toString() +
                          "/DroiDex/" + imgLocation;
        Bitmap bitmap = BitmapFactory.decodeFile(location);

        if (bitmap == null) img.setImageResource(defaultImg);
        else img.setImageBitmap(bitmap);
    }

    public static void setImage(ImageView img, Bitmap image, int defaultImg) {
        if (image == null) img.setImageResource(defaultImg);
        else img.setImageBitmap(image);
    }

    public static int getTypeColor(int type_id) {
        switch (type_id) {
            case 1  : return Color.parseColor("#999966");
            case 2  : return Color.parseColor("#993333");
            case 3  : return Color.parseColor("#9999cc");
            case 4  : return Color.parseColor("#993399");
            case 5  : return Color.parseColor("#cccc66");
            case 6  : return Color.parseColor("#cc9933");
            case 7  : return Color.parseColor("#99cc33");
            case 8  : return Color.parseColor("#666699");
            case 9  : return Color.parseColor("#b8b8d0");
            case 10 : return Color.parseColor("#ff6600");
            case 11 : return Color.parseColor("#6699ff");
            case 12 : return Color.parseColor("#66cc66");
            case 13 : return Color.parseColor("#ffcc33");
            case 14 : return Color.parseColor("#ff3366");
            case 15 : return Color.parseColor("#66cccc");
            case 16 : return Color.parseColor("#8c33ff");
            case 17 : return Color.parseColor("#41332a");
            case 18 : return Color.parseColor("#ff9999");
            default : return 0;
        }
    }

    public static int getTypeImage(int type_id) {
        switch (type_id) {
            case 1  : return R.drawable.type_normal;
            case 2  : return R.drawable.type_fighting;
            case 3  : return R.drawable.type_flying;
            case 4  : return R.drawable.type_poison;
            case 5  : return R.drawable.type_ground;
            case 6  : return R.drawable.type_rock;
            case 7  : return R.drawable.type_bug;
            case 8  : return R.drawable.type_ghost;
            case 9  : return R.drawable.type_steel;
            case 10 : return R.drawable.type_fire;
            case 11 : return R.drawable.type_water;
            case 12 : return R.drawable.type_grass;
            case 13 : return R.drawable.type_electric;
            case 14 : return R.drawable.type_psychic;
            case 15 : return R.drawable.type_ice;
            case 16 : return R.drawable.type_dragon;
            case 17 : return R.drawable.type_dark;
            case 18 : return R.drawable.type_fairy;
            default : return 0;
        }
    }

    public static int getTypeName(int type_id) {
        switch (type_id) {
            case 1  : return R.string.text_normal;
            case 2  : return R.string.text_fighting;
            case 3  : return R.string.text_flying;
            case 4  : return R.string.text_poison;
            case 5  : return R.string.text_ground;
            case 6  : return R.string.text_rock;
            case 7  : return R.string.text_bug;
            case 8  : return R.string.text_ghost;
            case 9  : return R.string.text_steel;
            case 10 : return R.string.text_fire;
            case 11 : return R.string.text_water;
            case 12 : return R.string.text_grass;
            case 13 : return R.string.text_electric;
            case 14 : return R.string.text_psychic;
            case 15 : return R.string.text_ice;
            case 16 : return R.string.text_dragon;
            case 17 : return R.string.text_dark;
            case 18 : return R.string.text_fairy;
            default : return 0;
        }
    }

    public interface pokemonInterface {
        void pokemonSelected(String id);
        void formSelected(String id, String img_id, String name, boolean formSwitchable);
    }

    public static long startDownload(DownloadManager mgr, String url, String title, String desc, String target) {
        File f = new File(Environment.getExternalStoragePublicDirectory("DroiDex"),target);
        if (f.exists()) f.delete();

        Uri uri = Uri.parse(url);
        Environment .getExternalStoragePublicDirectory("DroiDex") .mkdirs();

        return mgr.enqueue(new DownloadManager.Request(uri)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                        DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(title)
                .setDescription(desc)
                .setDestinationInExternalPublicDir("DroiDex", target));
    }
}