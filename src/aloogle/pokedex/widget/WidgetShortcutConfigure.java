package aloogle.pokedex.widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;
import java.io.File;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Other;

public class WidgetShortcutConfigure extends Activity {

	private WidgetShortcutConfigure context;
	private int widgetID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_shortcut_configure);
		setResult(RESULT_CANCELED);
		context = this;

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}

		Other.setTranslucentStatus(false, this, false);

		final AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
		final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_shortcut);

		final EditText et = (EditText)findViewById(R.id.editText1);
		Button b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String idpk = et.getText().toString();
				File art = new File(Environment.getExternalStorageDirectory() + "/DroiDex/art/sa_" + idpk + ".png");
				if (art.exists()) {
					Bitmap bit = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/DroiDex/art/sa_" + idpk + ".png");
					views.setImageViewBitmap(R.id.imageWidget1, bit);
					Intent intent = new Intent(WidgetShortcutConfigure.this, aloogle.pokedex.activity.ActivitySplashScreen.class);
					PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);

					views.setOnClickPendingIntent(R.id.imageWidget1, pending);

					Intent resultValue = new Intent();
					resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
					setResult(RESULT_OK, resultValue);

					SharedPreferences prefs =
						PreferenceManager.getDefaultSharedPreferences(context);
					Editor editor = prefs.edit();
					editor.putString(String.valueOf(widgetID), idpk);
					editor.commit();
					widgetManager.updateAppWidget(widgetID, views);
					finish();
				} else {
					Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.widgetshortcuttoastwarning), Toast.LENGTH_LONG);
					toast.show();
				}
			}
		});
		setTitle(getString(R.string.addpokemon));
	}
}
