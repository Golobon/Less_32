package com.example.less_106_fragments_work_with_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Fragment2.OnSomeEventListener {
    Fragment frag2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag2 = new Fragment2();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment2, frag2);
        ft.commit();

         findViewById(R.id.but_find).setOnClickListener(v -> {
            Fragment frag1 = getSupportFragmentManager().findFragmentById(R.id.fragment1);
             ((TextView) frag1.getView().findViewById(R.id.tv)).setText("Access to fragment1 from Activity");

             ((TextView) frag2.getView().findViewById(R.id.tv)).setText("Access to fragment2 from Activity");
        });
    }

    @Override
    public void someEvent(String s) {
        Fragment frag1 = getSupportFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView) frag1.getView().findViewById(R.id.tv)).setText("Text from fragment2:" + s);
    }
}