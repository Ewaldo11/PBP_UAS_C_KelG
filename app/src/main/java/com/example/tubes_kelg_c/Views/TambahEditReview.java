package com.example.tubes_kelg_c.Views;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes_kelg_c.api.ReviewAPI;
import com.example.tubes_kelg_c.Model.Review;
import com.example.tubes_kelg_c.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;
import static com.android.volley.Request.Method.PUT;

public class TambahEditReview extends Fragment {
    private TextInputEditText txtNama, txtReview;
    private Button btnSimpan, btnBatal;
    private String status, selectedJenisMotor;
    private Review review;
    private View view;
    private int idReview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tambah_edit, container, false);
        setAtribut(view);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNama.getText().length()<1  || txtReview.getText().length()<1)
                {
                    if(txtNama.getText().length()<1)
                        txtNama.setError("Nama Tidak Boleh Kosong");
                    if(txtNama.getText().length()<1)
                        txtNama.setError("Review Tidak Boleh Kosong");
                }
                else
                {
                    String nama     = txtNama.getText().toString();
                    String review      = txtReview.getText().toString();

                    if(status.equals("tambah"))
                        tambahReview(nama, review, selectedJenisMotor);
                    else
                        editReview(idReview, nama, review, selectedJenisMotor);
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void setAtribut(View view){
        review   = (Review) getArguments().getSerializable("review");
        txtNama     = view.findViewById(R.id.txtNama);
        txtReview   = view.findViewById(R.id.txtReview);
        btnSimpan   = view.findViewById(R.id.btnSimpan);
        btnBatal    = view.findViewById(R.id.btnBatal);

        status = getArguments().getString("status");
        final String[] JMArray = getResources().getStringArray(R.array.jenisMotor);

        if(status.equals("tambah"))
        {
            selectedJenisMotor = JMArray[0];
        }
        else
        {
            idReview = review.getIdReview();
            txtNama.setText(review.getNamaUser());
            txtReview.setText(review.getReviewMotor());
            for(String jm : JMArray)
            {
                if(jm.equals(review.getJenisMotor()))
                    selectedJenisMotor = jm;
            }
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.jenisMotor, android.R.layout.simple_spinner_item);
        final AutoCompleteTextView jenisMotorDropdown = view.findViewById(R.id.txtJenisMotor);
        jenisMotorDropdown.setText(selectedJenisMotor);
        jenisMotorDropdown.setAdapter(adapter);
        jenisMotorDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedJenisMotor = jenisMotorDropdown.getEditableText().toString();
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            fragmentTransaction.setReorderingAllowed(false);
        }
        fragmentTransaction.replace(R.id.tambah_edit_fragment, fragment)
                .detach(this)
                .attach(this)
                .commit();
    }

    public void closeFragment(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(TambahEditReview.this).detach(this)
                .attach(this).commit();
    }

    public void tambahReview(final String nama, final String review, final String jm){
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final  ProgressDialog  progressDialog;
        progressDialog  =  new  ProgressDialog(getContext());
        progressDialog.setMessage("loading 	");
        progressDialog.setTitle("Menambahkan  data  review");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(POST, ReviewAPI.URL_ADD , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("message").equals("Add Review Success")) {
                        loadFragment(new FragmentReview());
                    }
                    Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
            protected Map<String, String> getParams(){
                Map<String,  String>    params  =  new  HashMap<String,  String>();
                params.put("nama_user",  nama);
                params.put("nama_motor",  jm);
                params.put("review_user", review);

                return  params;

            }
        };

        queue.add(stringRequest);

    }

    public void editReview(final int idReview, final String nama, final String review, final String jm){
        RequestQueue  queue  =  Volley.newRequestQueue(getContext());

        final  ProgressDialog  progressDialog;
        progressDialog  =  new  ProgressDialog(getContext());
        progressDialog.setMessage("loading 	");
        progressDialog.setTitle("Mengubah data review");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(PUT, ReviewAPI.URL_UPDATE + idReview, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    loadFragment(new FragmentReview());
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
            protected Map<String, String> getParams(){
                Map<String,  String>    params  =  new  HashMap<String,  String>();
                params.put("nama_user",  nama);
                params.put("nama_motor",  jm);
                params.put("review_user", review);

                return  params;

            }
        };
        queue.add(stringRequest);
    }
}
