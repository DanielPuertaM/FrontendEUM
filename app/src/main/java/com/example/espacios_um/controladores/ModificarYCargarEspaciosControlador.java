package com.example.espacios_um.controladores;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.espacios_um.api.EspacioApi;
import com.example.espacios_um.modelos.Espacio;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModificarYCargarEspaciosControlador extends AppCompatActivity {
    Espacio espacio;
    EditText etxtNombre;
    EditText etxtCapacidad;

    TextView txtNombre;

    Button btnAceptar;

    Spinner spinner;
    String nombreT;
    EspacioApi espacioApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_ycargar_espacios);
        espacio= (Espacio) getIntent().getSerializableExtra("espacio");
        nombreT=getIntent().getStringExtra("String");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        etxtCapacidad=findViewById(R.id.eTxtCapacidad);
        etxtNombre=findViewById(R.id.eTextNombreEspacio);
        btnAceptar=findViewById(R.id.btnAceptar);
        spinner=findViewById(R.id.spinner);
        txtNombre=findViewById(R.id.txtNombreG);

        espacioApi= ApiClient.getRetrofit().create(EspacioApi.class);
        txtNombre.setText(nombreT);

        btnAceptar.setOnClickListener(view -> {
            if ("Modificar espacio".equalsIgnoreCase(nombreT)){
                modificarEspacio();
            }else if ("Cargar espacio".equalsIgnoreCase(nombreT)){
                crearEspacio();
            }



        });



    }


    private void modificarEspacio(){
        Map<String, String> body = new HashMap<>();

        body.put("nombre", String.valueOf(etxtNombre.getText()));
        body.put("capacidad", String.valueOf(etxtCapacidad.getText()));
        body.put("tipo", String.valueOf(spinner.getSelectedItem()));


        espacioApi.update(espacio.getId(), body).enqueue(new Callback<Espacio>() {
            @Override
            public void onResponse(Call<Espacio> call, Response<Espacio> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Espacio espacio = response.body();

                    Toast.makeText(ModificarYCargarEspaciosControlador.this, "Modificado correctamente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ModificarYCargarEspaciosControlador.this, "Error en modificar", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<Espacio> call, Throwable t) {
                Toast.makeText(ModificarYCargarEspaciosControlador.this, "Fallo de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void crearEspacio(){
        Map<String, String> body = new HashMap<>();

        body.put("nombre", String.valueOf(etxtNombre.getText()));
        body.put("capacidad", String.valueOf(etxtCapacidad.getText()));
        body.put("tipo", String.valueOf(spinner.getSelectedItem()));


        espacioApi.crear(body).enqueue(new Callback<Espacio>() {
            @Override
            public void onResponse(Call<Espacio> call, Response<Espacio> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Espacio espacio = response.body();

                    Toast.makeText(ModificarYCargarEspaciosControlador.this, "Creado correctamente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ModificarYCargarEspaciosControlador.this, "Error en crear", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<Espacio> call, Throwable t) {
                Toast.makeText(ModificarYCargarEspaciosControlador.this, "Fallo de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }
}