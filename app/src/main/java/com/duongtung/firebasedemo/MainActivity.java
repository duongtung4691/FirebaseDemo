package com.duongtung.firebasedemo;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextAddress;
    private TextView textViewPersons;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.INTERNET},
                1);

        setContentView(R.layout.activity_main);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);

        textViewPersons = (TextView) findViewById(R.id.textViewPersons);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Creating firebase object
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");


                //Getting values to store
                String name = editTextName.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();

                //Creating Person object
                Account person = new Account();

                //Adding values
                person.setName(name);
                person.setAddress(address);

                //Storing values to firebase
                myRef.child("Person").setValue(person);


                //Value event listener for realtime data update
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            //Getting the data from snapshot
                            Account person = postSnapshot.getValue(Account.class);

                            //Adding it to a string
                            String string = "Name: " + person.getName() + "\nAddress: " + person.getAddress() + "\n\n";

                            //Displaying it on textview
                            textViewPersons.setText(string);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });
            }
        });

    }
}
