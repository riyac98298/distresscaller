package com.example.riyac.allthebest;

//import android.support.annotation.NonNull;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
     EditText email, password;
     TextView signup;
     Button signinbtn;
    TextView textView4;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView4=(TextView)findViewById(R.id.textView4);

        mAuth = FirebaseAuth.getInstance();
        email= (EditText) findViewById(R.id.editText);
        password= (EditText) findViewById(R.id.editText2);
        signup=(TextView)findViewById(R.id.textView);
        signinbtn=(Button)findViewById(R.id.button);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = email.getText().toString();
                String pass = password.getText().toString();
                if (id.isEmpty() && !(pass.isEmpty()))
                {
                    email.setError("Please enter email id");
                    email.requestFocus();
                }
                else if (pass.isEmpty() && !(id.isEmpty()))
                {
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                else if (pass.isEmpty() && id.isEmpty())
                {
                    Toast.makeText(Main2Activity.this, "Fields are empty!", Toast.LENGTH_LONG ).show();
                    email.requestFocus();
                }
                else if(!pass.isEmpty() && !id.isEmpty())
                {
                    mAuth.signInWithEmailAndPassword(id, pass).addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                        public static final String TAG = "";

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Main2Activity.this, "SignIn is unsuccessful. Please try again!", Toast.LENGTH_LONG ).show();
                            }
                            else
                            {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                Toast.makeText(Main2Activity.this, "SignIn successful!", Toast.LENGTH_SHORT ).show();
                                startActivity(new Intent(Main2Activity.this, HomeActivity.class));
                                finish();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Main2Activity.this, "Something is wrong. Try again later.", Toast.LENGTH_LONG ).show();
                }
            }
        });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(Main2Activity.this, Main3Activity.class);
                    startActivity(intent);
                    finish();
                }
            });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, HomeActivity.class));
            }
        });

    }
}
