package com.example.less_130_audiorecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final static String LOG_TAG = "myLogs";
    int myBufferedSize = 8192;
    AudioRecord audioRecord;
    boolean isReading = false;
    Button btnStartRec, btnStopRec,
            btnStartRead, btnStopRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartRec = findViewById(R.id.bnt_start_rec);
        btnStartRec.setOnClickListener(this);

        btnStopRec = findViewById(R.id.bnt_stop_rec);
        btnStopRec.setOnClickListener(this);

        btnStartRead = findViewById(R.id.bnt_start_read);
        btnStartRead.setOnClickListener(this);

        btnStopRead = findViewById(R.id.bnt_stop_read);
        btnStopRead.setOnClickListener(this);

        createAudioRecorder();

        Log.d(LOG_TAG, "init state = " + audioRecord.getState());
    }

    private void createAudioRecorder() {
        int sampleRate = 8000;
        int channelConfig = AudioFormat.CHANNEL_IN_MONO;
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;

        int minInternalBufferedSize = AudioRecord.getMinBufferSize(sampleRate,
                channelConfig, audioFormat);
        int internalBufferedSize = minInternalBufferedSize * 4;
        Log.d(LOG_TAG, "minInternalBufferedSize = " + minInternalBufferedSize +
                ", internalBufferedSize = " + internalBufferedSize +
                ", myBufferedSize = " + myBufferedSize);

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.RECORD_AUDIO) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                sampleRate, channelConfig, audioFormat, internalBufferedSize);

        audioRecord.setPositionNotificationPeriod(1000);
        audioRecord.setNotificationMarkerPosition(10000);
        audioRecord.
                setRecordPositionUpdateListener(new AudioRecord.OnRecordPositionUpdateListener() {
                    @Override
                    public void onMarkerReached(AudioRecord recorder) {
                        Log.d(LOG_TAG, "onMarkerReached");
                        isReading = false;
                    }

                    @Override
                    public void onPeriodicNotification(AudioRecord recorder) {
                        Log.d(LOG_TAG, "onPeriodicNotification");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnStartRec)) startRec();
        if (v.equals(btnStopRec)) stopRec();
        if (v.equals(btnStartRead)) startRead();
        if (v.equals(btnStopRead)) stopRead();
    }
    public void startRec() {
        Log.d(LOG_TAG, "record start");
        audioRecord.startRecording();
        int recordingState = audioRecord.getRecordingState();
        Log.d(LOG_TAG, "recordingState = " + recordingState);
    }
    public void stopRec() {
        Log.d(LOG_TAG, "record stop");
        audioRecord.stop();
    }
    public void startRead() {
        Log.d(LOG_TAG, "read stop");
        isReading = true;
        new Thread(() -> {
            if (audioRecord == null) return;

            byte[] myBuffer = new byte[myBufferedSize];
            int readCount;
            int totalCount = 0;

            while (isReading) {
                readCount = audioRecord.read(myBuffer, 0, myBufferedSize);
                totalCount += readCount;
                Log.d(LOG_TAG, "readCount = " + readCount +
                ", totalCount = " + totalCount);
            }
        }).start();
    }
    public void stopRead() {
        Log.d(LOG_TAG, "read stop");
        isReading= false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        isReading = false;
        if (audioRecord != null)
            audioRecord.release();
    }
}