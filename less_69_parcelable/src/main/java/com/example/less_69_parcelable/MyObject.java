package com.example.less_69_parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyObject implements Parcelable {
    final static String LOG_TAG = "myLogs";

    public String s;
    public int i;

    public MyObject(String s, int i) {
        Log.d(LOG_TAG, "MyObject(String s, int i)");
        this.s = s;
        this.i = i;
    }
    private MyObject(Parcel parcel) {
        Log.d(LOG_TAG, "MyObject(Parcel parcel)");
        s = parcel.readString();
        i = parcel.readInt();
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        Log.d(LOG_TAG, "writeToParcel");
        parcel.writeString(s);
        parcel.writeInt(i);
    }

    public static final Creator<MyObject> CREATOR = new Creator<MyObject>() {
        @Override
        public MyObject createFromParcel(Parcel in) {
            Log.d(LOG_TAG, "createFromParcel");
            return new MyObject(in);
        }

        @Override
        public MyObject[] newArray(int size) {
            return new MyObject[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
}
