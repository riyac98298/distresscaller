package com.example.riyac.allthebest;


import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UpdateFragment extends Fragment {

    TextView textView8;
    EditText phone;
    Button update;
    DatabaseReference reff;
    Member member;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_update, container, false);

        phone=(EditText)view.findViewById(R.id.editText3);
        update=(Button)view.findViewById(R.id.button4);
        textView8=(TextView)view.findViewById(R.id.textView8);

        member=new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phonenumber=phone.getText().toString();
                textView8.setText(phonenumber);

                member.setPhonenumber(phonenumber);
                reff.push().setValue(member);


                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_LONG).show();
                phone.setText("");




            }
        });

        return view;
    }

}
