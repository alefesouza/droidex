package aloogle.pokedex.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import aloogle.pokedex.lib.AnimatedGifImageView;
import aloogle.pokedex.lib.AnimatedGifImageView.TYPE;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Other;

public class ActivitySplashScreen extends Activity {

	private static final int TIME = 1500;
	private AnimatedGifImageView animatedGifImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		animatedGifImageView = ((AnimatedGifImageView)findViewById(R.id.animatedGifImageView));
		animatedGifImageView.setAnimatedGif(R.raw.loading, TYPE.FIT_CENTER);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean prefSplash = preferences.getBoolean("prefSplash", true);
		if (prefSplash) {
			String userColor = preferences.getString("prefColor", "droidexblue");
			if (userColor.equals("red")) {
				getWindow().getDecorView().setBackgroundColor(0xffcc0000);
			} else if (userColor.equals("green")) {
				getWindow().getDecorView().setBackgroundColor(0xff00cc00);
			} else if (userColor.equals("blue")) {
				getWindow().getDecorView().setBackgroundColor(0xff0000cc);
			} else if (userColor.equals("yellow")) {
				getWindow().getDecorView().setBackgroundColor(0xffe5e500);
			} else if (userColor.equals("gold")) {
				getWindow().getDecorView().setBackgroundColor(0xffdaa520);
			} else if (userColor.equals("silver")) {
				getWindow().getDecorView().setBackgroundColor(0xffc0c0c0);
			} else if (userColor.equals("crystal")) {
				getWindow().getDecorView().setBackgroundColor(0xffa1e2ff);
			} else if (userColor.equals("ruby")) {
				getWindow().getDecorView().setBackgroundColor(0xffe0115f);
			} else if (userColor.equals("sapphire")) {
				getWindow().getDecorView().setBackgroundColor(0xff0f52ba);
			} else if (userColor.equals("emerald")) {
				getWindow().getDecorView().setBackgroundColor(0xff50c878);
			} else if (userColor.equals("diamond")) {
				getWindow().getDecorView().setBackgroundColor(0xffb9f2ff);
			} else if (userColor.equals("pearl")) {
				getWindow().getDecorView().setBackgroundColor(0xffeae0c8);
			} else if (userColor.equals("platinum")) {
				getWindow().getDecorView().setBackgroundColor(0xffe5e4e2);
			} else if (userColor.equals("black")) {
				getWindow().getDecorView().setBackgroundColor(0xff000000);
			} else if (userColor.equals("white")) {
				getWindow().getDecorView().setBackgroundColor(0xffffffff);
			} else if (userColor.equals("droidexblue")) {
				getWindow().getDecorView().setBackgroundColor(0xff0080ff);
			} else if (userColor.equals("dexdroidred")) {
				getWindow().getDecorView().setBackgroundColor(0xffff4444);
			}

			Other.SystemBarColor(this, false);

			String userAnimation = preferences.getString("prefSplashAnimation", "top");
			if (userAnimation.equals("top")) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
						if (tabletSize) {
							Intent intent = new Intent(ActivitySplashScreen.this, ActivityMainTablet.class);
							startActivity(intent);
						} else {
							Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
							startActivity(intent);
						}

						ActivitySplashScreen.this.finish();
						overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
					}
				}, TIME);
			} else if (userAnimation.equals("left")) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
						if (tabletSize) {
							Intent intent = new Intent(ActivitySplashScreen.this, ActivityMainTablet.class);
							startActivity(intent);
						} else {
							Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
							startActivity(intent);
						}
						ActivitySplashScreen.this.finish();
						overridePendingTransition(R.anim.right_in, R.anim.left_out);
					}
				}, TIME);
			} else if (userAnimation.equals("fade")) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {

						Intent intent = new Intent(ActivitySplashScreen.this,
								ActivityMain.class);
						startActivity(intent);

						ActivitySplashScreen.this.finish();
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					}
				}, TIME);
			} else if (userAnimation.equals("forward")) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
						if (tabletSize) {
							Intent intent = new Intent(ActivitySplashScreen.this, ActivityMainTablet.class);
							startActivity(intent);
						} else {
							Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
							startActivity(intent);
						}
						ActivitySplashScreen.this.finish();
						overridePendingTransition(R.anim.forward_in, R.anim.forward_out);
					}
				}, TIME);
			} else if (userAnimation.equals("forbackward")) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
						if (tabletSize) {
							Intent intent = new Intent(ActivitySplashScreen.this, ActivityMainTablet.class);
							startActivity(intent);
						} else {
							Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
							startActivity(intent);
						}
						ActivitySplashScreen.this.finish();
						overridePendingTransition(R.anim.forbackward_in, R.anim.forbackward_out);
					}
				}, TIME);
			}

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {}
			}, TIME);
		} else {
			boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
			if (tabletSize) {
				Intent intent = new Intent(ActivitySplashScreen.this, ActivityMainTablet.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
				startActivity(intent);
			}
			ActivitySplashScreen.this.finish();
		}
	}
}
