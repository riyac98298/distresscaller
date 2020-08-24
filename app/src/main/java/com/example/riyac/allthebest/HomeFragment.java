package com.example.riyac.allthebest;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telecom.Call;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.provider.ContactsContract.Intents.Insert.ACTION;


public class HomeFragment extends Fragment {
    ImageView call, message;
    DatabaseReference reference;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        call = (ImageView) view.findViewById(R.id.imageView5);
        message = (ImageView) view.findViewById(R.id.imageView6);

        //Toast.makeText(getContext(), "Firebase successful", Toast.LENGTH_SHORT).show();

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                reference = FirebaseDatabase.getInstance().getReference().child("Member").child("1");
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String str = dataSnapshot.child("phonenumber").getValue().toString();

                    String str="7985868489";
                        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + str));
                        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(intent);

//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    MyMessage();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, 0);
                }

            }

            private void MyMessage() {
                String phonenumber = "7985868489";
                String message = "helllooo";

                if (!phonenumber.equals("") || !message.equals("")) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phonenumber, null, message, null, null);
                    Toast.makeText(getContext(), "Sending Message..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Some error occured", Toast.LENGTH_SHORT).show();
                }
            }


            public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
            {
                onRequestPermissionsResult(requestCode, permissions, grantResults);

                switch (requestCode) {
                    case 0:
                        if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            MyMessage();
                        }
                        else {
                            Toast.makeText(getContext(), "You do not have required permissions.", Toast.LENGTH_LONG).show();
                        }
                }
            }

        });


        return view;
    }


}
