package com.example.less_75;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    final String FILE_NAME = "file";
    final String DIR_SD = "MyFiles";
    final String FILE_NAME_SD = "fileSD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_write).setOnClickListener(v -> writeFile());
        findViewById(R.id.btn_read).setOnClickListener(v -> readFile());
        findViewById(R.id.btn_write_sd).setOnClickListener(v -> writeFileSD());
        findViewById(R.id.btn_read_sd).setOnClickListener(v -> readFileSD());
    }
    private void writeFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILE_NAME, MODE_PRIVATE)));
            bw.write("Содержимое файла");
            bw.close();
            Log.d(LOG_TAG, "Файл записан");
        } catch (IOException e) { e.printStackTrace(); }
    }
    private void readFile() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILE_NAME)));
            String str;
            while ((str = br.readLine()) != null) Log.d(LOG_TAG, str);
        } catch (IOException e) { e.printStackTrace(); }
    }
    private void writeFileSD() {
        Log.d(LOG_TAG, "Метод writeFileSD");
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD карта недоступна: " +
                    Environment.getExternalStorageState());
            return;
        }
        File sdPath = Environment.getExternalStorageDirectory();
        Log.d(LOG_TAG, Environment.getExternalStorageDirectory().toString());
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        Log.d(LOG_TAG, sdPath.toString());
        File sdFile = new File(sdPath, FILE_NAME_SD);
        Log.d(LOG_TAG, sdFile.toString());
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            bw.write("Содержимое файла на SD");
            bw.close();
            Log.d(LOG_TAG, "Файл записан на SD");
        } catch (IOException e) { e.printStackTrace(); }
    }
    private void readFileSD() {
        Log.d(LOG_TAG, "Метод readFileSD");
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD карта недоступна: " +
                    Environment.getExternalStorageState());
            return;
        }
        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        File sdFile = new File(sdPath, FILE_NAME_SD);
        try {
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str;
            while ((str = br.readLine()) != null) Log.d(LOG_TAG, str);
        } catch (IOException e) { e.printStackTrace(); }
    }
}