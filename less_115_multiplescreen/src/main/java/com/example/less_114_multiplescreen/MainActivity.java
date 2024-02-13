package com.example.less_114_multiplescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.example.less_114_supportlibrary.R;

public class MainActivity extends AppCompatActivity implements TitlesFragment.OnItemClickListener {
    int position = 0;
    boolean withDetails = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
        }

        withDetails = (findViewById(R.id.cont) != null);

        if (withDetails) {
            showDetails(position);
        }
    }

    private void showDetails(int position) {
        if (withDetails) {
            DetailsFragment details = (DetailsFragment) getSupportFragmentManager().
                    findFragmentById(R.id.cont);
            if (details == null || details.getPosition() != position) {
                details = DetailsFragment.newInstance(position);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.cont, details).commit();
            }
        } else {
            startActivity(
                    new Intent(
                            this, DetailsActivity.class).
                            putExtra("position", position));
        }
    }

    @Override
    public void itemClick(int position) {
        this.position = position;
        showDetails(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }
}