package com.example.tubes_kelg_c;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes_kelg_c.api.UserAPI;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class SewaMotor extends AppCompatActivity {
    TextInputEditText nama,notelp;
    String pilihMotor,namaPenyewa,notelpPenyewa;
    Button sewa;
    Penyewa penyewa;
    String harga;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_motor);

        nama = findViewById(R.id.input_name);
        notelp = findViewById(R.id.input_telp);
        sewa = findViewById(R.id.sewaMotor);
//        harga = findViewById(R.id.tampil_harga);



        String[] INPUTMOTOR = new String[] {"Vario", "Beat", "Vespa", "Mio", "NMax", "PCX", "Revo"};

        AutoCompleteTextView namaMotor = findViewById(R.id.input_motor);

        ArrayAdapter<String> adapter_motor = new ArrayAdapter<String>(this, R.layout.dropdown_menu_popup_item, INPUTMOTOR);
        namaMotor.setAdapter(adapter_motor);
        namaMotor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String motor = adapterView.getItemAtPosition(i).toString();
                pilihMotor = motor;
                if(pilihMotor == "Vario"){
                    harga = "80000" ;
                }else if(pilihMotor == "Beat"){
                    harga = "70000";
                }else if(pilihMotor == "Vespa"){
                    harga = "150000";
                }else if(pilihMotor == "Mio"){
                    harga = "60000";
                }else if(pilihMotor == "NMax"){
                    harga = "120000";
                }else if(pilihMotor == "PCX"){
                    harga = "130000";
                }else if(pilihMotor == "Revo"){
                    harga = "70000";
                }
            }
        });

        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String namaPenyewa = nama.getText().toString();
//                String notelpPenyewa= notelp.getText().toString();
                String namaPenyewa = nama.getText().toString();
                String notelpPenyewa = notelp.getText().toString();
                String motor = namaMotor.getText().toString();
//                    penyewa.setNamaPenyewa(namaPenyewa);
//                    penyewa.setNoTelp(notelpPenyewa);
//                    penyewa.setJenisMotor(pilihMotor);
//                    penyewa.setHarga(harga);

//                AddPenyewa(nama.getText().toString(), notelp.getText().toString(), pilihMotor, harga);

                if(namaPenyewa.isEmpty() || notelpPenyewa.isEmpty() || motor.isEmpty())
                    Toast.makeText(SewaMotor.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_LONG).show();
                else{
                    //Double harga     = Double.parseDouble(txtHarga.getText().toString());
                    penyewa = new Penyewa(namaPenyewa, notelpPenyewa, motor, Integer.parseInt(harga));
                    AddPenyewa(penyewa);
//                    if(status.equals("tambah"))
//                        tambahBuku(namaBuku, pengarang, harga, image);
//                    else
//                        editBuku(idBuku, namaBuku, pengarang, harga, image);
                }
            }
        });
    }

    public void AddPenyewa(final Penyewa penyewa){
        RequestQueue queue = Volley.newRequestQueue(this);

        final ProgressDialog progressDialog;
        progressDialog  =  new  ProgressDialog(this);
        progressDialog.setMessage("loading 	");
        progressDialog.setTitle("Menambahkan  data  penyewa");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(POST, "https://rentalmotor.site/api/motor" , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("message").equals("Add Motor Success")) {
                        Intent intent = new Intent(SewaMotor.this, DaftarBooking.class);
                        startActivity(intent);
                    }
                    Toast.makeText(SewaMotor.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SewaMotor.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,  String>    params  =  new HashMap<String,  String>();
                params.put("nama_user",  penyewa.getNamaPenyewa());
                params.put("no_telp",  penyewa.getNoTelp());
                params.put("nama_motor",  penyewa.getJenisMotor());
                params.put("harga_motor", penyewa.getStringHarga());

                return  params;

            }
        };

        queue.add(stringRequest);

    }

}