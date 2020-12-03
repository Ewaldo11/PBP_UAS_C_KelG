package com.example.tubes_kelg_c;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tubes_kelg_c.database.DatabaseClient;

import java.util.List;

public class DaftarBooking extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_booking);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.penyewa_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = findViewById(R.id.btnback);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPenyewa();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        getPenyewa();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarBooking.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getPenyewa(){
        class GetPenyewa extends AsyncTask<Void, Void, List<Penyewa>> {

            @Override
            protected List<Penyewa> doInBackground(Void... voids) {
                List<Penyewa> penyewaList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getDatabase()
                        .penyewaDao()
                        .getAll();
                return penyewaList;
            }

            @Override
            protected void onPostExecute(List<Penyewa> penyewa) {
                super.onPostExecute(penyewa);
                BookingRecyclerViewAdapter adapter = new BookingRecyclerViewAdapter(DaftarBooking.this, penyewa);
                recyclerView.setAdapter(adapter);
                if (penyewa.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
                }
            }
        }

        GetPenyewa get = new GetPenyewa();
        get.execute();
    }
}