package com.example.espacios_um.controladores;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.espacios_um.api.ApiClient;
import com.example.espacios_um.api.UsuarioApi;
import com.example.espacios_um.modelos.Usuario;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {
    private EditText txtNombre, txtCorreo, txtPassword, txtCodigo;
    private Button btnRegistrar;
    private UsuarioApi usuarioApi;

    private TextView lblview;

    private Spinner spinner;

    private TextView txtIdentificacion;

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
        txtIdentificacion= findViewById(R.id.txtIdentificacion);

        lblview=findViewById(R.id.lblCodigo);
        btnRegistrar = findViewById(R.id.btnRegistro);

        usuarioApi = ApiClient.getRetrofit().create(UsuarioApi.class);

        btnRegistrar.setOnClickListener(v -> {
            if ("Estudiante".equalsIgnoreCase(spinner.getSelectedItem().toString())) {

                registrar();
            }else if ("Profesor".equalsIgnoreCase(spinner.getSelectedItem().toString())){
                registrarProfe();
            } else if ("Conserje".equalsIgnoreCase(spinner.getSelectedItem().toString())) {
                registrarConse();
            }
        });
        spinner= findViewById(R.id.spinner);

        spinner =findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = parent.getItemAtPosition(position).toString();
                if (seleccion.equalsIgnoreCase("Profesor") || seleccion.equalsIgnoreCase("Conserje")) {
                    txtCodigo.setVisibility(INVISIBLE);
                    lblview.setVisibility(INVISIBLE);
                } else if (seleccion.equalsIgnoreCase("Administrador") || seleccion.equalsIgnoreCase("Estudiante")) {
                    txtCodigo.setVisibility(VISIBLE);
                    lblview.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

     void registrar() {
        Map<String,String> body=new HashMap<>();

        body.put("nombre",String.valueOf(txtNombre.getText()) );
        body.put("email",String.valueOf(txtCorreo.getText()) );
        body.put("password", String.valueOf(txtPassword.getText())) ;
        body.put("identificacion",String.valueOf(txtIdentificacion.getText()) );
        body.put("codigo",String.valueOf(txtCodigo.getText())) ;

        usuarioApi.registrar(body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario= response.body();
                    usuario.setTipo("Estudiante");
                    Toast.makeText(Registro.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registro.this, Estudiante.class);
                    intent.putExtra("usuario_logueado",usuario);
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


    private void registrarProfe(){
        Map<String,String> body=new HashMap<>();

        body.put("nombre",String.valueOf(txtNombre.getText()) );
        body.put("email",String.valueOf(txtCorreo.getText()) );
        body.put("password", String.valueOf(txtPassword.getText())) ;
        body.put("identificacion",String.valueOf(txtIdentificacion.getText()) );


        usuarioApi.registrarProfe(body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario= response.body();
                    usuario.setTipo("Profesor");
                    Toast.makeText(Registro.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registro.this, Estudiante.class);
                    intent.putExtra("usuario_logueado",usuario);
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
    private void registrarConse(){
        Map<String,String> body=new HashMap<>();

        body.put("nombre",String.valueOf(txtNombre.getText()) );
        body.put("email",String.valueOf(txtCorreo.getText()) );
        body.put("password", String.valueOf(txtPassword.getText())) ;
        body.put("identificacion",String.valueOf(txtIdentificacion.getText()) );


        usuarioApi.registrarConse(body).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario= response.body();
                    usuario.setTipo("Conserje");
                    Toast.makeText(Registro.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registro.this, Estudiante.class);
                    intent.putExtra("usuario_logueado",usuario);
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