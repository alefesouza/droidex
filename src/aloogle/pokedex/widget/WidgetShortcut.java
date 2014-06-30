package aloogle.pokedex.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import aloogle.pokedex.R;

public class WidgetShortcut extends AppWidgetProvider {
	@Override
	public void onDeleted(Context context, int[]appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			SharedPreferences prefs =
				PreferenceManager.getDefaultSharedPreferences(context);
			Editor editor = prefs.edit();
			editor.remove(String.valueOf(appWidgetId));
			editor.commit();
		}
	}
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[]appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
			String pokeID = preferences.getString(String.valueOf(appWidgetId), "missingno");
			if (pokeID.equals("missingno")) {
				RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_shortcut);

				views.setImageViewResource(R.id.imageWidget1, R.drawable.unknown_large);
				appWidgetManager.updateAppWidget(appWidgetId, views);
			} else {
				Bitmap bit = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/DroiDex/art/sa_" + pokeID + ".png");

				final Intent intent = new Intent(Intent.ACTION_MAIN, null);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				final ComponentName cn = new ComponentName("aloogle.pokedex", "aloogle.pokedex.activity.ActivitySplashScreen");
				intent.setComponent(cn);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_shortcut);

				PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
				views.setOnClickPendingIntent(R.id.imageWidget1, pending);
				views.setImageViewBitmap(R.id.imageWidget1, bit);

				appWidgetManager.updateAppWidget(appWidgetId, views);
			}
		}
	}
}
