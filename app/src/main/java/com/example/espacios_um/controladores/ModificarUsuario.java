package com.example.espacios_um.controladores;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModificarUsuario extends AppCompatActivity {
    EditText txtNombre;
    EditText txtCodigoEstudiantil;
    EditText txtEmail;
    Button btnAceptar;

    UsuarioApi usuarioApi;

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_usuario);
        usuario= (Usuario) getIntent().getSerializableExtra("usuario_a_modificar");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombre = findViewById(R.id.txtNombre);
        txtCodigoEstudiantil= findViewById(R.id.txtCodigoEstudiantil);
        txtEmail= findViewById(R.id.txtEmail);
        btnAceptar= findViewById(R.id.btnAceptar);

        usuarioApi = ApiClient.getRetrofit().create(UsuarioApi.class);
            /*
        Call<Usuario> call = usuarioApi.update(usuario.getID());
        call.enqueue(new Callback<List<Espacio>>() {
            @Override
            public void onResponse(Call<List<Espacio>> call, Response<List<Espacio>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Espacio> espacios = response.body();
                    //Toast.
                    EspacioAdapter adapter = new EspacioAdapter(EspacioControlador.this, espacios,EspacioControlador.this,tipos);
                    listEspacios.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Espacio>> call, Throwable t) {
                Toast.makeText(EspacioControlador.this, "Error al cargar los espacios", Toast.LENGTH_SHORT).show();
            }
        });


             */








    }
}