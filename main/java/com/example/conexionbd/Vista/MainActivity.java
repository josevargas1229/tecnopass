package com.example.conexionbd.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conexionbd.Presentador.Interface_PresentadorL;
import com.example.conexionbd.Presentador.Presentador_login;
import com.example.conexionbd.R;

public class MainActivity extends AppCompatActivity implements Interface_Login{

    EditText txtUsuario,txtPassword;
    Button btnAcceso, btnRecuperar;
    Interface_PresentadorL P;
    public static Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario=findViewById(R.id.txtUsuario);
        txtPassword=findViewById(R.id.txtPassword);
        btnAcceso=findViewById(R.id.btnAcceso);
        btnRecuperar=findViewById(R.id.btnRecuperar);
        btnAcceso.setOnClickListener(this::eventoAcceder);
        btnRecuperar.setOnClickListener(this::eventoRecuperar);
        P=new Presentador_login(this);
        contexto=this;
    }
    public void eventoAcceder(View vista){
        //Toast.makeText(this, txtUsuario.getText().toString()+ "" + txtPassword.getText().toString(), Toast.LENGTH_SHORT).show();
        P.login(txtUsuario.getText().toString(),txtPassword.getText().toString());
    }

    @Override
    public void acceso(String miusuario, String foto) {
        if(!(miusuario.trim().isEmpty()==true)){
            //Toast.makeText(this, "Encontrado..." + miusuario + foto, Toast.LENGTH_SHORT).show();
            Intent vista2 = new Intent(this,PrincipalActivity.class);
            vista2.putExtra("usuario",miusuario);
            vista2.putExtra("foto",foto);
            startActivity(vista2);
        }
    }

    @Override
    public void eventoRecuperar(View vista) {
        Intent vista3=new Intent(this, RecuperacionActivity.class);
        startActivity(vista3);
    }
}