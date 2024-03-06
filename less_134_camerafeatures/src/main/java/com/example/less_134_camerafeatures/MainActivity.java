package com.example.less_134_camerafeatures;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SurfaceView surfaceView;
    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surface_view);

        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                try {
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format,
                                       int width, int height) {
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA}, 1);
        }
        camera = Camera.open();
        initSpinners();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null)
            camera.release();
        camera = null;
    }

    private void initSpinners() {
        final List<String> colorEffects = camera.getParameters()
                .getSupportedColorEffects();
        Log.d("boob", "colorEffects: " + colorEffects);
        Spinner spEffect = initSpinner(R.id.sp_effect, colorEffects,
                camera.getParameters().getColorEffect());
        spEffect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Parameters params = camera.getParameters();
                params.setColorEffect(colorEffects.get(position));
                camera.setParameters(params);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final List<String> flashModes =
                camera.getParameters().getSupportedFlashModes();
        Spinner spFlash = initSpinner(R.id.sp_flash, flashModes,
                camera.getParameters().getFlashMode());
        spFlash.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Parameters params = camera.getParameters();
                params.setFlashMode(flashModes.get(position));
                camera.setParameters(params);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private Spinner initSpinner(int spinnerId, List<String> data,
                                String currentValue) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, data);
        try {
            Log.d("boob", "adapter == null: " + (adapter == null) +
                    "\ndata == null: " + (data == null) +
                    "\ndata.size: " + data.size() +
                    "\ndata.get(0): " + data.get(0));
        } catch (NullPointerException e) {
            Log.d("boob", "NullPointerException:");
            e.printStackTrace();
        }

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        try {
            spinner.setAdapter(adapter);
        } catch (NullPointerException e) {
            Log.d("boob", "NullPointerException:");
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < data.size(); i++) {
                String item = data.get(i);
                if (item.equals(currentValue)) {
                    spinner.setSelection(i);
                }
            }
        } catch (NullPointerException e) {
            Log.d("boob", "NullPointerException:");
            e.printStackTrace();
        }

        return spinner;
    }
}