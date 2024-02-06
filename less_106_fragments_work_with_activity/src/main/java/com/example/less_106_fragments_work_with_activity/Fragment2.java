package com.example.less_106_fragments_work_with_activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
public class Fragment2 extends Fragment {

    public interface OnSomeEventListener {
        public void someEvent(String s);
    }
    OnSomeEventListener someEventListener;
    final String LOG_TAG = "myLogs";

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            someEventListener = (OnSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View veiw = inflater.inflate(R.layout.fragment2, null);

        veiw.findViewById(R.id.but).setOnClickListener(v -> {
            someEventListener.someEvent("Test text to fragment1");
        });
        return veiw;
    }
}