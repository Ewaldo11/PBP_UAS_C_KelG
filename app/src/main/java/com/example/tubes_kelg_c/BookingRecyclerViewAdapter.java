package com.example.tubes_kelg_c;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingRecyclerViewAdapter extends RecyclerView.Adapter<BookingRecyclerViewAdapter.BookingViewHolder> {

    private Context context;
    private List<Penyewa> penyewaList;

    public BookingRecyclerViewAdapter(Context context, List<Penyewa> penyewaList) {
        this.context = context;
        this.penyewaList = penyewaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_penyewa, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Penyewa penyewa = penyewaList.get(position);
        holder.textView.setText("Nama   : " + penyewa.getNama());
        holder.textView1.setText("NoTelp: "+ penyewa.getTelp());
        holder.textView2.setText("Motor : "+ penyewa.getNamaMotor());
        holder.textView3.setText("Harga : "+penyewa.getHarga());
    }

    @Override
    public int getItemCount() {
        return penyewaList.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView, textView1, textView2, textView3;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name_text);
            textView1 = itemView.findViewById(R.id.notelp_text);
            textView2 = itemView.findViewById(R.id.motor_text);
            textView3 = itemView.findViewById(R.id.harga_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Penyewa penyewa = penyewaList.get(getAdapterPosition());
            Bundle data = new Bundle();
            data.putSerializable("penyewa", penyewa);
            UpdateBooking updateBooking = new UpdateBooking();
            updateBooking.setArguments(data);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, updateBooking)
                    .commit();
        }
    }
}