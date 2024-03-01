package com.example.less_133_camerarecord;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SurfaceView surfaceView;
    Camera camera;
    MediaRecorder mediaRecorder;
    File photoFile;
    File videoFile;
    Button btnTakePicture, btnStartRec, nbtnStopRec;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA,
                            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO}, 1);
        }

        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnTakePicture.setOnClickListener(this);
        btnStartRec = findViewById(R.id.btnStartRecord);
        btnStartRec.setOnClickListener(this);
        nbtnStopRec = findViewById(R.id.btnStopRecord);
        nbtnStopRec.setOnClickListener(this);

        File pictures =  getBaseContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        Environment.getExternalStoragePublicDirectory(
//                        Environment.DIRECTORY_PICTURES
//                );
        photoFile = new File(pictures, "myPhoto.jpeg");
        Log.d("mylog", "photoFile: " + photoFile.getAbsolutePath());
        videoFile = new File(pictures, "myVideo.3gp");
        Log.d("mylog", "videoFile: " + videoFile.getAbsolutePath());

        surfaceView = findViewById(R.id.surfaceView);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                try {
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder,
                                       int format, int width, int height) {

            }
            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        camera = Camera.open();
    }
    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaRecorder();
        if (camera != null) camera.release();
        camera = null;
    }
    public void onClickPicture() {
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    FileOutputStream fos = new FileOutputStream(photoFile);
                    fos.write(data);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();
            }
        });
    }
    public void onClickStartRecord() {
        if (prepareVideoRecorder()) {
            mediaRecorder.start();
        } else {
            releaseMediaRecorder();
        }
    }
    public void onClickStopRecord() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            releaseMediaRecorder();
        }
    }
    private boolean prepareVideoRecorder() {
        camera.stopPreview();
        camera.unlock();

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setCamera(camera);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setProfile(CamcorderProfile.
                get(CamcorderProfile.QUALITY_HIGH));
        mediaRecorder.setOutputFile(videoFile.getAbsoluteFile());
        mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());

        try {
            mediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            camera.lock();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnTakePicture)) onClickPicture();
        if (v.equals(btnStartRec)) onClickStartRecord();
        if (v.equals(nbtnStopRec)) onClickStopRecord();
    }
}