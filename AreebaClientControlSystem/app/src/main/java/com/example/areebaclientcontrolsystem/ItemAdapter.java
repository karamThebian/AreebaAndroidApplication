package com.example.areebaclientcontrolsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;

    List<Post> list;

    public ItemAdapter(Context c, List<Post> mylist) {

        list=mylist;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i).getId();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.layout_list_item, null);
        TextView idTextView = (TextView) v.findViewById(R.id.id);
        TextView nameTextView = (TextView) v.findViewById(R.id.name);
        TextView phoneNumberTextView = (TextView) v.findViewById(R.id.phoneNumber);
        TextView addressTextView = (TextView) v.findViewById(R.id.address);
        Long id = list.get(i).getId();
        String name = list.get(i).getName();
        String phoneNumber = list.get(i).getPhoneNumber();
        String address = list.get(i).getAddress();

        idTextView.setText(id.toString());
        nameTextView.setText(name);
        phoneNumberTextView.setText(phoneNumber);
        addressTextView.setText(address);
        return v;
    }
}
