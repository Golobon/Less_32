package com.example.less_110_fragments_preferencefragment_headers;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceHeaderFragmentCompat;

public class ActivityPreference extends PreferenceHeaderFragmentCompat {
    @NonNull
    @Override
    public PreferenceFragmentCompat onCreatePreferenceHeader() {
        return new HeaderFragment();
    }
}
