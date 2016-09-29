package com.example.tantelytiana.mydokta;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView list;

    static final String[] MOBILE_OS =
            new String[] {"Login","Docteur en ligne","Carte"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.list);

        MobileArrayAdapter adapter = new MobileArrayAdapter(this, MOBILE_OS);

        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedValue = MOBILE_OS[position];
                System.out.println(selectedValue);

                if (selectedValue.equals("Login")) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                else if (selectedValue.equals("Contact")){}
                else if (selectedValue.equals("Docteur en ligne")){
                    Intent intent = new Intent(MainActivity.this, DocOnLine.class);
                    startActivity(intent);

                }
                else if (selectedValue.equals("Rechercher medicament")){}
                else if (selectedValue.equals("Carte")){
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                }
                else {}

            }

        });

    }





}
