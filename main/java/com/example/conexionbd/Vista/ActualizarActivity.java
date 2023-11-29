package com.example.conexionbd.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conexionbd.Presentador.Presentador_Actualizar;
import com.example.conexionbd.Presentador.Presentador_Listaproductos;
import com.example.conexionbd.R;

public class ActualizarActivity extends AppCompatActivity {
    EditText txtNvaPass,txtConfPass;
    Button btnActualizar;
    Presentador_Actualizar P;
    public static Context contexto;
    int dato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        txtNvaPass=findViewById(R.id.txtNvaPass);
        txtConfPass=findViewById(R.id.txtConfPass);
        btnActualizar=findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(this::eventoActualizar);
        Bundle parametros = getIntent().getExtras();
        dato = parametros.getInt("id");
        contexto=this;
        P=new Presentador_Actualizar(this);

    }

    private void eventoActualizar(View vista) {
        String pass=txtNvaPass.getText().toString();
        String confpass=txtConfPass.getText().toString();
        if(pass.equals(confpass)){
            P.actualizar(dato,pass);
        }
        else
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
    }
    public void actualizado(){
        Toast.makeText(this, "La contraseña fue modificada exitosamente", Toast.LENGTH_SHORT).show();
        Intent vista4=new Intent(this, MainActivity.class);
        startActivity(vista4);
    }
}