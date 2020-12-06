package com.example.tubes_kelg_c;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes_kelg_c.api.ReviewAPI;
import com.example.tubes_kelg_c.api.UserAPI;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.PUT;


public class UpdateBooking extends Fragment {
    Penyewa penyewa;
    TextInputEditText nama, notelp;
    Button saveBtn, deleteBtn, cancelBtn;
    AutoCompleteTextView motor;
    String updatemotor, harga,namaP,telp;
    private int idPenyewa;
    private Context context;

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
        idPenyewa = penyewa.getIdPenyewa();

        try{
            if(penyewa.getNamaPenyewa() != null){
                nama.setText(penyewa.getNamaPenyewa());
            }else{
                nama.setText("-");
            }if(penyewa.getNoTelp() != null){
                notelp.setText(penyewa.getNoTelp());
            }else{
                notelp.setText("-");
            }if(penyewa.getJenisMotor() != null){
                motor.setText(penyewa.getJenisMotor());
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
                    harga = "80000" ;
                }else if(updatemotor == "Beat"){
                    harga = "70000";
                }else if(updatemotor == "Vespa"){
                    harga = "150000";
                }else if(updatemotor == "Mio"){
                    harga = "60000";
                }else if(updatemotor == "NMax"){
                    harga = "120000";
                }else if(updatemotor == "PCX"){
                    harga = "130000";
                }else if(updatemotor == "Revo"){
                    harga = "70000";
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
                String namaP = nama.getText().toString();
                String telp = notelp.getText().toString();
                String namaMotor = motor.getText().toString();

                Penyewa penyewa = new Penyewa(idPenyewa, nama.getText().toString(), notelp.getText().toString(),
                        motor.getText().toString(), Integer.parseInt(harga));
                update(penyewa);
//                Intent i = new Intent(getContext(), DaftarBooking.class);
//                startActivity(i);
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
                        Intent i = new Intent(getContext(), DaftarBooking.class);
                        startActivity(i);
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

    public void update(Penyewa penyewa){
        RequestQueue queue  =  Volley.newRequestQueue(getContext());

        final ProgressDialog progressDialog;
        progressDialog  =  new  ProgressDialog(getContext());
        progressDialog.setMessage("loading 	");
        progressDialog.setTitle("Mengubah data penyewa");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(PUT, "https://rentalmotor.site/api/motor/"+penyewa.getIdPenyewa(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), DaftarBooking.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String>  getParams(){
                Map<String,  String>    params  =  new HashMap<String,  String>();
                params.put("nama_user",  penyewa.getNamaPenyewa());
                params.put("nama_motor",  penyewa.getJenisMotor());
                params.put("no_telp",  penyewa.getNoTelp());
                params.put("harga_motor", penyewa.getStringHarga());

                return  params;
            }
        };
        queue.add(stringRequest);
    }

    public void delete(Penyewa penyewa){
        //Pendeklarasian queue
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menghapus data penyewa");
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        //Memulai membuat permintaan request menghapus data ke jaringan
        StringRequest stringRequest = new StringRequest(DELETE, UserAPI.URL_DELETE + penyewa.getIdPenyewa(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Disini bagian jika response jaringan berhasil tidak terdapat ganguan/error
                progressDialog.dismiss();
                try {
                    //Mengubah response string menjadi object
                    JSONObject obj = new JSONObject(response);
                    //obj.getString("message") digunakan untuk mengambil pesan message dari response
                    Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }
}