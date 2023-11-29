package com.example.conexionbd.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conexionbd.Presentador.Presentador_Codigo;
import com.example.conexionbd.R;
import org.apache.commons.lang3.RandomStringUtils;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.time.Instant;
import java.util.Properties;

public class RecuperacionActivity extends AppCompatActivity {
    EditText txtCorreo, txtCodigo;
    Button btnEnviarCodigo, btnValidarCodigo;
    public static Context contexto;
    Presentador_Codigo p;
    Instant instancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtCodigo = findViewById(R.id.txtCodigo);
        btnEnviarCodigo = findViewById(R.id.btnEnviarCodigo);
        btnValidarCodigo = findViewById(R.id.btnValidarCodigo);

        btnEnviarCodigo.setOnClickListener(this::eventoEnviarCodigo);
        btnValidarCodigo.setOnClickListener(this::eventoValidarCodigo);
        contexto=this;
        p=new Presentador_Codigo(this);
    }

    private void eventoEnviarCodigo(View vista) {
        String correoDestinatario = txtCorreo.getText().toString();
        p.validar(correoDestinatario);
    }
    public void validado(String correoDestinatario, int id){
        String username = "20221015@uthh.edu.mx";
        String password = "Zafkiel29";
        String codigoVerificacion = RandomStringUtils.randomAlphanumeric(4);

        EnviarCorreoTask enviarCorreoTask = new EnviarCorreoTask(username, password, correoDestinatario, codigoVerificacion,id);
        enviarCorreoTask.execute();
    }

    public void eventoValidarCodigo(View vista){
        String email=txtCorreo.getText().toString();
        String codigo=txtCodigo.getText().toString();
        p.validarCodigo(email,codigo);

    }
    public void codigoValidado(int id){
        Intent vista4 = new Intent(this,ActualizarActivity.class);
        vista4.putExtra("id",id);
        startActivity(vista4);
    }

    private class EnviarCorreoTask extends AsyncTask<String, Void, Boolean> {
        private String username;
        private String password;
        private String correoDestinatario;
        private String codigoVerificacion;
        private int id;

        public EnviarCorreoTask(String username, String password, String correoDestinatario, String codigoVerificacion,int id) {
            this.username = username;
            this.password = password;
            this.correoDestinatario = correoDestinatario;
            this.codigoVerificacion = codigoVerificacion;
            this.id=id;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(correoDestinatario));
                message.setSubject("Código de verificación");
                message.setText("Tu código de verificación es: " + codigoVerificacion);

                Transport.send(message);

                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error al mandar el correo", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(RecuperacionActivity.this, "Correo enviado", Toast.LENGTH_SHORT).show();
                p.actContra(codigoVerificacion,id);
                txtCodigo.setVisibility(View.VISIBLE);
                btnValidarCodigo.setVisibility(View.VISIBLE);
                txtCorreo.setVisibility(View.INVISIBLE);
                btnEnviarCodigo.setVisibility(View.INVISIBLE);
                txtCodigo.requestFocus();

            } else {
                Toast.makeText(RecuperacionActivity.this, "Error al mandar el correo", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
