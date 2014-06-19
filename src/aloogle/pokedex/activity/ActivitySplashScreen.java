package aloogle.pokedex.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import aloogle.pokedex.R;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ActivitySplashScreen extends Activity {

	private static final int TIME = 3 * 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().hide();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String userColor = preferences.getString("prefColor", "droidexblue");
		if (userColor.equals("red"))
			getWindow().getDecorView().setBackgroundColor(0xffff0000);
		else if (userColor.equals("green"))
			getWindow().getDecorView().setBackgroundColor(0xff00ff00);
		else if (userColor.equals("blue"))
			getWindow().getDecorView().setBackgroundColor(0xff0000ff);
		else if (userColor.equals("yellow"))
			getWindow().getDecorView().setBackgroundColor(0xffffff00);
		else if (userColor.equals("gold"))
			getWindow().getDecorView().setBackgroundColor(0xffdaa520);
		else if (userColor.equals("silver"))
			getWindow().getDecorView().setBackgroundColor(0xffc0c0c0);
		else if (userColor.equals("crystal"))
			getWindow().getDecorView().setBackgroundColor(0xffa1e2ff);
		else if (userColor.equals("ruby"))
			getWindow().getDecorView().setBackgroundColor(0xffe0115f);
		else if (userColor.equals("sapphire"))
			getWindow().getDecorView().setBackgroundColor(0xff0f52ba);
		else if (userColor.equals("emerald"))
			getWindow().getDecorView().setBackgroundColor(0xff50c878);
		else if (userColor.equals("diamond"))
			getWindow().getDecorView().setBackgroundColor(0xffb9f2ff);
		else if (userColor.equals("pearl"))
			getWindow().getDecorView().setBackgroundColor(0xffeae0c8);
		else if (userColor.equals("platinum"))
			getWindow().getDecorView().setBackgroundColor(0xffe5e4e2);
		else if (userColor.equals("black"))
			getWindow().getDecorView().setBackgroundColor(0xff000000);
		else if (userColor.equals("droidexblue"))
			getWindow().getDecorView().setBackgroundColor(0xff0080ff);
		else if (userColor.equals("dexdroidred"))
			getWindow().getDecorView().setBackgroundColor(0xffff4444);

		String userIcon = preferences.getString("prefIcon", "blue");
		if (userIcon.equals("red"))
			getActionBar().setIcon(R.drawable.ic_itemdex);
		else if (userIcon.equals("green"))
			getActionBar().setIcon(R.drawable.ic_abilitydex);
		else if (userIcon.equals("blue"))
			getActionBar().setIcon(R.drawable.ic_pokedex);
		else if (userIcon.equals("yellow"))
			getActionBar().setIcon(R.drawable.ic_movedex);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(ActivitySplashScreen.this,
						ActivityMain.class);
				startActivity(intent);

				ActivitySplashScreen.this.finish();

				overridePendingTransition(R.anim.bottom_in, R.anim.top_out);

			}
		}, TIME);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {}
		}, TIME);

	}

	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
}
