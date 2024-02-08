package com.example.less_110_fragments_dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class Dialog2 extends DialogFragment implements DialogInterface.OnClickListener {
    final String LOG_TAG = "myLogs";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity()).
                setTitle("Title!").setPositiveButton(R.string.yes, this).
                setNegativeButton(R.string.no, this).
                setNeutralButton(R.string.maybe, this).
                setMessage(R.string.message_text);
        return adb.create();
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        if (which == Dialog.BUTTON_POSITIVE) i = R.string.yes;
        if (which == Dialog.BUTTON_NEGATIVE) i = R.string.no;
        if (which == Dialog.BUTTON_NEUTRAL) i = R.string.maybe;

        if (i > 0) Log.d(LOG_TAG, "DIalog 2: " + getResources().getString(i));
    }
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "DIalog 2: onDismiss");
    }
    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "DIalog 2: onCancel");
    }


}