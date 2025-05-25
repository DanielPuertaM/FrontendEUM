package com.example.espacios_um.controladores;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {
    private EditText txtNombre, txtCorreo, txtPassword, txtCodigo;
    private Button btnRegistrar;
    private UsuarioApi usuarioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPassword);
        txtCodigo = findViewById(R.id.txtCodigo);

        btnRegistrar = findViewById(R.id.btnRegistro);

        usuarioApi = ApiClient.getRetrofit().create(UsuarioApi.class);

        btnRegistrar.setOnClickListener(v -> registrar());
    }

    private void registrar() {
        String nombre = txtNombre.getText().toString();
        String correo = txtCorreo.getText().toString();
        String password = txtPassword.getText().toString();
        String codigo = txtCodigo.getText().toString();
        Usuario usuario = new Usuario(0, nombre, codigo, correo, password);

        usuarioApi.registrar(usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Registro.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registro.this, IniciarSesion.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Registro.this, "Error en registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(Registro.this, "Fallo de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }
}