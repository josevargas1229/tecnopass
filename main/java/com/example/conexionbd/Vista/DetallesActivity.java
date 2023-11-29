package com.example.conexionbd.Vista;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conexionbd.R;
import com.squareup.picasso.Picasso;

public class DetallesActivity extends AppCompatActivity {
    TextView txtProducto;
    EditText txtDescripcion;
    ImageView imgProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        txtDescripcion=findViewById(R.id.txtDescripcion);
        imgProducto=findViewById(R.id.imgProducto);
        txtProducto=findViewById(R.id.txtProducto);
        Bundle parametros = getIntent().getExtras();
        String dato = parametros.getString("descripcion");
        String imagen = parametros.getString("foto");
        String producto=parametros.getString("nombre");
        txtProducto.setText(producto);
        txtDescripcion.setText(dato);
        Picasso.get().load(imagen).resize(350,350).into(imgProducto);
    }
}