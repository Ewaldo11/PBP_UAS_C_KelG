package com.example.tubes_kelg_c;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubes_kelg_c.database.DatabaseClient;
import com.google.android.material.textfield.TextInputEditText;

public class SewaMotor extends AppCompatActivity {
    TextInputEditText nama,notelp;
    String pilihMotor;
    Button sewa;
    Penyewa penyewa;
    String harga;

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
                    harga = "Rp 80.000" ;
                }else if(pilihMotor == "Beat"){
                    harga = "Rp 70.000";
                }else if(pilihMotor == "Vespa"){
                    harga = "Rp 150.000";
                }else if(pilihMotor == "Mio"){
                    harga = "Rp 60.000";
                }else if(pilihMotor == "NMax"){
                    harga = "Rp 120.000";
                }else if(pilihMotor == "PCX"){
                    harga = "Rp 130.000";
                }else if(pilihMotor == "Revo"){
                    harga = "Rp 70.000";
                }
            }
        });

        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPenyewa(penyewa);
            }
        });
    }

    public void AddPenyewa(final Penyewa penyewa){
        final String namaPenyewa = nama.getText().toString();
        final String telp = notelp.getText().toString();
        final String motor = pilihMotor;
//        final String hargaSewa = harga.getText().toString();
        final String hargaSewa = harga;

        class addPenyewa extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Penyewa penyewa = new Penyewa();
                penyewa.setNama(namaPenyewa);
                penyewa.setTelp(telp);
                penyewa.setNamaMotor(motor);
                penyewa.setHarga(hargaSewa);
                DatabaseClient.getInstance(getApplicationContext()).getDatabase()
                        .penyewaDao()
                        .insert(penyewa);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Booking berhasil", Toast.LENGTH_SHORT).show();
                Intent sewaMotor = new Intent(SewaMotor.this, DaftarBooking.class);
                sewaMotor.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(sewaMotor);
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.hide(AddFragment.this).commit();
            }
        }

        addPenyewa add = new addPenyewa();
        add.execute();
    }

}