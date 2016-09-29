package com.example.tantelytiana.mydokta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tantely Tiana on 18/07/2016.
 */
public class ListDocAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> values1;
    private final List<String> values2;
    private final List<String> values3;

    public ListDocAdapter(Context context, List<String> values1, List<String> values2, List<String> values3) {
        super(context, R.layout.listdoconline,values1);

        this.context = context;
        this.values1 = values1;
        this.values2 = values2;
        this.values3= values3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.listdoconline, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textnom);
        TextView textView2 = (TextView) rowView.findViewById(R.id.textadresse);
        TextView textView3 = (TextView) rowView.findViewById(R.id.textspecialite);

        if(values1.isEmpty())
        {

        }
        else
        {
            textView.setText(values1.get(position));
        }

        if(values2.isEmpty())
        {
          //  textView2.setText("non precise");

        }
        else
        {
          //  textView2.setText(values2.get(position));

        }

        if(values3.isEmpty())
        {

        }
        else
        {

            textView3.setText(values3.get(position));

        }

        return rowView;
    }
}
