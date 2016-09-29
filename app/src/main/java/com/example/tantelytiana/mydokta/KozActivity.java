package com.example.tantelytiana.mydokta;

import android.app.ListActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class KozActivity extends ListActivity {


    private ChatListAdapter mChatListAdapter;
    String groupeKey1,ref,refContact,nomContact;

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference myRef = database.getReference("conversation");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koz);

        Intent intent = getIntent();

         ref = intent.getStringExtra("ref");
         refContact = intent.getStringExtra("refContact");
         nomContact = intent.getStringExtra("nomContact");

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendMessage(cle, IdUser);

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String data = postSnapshot.getValue().toString();
                    System.out.println(data + "......");
                    groupeKey1 = postSnapshot.getKey();

                    if (data.contains(ref) && (data.contains(refContact))) {

                        System.out.println("io n tadiavko :" + groupeKey1);

                        final ListView listView = getListView();
                        mChatListAdapter = new ChatListAdapter(database.getReference("conversation/" + groupeKey1), KozActivity.this, R.layout.content_koz, nomContact, refContact);
                        listView.setAdapter(mChatListAdapter);
                        mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
                            @Override
                            public void onChanged() {
                                super.onChanged();
                                listView.setSelection(mChatListAdapter.getCount() - 1);
                            }
                        });


                    } else {


                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

    }


    public void sendMessage(final String sender, final String receiver)
    {
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        final String input = inputText.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Date date = new Date();





        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Integer manisa = 0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String data = postSnapshot.getValue().toString();
                    System.out.println(data);
                    System.out.println(sender);
                    System.out.println(receiver);
                    System.out.println("_____________________________");
                    if (data.contains(sender) && (data.contains(receiver))) {

                        manisa = manisa + 1;

                        String groupeKey2 = postSnapshot.getKey();


                        if (!input.equals("")) {
                            // Create our 'model', a Chat object
                            Message res = new Message(input, date, sender);
                            // Create a new, auto-generated child of that chat location, and save our chat data there

                            database.getReference("message/"+groupeKey2).push().setValue(res);
                            System.out.println(groupeKey2);
                            System.out.println("miditra ");

                        }


                    } else {


                    }


                }

                if (manisa == 0) {


                    System.out.println(manisa);




                    Map<String, Boolean> post1 = new HashMap<String, Boolean>();
                    post1.put(sender, true);
                    post1.put(receiver, true);
                    myRef.push().setValue(post1);


                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {

                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                String data = postSnapshot.getValue().toString();
                                System.out.println(data);
                                System.out.println(sender);
                                System.out.println(receiver);
                                System.out.println("_____________________________");
                                if (data.contains(sender) && (data.contains(receiver))) {
                                    String groupeKey3 = postSnapshot.getKey();


                                    database.getReference("utilisateur").child(sender).child("conversation").child(groupeKey3).setValue(true);

                                    database.getReference("utilisateur").child(receiver).child("conversation").child(groupeKey3).setValue(true);


                                } else {


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

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });



        inputText.setText("");


    }


}
