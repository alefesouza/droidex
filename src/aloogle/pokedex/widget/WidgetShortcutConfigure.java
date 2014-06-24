package aloogle.pokedex.widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;
import java.io.File;
import aloogle.pokedex.R;

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

		final AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
		final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_shortcut);

		final EditText et = (EditText)findViewById(R.id.editText1);
		Button b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String idpk = et.getText().toString();
				File art = new File(Environment.getExternalStorageDirectory() + "/DroiDex/art/sa_" + idpk + ".png");
				String files = art.toString();
				if (art.exists()) {
					Uri imgUri = Uri.parse(files);
					views.setImageViewUri(R.id.imageView1, imgUri);
					Intent intent = new Intent(WidgetShortcutConfigure.this, aloogle.pokedex.activity.ActivitySplashScreen.class);
					PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);

					views.setOnClickPendingIntent(R.id.imageView1, pending);
					widgetManager.updateAppWidget(widgetID, views);

					Intent resultValue = new Intent();
					resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
					setResult(RESULT_OK, resultValue);
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
