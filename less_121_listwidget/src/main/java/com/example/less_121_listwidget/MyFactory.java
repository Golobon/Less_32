package com.example.less_121_listwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.Date;

public class MyFactory implements RemoteViewsService.RemoteViewsFactory {
    ArrayList<String> data;
    Context context;
    SimpleDateFormat sdf;
    int widgetID;

    public MyFactory(Context ctx, Intent intent) {
        context = ctx;
        sdf = new SimpleDateFormat("HH:mm:ss");
        widgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        data = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rView = new RemoteViews(context.getPackageName(),
                R.layout.item);
        rView.setTextViewText(R.id.tvItemText, data.get(position));
        Intent clickIntent = new Intent();
        clickIntent.putExtra(MyProvider.ITEM_POSITION, position);
        rView.setOnClickFillInIntent(R.id.tvItemText, clickIntent);
        return rView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onDataSetChanged() {
        data.clear();
        data.add(sdf.format(new Date(System.currentTimeMillis())));
        data.add(String.valueOf(hashCode()));
        data.add(String.valueOf(widgetID));
        for (int i = 3; i < 15; i++) {
            data.add("item " + i);
        }
    }

    @Override
    public void onDestroy() {

    }
}
