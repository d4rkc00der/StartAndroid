package com.soho.evgeny.customadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by evgeny on 11.08.16.
 */
public class BoxAdapter  extends BaseAdapter {
    Context ctx;
    LayoutInflater inflater;
    ArrayList<Product> objects;

    public BoxAdapter(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View view = convertview;

        if(view == null){
            view = inflater.inflate(R.layout.item,parent,false);
        }

        Product p = getProduct(position);

        ((TextView)view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView)view.findViewById(R.id.tvPrice)).setText(p.price + " ");
        ((ImageView)view.findViewById(R.id.ivImage)).setImageResource(p.img);

        CheckBox cbBuy = (CheckBox)view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        cbBuy.setTag(position);
        cbBuy.setChecked(p.box);
        return view;
    }

    Product getProduct(int pos) {
        return ((Product)getItem(pos));
    }

    ArrayList<Product> getBox(){
        ArrayList<Product> box = new ArrayList<Product>();
        for(Product p : objects) {
            if(p.box) {
                box.add(p);
            }

        }
        return box;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            getProduct((Integer)compoundButton.getTag()).box = isChecked;
        }
    };

}
