package com.example.conexionbd.Presentador;

import android.util.Log;
import android.widget.Toast;

import com.example.conexionbd.Modelo.Interface_ModeloC;
import com.example.conexionbd.Modelo.Modelo_codigo;
import com.example.conexionbd.Vista.RecuperacionActivity;

public class Presentador_Codigo {
    RecuperacionActivity v;
    Interface_ModeloC m;

    public Presentador_Codigo(RecuperacionActivity v) {
        this.v = v;
        m=new Modelo_codigo(this);
    }

    public void validar(String email) {
        m.validar(email);
    }

    public void validado(String miCorreo, int id) {
        v.validado(miCorreo, id);
    }

    public void actContra(String codigo,int id){
        m.actualizar(codigo,id);
    }
    public void validarCodigo(String email,String codigo){
        m.validarCodigo(email,codigo);
    }
    public void codigoValidado(int id){
        v.codigoValidado(id);
    }
}
