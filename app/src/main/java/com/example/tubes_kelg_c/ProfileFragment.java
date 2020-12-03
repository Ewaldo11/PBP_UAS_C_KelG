package com.example.tubes_kelg_c;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tubes_kelg_c.camera.Camera;
import com.example.tubes_kelg_c.splash.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
    ImageButton open_camera;
    TextView textuser, txttelp, txtemail, txtfullname;
    Button btn_edit;
    private static final String USERS ="users";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        open_camera = view.findViewById(R.id.open_camera);

        SharedPreferences prefs = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        String email = prefs.getString("email", "");

        txtfullname = (TextView) view.findViewById(R.id.txtFullname);
        textuser = (TextView) view.findViewById(R.id.textUsername);
        txttelp = (TextView) view.findViewById(R.id.texttelp);
        txtemail = (TextView) view.findViewById(R.id.textemail);
        btn_edit = view.findViewById(R.id.btn_edit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(USERS);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)){
                        txtfullname.setText(ds.child("fullname").getValue(String.class));
                        textuser.setText(ds.child("username").getValue(String.class));
                        txttelp.setText(ds.child("notelp").getValue(String.class));
                        txtemail.setText(ds.child("email").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Camera.class));
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UpdateProfile.class));
            }
        });

        return view;
    }
}