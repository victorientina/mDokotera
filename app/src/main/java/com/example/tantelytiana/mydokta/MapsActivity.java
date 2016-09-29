package com.example.tantelytiana.mydokta;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String nom;
    private String adresse;
    private String lat;
    private String lng;
    private Double latitude;
    private Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("utilisateur");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    final String cle = postSnapshot.getKey();
                    final DatabaseReference myRef2 = myRef.child(cle);


                    myRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot2) {

                            for (DataSnapshot postSnapshot2 : snapshot2.getChildren()) {
                                String cle2 = postSnapshot2.getKey();


                                String data = postSnapshot2.getValue().toString();




                                if (cle2.equals("nom")) {
                                    nom=data;


                                }

                                if (cle2.equals("adresse")) {
                                    adresse=data;
                                }

                                if (data.contains("lng")) {
                                    String one = data.replace("=", ":");
                                    String two= one.replace("{","{\"");
                                    String three = two.replace(":","\":\"");
                                    String four = three.replace(",","\",\"");
                                    String five = four.replace("}","\"}");
                                    String sixe = five.replace("\" lat","\"lat");
                                    System.out.println(sixe);
                                    ObjectMapper mapper = new ObjectMapper();
                                    JsonNode actualObj = null;

                                    try {
                                        actualObj = mapper.readTree(sixe);

                                        lng = actualObj.path("lng").asText();
                                        lat = actualObj.path("lat").asText();

                                        longitude=Double.parseDouble(lat);

                                        latitude=Double.parseDouble(lat);
                                        LatLng dokotera = new LatLng(latitude, longitude);
                                        mMap.addMarker(new MarkerOptions().position(dokotera).title("Nom du cabinet medical  :" + nom).snippet(cle).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                                        mMap.moveCamera(CameraUpdateFactory.newLatLng(dokotera));

                                        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                                            @Override
                                            public View getInfoWindow(Marker arg0) {
                                                return null;
                                            }

                                            @Override
                                            public View getInfoContents(Marker marker) {

                                                Context context = getApplicationContext(); //or getActivity(), YourActivity.this, etc.

                                                LinearLayout info = new LinearLayout(context);
                                                info.setOrientation(LinearLayout.VERTICAL);

                                                TextView title = new TextView(context);
                                                title.setTextColor(Color.BLACK);
                                                title.setGravity(Gravity.CENTER);
                                                title.setTypeface(null, Typeface.BOLD);
                                                title.setText(marker.getTitle());

                                                TextView snippet = new TextView(context);
                                                snippet.setTextColor(Color.WHITE);
                                                snippet.setText(marker.getSnippet());

                                                info.addView(title);
                                                info.addView(snippet);

                                                return info;
                                            }
                                        });



                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }



                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError2) {
                            System.out.println("The read failed: " + databaseError2.getMessage());
                        }
                    });


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });



        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }




    }



}
