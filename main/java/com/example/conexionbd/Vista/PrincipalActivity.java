package com.example.conexionbd.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conexionbd.Modelo.Adaptador;
import com.example.conexionbd.Presentador.Presentador_Listaproductos;
import com.example.conexionbd.R;
import com.squareup.picasso.Picasso;

public class PrincipalActivity extends AppCompatActivity{
    TextView txtUser;
    ImageView imgUser;
    RecyclerView rcvlista;
    EditText txtBuscar;
    Button btnBuscar;
    public static Context VistaPrincipal;
    Presentador_Listaproductos P;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txtUser=findViewById(R.id.txtUser);
        imgUser=findViewById(R.id.imgUser);
        rcvlista=findViewById(R.id.rcvLista);
        txtBuscar=findViewById(R.id.txtBuscar);
        btnBuscar=findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(this::eventoBuscar);
        rcvlista.setLayoutManager(new GridLayoutManager(this,1));

        Bundle parametros = getIntent().getExtras();
        String dato = parametros.getString("usuario");
        String imagen = parametros.getString("foto");

        txtUser.setText(dato);
        Picasso.get().load("http://189.240.192.140/php20221015/imagenes/" + imagen).resize(350,350).into(imgUser);
        VistaPrincipal=this;
        P=new Presentador_Listaproductos(this);
    }

    private void eventoBuscar(View vista) {
        P.buscarproductos(txtBuscar.getText().toString());
    }

    public void actualizarlistaproductos(Adaptador AdaptadorRecycler){
        rcvlista.setAdapter(AdaptadorRecycler);
    }
    public void cargarlistaproductos(Adaptador AdaptadorRecycler)
    {
        rcvlista.setAdapter(AdaptadorRecycler);
    }

}