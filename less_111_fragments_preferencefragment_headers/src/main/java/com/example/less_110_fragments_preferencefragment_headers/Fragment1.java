package com.example.less_110_fragments_preferencefragment_headers;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

public class Fragment1 extends PreferenceFragmentCompat {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.pref1, rootKey);
    }
}
