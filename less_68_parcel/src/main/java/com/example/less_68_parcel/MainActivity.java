package com.example.less_68_parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    Parcel p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeParcel();
        readParcel();
    }

    private void writeParcel() {
        p = Parcel.obtain();
        byte b = 1;
        int i = 2;
        long l = 3;
        float f = 4;
        double d = 5;
        String s = "abcdefgh";

        logWtiteInfo("before writing");
        p.writeByte(b);
        logWtiteInfo("byte");
        p.writeInt(i);
        logWtiteInfo("int");
        p.writeLong(l);
        logWtiteInfo("long");
        p.writeFloat(f);
        logWtiteInfo("float");
        p.writeDouble(d);
        logWtiteInfo("double");
        p.writeString(s);
        logWtiteInfo("String");
    }

    private void logWtiteInfo(String txt) {
        Log.d(LOG_TAG, txt + ": " + "dataSize = " + p.dataSize());
    }
    private void readParcel() {
        logReadInfo("Before reading");
        p.setDataPosition(0);
        logReadInfo("byte = " + p.readByte());
        logReadInfo("int = " + p.readInt());
        logReadInfo("long = " + p.readLong());
        logReadInfo("float = " + p.readFloat());
        logReadInfo("double = " + p.readDouble());
        logReadInfo("string = " + p.readString());
    }
    private void logReadInfo(String txt) {
        Log.d(LOG_TAG, txt + ": " + "dataPisition = " + p.dataPosition());
    }
}