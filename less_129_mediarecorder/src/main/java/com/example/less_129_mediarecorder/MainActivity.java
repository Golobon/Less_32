package com.example.less_129_mediarecorder;

import static android.content.pm.PackageManager.FLAG_PERMISSION_WHITELIST_INSTALLER;
import static android.content.pm.PackageManager.FLAG_PERMISSION_WHITELIST_SYSTEM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.security.PrivateKey;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final static String LOG_TAG = "myLogs";
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String fileName;
    private Button btnStartRec, btnStopRec,
    btnStartPlay, btnStopPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileName = getExternalCacheDir().getPath() + "/record.3gpp";
        Log.d(LOG_TAG, fileName);

        btnStartRec = findViewById(R.id.btn_start_rec_1);
        btnStartRec.setOnClickListener(this);

        btnStopRec = findViewById(R.id.btn_stop_rec_1);
        btnStopRec.setOnClickListener(this);
        btnStopRec.setEnabled(false);

        btnStartPlay = findViewById(R.id.btn_start_play_2);
        btnStartPlay.setOnClickListener(this);

        btnStopPlay = findViewById(R.id.btn_stop_play_2);
        btnStopPlay.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(getBaseContext(),
                android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.RECORD_AUDIO},
                    1);
        }
    }
    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, fileName);
        if (v.equals(btnStartRec)) recordStart();
        if (v.equals(btnStopRec)) recordStop();
        if (v.equals(btnStartPlay)) playStart();
        if (v.equals(btnStopPlay)) playStop();
    }
    public void recordStart() {
        btnStopRec.setEnabled(true);
        btnStartRec.setEnabled(false);
        try {
            releaseRecorder();

            File outFile = new File(fileName);
            if (outFile.exists()) {
                Log.d(LOG_TAG, "Файл удален: " + outFile.delete());
            }
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_DOWNLINK);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setAudioEncodingBitRate(16);
            mediaRecorder.setAudioSamplingRate(44100);
            mediaRecorder.setOutputFile(fileName);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            Log.d(LOG_TAG, "error");
            e.printStackTrace();
        }
    }
    public void recordStop() {
        btnStopRec.setEnabled(false);
        btnStartRec.setEnabled(true);
        try {
            if (mediaRecorder != null)
                mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        } catch (IllegalStateException e){
            e.printStackTrace();
        }

    }
    public void playStart() {
        try {
            releasePlayer();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playStop() {
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }
    public void releaseRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
    public void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
        releaseRecorder();
    }
}