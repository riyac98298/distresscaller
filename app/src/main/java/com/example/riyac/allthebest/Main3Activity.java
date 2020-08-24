package com.example.riyac.allthebest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class Main3Activity extends AppCompatActivity {

    EditText email, password;
    TextView signin;
    Button signupbtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        signin = (TextView) findViewById(R.id.textView);
        signupbtn = (Button) findViewById(R.id.button);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String id = email.getText().toString();
                String pass = password.getText().toString();
                if (id.isEmpty() && !(pass.isEmpty())) {
                    email.setError("Please enter email id");
                    email.requestFocus();
                } else if (pass.isEmpty() && !(id.isEmpty())) {
                    password.setError("Please enter password");
                    password.requestFocus();
                } else if (pass.isEmpty() && id.isEmpty()) {
                    Toast.makeText(Main3Activity.this, "Fields are empty!", Toast.LENGTH_LONG).show();
                    email.requestFocus();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(id, pass).addOnCompleteListener(Main3Activity.this, new OnCompleteListener<AuthResult>() {

                        // public static final String TAG = "";

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(Main3Activity.this, "SignUp is unsuccessful. Please try again!", Toast.LENGTH_LONG).show();
                            } else {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                Toast.makeText(Main3Activity.this, "SignUp successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Main3Activity.this, HomeActivity.class));
                            }
                        }
                    });
                 //   Toast.makeText(Main3Activity.this, "Something is wrong. Try again later.", Toast.LENGTH_LONG).show();
                }


            }
        });


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();

                if (mFirebaseUser != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + mFirebaseUser.getUid());
                    Toast.makeText(Main3Activity.this, "You're signed up!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main3Activity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    //Toast.makeText(Main3Activity.this, "Some error occurred. Please SignUp again.", Toast.LENGTH_LONG).show();
                }
            }
        };



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }


}
