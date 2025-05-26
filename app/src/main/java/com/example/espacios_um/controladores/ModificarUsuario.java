package com.example.espacios_um.controladores;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.espacios_um.R;
import com.example.espacios_um.adapters.EspacioAdapter;
import com.example.espacios_um.api.ApiClient;
import com.example.espacios_um.api.EspacioApi;
import com.example.espacios_um.api.UsuarioApi;
import com.example.espacios_um.modelos.Espacio;
import com.example.espacios_um.modelos.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModificarUsuario extends AppCompatActivity {
    private EditText txtNombre, txtCorreo, txtPassword, txtCodigo;
    private Button btnAceptar;
    private UsuarioApi usuarioApi;

    private TextView lblview;

    private Spinner spinner;

    private TextView txtIdentificacion;

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_usuario);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario_a_modificar");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPassword);
        txtCodigo = findViewById(R.id.txtCodigo);
        txtIdentificacion = findViewById(R.id.txtIdentificacion);

        lblview = findViewById(R.id.lblCodigo);
        btnAceptar = findViewById(R.id.btnAceptar);
        spinner= findViewById(R.id.spinner);
        usuarioApi = ApiClient.getRetrofit().create(UsuarioApi.class);

        if ("Profesores".equalsIgnoreCase(usuario.getTipo()) || "Conserjes".equalsIgnoreCase(usuario.getTipo())) {
            txtCodigo.setVisibility(INVISIBLE);
            lblview.setVisibility(INVISIBLE);
        } else if ("Estudiantes".equalsIgnoreCase(usuario.getTipo())) {
            txtCodigo.setVisibility(VISIBLE);
            lblview.setVisibility(VISIBLE);
        }


        btnAceptar.setOnClickListener(view -> {
            if ("Estudiantes".equalsIgnoreCase(usuario.getTipo())) {

                modificarEstudiante();
            }else if ("Profesores".equalsIgnoreCase(usuario.getTipo())){
                modificarProfesor();
            } else if ("Conserjes".equalsIgnoreCase(usuario.getTipo())) {
                modificarConcerje();
            }
        });
    }

    private void modificarEstudiante() {
        Map<String,String> body=new HashMap<>();

        body.put("nombre",String.valueOf(txtNombre.getText()) );
        body.put("email",String.valueOf(txtCorreo.getText()) );
        body.put("password", String.valueOf(txtPassword.getText())) ;
        body.put("identificacion",String.valueOf(txtIdentificacion.getText()) );
        body.put("codigo",String.valueOf(txtCodigo.getText())) ;

        usuarioApi.update(usuario.getID(),body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario= response.body();
                    usuario.setTipo("Estudiante");
                    Toast.makeText(ModificarUsuario.this, "Modificado correctamente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ModificarUsuario.this, "Error en modificar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(ModificarUsuario.this, "Fallo de conexión", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void modificarProfesor() {
        Map<String, String> body = new HashMap<>();

        body.put("nombre", String.valueOf(txtNombre.getText()));
        body.put("email", String.valueOf(txtCorreo.getText()));
        body.put("password", String.valueOf(txtPassword.getText()));
        body.put("identificacion", String.valueOf(txtIdentificacion.getText()));


        usuarioApi.updateProfesor(usuario.getID(), body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario = response.body();
                    usuario.setTipo("Estudiante");
                    Toast.makeText(ModificarUsuario.this, "Modificado correctamente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ModificarUsuario.this, "Error en modificar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(ModificarUsuario.this, "Fallo de conexión", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void modificarConcerje() {
        Map<String, String> body = new HashMap<>();

        body.put("nombre", String.valueOf(txtNombre.getText()));
        body.put("email", String.valueOf(txtCorreo.getText()));
        body.put("password", String.valueOf(txtPassword.getText()));
        body.put("identificacion", String.valueOf(txtIdentificacion.getText()));


        usuarioApi.updateConserje(usuario.getID(), body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario = response.body();
                    usuario.setTipo("Estudiante");
                    Toast.makeText(ModificarUsuario.this, "Modificado correctamente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ModificarUsuario.this, "Error en modificar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(ModificarUsuario.this, "Fallo de conexión", Toast.LENGTH_SHORT).show();
            }
        });


    }







}