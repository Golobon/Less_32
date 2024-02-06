package com.example.less_105_fragments_dynamic_work;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.CheckBox;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Fragment1 frag1;
    Fragment2 frag2;
    FragmentTransaction fTrans;
    CheckBox chbStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = new Fragment1();
        frag2 = new Fragment2();

        chbStack = findViewById(R.id.ch_box);

        findViewById(R.id.but_add).setOnClickListener(this::onClick);

        findViewById(R.id.but_remove).setOnClickListener(this::onClick);

        findViewById(R.id.but_replace).setOnClickListener(this::onClick);
    }

    public void onClick (View v) {
        fTrans = getSupportFragmentManager().beginTransaction();

        int vId = v.getId();
        if (vId == R.id.but_add){
            fTrans.add(R.id.frgm_cont, frag1);
        } else if (vId == R.id.but_remove) {
            fTrans.remove(frag1);
        } else if (vId == R.id.but_replace) {
            fTrans.replace(R.id.frgm_cont, frag2);
        }
        if (chbStack.isChecked()) fTrans.addToBackStack(null);
        fTrans.commit();
    }
}