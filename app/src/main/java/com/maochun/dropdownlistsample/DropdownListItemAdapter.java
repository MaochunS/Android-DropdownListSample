package com.maochun.dropdownlistsample;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by maochuns.sun@gmail.com on 2020/3/30
 */
public class DropdownListItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pair<Integer, String>> itemArray;


    public DropdownListItemAdapter() {
        super();
    }

    public DropdownListItemAdapter(Context context, ArrayList<Pair<Integer, String>> itemList) {
        this.context = context;
        this.itemArray = itemList;
    }

    @Override
    public int getCount() {
        return itemArray.size();
    }

    @Override
    public Object getItem(int position) {
        return itemArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.cardview_dropdown_item, parent, false);

        Pair<Integer, String> item = itemArray.get(position);

        ImageView imgView = convertView.findViewById(R.id.imageView_supported_cointoken_coinlogo);
        imgView.setImageResource(item.first);

        TextView tvItemTitle = (TextView) convertView.findViewById(R.id.textView_supported_cointoken_name);
        tvItemTitle.setText(item.second);


        return convertView;
    }

}
