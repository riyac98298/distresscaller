package com.example.riyac.allthebest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MyAccountActivity extends AppCompatActivity {
    ImageView profilepic, editname;
    TextView username;
    Button logout;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        profilepic=(ImageView)findViewById(R.id.imageView4);
        editname=(ImageView)findViewById(R.id.imageView7);
        username=(TextView)findViewById(R.id.textView9);
        logout=(Button)findViewById(R.id.logout);
        builder=new AlertDialog.Builder(MyAccountActivity.this);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               builder.setMessage("Do you want to logout?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        Toast.makeText(getApplicationContext(), "You are logged out!", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MyAccountActivity.this, Main2Activity.class));

                   }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.setTitle("Logout");
                alertDialog.setIcon(R.drawable.common_google_signin_btn_icon_dark);
                alertDialog.show();

            }
        });



    }
}
