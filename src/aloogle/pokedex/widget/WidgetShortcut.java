package aloogle.pokedex.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import aloogle.pokedex.R;

public class WidgetShortcut extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[]appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];

			final Intent intent = new Intent(Intent.ACTION_MAIN, null);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			final ComponentName cn = new ComponentName("aloogle.pokedex", "aloogle.pokedex.activity.ActivitySplashScreen");
			intent.setComponent(cn);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_shortcut);

			PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
			views.setOnClickPendingIntent(R.id.imageView1, pending);

			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
}
