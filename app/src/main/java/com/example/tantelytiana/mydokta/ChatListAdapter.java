package com.example.tantelytiana.mydokta;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Tantely Tiana on 17/07/2016.
 */
public class ChatListAdapter extends FirebaseListAdapter<Message>{

    private String prenom;
    private String cle;


    public ChatListAdapter(Query ref, Activity activity, int layout, String PRENOM, String CLE) {
        super(ref, Message.class, layout, activity);
        prenom=PRENOM;
        cle=CLE;


    }


    @Override
    protected void populateView(View view, Message resaka) {

        String author = resaka.sender;
        TextView authorText = (TextView) view.findViewById(R.id.author);


        LinearLayout layout = (LinearLayout) view.findViewById(R.id.bubble_layout);
        LinearLayout parent_layout = (LinearLayout) view.findViewById(R.id.bubble_layout_parent);

        if(author.equals(cle))
        {
            authorText.setText(prenom + ":");
            layout.setBackgroundResource(R.drawable.bubble2);
            parent_layout.setGravity(Gravity.RIGHT);

        }

        else
        {
            authorText.setText("Cabinet medical :");
            authorText.setTextColor(Color.GRAY);
            layout.setBackgroundResource(R.drawable.bubble1);
            parent_layout.setGravity(Gravity.LEFT);


        }



        DateFormat df = new SimpleDateFormat("MM/dd/yyyy ");
        String reportDate = df.format(resaka.getDate_message());

        ((TextView) view.findViewById(R.id.message)).setText(resaka.getContenu());
        ((TextView) view.findViewById(R.id.daty)).setText(reportDate);

    }

}
