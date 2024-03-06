package com.example.less_135_loader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.ContentObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    final String LOG_TAG = "myLogs";
    static final int LOADER_TIME_ID = 1;

    TextView tvTime;
    RadioGroup rgTimeFormat;
    static int lastCheckedId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = findViewById(R.id.tvTime);
        rgTimeFormat = findViewById(R.id.rgTimeFormat);

        findViewById(R.id.btnGetTime).setOnClickListener(v -> getTimeClick());
        findViewById(R.id.btnObserver).setOnClickListener(this::observerClick);

        Bundle bundle = new Bundle();
        bundle.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
        getLoaderManager().initLoader(LOADER_TIME_ID, bundle, this);
        lastCheckedId = rgTimeFormat.getCheckedRadioButtonId();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        if (id == LOADER_TIME_ID) {
            loader = new TimeAsyncLoader(this, args);
            Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d(LOG_TAG, "onLoadFinished for loader " +
                loader.hashCode() + ", result = " + data);
        tvTime.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(LOG_TAG, "onLoaderReset for loader " +
                loader.hashCode());
    }
    public void getTimeClick () {
        Loader<String> loader;
        int id = rgTimeFormat.getCheckedRadioButtonId();
        if (id == lastCheckedId) {
            loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
            loader = getLoaderManager().restartLoader(LOADER_TIME_ID, bundle, this);
            lastCheckedId = id;
        }
        loader.forceLoad();
    }
    String getTimeFormat() {
        String result = TimeLoader.TIME_FORMAT_SHORT;
        int rgBtnId = rgTimeFormat.getCheckedRadioButtonId();
        if (rgBtnId == R.id.rdShort) {
            result = TimeLoader.TIME_FORMAT_SHORT;
        }
        if (rgBtnId == R.id.rdLong) {
            result = TimeLoader.TIME_FORMAT_LONG;
        }
        return result;
    }
    public void observerClick (View v) {
        Log.d(LOG_TAG, "observerClick");
        Loader<String> loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        final ContentObserver observer = loader.new ForceLoadContentObserver();
        v.postDelayed(() -> observer.dispatchChange(false), 5000);
    }
}