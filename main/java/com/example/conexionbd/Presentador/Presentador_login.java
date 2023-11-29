package com.example.conexionbd.Presentador;

import android.widget.Toast;

import com.example.conexionbd.Modelo.Interface_ModeloL;
import com.example.conexionbd.Modelo.Modelo_Login;
import com.example.conexionbd.Vista.MainActivity;

public class Presentador_login implements Interface_PresentadorL{
    MainActivity v;
    Interface_ModeloL m;

    public Presentador_login(MainActivity v) {
        this.v = v;
        m=new Modelo_Login(this);
    }

    @Override
    public void login(String usuario, String passw) {
        //Toast.makeText(MainActivity.contexto, usuario+" "+passw+" Presentador", Toast.LENGTH_SHORT).show();
        m.login(usuario,passw);
    }

    @Override
    public void acceso(String miusuario, String foto) {
        v.acceso(miusuario, foto);
    }
}
