package com.example.conexionbd.Modelo;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.conexionbd.Presentador.Presentador_Actualizar;
import com.example.conexionbd.Vista.ActualizarActivity;

import java.util.HashMap;
import java.util.Map;

public class Modelo_Actualizar implements Interface_ModeloA{
    Presentador_Actualizar P;
    int id;
    String pass;
    public Modelo_Actualizar(Presentador_Actualizar P) {
        this.P=P;

    }

    @Override
    public void actualizar(int id, String pass) {
        this.id=id;
        this.pass=pass;
        consulta(id,pass);
    }

    public void consulta(int id, String pass) {
        //inicio metodo
        String URL="http://189.240.192.140/php20221015/actualizarcontra.php"; //Servicio Web
        //Esta es la petición de Volley
        StringRequest respuesta= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                P.actualizado();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActualizarActivity.contexto, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("password",pass);
                parametros.put("id",String.valueOf(id));
                return parametros;
            }
        };

        RequestQueue envio= Volley.newRequestQueue(ActualizarActivity.contexto);
        envio.add(respuesta);
    }
}
