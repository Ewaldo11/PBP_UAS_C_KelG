package com.example.tubes_kelg_c;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes_kelg_c.api.UserAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class DaftarBooking extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingRecyclerViewAdapter adapter;
    private List<Penyewa> listPenyewa;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_booking);
        listPenyewa = new ArrayList<Penyewa>();
        adapter = new BookingRecyclerViewAdapter(DaftarBooking.this, listPenyewa);

        recyclerView = findViewById(R.id.recycler_view_booking);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarBooking.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        back = findViewById(R.id.btnback);

        getPenyewa();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarBooking.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getPenyewa() {
        //Pendeklarasian queue
        RequestQueue queue = Volley.newRequestQueue(DaftarBooking.this);

        //Meminta tanggapan string dari URL yang telah disediakan menggunakan method GET
        //untuk request ini tidak memerlukan parameter
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(DaftarBooking.this);
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Menampilkan data Review");
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, UserAPI.URL_SELECT
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Disini bagian jika response jaringan berhasil tidak terdapat ganguan/error
                progressDialog.dismiss();
                try {
                    //Mengambil data response json object yang berupa data mahasiswa
                    JSONArray jsonArray = response.getJSONArray("data");

                    if(!listPenyewa.isEmpty())
                        listPenyewa.clear();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        //Mengubah data jsonArray tertentu menjadi json Object
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        int idPenyewa = jsonObject.optInt("id");
                        String nama             = jsonObject.optString("nama_user");
                        String notelp           = jsonObject.optString("no_telp");
                        String jenis_motor    = jsonObject.optString("nama_motor");
                        String harga            = jsonObject.optString("harga_motor");

                        //Membuat objek user
                        Penyewa penyewa = new Penyewa(idPenyewa, nama, notelp,  jenis_motor, Integer.parseInt(harga));

                        //Menambahkan objek user tadi ke list user
                        listPenyewa.add(penyewa);
                    }
                    adapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
                Toast.makeText(DaftarBooking.this, response.optString("message"),
                        Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                progressDialog.dismiss();
                Toast.makeText(DaftarBooking.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }
}