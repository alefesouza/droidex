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
		//version 5.5.5 launched 29 June 2014 fix widget dont load on system restart, allow widget resize and add a aleatory pokemon widget, too allow watch pokemon in fullscreen on DroiDex News
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x version 5.5</h3>\n" +
		"Launched in 23 June 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Now you can put widgets of ALL Pok&eacute;mons in your home screen.</li>\n" +
		"<li>DroiD&eacute;x News.</li>\n" +
		"<li>New app icon.</li>\n" +
		"<li>In version 5.1.5 were added option of deactivate splash screen and change animation.</li>\n" +
		"<li>And some others improvements.</li>\n" +
		"</ul>\n" +
		//version 5.1.5 launched in 21 June 2014 update pokemon data and added a option of deactivate splash screen or change animation
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x version 5.1</h3>\n" +
		"Launched in 19 June 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Added settings page, with change color and action bar icon option.</li>\n" +
		"<li>Added \"Open on Bulbapedia\" and Serebii on Pok&eacute;mon details.</li>\n" +
		"<li>Portuguese version.</li>\n" +
		"</ul>\n" +
		//version 5.0.1 launched in 17 June 2014 fix some names and add a share function
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x version 5.0</h3>\n" +
		"Launched in 15 June 2014\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Newest Pok&eacute;mons announced and unannounced officially  of 6th generation, and announced for Omega Ruby/Alpha Sapphire until 15 June 2014.</li>\n" +
		"<li>Added all description of 6th moves.</li>\n" +
		"<li>Added all 6th generation sprites.</li>\n" +
		"<li>And some others improvements.</li>\n" +
		"</ul>\n" +
		"<p style=\"text-align: justify;\">I love D&eacute;xDroid but it was removed from Play Store and didn't receive any updates, but this is an open source (the source is on GitHub for everyone), and I decided to create the DroiD&eacute;x, is only the original D&eacute;xDroid with most recent Pok&eacute;mon announcements.</p>\n";
	private final static String about = "" +
		"<h3 style=\"text-align: justify;\">DroiD&eacute;x</h3>\n" +
		"<p style=\"text-align: justify;\">DroiD&eacute;x is a modified version of D&eacute;xDroid, developed by <a href=\"http://google.com/+AlefeSouza/about \">Alefe Souza</a>.</p>\n" +
		"<p style=\"text-align: justify;\">I noticed that the D&eacute;xDroid was removed from the Play Store and didn't receive any updates, I did not like this because I loved D&eacute;xDroid and always indicated to my friends, then I decided create DroiD&eacute;x, a modified version of D&eacute;xDroid 3.1 with most recent Pok&eacute;mon updates.</p>\n" +
		"<p style=\"text-align: justify;\">Thanks to:</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li><a href=\"http://pkparaiso.com/\">PkPara&iacute;so</a>, for all the sprites of 6th generation.</li>\n" +
		"<li><a href=\"http://deviantart.com/ \">DeviantArt</a>, for Sugimori art and sprites of most recent Pok&eacute;mons announced and unannounced officially.</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Source code</h3>\n" +
		"<p style=\"text-align: justify;\">Please notice the \"License\" section of this page, it remains the same of D&eacute;xDroid, you can see the source code of DroiD&eacute;x in my <a href=\"https://github.com/alefesouza/droidex \">Github</a>. If you intend to use any DroiDéx code (such as widgets and settings, or anything that is not included in the original app), credit me too.</p>\n" +
		"<hr>\n" +
		"<h3 style=\"text-align: justify;\">D&eacute;xDroid 3.1 about</h3>\n" +
		"<p style=\"text-align: justify;\">D&eacute;xDroid is Pok&eacute;dex (Pok&eacute;mon encyclopedia) for Android. It contains data of all Pok&eacute;mon species from every series of Pok&eacute;mon game (Gen I to Gen VI). Every Pok&eacute;mon's data is consisted by :</p>\n" +
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
		"<h3 style=\"text-align: justify;\">License</h3>\n" +
		"<p style=\"text-align: justify;\">This app is released under <a href=\"http://choosealicense.com/licenses/gpl-v3/\">GPLv3 License</a> and its source code is available in my <a href=\"https://github.com/Acrophobic/Pokedex\">Github</a>. Everyone is allowed to modify this app and release it in their own name, but they have to open the source code. And, if you would, please give proper credit to me.</p>\n" +
		"<h3 style=\"text-align: justify;\">Sources</h3>\n" +
		"<p style=\"text-align: justify;\">Database is taken from Veekun's git (<a href=\"http://git.veekun.com/pokedex.git/tree/HEAD:/pokedex/data/csv\">here</a> or <a href=\"https://github.com/veekun/pokedex/tree/master/pokedex/data/csv\">here</a>). I just convert it from CSV to SQLITE.</p>\n" +
		"<p style=\"text-align: justify;\">Images and sprites are taken from :</p>\n" +
		"<ul style=\"text-align: justify;\">\n" +
		"<li>Veekun's <a href=\"http://git.veekun.com/pokedex-media.git\">git</a> and his <a href=\"http://veekun.com/dex/downloads\">site</a></li>\n" +
		"<li><a href=\"http://bulbapedia.bulbagarden.net\">Bulbapedia</a>, the community driven Pok&eacute;mon encyclopedia</li>\n" +
		"<li><a href=\"http://www.legendarypokemon.net/\">LegendaryPokemon</a>, where I took some of Sugimori Art</li>\n" +
		"<li><a href=\"http://www.serebii.net\">Serebii</a>, where I took some sprites for Pok&eacute;mon X/Y and Mega Evolution</li>\n" +
		"</ul>\n" +
		"<h3 style=\"text-align: justify;\">Acknowledgements</h3>\n" +
		"<p style=\"text-align: justify;\">In this app, I use following library / tools :</p>\n" +
		"<ul>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/jgilfelt/android-sqlite-asset-helper\">Android SQLiteAssetHelper</a> library by <a href=\"https://github.com/jgilfelt\">Jeff Gilfelt</a></li>\n" +
		"<li style=\"text-align: justify;\"><a href=\"https://github.com/jeromevdl/android-holo-colors-idea-plugin\">Android Holo Colors Plugin</a>&nbsp;by <a href=\"https://github.com/jeromevdl\"><span class=\"vcard-fullname\">J&eacute;r&ocirc;me Van Der Linden</span></a></li>\n" +
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
