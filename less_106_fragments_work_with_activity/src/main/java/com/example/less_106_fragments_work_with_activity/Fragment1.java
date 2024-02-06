package com.example.less_106_fragments_work_with_activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    final String LOG_TAG = "myLogs";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View veiw = inflater.inflate(R.layout.fragment1, null);

        veiw.findViewById(R.id.but).setOnClickListener(v ->
                        ((Button) getActivity().findViewById(R.id.but_find)).setText("Access from fragment1"));
                Log.d(LOG_TAG, "Button click in fragment1");
        return veiw;
    }
}