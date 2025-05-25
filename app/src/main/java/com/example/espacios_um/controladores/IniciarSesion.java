package com.example.espacios_um.controladores;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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

        apiConecction =  ApiClient.getRetrofit().create(UsuarioApi.class);

        btnIniciarSesion.setOnClickListener(v -> {
            login();
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

                    Toast.makeText(IniciarSesion.this, "aqui", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(IniciarSesion.this, Estudiante.class);
                    intent.putExtra("usuario_logueado", usuario);
                    startActivity(intent);
                } else {
                    Toast.makeText(IniciarSesion.this, "Error en login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(IniciarSesion.this, "Error de conexion" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}