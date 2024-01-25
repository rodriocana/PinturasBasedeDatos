package com.example.pinturasbasededatos.Vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinturasbasededatos.Modelo.Pinturas;
import com.example.pinturasbasededatos.R;

import java.util.List;

public class PinturasAdapter extends RecyclerView.Adapter<PinturasAdapter.ViewHolder> {

    private List<Pinturas> pinturasList;
    private Context context;

    public PinturasAdapter(List<Pinturas> pinturasList, Context context) {
        this.pinturasList = pinturasList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pinturas pintura = pinturasList.get(position);

        holder.txtTitulo.setText(pintura.getTitulo());
        holder.txtAutor.setText(pintura.getAutor());
        holder.txtAnio.setText(String.valueOf(pintura.getAnio()));
    }

    @Override
    public int getItemCount() {
        return pinturasList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtAutor, txtAnio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txxTituloPintura);
            txtAutor = itemView.findViewById(R.id.txtAutorPintura);
            txtAnio = itemView.findViewById(R.id.txtAnioPintura);
        }
    }
}