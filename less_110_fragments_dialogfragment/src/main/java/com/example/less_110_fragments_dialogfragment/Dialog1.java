package com.example.less_110_fragments_dialogfragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class Dialog1 extends DialogFragment implements View.OnClickListener {
    final String LOG_TAG = "myLogs";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requireDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog1, container, false);
        v.findViewById(R.id.btn_yes).setOnClickListener(this);
        v.findViewById(R.id.btn_no).setOnClickListener(this);
        v.findViewById(R.id.btn_may_be).setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "DIalog 1: " + ((Button) v).getText());
        dismiss();
    }
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "DIalog 1: onDismiss");
    }
    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "DIalog 1: onCancel");
    }
}