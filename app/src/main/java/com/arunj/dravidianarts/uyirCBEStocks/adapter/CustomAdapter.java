package com.arunj.dravidianarts.uyirCBEStocks.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arunj.dravidianarts.uyirCBEStocks.R;
import com.arunj.dravidianarts.uyirCBEStocks.model.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mushtaq on 12-04-2017.
 */

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<Users> usersList;

    public CustomAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = new ArrayList<>();
        this.usersList = usersList;
    }

    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView your_first_text_view = (TextView) rowView.findViewById(R.id.listview_firsttextview);
        TextView your_second_text_view = (TextView) rowView.findViewById(R.id.listview_secondtextview);

        your_first_text_view.setText(usersList.get(position).getName());
        your_second_text_view.setText(usersList.get(position).getBatch());

        return rowView;
    }
}
