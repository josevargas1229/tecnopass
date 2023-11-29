package com.example.conexionbd.Presentador;

import com.example.conexionbd.Modelo.Interface_ModeloA;
import com.example.conexionbd.Modelo.Modelo_Actualizar;
import com.example.conexionbd.Vista.ActualizarActivity;

public class Presentador_Actualizar {
    ActualizarActivity v;
    Interface_ModeloA m;

    public Presentador_Actualizar(ActualizarActivity v) {
        this.v = v;
        m=new Modelo_Actualizar(this);
    }
    public void actualizar(int id, String pass){
        m.actualizar(id,pass);
    }
    public void actualizado(){
        v.actualizado();
    }
}
