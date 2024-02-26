package com.example.less_126_mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        View.OnClickListener {
    final String LOG_TAG = "myLogs";
    final String DATA_HTTP = "http://www.botik.ru/~duzhin/PESNI/Mokrousov/moreshum/abramov-more_shumit.mp3";
    final String DATA_STREAM = "http://online.radiorecord.ru:8101/rr_128";
    final String DATA_SD = Environment.
            getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) +
            "/music.mp3";
    final Uri DATA_URI = ContentUris
            .withAppendedId(
                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    13359);
    MediaPlayer mediaPlayer;
    AudioManager am;
    CheckBox chbLoop;
    Button btnHTTP, btnStream, btnSD, btnUri, btnRaw,
            btnPause, btnResume, btnStop,
            btnBack, btnForw, btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHTTP = findViewById(R.id.btn_http);
        btnHTTP.setOnClickListener(this);
        btnStream = findViewById(R.id.btn_stream);
        btnStream.setOnClickListener(this);
        btnSD = findViewById(R.id.btn_sd);
        btnSD.setOnClickListener(this);
        btnUri = findViewById(R.id.btn_uri);
        btnUri.setOnClickListener(this);
        btnRaw = findViewById(R.id.btn_raw);
        btnRaw.setOnClickListener(this);
        btnPause = findViewById(R.id.btn_pause);
        btnPause.setOnClickListener(this);
        btnResume = findViewById(R.id.btn_resume);
        btnResume.setOnClickListener(this);
        btnStop = findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(this);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        btnForw = findViewById(R.id.btn_foward);
        btnForw.setOnClickListener(this);
        btnInfo = findViewById(R.id.btn_info);
        btnInfo.setOnClickListener(this);

        am = (AudioManager) getSystemService(AUDIO_SERVICE);

        chbLoop = (CheckBox) findViewById(R.id.chb_loop);
        chbLoop.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (mediaPlayer != null) mediaPlayer.setLooping(isChecked);
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnHTTP) || v.equals(btnStream) ||
                v.equals(btnSD) || v.equals(btnUri) || v.equals(btnRaw)) {
            onClickStart(v);
        } else onClickBtnControl(v);
    }

    public void onClickStart(View v) {
        releaseMP();
        try {
            if (v.equals(btnHTTP)) {
                Log.d(LOG_TAG, "start HTTP");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(DATA_HTTP);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Log.d(LOG_TAG, "prepareAsync");
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();
            } else if (v.equals(btnStream)) {
                Log.d(LOG_TAG, "start Stream");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(DATA_STREAM);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Log.d(LOG_TAG, "prepareAsync");
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();
            } else if (v.equals(btnSD)) {
                Log.d(LOG_TAG, "start SD");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(DATA_SD);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } else if (v.equals(btnUri)) {
                Log.d(LOG_TAG, "start Uri");
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(this, DATA_URI);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } else if (v.equals(btnRaw)) {
                Log.d(LOG_TAG, "start Raw");
                mediaPlayer = MediaPlayer.create(this, null);
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mediaPlayer == null) return;
        mediaPlayer.setLooping(chbLoop.isChecked());
        mediaPlayer.setOnCompletionListener(this);
    }

    public void onClickBtnControl(View v) {
        if (mediaPlayer == null) return;

        if (v.equals(btnPause)) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.pause();
        } else if (v.equals(btnResume)) {
            if (!mediaPlayer.isPlaying())
                mediaPlayer.start();
        } else if (v.equals(btnStop)) {
            mediaPlayer.stop();
        } else if (v.equals(btnBack)) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);
        } else if (v.equals(btnForw)) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
        } else if (v.equals(btnInfo)) {
            Log.d(LOG_TAG, "Playing " + mediaPlayer.isPlaying());
            Log.d(LOG_TAG, "Time " + mediaPlayer.getCurrentPosition() + " / "
                    + mediaPlayer.getDuration());
            Log.d(LOG_TAG, "Looping " + mediaPlayer.isLooping());
            Log.d(LOG_TAG,
                    "Volume " + am.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(LOG_TAG, "onPrepared");
        mp.start();
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(LOG_TAG, "onCompletion");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }
}