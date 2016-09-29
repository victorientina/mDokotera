package com.example.tantelytiana.mydokta;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DocOnLine extends AppCompatActivity {

    ListView list;

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference myRef = database.getReference("utilisateur");


    public static List<String> li = new ArrayList<String>();
    public static List<String> li2 = new ArrayList<String>();
    public static List<String> li3 = new ArrayList<String>();
    static final String[] NOM =
            new String[] { "Contact", "Docteur en ligne","Carte"};

    static final String[] PRENOM =
            new String[] { "fff", "ggg","ll"};

    static final String[] SPEC =
            new String[] { "ccc", "mmmm","vvv"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_on_line);


        list = (ListView) findViewById(R.id.listDoc);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    final String data = postSnapshot.getValue().toString();
                    final String cle = postSnapshot.getKey();
                    final DatabaseReference myRef2 = myRef.child(cle);

                    if (data.contains("type_utilisateur=6")) {


                        myRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {

                                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                    String data2 = postSnapshot.getValue().toString();
                                    String cle2 = postSnapshot.getKey();
                                    if (cle2.equals("nom")) {
                                        li.add(data2);
                                        System.out.println(data2+",,,,,,,,");

                                    }
                                    if (cle2.equals("addresse")) {
                                        li2.add(data2);
                                        System.out.println(data2+"''''''''''");
                                    }

                                    if (cle2.equals("specialite")) {
                                        System.out.println(data2+"......");
                                        li3.add(data2);
                                    }

                                    ListDocAdapter adapter = new ListDocAdapter(getApplicationContext(), li, li2, li3);
                                    list.setAdapter(adapter);


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

    @Override
    public void onStart() {
        super.onStart();


    }
}
