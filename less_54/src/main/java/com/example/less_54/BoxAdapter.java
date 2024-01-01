package com.example.less_54;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> object;

    public BoxAdapter(Context ctx, ArrayList<Product> object) {
        this.ctx = ctx;
        this.object = object;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return object.size();
    }
    @Override
    public Object getItem(int position) {
        return object.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) view = lInflater.inflate(R.layout.item, parent, false);

        Product p = getProduct(position);

        ((TextView) view.findViewById(R.id.tv_descr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tv_price)).setText(p.price + "");
        ((ImageView) view.findViewById(R.id.iv_image)).setImageResource(p.image);

        CheckBox cbBuy = view.findViewById(R.id.cb_box);
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        cbBuy.setTag(position);
        cbBuy.setChecked(p.box);

        return view;
    }
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }
    ArrayList<Product> getBox(){
        ArrayList<Product> box = new ArrayList<>();
        for(Product p : object) {
            if(p.box) box.add(p);
        }
        return box;
    }

    OnCheckedChangeListener myCheckChangeList =
            (buttonView, isChecked) ->
                    getProduct((Integer) buttonView.getTag()).box = isChecked;
}
