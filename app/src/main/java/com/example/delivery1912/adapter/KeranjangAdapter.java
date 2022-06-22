package com.example.delivery1912.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.delivery1912.R;
import com.example.delivery1912.model.KeranjangModel;
import com.example.delivery1912.model.Menu;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.MyViewHolder> {

    private Context context;
    private List<KeranjangModel> list;
    private MenuAdapter.Dialog dialog;

    public KeranjangAdapter(Context context, List<KeranjangModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View itemView   = LayoutInflater.from(parent.getContext()).inflate(R.layout.keranjang_item,parent,false);
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.keranjang_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.harga.setText(list.get(position).getHarga());
        holder.deskripsi.setText(list.get(position).getDesk());
        holder.jumlah.setText(list.get(position).getJumlah());
        holder.totalHarga.setText(list.get(position).getHargaTotal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, harga, deskripsi, jumlah, totalHarga;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cartName);
            harga = itemView.findViewById(R.id.cartHarga);
            deskripsi = itemView.findViewById(R.id.cartDesk);
            jumlah = itemView.findViewById(R.id.cartJumlah);
            totalHarga = itemView.findViewById(R.id.cartTotal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null) {
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
