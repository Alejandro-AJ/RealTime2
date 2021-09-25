package com.example.realtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private String[] header={"Id","Latitud","Longitud","Fecha"};
    private DatabaseReference databaseReference;
    private static final String TAG = "MyActivity";
    private ArrayList<String[]> rows=new ArrayList<>();
    private int conta=0;
    private boolean bandera=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        databaseReference=FirebaseDatabase.getInstance().getReference();

        tableLayout=(TableLayout)findViewById(R.id.table);


        final TableDynamic tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeader(header);

        databaseReference.child("Informacion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              if(dataSnapshot.exists()){
                  if(bandera) {
                      for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                          Informacion coo = snapshot.getValue(Informacion.class);
                          Log.w(TAG, "Id ubicacion: " + coo.getID());
                          String[] item = new String[]{coo.getID(), coo.getC1(), coo.getC2(), coo.getFecha()};
                          // if(conta<=4){
                          rows.add(item);
                       /* }else {
                            tableDynamic.addItems(item);
                        }

                       */
                       bandera=false;
                          conta++;
                      }

                      //if(conta<=4){

                      Toast.makeText(getApplicationContext(),""+conta,Toast.LENGTH_LONG).show();
                      //tableDynamic.backgroundHeader(Color.BLUE);
                      tableDynamic.addData(rows, conta);
                      //}
                  }
                  else{
                      for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                          Informacion coo= snapshot.getValue(Informacion.class);
                          Log.w(TAG,"Id ubicacion: "+coo.getID());
                          String[] item=new String[]{coo.getID(),coo.getC1(),coo.getC2(),coo.getFecha()};

                            tableDynamic.addItems(item);

                      }
                  }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
