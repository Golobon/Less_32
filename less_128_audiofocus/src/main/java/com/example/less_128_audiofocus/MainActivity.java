package com.example.less_128_audiofocus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements MediaPlayer.OnCompletionListener, View.OnClickListener {
    final static String LOG_TAG = "myLogs";
    AudioManager audioManager;
    AFListener afListenerMusic;
    AFListener afListenerSound;
    MediaPlayer mpMusic;
    MediaPlayer mpSound;
    Button btnPlayMusic,
           btnPlaySoundG, btnPlaySoundGT, btnPlaySoundGTD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        btnPlayMusic = findViewById(R.id.btn_play_music);
        btnPlayMusic.setOnClickListener(this);
        btnPlaySoundG = findViewById(R.id.btn_play_sound_g);
        btnPlaySoundG.setOnClickListener(this);
        btnPlaySoundGT = findViewById(R.id.btn_play_sound_g_t);
        btnPlaySoundGT.setOnClickListener(this);
        btnPlaySoundGTD = findViewById(R.id.btn_play_sound_g_t_d);
        btnPlaySoundGTD.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.equals(btnPlayMusic)) onClickMusic(v);
        else onClickSound(v);
    }
    public void onClickMusic(View view) {
        mpMusic = new MediaPlayer();
        try {
            Log.d(LOG_TAG, getExternalCacheDir().getPath());
            Log.d(LOG_TAG, Arrays.toString(getExternalMediaDirs()));
            Log.d(LOG_TAG, Arrays.toString(getExternalCacheDirs()));
            mpMusic.setDataSource("/storage/emulated/0/Android/data/com.example.less_128_audiofocus/cache/music.mp3");
            mpMusic.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mpMusic.setOnCompletionListener(this);
        afListenerMusic = new AFListener("Music", mpMusic);
        int requestResult = audioManager.requestAudioFocus(afListenerMusic,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        Log.d(LOG_TAG, "Music request focus, result: " + requestResult);

        mpMusic.start();
    }
    public void onClickSound(View view) {
        int durationHint = AudioManager.AUDIOFOCUS_GAIN;
        if (view.equals(btnPlaySoundG))
            durationHint = AudioManager.AUDIOFOCUS_GAIN;
        else if (view.equals(btnPlaySoundGT))
            durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
        else if (view.equals(btnPlaySoundGTD))
            durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;

        mpSound = MediaPlayer.create(this, R.raw.explosion);
        mpSound.setOnCompletionListener(this);

        afListenerSound = new AFListener("Sound", mpSound);
        int requestResult = audioManager.requestAudioFocus(afListenerSound,
                AudioManager.STREAM_MUSIC, durationHint);
        Log.d(LOG_TAG, "Sound request focus, result: " + requestResult);

        mpSound.start();
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mp == mpMusic) {
            Log.d(LOG_TAG, "Music: abandon focus");
            audioManager.abandonAudioFocus(afListenerMusic);
        } else if (mp == mpSound) {
            Log.d(LOG_TAG, "Sound: abandon focus");
            audioManager.abandonAudioFocus(afListenerSound);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpMusic != null)
            mpMusic.release();
        else if (mpSound != null)
            mpSound.release();
        else if (afListenerMusic != null)
            audioManager.abandonAudioFocus(afListenerMusic);
        else if (afListenerSound != null)
            audioManager.abandonAudioFocus(afListenerSound);
    }

    class AFListener implements AudioManager.OnAudioFocusChangeListener {
        String label = "";
        MediaPlayer mp;

        public AFListener(String label, MediaPlayer mp) {
            this.label = label;
            this.mp = mp;
        }
        @Override
        public void onAudioFocusChange(int focusChange) {
            String event = "";
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    event = "AUDIOFOCUS_LOSS"; mp.pause(); break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    event = "AUDIOFOCUS_LOSS_TRANSIENT"; mp.pause(); break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    event = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK"; mp.setVolume(0.5f, 0.5f); break;
                case AudioManager.AUDIOFOCUS_GAIN: {
                    event = "AUDIOFOCUS_GAIN";
                    if (!mp.isPlaying()) mp.start();
                    mp.setVolume(1f, 1f);
                    break;

                }
            }
            Log.d(LOG_TAG, label + " onAudioFocusChange: " + event);
        }
    }
}