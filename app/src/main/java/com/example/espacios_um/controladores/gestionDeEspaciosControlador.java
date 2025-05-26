package com.example.espacios_um.controladores;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.espacios_um.R;
import com.example.espacios_um.adapters.EspacioAdapter;
import com.example.espacios_um.adapters.OnEspaciosClickListener;
import com.example.espacios_um.api.ApiClient;
import com.example.espacios_um.api.EspacioApi;
import com.example.espacios_um.modelos.Espacio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gestionDeEspaciosControlador extends AppCompatActivity implements OnEspaciosClickListener {

    ListView listView;
    EspacioApi espacioApi;
    Button btnCargarEspacios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestion_de_espacios);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView=findViewById(R.id.listEspacio);
        espacioApi = ApiClient.getRetrofit().create(EspacioApi.class);
        btnCargarEspacios= findViewById(R.id.btnCargarEspacios);
        listEspacio();

        btnCargarEspacios.setOnClickListener(view -> {
            Intent intent = new Intent(gestionDeEspaciosControlador.this, ModificarYCargarEspaciosControlador.class);

            intent.putExtra("String","Cargar espacio");
            startActivity(intent);
        });

    }



    /**
     * @param espacio
     */
    @Override
    public void onEspacioClick(Espacio espacio) {

    }

    /**
     * @param espacio
     */
    @Override
    public void onEspacioReportarClick(Espacio espacio) {

    }

    /**
     * @param espacio
     */
    @Override
    public void onEspacioModificarClick(Espacio espacio) {

        Intent intent = new Intent(gestionDeEspaciosControlador.this, ModificarYCargarEspaciosControlador.class);
        intent.putExtra("espacio",espacio);
        intent.putExtra("String","Modificar espacio");
        startActivity(intent);


    }

    /**
     * @param espacio
     */
    @Override
    public void onEspacioEliminarClick(Espacio espacio) {
        new AlertDialog.Builder(gestionDeEspaciosControlador.this)
                .setTitle("Confirmación")
                .setMessage("¿Estás seguro de que deseas eliminar este espacio?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    eliminarEspacio(espacio);


                })
                .setNegativeButton("Cancelar", null)
                .show();


    }
    private void eliminarEspacio(Espacio espacio){

        Call<Void> call = espacioApi.delete(espacio.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(gestionDeEspaciosControlador.this, "Espacio eliminado", Toast.LENGTH_SHORT).show();
                listEspacio();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(gestionDeEspaciosControlador.this, "Conserje no encontrado", Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void listEspacio(){

        Call<List<Espacio>> call = espacioApi.listar();
        call.enqueue(new Callback<List<Espacio>>() {
            @Override
            public void onResponse(Call<List<Espacio>> call, Response<List<Espacio>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Espacio> espacios = response.body();

                    EspacioAdapter adapter = new EspacioAdapter(gestionDeEspaciosControlador.this, espacios,gestionDeEspaciosControlador.this, "admin");
                    listView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Espacio>> call, Throwable t) {
                Toast.makeText(gestionDeEspaciosControlador.this, "Error al cargar los espacios", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listEspacio();
    }
}
