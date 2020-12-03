package com.example.tubes_kelg_c;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.example.tubes_kelg_c.databinding.ActivityRecyclerViewListMotorBinding;

public class RecyclerViewListMotor extends RecyclerView.Adapter<RecyclerViewListMotor.MyViewHolder> {
    private Context context;
    public ArrayList<Motor> result;
    ActivityRecyclerViewListMotorBinding binding;

//    public RecyclerViewListMotor(ArrayList<Motor> ListMotor){}

    public RecyclerViewListMotor(Context context, ArrayList<Motor> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycler_view_list_motor, parent, false);
//        final MyViewHolder holder = new MyViewHolder(v);
//
//        return holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater,
                R.layout.activity_recycler_view_list_motor, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Motor mtr = result.get(position);
//        holder.nama.setText(mtr.getNama());
//        holder.jenis.setText(mtr.getJenis());
//        holder.harga.setText(mtr.getharga());
        holder.bind(mtr);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ActivityRecyclerViewListMotorBinding binding;
//        private TextView nama, jenis, harga;
//        private CardView parent;

        public MyViewHolder(@NonNull ActivityRecyclerViewListMotorBinding binding){
            super(binding.getRoot());
//            nama = itemView.findViewById(R.id.tvMotor);
//            jenis = itemView.findViewById(R.id.tvJenis);
//            harga = itemView.findViewById(R.id.tvHarga);
//            parent = itemView.findViewById(R.id.ParentAdapter);
            this.binding = binding;
        }

        public void bind(Object object){
            binding.setVariable(BR.mtr, object);
            binding.executePendingBindings();
        }
    }
}