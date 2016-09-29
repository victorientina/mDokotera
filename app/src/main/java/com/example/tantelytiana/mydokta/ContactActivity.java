package com.example.tantelytiana.mydokta;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private String[] conts = new String[30];

   public static List<String> li = new ArrayList<String>();
    public static List<String> refcont = new ArrayList<String>();

    MakaLista mk = new MakaLista();

    ListView listView;

    String contact;
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference myRef = database.getReference("conversation");
    private static final DatabaseReference myRefUser = database.getReference("utilisateur");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);
        listView = (ListView) findViewById(R.id.listView);



        Intent intent = getIntent();
        final String ref = intent.getStringExtra("ref");




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    final String cle = postSnapshot.getKey();
                    final DatabaseReference myRef2 = myRef.child(cle);
                    String data = postSnapshot.getValue().toString();
                    // System.out.println(data+"::::::::::::::::::::::::;");

                    if (data.contains(ref)) {

                        myRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot2) {

                                for (DataSnapshot postSnapshot2 : snapshot2.getChildren()) {
                                    String cle2 = postSnapshot2.getKey();

                                    if (cle2.equals(ref)) {

                                    } else {

                                        contact = cle2;
                                        DatabaseReference myRef3 = myRefUser.child(contact);

                                        refcont.add(contact);

                                        myRef3.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot snapshot3) {

                                                for (DataSnapshot postSnapshot3 : snapshot3.getChildren()) {

                                                    String cle3 = postSnapshot3.getKey();

                                                    if (cle3.equals("nom")) {
                                                        System.out.println(postSnapshot3.getValue().toString() + ".............");

                                                        li.add(postSnapshot3.getValue().toString());

                                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ContactActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, li);
                                                        listView.setAdapter(adapter);


                                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                            public void onItemClick(AdapterView<?> parent, View view,
                                                                                    int position, long id) {
                                                                // When clicked, show a toast with the TextView text
                                                                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                                                                String maka = ((TextView) view).getText().toString();
                                                                int pos= li.indexOf(maka);
                                                                System.out.println(refcont.get(pos)+"////////////");

                                                                Intent intent = new Intent(ContactActivity.this, KozActivity.class);
                                                                intent.putExtra("ref",ref);
                                                                intent.putExtra("refContact",refcont.get(pos));
                                                                intent.putExtra("nomContact",maka);
                                                                startActivityForResult(intent, 0);


                                                            }
                                                        });

                                                    }


                                                }


                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                System.out.println("The read failed: " + databaseError.getMessage());
                                            }
                                        });


                                    }

                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                System.out.println("The read failed: " + databaseError.getMessage());
                            }
                        });


                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });






      // ListView listView = getListView();
        listView.setTextFilterEnabled(true);





    }



    @Override
    protected void onStop()
    {
        super.onStop();
        li.clear();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        li.clear();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        li.clear();
    }




}


