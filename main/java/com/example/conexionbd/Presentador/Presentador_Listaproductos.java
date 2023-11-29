package com.example.conexionbd.Presentador;

import android.widget.Toast;

import com.example.conexionbd.Modelo.Adaptador;
import com.example.conexionbd.Modelo.Modelo_listaproductos;
import com.example.conexionbd.Vista.PrincipalActivity;

public class Presentador_Listaproductos {
    PrincipalActivity V;
    Modelo_listaproductos M;
    public Presentador_Listaproductos(PrincipalActivity V) {
        this.V=V;
        M=new Modelo_listaproductos(this);
    }
    public void cargarlistaproductos(Adaptador AdaptadorRecycler)
    {
        V.cargarlistaproductos(AdaptadorRecycler);
    }
    public void buscarproductos(String buscado){
        M.ActualizarLista(buscado);
    }
    public void actualizarlistaproductos(Adaptador AdaptadorRecycler)
    {
        V.actualizarlistaproductos(AdaptadorRecycler);
    }
}

