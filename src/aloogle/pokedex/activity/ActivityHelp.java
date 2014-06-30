package aloogle.pokedex.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import aloogle.pokedex.R;

public class ActivityHelp extends Activity {
	private final static String help = "" +
		"<h3 style=\"text-align: justify;\">FAQ</h3>\n" +
		"<p><b>THIS IS NOT END-USER LICENSE AGREEMENT, PLEASE READ IT</b> before asking anything.</p>\n" +
		"<p><b>Why there is no images, only Missingnos?</b></p>\n" +
		"<p>The image data is not included in the application pack to make it lighter, but it is possible to download in-app: tap Menu (the tree little squares on the right top, or if you have a phone wich has a Menu button, press it), after tap \"Download image\".</p>\n" +
		"<p><b>Ok. I did it all, but appeared \"Sugimori art\" and \"Sprite\", wich one is the right one? What\'s the difference between one another?</b></p>\n" +
		"<p>Sprites are the little image that appear in-batte and Sugimori art the big ones, that\'s why they\'re such heavier, notice that if you only download the sprites, big image won\'t appear, and if you only download Sugimori art the tiny ones won\'t appear too, if you want it all working download both.</p>\n" +
		"<p><b>Why shouldn\'t I close the app while download is in progress? </b></p>\n" +
		"<p>The app downloads zipped image files that are extracted in the end of the download. Since there is no way to do anything with an closed app, the app won't extract if closed (unless that boring apps that you never open and are always working).</p>\n" +
		"<p><b>I have all the images but the Missingnos keep appearing</b></p>\n" +
		"<p>Normally, recently announced and unannounced officially Pok&eacute;mon don't have it.</p>\n" +
		"<p><b>How can I put Pok&eacute;mon in my homescreen?</b></p>\n" +
		"<p>First, download the Sugimori art, after go to the app drawer of your device and tap Widgets, and press \"Add Pok&eacute;mon\" (if there are no Widgets in this menu, press for a while an empty spot in your homescreen and then tap Widgets).</p>" +
		"<p>If some doubt still persists, use the option \"Send feedback\" in-app.</p>\n";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changelog_about);
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

		TextView txtOK = (TextView)findViewById(R.id.txtOkChngAbout);
		WebView viewChangelog = (WebView)findViewById(R.id.viewAboutChangelog);

		setTitle("Ajuda");
		viewChangelog.loadData(help, "text/html", "UTF-8");

		txtOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ActivityHelp.this, ActivityMain.class);
				startActivity(intent);
				finish();
			}
		});

	}
}
