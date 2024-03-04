package com.example.less_133_camerarecord;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SurfaceView surfaceView;
    Camera camera;
    MediaRecorder mediaRecorder;
    File photoFile;
    File videoFile;
    Button btnTakePicture, btnStartRec, nbtnStopRec;
    TextView tvText;
    File pictures;

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

        pictures = new File(getPhotoDirectory(getBaseContext()));

        if (pictures.exists()) {
            Log.d("mylog", "pictures.exists(): " + pictures.exists());
            if (!pictures.isDirectory()){
                this.finish();
            }
        } else {
            Log.d("mylog", "mkdir: " + pictures.mkdir());
        }
//getBaseContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        Environment.getExternalStoragePublicDirectory(
//                        Environment.DIRECTORY_PICTURES
//                );

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

    private File getPhotoVideoFileName(int mediaType) {
        File path = null;
        if (mediaType == 1)
            path = new File(pictures,
                System.currentTimeMillis() +
                        "_myPhoto.jpeg");

//        Log.d("mylog", "photoFile: " + photoFile.getAbsolutePath());
//
//        Log.d("mylog", "isDirectory: " + photoFile.isDirectory());
//        Log.d("mylog", "isFile: " + photoFile.isFile());
//        Log.d("mylog", "photoFile.exists(): " + photoFile.exists());
//        Log.d("mylog", "photoFile.length(): " + photoFile.length());

        if (mediaType == 2) path = new File(pictures, System.currentTimeMillis() + " myVideo.3gp");
//        Log.d("mylog", "videoFile: " + videoFile.getAbsolutePath());
//
//        Log.d("mylog", "isDirectory: " + videoFile.isDirectory());
//        Log.d("mylog", "isFile: " + videoFile.isFile());
//        Log.d("mylog", "videoFile.exists(): " + videoFile.exists());
//        Log.d("mylog", "videoFile.length(): " + videoFile.length());
        return path;
    }

    public String getPhotoDirectory(Context context)
    {
//        Log.d("mylog", "context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath(): " +
//            context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath());
//        Log.d("mylog", "Environment.getExternalStorageDirectory().getPath(): " +
//                Environment.getExternalStorageDirectory().getPath());
//        Log.d("mylog", "context.getFilesDir(): " +
//                context.getFilesDir());
//        Log.d("mylog", "getBaseContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES): " +
//                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
//        for (File f: context.getExternalMediaDirs()) {
//            Log.d("mylog", "getExternalMediaDirs: " +
//                    f.getPath() + " length: " + context.getExternalMediaDirs().length);
//
//        }

//        Log.d("mylog", "Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES): " +
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));

        //return Environment.getExternalStorageDirectory()+"/cbo-up"; //Good
        //return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"";
        //return context.getFilesDir() + "/cbo-up";
        //return getBaseContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"";
        //return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +"";
//                File.separator + "Camera"; //Good
        //return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "";
        return MediaStore.Images.Media.DATE_ADDED + "";
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
                photoFile = getPhotoVideoFileName(1);
                Log.d("myLogs", "photoFile exists: " + photoFile.exists());
                try {
                    FileOutputStream fos = new FileOutputStream(photoFile);
                    fos.write(data);
                    fos.flush();
                    fos.getFD().sync();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();
//                sendBroadcast(new Intent(
//                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                        Uri.parse(
//                              //  "file://" +
//                                        photoFile.getAbsolutePath())));

//                MediaScannerConnection.scanFile(getBaseContext(),
//                        new String[]{"file://" +photoFile.getAbsolutePath()},
//                        null, null);

                MediaScannerConnection.scanFile(MainActivity.this,
                        new String[] { photoFile.getAbsolutePath() },
                        new String[] {"image/jpeg"},
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("myLogs ExternalStorage ", "Scanned " + path + ":");
                                Log.i("myLogs ExternalStorage ", "-> uri = " + uri);
                            }
                        });

                Log.d("myLogs", "photoFile: " + photoFile);
                Log.d("myLogs", "photoFile exist2: " + photoFile.exists());
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
//            sendBroadcast(new Intent(
//                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                    Uri.parse(
//                            "file://" + videoFile.getAbsolutePath())));
            MediaScannerConnection.scanFile(getBaseContext(),
                    new String[]{videoFile.toString()},
                    null, null);
            Log.d("myLogs", "videoFile: " + videoFile);
        }
    }
    private boolean prepareVideoRecorder() {
        videoFile = getPhotoVideoFileName(2);

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