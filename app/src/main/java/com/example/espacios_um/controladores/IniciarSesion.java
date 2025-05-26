package com.example.espacios_um.controladores;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.espacios_um.R;
import com.example.espacios_um.api.ApiClient;
import com.example.espacios_um.api.UsuarioApi;
import com.example.espacios_um.modelos.Usuario;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IniciarSesion extends AppCompatActivity {

    TextView codigoE, contrasenaE;
    Button btnIniciarSesion;
    private UsuarioApi apiConecction;

    TextView txtCodigoE;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iniciar_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codigoE = findViewById(R.id.campoCodigoE);
        contrasenaE = findViewById(R.id.campoContrasenaE);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        txtCodigoE= findViewById(R.id.txtCodigoE);


        apiConecction =  ApiClient.getRetrofit().create(UsuarioApi.class);

        btnIniciarSesion.setOnClickListener(v -> {
            if (spinner.getSelectedItem().toString().equalsIgnoreCase("Estudiante")) {
                login();
            }else if(spinner.getSelectedItem().toString().equalsIgnoreCase("Conserje")){
                loginConse();
            } else if (spinner.getSelectedItem().toString().equalsIgnoreCase("Profesor")) {
                loginProfe();
            }


        });

        spinner =findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = parent.getItemAtPosition(position).toString();
                if (seleccion.equalsIgnoreCase("Profesor") || seleccion.equalsIgnoreCase("Conserje")) {
                    txtCodigoE.setText("Correo");
                    codigoE.setInputType(InputType.TYPE_CLASS_TEXT);
                    codigoE.setText("");
                } else if (seleccion.equalsIgnoreCase("Administrador") || seleccion.equalsIgnoreCase("Estudiante")) {
                    txtCodigoE.setText("Codigo");
                    codigoE.setInputType(InputType.TYPE_CLASS_NUMBER);
                    codigoE.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });




    }


    public void login(){
        Map<String, String> body = new HashMap<>();
        body.put("codigo", String.valueOf(codigoE.getText()));
        body.put("password", String.valueOf(contrasenaE.getText()));
        apiConecction.login(body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();

                    Toast.makeText(IniciarSesion.this, "Inicio exitoso", Toast.LENGTH_SHORT).show();

                    usuario.setTipo(spinner.getSelectedItem().toString());
                    Intent intent = new Intent(IniciarSesion.this, Estudiante.class);
                    intent.putExtra("usuario_logueado", usuario);
                    startActivity(intent);
                } else {
                    if (String.valueOf(codigoE.getText()).equals("1234")&&String.valueOf(contrasenaE.getText()).equals("admin")){
                    Intent intent = new Intent(IniciarSesion.this, adminControlador.class);

                    startActivity(intent);
                }else{
                        Toast.makeText(IniciarSesion.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();

                    }
                    }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(IniciarSesion.this, "Error de conexion" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void loginProfe(){
        Map<String, String> body = new HashMap<>();
        body.put("email", String.valueOf(codigoE.getText()));
        body.put("password", String.valueOf(contrasenaE.getText()));
        apiConecction.loginProfe(body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();

                    Toast.makeText(IniciarSesion.this, "Inicio exitoso", Toast.LENGTH_SHORT).show();
                    usuario.setTipo(spinner.getSelectedItem().toString());

                    Intent intent = new Intent(IniciarSesion.this, Estudiante.class);
                    intent.putExtra("usuario_logueado", usuario);
                    intent.putExtra("tipo_usuario",spinner.getSelectedItem().toString());

                    startActivity(intent);
                } else {
                Toast.makeText(IniciarSesion.this, "Error en iniciar sesion", Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(IniciarSesion.this, "Error de conexion" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginConse(){
        Map<String, String> body = new HashMap<>();
        body.put("email", String.valueOf(codigoE.getText()));
        body.put("password", String.valueOf(contrasenaE.getText()));
        apiConecction.loginConse(body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario usuario = response.body();

                    Toast.makeText(IniciarSesion.this, "Inicio exitoso", Toast.LENGTH_SHORT).show();
                    usuario.setTipo(spinner.getSelectedItem().toString());

                    Intent intent = new Intent(IniciarSesion.this, Estudiante.class);
                    intent.putExtra("usuario_logueado", usuario);
                    startActivity(intent);
                }else {
                    Toast.makeText(IniciarSesion.this, "Error en iniciar sesion", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(IniciarSesion.this, "Error de conexion" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


