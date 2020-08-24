package com.example.riyac.allthebest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Main4Activity extends AppCompatActivity {
    Button logoutbtn;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        logoutbtn=(Button)findViewById(R.id.button2);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Main4Activity.this, Main2Activity.class));
            }
        });
    }
}
