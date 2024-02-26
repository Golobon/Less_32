package com.example.less_125_viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class PageFragment extends Fragment {
    static final String TAG = "myLogs";
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String SAVE_PAGE_NUMBER = "save_page_number";
    int pageNubmer;
    int backColor;
    static PageFragment newInstance (int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNubmer = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        Log.d(TAG, "onCreate: "+ pageNubmer);
        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256),
                rnd.nextInt(256), rnd.nextInt(256));
        int savedPagerNumber = -1;
        if (savedInstanceState != null) {
            savedPagerNumber = savedInstanceState.getInt(SAVE_PAGE_NUMBER);
        }
        Log.d(TAG, "savedPagerNumber = " + savedPagerNumber);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        TextView tvPage = view.findViewById(R.id.tv_page);
        tvPage.setText("Page " + pageNubmer);
        tvPage.setBackgroundColor(backColor);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_PAGE_NUMBER, pageNubmer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + pageNubmer);
    }
}
