package aloogle.pokedex.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.view.MenuItem;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Other;

public class ActivitySettings extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		addPreferencesFromResource(R.xml.activity_settings);

		Other.ActionBarColor(this);
		Other.ActionBarColorIcons(this, getString(R.string.settings));
		Other.ActionBarIcon(this);
		Other.SystemBarColor(this, true);

		Preference prefColor = findPreference("prefColor");
		prefColor.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Other.ActionBarColor(ActivitySettings.this);
						Other.ActionBarColorIcons(ActivitySettings.this, getString(R.string.settings));
						Other.SystemBarColor(ActivitySettings.this, true);
					}
				}, 100);
				return true;
			}
		});

		Preference prefColorIcons = findPreference("prefColorIcons");
		prefColorIcons.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Other.ActionBarColorIcons(ActivitySettings.this, getString(R.string.settings));
					}
				}, 100);
				return true;
			}
		});

		Preference prefIcon = findPreference("prefIcon");
		prefIcon.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Other.ActionBarIcon(ActivitySettings.this);
					}
				}, 100);
				return true;
			}
		});

		if (Build.VERSION.SDK_INT >= 19) {
			boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
			if (tabletSize) {
				CheckBoxPreference prefSystemBarColor = (CheckBoxPreference)findPreference("prefSystemBarColor");
				PreferenceCategory categoryColor = (PreferenceCategory)findPreference("categoryColor");
				categoryColor.removePreference(prefSystemBarColor);
			} else {
				Preference prefSystemBarColor = findPreference("prefSystemBarColor");
				prefSystemBarColor.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
					@Override
					public boolean onPreferenceChange(Preference preference, Object newValue) {
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								Other.SystemBarColor(ActivitySettings.this, true);
							}
						}, 100);
						return true;
					}
				});
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			ActivitySettings.this.finish();
			return true;
		default:
			return
			super.onOptionsItemSelected(item);
		}
	}
}
