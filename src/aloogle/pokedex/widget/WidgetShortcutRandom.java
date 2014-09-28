package aloogle.pokedex.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.RemoteViews;
import android.widget.Toast;
import java.io.File;
import java.util.Random;
import aloogle.pokedex.R;

public class WidgetShortcutRandom extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[]appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			File art = new File(Environment.getExternalStorageDirectory() + "/DroiDex/art/");
			if (art.exists()) {
				Random r = new Random();
				int aleatory = r.nextInt(721 - 1 + 1) + 1;
				int aleatory2 = r.nextInt(10185 - 10000 + 1) + 10000;
				int aleatory3 = r.nextInt(2 - 1 + 1) + 1;

				if (aleatory3 == 1) {

					Bitmap bit = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/DroiDex/art/sa_" + aleatory + ".png");

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
				} else {
					Bitmap bit = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/DroiDex/art/sa_" + aleatory2 + ".png");

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
			} else {
				Toast toast = Toast.makeText(context, R.string.widgetshortcutrandomwarning, Toast.LENGTH_LONG);
				toast.show();
			}
		}
	}
}
