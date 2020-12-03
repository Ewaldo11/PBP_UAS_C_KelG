package com.example.tubes_kelg_c;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;

import com.example.tubes_kelg_c.databinding.FragmentBookingBinding;

public class BookingFragment extends Fragment {
    private ArrayList<Motor> ListMotor;
    private RecyclerView recyclerView;
    private RecyclerViewListMotor adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnSewa, btnDaftarbooking;
    FragmentBookingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container,false);
        View view = binding.getRoot();
        btnSewa = (Button) view.findViewById(R.id.btnSewa);
        btnDaftarbooking = (Button) view.findViewById(R.id.btnDaftarBooking);
        //get data mahasiswa
        ListMotor = new DaftarMotor().MOTOR;

        adapter = new RecyclerViewListMotor(getContext(), ListMotor);
        binding.recyclerViewMotor.setLayoutManager(new GridLayoutManager(getContext(), 1));
        binding.recyclerViewMotor.setAdapter(adapter);


        //recycler view
//        recyclerView = view.findViewById(R.id.recycler_view_motor);
//        mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        adapter = new RecyclerViewListMotor(getContext(), ListMotor);
//        recyclerView.setAdapter(adapter);

        btnSewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SewaMotor.class));
            }
        });


        btnDaftarbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),DaftarBooking.class));
            }
        });

        return view;
    }

}