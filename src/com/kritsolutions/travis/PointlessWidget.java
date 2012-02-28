package com.kritsolutions.travis;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

public class PointlessWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Random r = new Random();
		int randomInt = r.nextInt(1000000000);
		String rand = String.valueOf(randomInt);
		
		final int N = appWidgetIds.length;
		for(int i = 0; i < N; i++) {
			int aWidgetId = appWidgetIds[i];
			RemoteViews v = new RemoteViews(context.getPackageName(), R.layout.widget);
			v.setTextViewText(R.id.tvWidgetUpdate, rand);
			appWidgetManager.updateAppWidget(aWidgetId, v);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "See you sucka!", Toast.LENGTH_SHORT).show();
	}

}
