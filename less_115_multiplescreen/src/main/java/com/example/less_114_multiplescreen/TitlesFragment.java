package com.example.less_114_multiplescreen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import com.example.less_114_supportlibrary.R;

public class TitlesFragment extends ListFragment {
    public interface OnItemClickListener {
        void itemClick(int position);
    }
    OnItemClickListener listener;
    @Override
    public void onCreate(Bundle savedInstanse) {
        super.onCreate(savedInstanse);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.headers));
        setListAdapter(adapter);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnItemClickListener) context;
    }
    @Override
    public void onListItemClick(ListView l, View v,
                                int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.itemClick(position);
    }
}
