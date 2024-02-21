package com.example.less_121_listwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyProvider extends AppWidgetProvider {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    final String ACTION_ON_CLICK = "com.example.less_121_listwidget.itemonclick";
    final static String ITEM_POSITION = "item_position";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }
    }
    private void updateWidget(Context ctx, AppWidgetManager appWidgetManager,
        int appWidgetId) {
        RemoteViews rv = new RemoteViews(ctx.getPackageName(),
                R.layout.widget);
        setUpdateTV(rv, ctx, appWidgetId);
        setList(rv, ctx, appWidgetId);
        setListClick(rv, ctx, appWidgetId);
        appWidgetManager.updateAppWidget(appWidgetId, rv);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,
            R.id.lv_list);
    }
    private void setUpdateTV(RemoteViews rv, Context ctx, int appWidgetId) {
        rv.setTextViewText(R.id.tv_update, sdf.format(new Date(System.currentTimeMillis())));
        Intent updIntent = new Intent(ctx, MyProvider.class);
        updIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                new int[]{ appWidgetId });
        PendingIntent updPIntent = PendingIntent.getBroadcast(ctx,
                appWidgetId, updIntent, 0);
        rv.setOnClickPendingIntent(R.id.tv_update, updPIntent);
    }
    private void setList(RemoteViews rv, Context ctx, int appWidgetId) {
        Intent adapter = new Intent(ctx, MyService.class);
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        Uri data = Uri.parse(adapter.toUri(Intent.URI_INTENT_SCHEME));
        adapter.setData(data);
        rv.setRemoteAdapter(R.id.lv_list, adapter);
    }
    private void setListClick(RemoteViews rv, Context ctx, int appWidgetId) {
        Intent listClickIntent = new Intent(ctx, MyProvider.class);
        listClickIntent.setAction(ACTION_ON_CLICK);
        PendingIntent listClickPIntent = PendingIntent.getBroadcast(ctx, 0,
                listClickIntent, 0);
        rv.setPendingIntentTemplate(R.id.lv_list, listClickPIntent);
    }
    @Override
    public void onReceive(Context ctx, Intent intent) {
        super.onReceive(ctx, intent);
        if (intent.getAction().equalsIgnoreCase(ACTION_ON_CLICK)) {
            int itemPos = intent.getIntExtra(ITEM_POSITION, -1);
            if (itemPos != -1) {
                Toast.makeText(ctx, "Clicked on item " + itemPos,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
