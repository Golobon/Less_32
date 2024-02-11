package com.example.less_112_dynamicactionbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    final int MENU_ID = 1;

    CheckBox chbAddDel;
    CheckBox chbVisible;
    Fragment frag;
    Fragment frag1;
    Fragment frag2;
    Button btnFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chbAddDel = findViewById(R.id.chbAddDel);
        chbVisible = findViewById(R.id.chbVisib);
        btnFrag = findViewById(R.id.btnFrag);

        frag = frag1 = new Fragment1();
        frag2 = new Fragment2();

        btnFrag.setOnClickListener(MainActivity.this::onClick);
        chbAddDel.setOnClickListener(MainActivity.this::onClick);
        chbVisible.setOnClickListener(MainActivity.this::onClick);

        Objects.requireNonNull(getSupportActionBar()).
                setDisplayShowTitleEnabled(false);

    }
    private void onClick(View v) {
        if (v.equals(chbAddDel) || v.equals(chbVisible))
            invalidateMenu();
        else if (v.equals(btnFrag)) {
            frag = (frag == frag1) ? frag2 : frag1;
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.flCont, frag).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.setGroupVisible(R.id.groupVsbl, chbVisible.isChecked());
        if (chbAddDel.isChecked()) {
            menu.add(0, MENU_ID, 0, "item1").
                    setIcon(android.R.drawable.ic_delete).
                    setShowAsAction(
                            MenuItem.SHOW_AS_ACTION_ALWAYS |
                                    MenuItem.SHOW_AS_ACTION_WITH_TEXT
                    );
        } else {
            menu.removeItem(MENU_ID);
        }
        return true;
    }
}