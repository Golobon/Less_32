package com.example.less_114_multiplescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.less_114_supportlibrary.R;

public class DetailsFragment extends Fragment {
    public static DetailsFragment newInstance(int pos) {
        DetailsFragment details = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("position", pos);
        details.setArguments(args);
        return details;
    }
    int getPosition() {
        return getArguments().
                getInt("position", 0);
    }
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.details, container,false);
        TextView tv = v.findViewById(R.id.tvText);
        tv.setText(getResources().getStringArray(R.array.content)[getPosition()]);
        return v;
    }
}
