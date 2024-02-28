package com.example.less_131_cameraintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.nio.file.Path;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final static String LOG_TAG = "myLogs";

    File directory;
    final int TYPE_PHOTO = 1;
    final int TYPE_VIDEO = 2;

    final int REQUEST_CODE_PHOTO = 1;
    final int REQUEST_CODE_VIDEO = 2;
    ImageView ivPhoto;
    Button btnPhoto, btnVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDirectory();
        ivPhoto = findViewById(R.id.ivPhoto);

        btnPhoto = findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(this);

        btnVideo = findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(this);
    }

    private void createDirectory() {
        directory = new File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyFolder");
        Log.d(LOG_TAG, "Created: " + directory);
        if (!directory.exists()) Log.d(LOG_TAG,
                "create directories: " + directory.mkdir() +
                        " Directory: " + directory);
    }
    @Override
    public void onClick(View v) {
        if (v.equals(btnPhoto)) onClickPhoto();
        if (v.equals(btnVideo)) onClickVideo();
    }

    private void onClickPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO));
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    private void onClickVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_VIDEO));
        startActivityForResult(intent, REQUEST_CODE_VIDEO);
    }
    private Uri generateFileUri(int type) {
        File file = null;
        switch (type) {
            case TYPE_PHOTO :
                file = new File(directory.getPath() + File.separator +
                        "photo_" + System.currentTimeMillis() + ".jpg"); break;
            case TYPE_VIDEO : file = new File(directory.getPath() + File.separator +
                    "video_" + System.currentTimeMillis()+ ".mp4"); break;
        }
        Log.d(LOG_TAG, "fileName = " + file);
        Uri u = FileProvider.getUriForFile(MainActivity.this,
                getApplicationContext().getPackageName() + ".provider", file);
        Log.d(LOG_TAG, "uri: " + u.getPath());

        return FileProvider.getUriForFile(MainActivity.this,
                getApplicationContext().getPackageName() + ".provider", file);
                //Uri.fromFile(file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (intent == null) {
                    Log.d(LOG_TAG, "Intent photo is null");
                } else {
                    Log.d(LOG_TAG, "Photo uri: " + intent.getData());
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        Object obj = intent.getExtras().get("data");
                        if (obj instanceof Bitmap) {
                            Bitmap bitmap = (Bitmap) obj;
                            Log.d(LOG_TAG, "bitmap " + bitmap.getWidth() + " x " +
                                    bitmap.getHeight());
                            ivPhoto.setImageBitmap(bitmap);
                        }
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(LOG_TAG, "Cancelled");
            }
        }
        if (requestCode == REQUEST_CODE_VIDEO) {
            if (resultCode == RESULT_OK) {
                if (intent == null) {
                    Log.d(LOG_TAG, "Intent video is null");
                } else {
                    Log.d(LOG_TAG, "Video uri: " + intent.getData());
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(LOG_TAG, "Cancelled");
            }
        }
    }
}