package com.example.tantelytiana.mydokta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tantely Tiana on 02/06/2016.
 */

public class MobileArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MobileArrayAdapter(Context context, String[] values) {
        super(context, R.layout.list_menu, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_menu, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);

        // Change icon based on name
        String s = values[position];

        System.out.println(s);

        if (s.equals("Contact")) {
            imageView.setImageResource(R.drawable.contact);
        } else if (s.equals("Docteur en ligne")) {
            imageView.setImageResource(R.drawable.dokotera);
        } else if (s.equals("Rechercher medicament")) {
            imageView.setImageResource(R.drawable.fanafody);
        }  else if (s.equals("Carte")) {
            imageView.setImageResource(R.drawable.carte);
        }  else {
            imageView.setImageResource(R.drawable.dokotera_online);
        }

        return rowView;
    }
}

