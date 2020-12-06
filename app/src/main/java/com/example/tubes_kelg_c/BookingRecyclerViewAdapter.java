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
        View view = LayoutInflater.from(context).inflate(R.layout.activity_adapter_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingRecyclerViewAdapter.BookingViewHolder holder, int position) {
        final Penyewa penyewa = penyewaList.get(position);
        holder.textView.setText("Nama   : " + penyewa.getNamaPenyewa());
        holder.textView1.setText("NoTelp: "+ penyewa.getNoTelp());
        holder.textView2.setText("Motor : "+ penyewa.getJenisMotor());
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
            textView = itemView.findViewById(R.id.tvNama);
            textView1 = itemView.findViewById(R.id.tvNotelp);
            textView2 = itemView.findViewById(R.id.tvMotor);
            textView3 = itemView.findViewById(R.id.tvHarga);
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