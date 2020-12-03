package com.example.tubes_kelg_c;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.tubes_kelg_c.database.DatabaseClient;
import com.google.android.material.textfield.TextInputEditText;


public class UpdateBooking extends Fragment {
    Penyewa penyewa;
    TextInputEditText nama, notelp;
    Button saveBtn, deleteBtn, cancelBtn;
    AutoCompleteTextView motor;
    String updatemotor, harga;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_booking, container, false);
        penyewa = (Penyewa) getArguments().getSerializable("penyewa");
        nama = view.findViewById(R.id.update_nama);
        notelp = view.findViewById(R.id.update_telp);
        motor = view.findViewById(R.id.update_motor);
        saveBtn = view.findViewById(R.id.btn_save);
        deleteBtn = view.findViewById(R.id.btn_delete);
        cancelBtn = view.findViewById(R.id.btn_cancel);


        try{
            if(penyewa.getNama() != null){
                nama.setText(penyewa.getNama());
            }else{
                nama.setText("-");
            }if(penyewa.getTelp() != null){
                notelp.setText(penyewa.getTelp());
            }else{
                notelp.setText("-");
            }if(penyewa.getNamaMotor() != null){
                motor.setText(penyewa.getNamaMotor());
            }else{
                motor.setText("-");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        String[] UPDATEMOTOR = new String[] {"Vario", "Beat", "Vespa", "Mio", "NMax", "PCX", "Revo"};
        ArrayAdapter<String> adapter_motor = new ArrayAdapter<String>(getActivity(), R.layout.dropdown_menu_popup_item, UPDATEMOTOR);
        motor.setAdapter(adapter_motor);

        motor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String motor = adapterView.getItemAtPosition(i).toString();
                updatemotor = motor;
                if(updatemotor == "Vario"){
                    harga = "Rp 80.000" ;
                }else if(updatemotor == "Beat"){
                    harga = "Rp 70.000";
                }else if(updatemotor == "Vespa"){
                    harga = "Rp 150.000";
                }else if(updatemotor == "Mio"){
                    harga = "Rp 60.000";
                }else if(updatemotor == "NMax"){
                    harga = "Rp 120.000";
                }else if(updatemotor == "PCX"){
                    harga = "Rp 130.000";
                }else if(updatemotor == "Revo"){
                    harga = "Rp 70.000";
                }
            }
        });

         return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penyewa.setNama(nama.getText().toString());
                penyewa.setTelp(notelp.getText().toString());
                penyewa.setNamaMotor(motor.getText().toString());
                penyewa.setHarga(harga);
                update(penyewa);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Warning !");
                builder.setMessage("Hapus Booking ini?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(penyewa);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateBooking.this).commit();
            }
        });
    }

    private void update(final Penyewa penyewa){
        class UpdatePenyewa extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .penyewaDao()
                        .update(penyewa);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Booking updated", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateBooking.this).commit();
            }
        }

        UpdatePenyewa update = new UpdatePenyewa();
        update.execute();
    }

    private void delete(final Penyewa penyewa){
        class DeletePenyewa extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .penyewaDao()
                        .delete(penyewa);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Booking deleted", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdateBooking.this).commit();
            }
        }

        DeletePenyewa delete = new DeletePenyewa();
        delete.execute();
    }
}