package aloogle.pokedex.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Other;

public class ActivityAboutChangelog extends Activity {
	private final static String changelog = "" +
		"<h3 style=\"text-align: justify;\">Version 5.5</h3>\n" +
		"Launched June, 23, 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Place widgets of ALL Pok&eacute;mom on your homescreen.</li>\n" +
		"<li>DroiD&eacute;x News.</li>\n" +
		"<li>New app icon.</li>\n" +
		"<li>And some others improvements.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.5.5:</b> Launched June, 29, 2014 - Fixed widgets not loading after restarting the device, widget resizing and random Pok&eacute;mon widget, also watching Pok&eacute;mon in fullscreen on DroiD&eacute;x News.</p>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.5.7:</b> Launched July, 09, 2014 - Added open in Smogon option, new sidebar in smartphones for a app menu more organized and fixed some bugs.</p>\n" +
		"<h3 style=\"text-align: justify;\">Version 5.1</h3>\n" +
		"Launched June, 19, 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Settings page added, with app color and icon changing options.</li>\n" +
		"<li>\"Open on Bulbapedia\" and Serebii added in Pok&eacute;mon details.</li>\n" +
		"<li>Portuguese version.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.1.5:</b> Launched June, 21, 2014 - Pok&eacute;mon data updated, splash screen deactivation option and its animation type changing option added.</p>\n" +
		"<h3 style=\"text-align: justify;\">Version 5.0</h3>\n" +
		"Launched June, 15, 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Newest Pok&eacute;mons announced and unannounced officially from 6th generation, and announced for Omega Ruby/Alpha Sapphire until June, 15, 2014.</li>\n" +
		"<li>Added all 6th moves description.</li>\n" +
		"<li>Added all 6th generation sprites.</li>\n" +
		"<li>And some others improvements.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\"><b>Version 5.0.1:</b> Launched June, 17, 2014 - Fixed some names and added a share the app option.</p>\n" +
		"<p style=\"text-align: justify;\">This was the first version totally based on DéxDroid 3.1, I realized that it wouldn't be updated anymore so I decided updating the app by myself, since I liked too much DéxDroid.</p>\n";
	private final static String about = "" +
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x</h3>\n" +
		"<p style=\"text-align: justify;\">DroiD&eacute;x is a Pok&eacute;dex app based on D&eacute;xDroid, developed by <a href=\"http://google.com/+AlefeSouza/about \">Alefe Souza</a>.</p>\n" +
		"<p style=\"text-align: justify;\">I noticed that the D&eacute;xDroid wouldn't receive updates anymore, I did not like this because I loved D&eacute;xDroid and always indicated to my friends, then I decided create DroiD&eacute;x, a new app based on D&eacute;xDroid 3.1 with most recent Pok&eacute;mon updates and some others functions.</p>\n" +
		"<p style=\"text-align: justify;\">Since is based on D&eacute;xDroid, it has the same functions, as data of all Pok&eacute;mon species from every series of Pok&eacute;mon game (Gen I to Gen VI), every Pok&eacute;mon's data is consisted by:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Image (Sugimori Art and sprites)</li>\n" +
		"<li>Name (English, Japanese, and Romaji name)</li>\n" +
		"<li>Dex number for all region</li>\n" +
		"<li>Description (from all Pok&eacute;mon version)</li>\n" +
		"<li>Height</li>\n" +
		"<li>Weight</li>\n" +
		"<li>Ability</li>\n" +
		"<li>Type efficacy</li>\n" +
		"<li>Base stat</li>\n" +
		"<li>Move (separated by version group)</li>\n" +
		"<li>Location (separated by Pok&eacute;mon version)</li>\n" +
		"<li>Evolution (including Mega Evolution)</li>\n" +
		"<li>Etc.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\">Compared to the D&eacute;xDroid, the major differences are a settings page to change the app theme color and the function to add widgets of ANY Pok&eacute;mon onto your home screen.</p>\n" +
		"<h3 style=\"text-align: justify;\">License</h3>\n" +
		"<p style=\"text-align: justify;\">This app is released under <a href=\"http://choosealicense.com/licenses/gpl-v3/\">GPLv3 License</a> and its source code is available in my <a href=\"https://github.com/alefesouza/droidex\">Github</a>. Everyone is allowed to modify this app and release it in their own name, but they have to open the source code. And, if you would, please give proper credit to me, <a href=\"https://github.com/alefesouza\">Alefe Souza</a>, and the developer of D&eacute;xDroid, <a href=\"https://github.com/RadhiFadlillah\">Radhi</a>.</p>\n" +
		"<h3 style=\"text-align: justify;\">Sources</h3>\n" +
		"<p style=\"text-align: justify;\">Database is taken from Veekun's git (<a href=\"http://git.veekun.com/pokedex.git/tree/HEAD:/pokedex/data/csv\">here</a> or <a href=\"https://github.com/veekun/pokedex/tree/master/pokedex/data/csv\">here</a>). Were just converted it from CSV to SQLITE.</p>\n" +
		"<p style=\"text-align: justify;\">Images and sprites are taken from:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Veekun's <a href=\"http://git.veekun.com/pokedex-media.git\">git</a> and his <a href=\"http://veekun.com/dex/downloads\">site</a>.</li>\n" +
		"<li><a href=\"http://bulbapedia.bulbagarden.net\">Bulbapedia</a>, the community driven Pok&eacute;mon encyclopedia.</li>\n" +
		"<li><a href=\"http://www.legendarypokemon.net/\">LegendaryPokemon</a>, for some Sugimori Art.</li>\n" +
		"<li><a href=\"http://www.serebii.net\">Serebii</a>, for some sprites for Pok&eacute;mon X/Y and Mega Evolution.</li>\n" +
		"<li><a href=\"http://pkparaiso.com/\">PkPara&iacute;so</a>, for all the sprites of 6th generation.</li>\n" +
		"<li><a href=\"http://deviantart.com/ \">DeviantArt</a>, for all Sugimori Art and sprites of most recent Pok&eacute;mons announced and unannounced officially.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Acknowledgements</h3>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/RadhiFadlillah\">Radhi</a>, developer of D&eacute;xDroid.</li>\n" +
		"<p style=\"text-align: justify;\">In this app, were used following library/tools:</p>\n" +
		"<ul>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/jgilfelt/android-sqlite-asset-helper\">Android SQLiteAssetHelper</a> library by <a href=\"https://github.com/jgilfelt\">Jeff Gilfelt</a>.</li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/jeromevdl/android-holo-colors-idea-plugin\">Android Holo Colors Plugin</a>&nbsp;by <a href=\"https://github.com/jeromevdl\"><span class=\"vcard-fullname\">J&eacute;r&ocirc;me Van Der Linden</span></a>.</li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://code.google.com/p/giflib/\">Animated GIF ImageView Library for Android</a> by <a href=\"http://abhinavasblog.blogspot.com.br/2014/04/animated-gif-imageview-library-for.html\"><span class=\"vcard-fullname\">Abhinava Srivastava</span></a>.</li>\n" +
		"</ul>";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changelog_about);
		getWindow().setBackgroundDrawable(null);
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

		TextView txtOK = (TextView)findViewById(R.id.txtOkChngAbout);
		WebView viewChangelog = (WebView)findViewById(R.id.viewAboutChangelog);

		int AboutOrChange = getIntent().getIntExtra(Other.AboutOrChange, 0);

		if (AboutOrChange == 0) {
			setTitle("Changelog");
			viewChangelog.loadData(changelog, "text/html", "UTF-8");
		} else {
			setTitle("About");
			viewChangelog.loadData(about, "text/html", "UTF-8");
		}

		txtOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
