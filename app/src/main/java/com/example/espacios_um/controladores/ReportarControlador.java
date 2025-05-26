package com.example.espacios_um.controladores;

import android.os.Bundle;
import android.util.Log;
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
import com.example.espacios_um.api.ReporteApi;
import com.example.espacios_um.api.UsuarioApi;
import com.example.espacios_um.modelos.Espacio;
import com.example.espacios_um.modelos.Reporte;
import com.example.espacios_um.modelos.Usuario;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportarControlador extends AppCompatActivity {
    Espacio espacio;

    Usuario usuario;
    Spinner spinner;
    Button btnreportar;

    TextView nombre;
    TextView capacidad;

    ReporteApi apiConecction;


    EditText comentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reportar_controlador);
        espacio= (Espacio) getIntent().getSerializableExtra("espacio_a_reportar");
        usuario= (Usuario) getIntent().getSerializableExtra("usuario_conserje");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner= findViewById(R.id.spinner);
        nombre=findViewById(R.id.txtNombre);
        capacidad=findViewById(R.id.txtCapacidad);
        comentario= findViewById(R.id.etComentario);
        btnreportar= findViewById(R.id.btnreportar);

        nombre.setText(espacio.getNombre());
        capacidad.setText(String.valueOf(espacio.getCapacidad()));

        btnreportar.setOnClickListener(view -> {
            reportar();
        });
    }

    void reportar() {
        apiConecction= ApiClient.getRetrofit().create(ReporteApi.class);
        Map<String, String> body = new HashMap<>();
        body.put("descripcion", String.valueOf(comentario.getText()));
        body.put("idConserje", String.valueOf(usuario.getID()));
        body.put("idEspacio", String.valueOf(espacio.getId()));

        apiConecction.crear(body).enqueue(new Callback<Reporte>() {
            @Override
            public void onResponse(Call<Reporte> call, Response<Reporte> response) {
                if (response.isSuccessful() && response.body() != null)  {
                    Reporte reporte = response.body();

                    Toast.makeText(ReportarControlador.this, "Reporte exitoso", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<Reporte> call, Throwable t) {
                Toast.makeText(ReportarControlador.this, "Error de conexion" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}