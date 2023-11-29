package com.example.conexionbd.Modelo;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.conexionbd.Presentador.Presentador_Codigo;
import com.example.conexionbd.Vista.MainActivity;
import com.example.conexionbd.Vista.RecuperacionActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Modelo_codigo implements Interface_ModeloC{
    Presentador_Codigo P;
    String email;
    String datoUsuarioEncontrado;
    int id;

    public Modelo_codigo(Presentador_Codigo P) {
        this.P=P;

    }

    public void validar(String email) {
        this.email=email;
        consulta(email);
    }
    public void consulta(String email)
    {
        //inicio metodo
        String URL="http://189.240.192.140/php20221015/buscacorreo.php"; //Servicio Web
        //Esta es la petición de Volley
        StringRequest respuesta= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try{
                    JSONArray datos=new JSONArray(response);
                    for (int x = 0; x < datos.length(); x++) {
                        JSONObject valores = datos.getJSONObject(x);
                        datoUsuarioEncontrado = valores.getString("email");
                        id = valores.getInt("id");
                    }
                    P.validado(datoUsuarioEncontrado, id);

                }catch(Exception e){
                    Toast.makeText(RecuperacionActivity.contexto, "No se encontró el correo", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecuperacionActivity.contexto, error.getMessage(), Toast.LENGTH_LONG).show();


            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("email",email);
                return parametros;
            }
        };

        RequestQueue envio= Volley.newRequestQueue(RecuperacionActivity.contexto);
        envio.add(respuesta);

    }

    @Override
    public void actualizar(String codigo, int id) {
        //inicio metodo
        String URL="http://189.240.192.140/php20221015/colocarcodigo.php"; //Servicio Web
        //Esta es la petición de Volley
        StringRequest respuesta= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RecuperacionActivity.contexto, "Error al actualizar", Toast.LENGTH_LONG).show();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("codigo",codigo);
                parametros.put("id",String.valueOf(id));
                return parametros;
            }
        };

        RequestQueue envio= Volley.newRequestQueue(RecuperacionActivity.contexto);
        envio.add(respuesta);
    }

    @Override
    public void validarCodigo(String email, String codigo) {
        //inicio metodo
        String URL="http://189.240.192.140/php20221015/validarcodigo.php"; //Servicio Web
        //Esta es la petición de Volley
        StringRequest respuesta= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try{
                    JSONArray datos=new JSONArray(response);
                    for(int x=0;x<datos.length();x++)
                    {
                        JSONObject valores= datos.getJSONObject(x);
                        id=valores.getInt("id");
                    }
                    P.codigoValidado(id);

                }catch(Exception e){
                    Toast.makeText(RecuperacionActivity.contexto, "El código no es válido", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecuperacionActivity.contexto, "El código no es válido", Toast.LENGTH_LONG).show();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("codigo",codigo);
                parametros.put("email",email);
                return parametros;
            }
        };

        RequestQueue envio= Volley.newRequestQueue(RecuperacionActivity.contexto);
        envio.add(respuesta);
    }

}
