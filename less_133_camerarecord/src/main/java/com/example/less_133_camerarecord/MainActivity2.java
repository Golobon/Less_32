package com.example.less_133_camerarecord;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    SurfaceView surfaceView;
    Camera camera;
    MediaRecorder mediaRecorder;
    Button btnTakePicture, btnStartRec, nbtnStopRec;
    TextView tvText;

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
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO},
                    PackageManager.PERMISSION_GRANTED);
        }

        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnTakePicture.setOnClickListener(this);
        btnStartRec = findViewById(R.id.btnStartRecord);
        btnStartRec.setOnClickListener(this);
        nbtnStopRec = findViewById(R.id.btnStopRecord);
        nbtnStopRec.setOnClickListener(this);

        tvText = findViewById(R.id.tv_text);

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

                Uri uriSavedImage;
                ContentResolver resolver = getContentResolver();
                String pathName = "Boo";
                String imageFileName = "image_" + System.currentTimeMillis() + ".jpeg";
                ContentValues valuesImages;
                valuesImages = new ContentValues();
                valuesImages.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + pathName);
                valuesImages.put(MediaStore.Images.Media.TITLE, imageFileName);
                valuesImages.put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName);
                valuesImages.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                valuesImages.put(
                        MediaStore.Images.Media.DATE_ADDED,
                        System.currentTimeMillis() / 1000);

                Uri collection =
                        MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                Log.d("myLogs", "URI: " + collection);
                Log.d("myLogs", "URI getPath: " + collection.getPath());

                uriSavedImage = resolver.insert(collection, valuesImages);

                valuesImages.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                valuesImages.put(MediaStore.Images.Media.IS_PENDING, 1);

                ParcelFileDescriptor pfd;
                FileOutputStream out;
                try {
                    pfd = resolver.openFileDescriptor(uriSavedImage, "w");

                    out = new FileOutputStream(pfd.getFileDescriptor());

                    out.write(data);

                    out.close();
                    pfd.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                    valuesImages.clear();
                    valuesImages.put(MediaStore.Images.Media.IS_PENDING, 0);
                    getContentResolver().update(uriSavedImage, valuesImages, null, null);

                camera.startPreview();
            }
        });
    }
    public void onClickStartRecord() {

    }
    public void onClickStopRecord() { }

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