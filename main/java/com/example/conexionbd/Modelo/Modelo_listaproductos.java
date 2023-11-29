package com.example.conexionbd.Modelo;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.conexionbd.Presentador.Presentador_Listaproductos;
import com.example.conexionbd.Vista.PrincipalActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Modelo_listaproductos implements Interface_Listaproductos{
    Presentador_Listaproductos P;

    String URL="http://189.240.192.140/php20221015/listaproductos.php";
    String URLBusca="http://189.240.192.140/php20221015/buscaproductos.php";

    public Modelo_listaproductos(Presentador_Listaproductos P) {
        this.P=P;
        MostrarLista(URL,"");
    }

    //aqui la peticion de Volley
    @Override
    public void MostrarLista(String url, String buscado) {
        ArrayList<ClaseDatosProductos> productos=new ArrayList<ClaseDatosProductos>();
        StringRequest respuesta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray datos = new JSONArray(response);
                    for (int x = 0; x < datos.length(); x++) {
                        JSONObject valores = datos.getJSONObject(x);
                        String nombre = valores.getString("nombre");
                        double precio = valores.getDouble("precio_venta");
                        int existencia = valores.getInt("existencia");
                        String imagen = valores.getString("img");
                        String descripcion=valores.getString("descripcion");
                        productos.add(new ClaseDatosProductos(nombre, precio, existencia, imagen,descripcion));
                    }
                    Adaptador AdaptadorRecycler = new Adaptador(productos);
                    P.cargarlistaproductos(AdaptadorRecycler);
                } catch (Exception e) {
                    Toast.makeText(PrincipalActivity.VistaPrincipal, "error en la extraciòn de los datos del JSON", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PrincipalActivity.VistaPrincipal, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("producto",buscado);
                return parametros;
            }
        };;
        RequestQueue envio = Volley.newRequestQueue(PrincipalActivity.VistaPrincipal);
        envio.add(respuesta);
    }

    @Override
    public void ActualizarLista(String buscado) {
        this.MostrarLista(URLBusca,buscado);
    }
}
