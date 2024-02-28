package com.example.less_131_camera;

import androidx.camera.core.Camera2Config;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.impl.CameraXConfig.Provider;

import android.app.Application;
import android.util.Log;

public class MainApplication extends Application implements Provider {
    @Override
    public CameraXConfig getCameraXConfig() {
        return CameraXConfig.Builder.fromConfig(Camera2Config.defaultConfig())
                .setMinimumLoggingLevel(Log.ERROR).build();
    }
}
