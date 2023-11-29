package com.example.conexionbd.Modelo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conexionbd.R;
import com.example.conexionbd.Vista.DetallesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.VistaHolderProductos> {

    ArrayList<ClaseDatosProductos> productos;
    public Adaptador(ArrayList<ClaseDatosProductos> productos) {
        this.productos=productos;
    }

    @NonNull
    @Override
    public VistaHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View V= LayoutInflater.from(parent.getContext()).inflate(R.layout.productos,null,false);
        return new VistaHolderProductos(V);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VistaHolderProductos holder, @SuppressLint("RecyclerView") int position) {
        String foto="http://189.240.192.140/php20221015/imagenes/"+productos.get(position).getFoto();
        Picasso.get().load(foto).resize(300,300).into(holder.imgProducto);
        holder.txtNombre.setText(productos.get(position).getNombre());
        holder.txtPrecio.setText("Precio: $"+ productos.get(position).getPrecio());
        holder.txtStock.setText("Existencias: "+ productos.get(position).getExistencia() +" unidades");
        holder.btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent v3 = new Intent(v.getContext(),DetallesActivity.class);
                v3.putExtra("nombre","Nombre: "+productos.get(position).getNombre());
                v3.putExtra("descripcion","Descripción: "+productos.get(position).getDescripcion());
                v3.putExtra("foto",foto);
                v.getContext().startActivity(v3);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class VistaHolderProductos extends RecyclerView.ViewHolder{
        ImageView imgProducto;
        TextView txtNombre;
        TextView txtPrecio;
        TextView txtStock;
        Button btnDetalles;
        public VistaHolderProductos(@NonNull View itemView) {
            super(itemView);
            imgProducto=itemView.findViewById(R.id.imgproductos);
            txtNombre=itemView.findViewById(R.id.txtNombre);
            txtPrecio=itemView.findViewById(R.id.txtPrecio);
            txtStock=itemView.findViewById(R.id.txtStock);
            btnDetalles=itemView.findViewById(R.id.btnDetalles);
        }
    }
}

