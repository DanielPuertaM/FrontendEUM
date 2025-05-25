package com.example.espacios_um.controladores;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.espacios_um.R;
import com.example.espacios_um.adapters.EspacioAdapter;
import com.example.espacios_um.adapters.HorariosAdapter;
import com.example.espacios_um.adapters.OnEspaciosClickListener;
import com.example.espacios_um.adapters.OnHorarioClickListener;
import com.example.espacios_um.api.ApiClient;
import com.example.espacios_um.api.EspacioApi;
import com.example.espacios_um.api.HorarioApi;
import com.example.espacios_um.modelos.Espacio;
import com.example.espacios_um.modelos.Horario;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspacioControlador extends AppCompatActivity implements OnEspaciosClickListener, OnHorarioClickListener {
    TextView txtLunes;
    TextView txtMartes;
    TextView txtMiercoles;
    TextView txtJueves;
    TextView txtViernes;
    TextView txtSabado;
    TextView txtNombreEspacio;
    ListView listEspacios;
    private EspacioApi espacioApi;
    private HorarioApi horarioApi;
    ListView listViewReservas;

    ImageView imagen;

    ConstraintLayout layout;

    private String tipos;
    private String tipo;
    private boolean abierto;

    List<Horario> horarios;


    /**
     * @param horario
     */
    @Override
    public void onHorarioClick(Horario horario) {

        Toast.makeText(EspacioControlador.this, "Reservado", Toast.LENGTH_SHORT).show();



    }

    /**
     * @param horario
     */
    @Override
    public void onHorarioCancelarClick(Horario horario) {

    }


    /**
     * @param espacio
     */
    @Override
    public void onEspacioClick(Espacio espacio) {


        Toast.makeText(this, "Horarios cargados correctamente", Toast.LENGTH_SHORT).show();

        horariosPorEspacio(espacio);

    }

    /**
     * @param espacio
     */
    @Override
    public void onEspacioCerrarClick(Espacio espacio) {

    }





    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_espacio);
        tipos = getIntent().getStringExtra("nombre_tipos");
        tipo = getIntent().getStringExtra("nombre_tipo");



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        txtNombreEspacio= findViewById(R.id.txtNombreEspacio);
        imagen=findViewById(R.id.imageView2);
        layout= findViewById(R.id.main);
        txtNombreEspacio.setText(tipos);
        listViewReservas=findViewById(R.id.listViewReservas);
        if (tipos.equals("Mis reservas")) {

            imagen.setVisibility(View.GONE);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(layout);


            constraintSet.connect(R.id.listEspacio, ConstraintSet.TOP, R.id.txtMiercoles, ConstraintSet.BOTTOM, 40);

            constraintSet.applyTo(layout);
        }

        txtLunes = findViewById(R.id.txtLunes);
        txtMartes= findViewById(R.id.txtMartes);
        txtMiercoles= findViewById(R.id.txtMiercoles);
        txtJueves= findViewById(R.id.txtJuevas);
        txtViernes= findViewById(R.id.txtViernes);
        txtSabado= findViewById(R.id.txtSabado);
        listEspacios= findViewById(R.id.listEspacio);

        espacioApi = ApiClient.getRetrofit().create(EspacioApi.class);

        Call<List<Espacio>> call = espacioApi.listarPorTipo(tipo);
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











        layout.setOnTouchListener((v, event) -> {
            abierto=false;
            listViewReservas.setVisibility(View.GONE);
            return false;
        });


        txtLunes.setOnClickListener(v->{
            grisTodo();
            txtLunes.setTextColor(Color.BLACK);




        });

        txtMartes.setOnClickListener(v->{
            grisTodo();
            txtMartes.setTextColor(Color.BLACK);


        });

        txtMiercoles.setOnClickListener(v->{
            grisTodo();
            txtMiercoles.setTextColor(Color.BLACK);

        });

        txtJueves.setOnClickListener(v->{
            grisTodo();
            txtJueves.setTextColor(Color.BLACK);

        });

        txtViernes.setOnClickListener(v->{
            grisTodo();
            txtViernes.setTextColor(Color.BLACK);

        });

        txtSabado.setOnClickListener(v->{
            grisTodo();
            txtSabado.setTextColor(Color.BLACK);

        });


    }

    void grisTodo(){
        txtLunes.setTextColor(Color.rgb(138,138,138));
        txtMartes.setTextColor(Color.rgb(138,138,138));
        txtMiercoles.setTextColor(Color.rgb(138,138,138));
        txtJueves.setTextColor(Color.rgb(138,138,138));
        txtViernes.setTextColor(Color.rgb(138,138,138));
        txtSabado.setTextColor(Color.rgb(138,138,138));
    }



    void horariosPorEspacio(Espacio espacio){

        horarioApi = ApiClient.getRetrofit().create(HorarioApi.class);

        Call<List<Horario>> call = horarioApi.listarPorId(1L);
        call.enqueue(new Callback<List<Horario>>() {
            @Override
            public void onResponse(Call<List<Horario>> call, Response<List<Horario>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Horario> horarios = response.body();
                    HorariosAdapter adapter = new HorariosAdapter(EspacioControlador.this,horarios,EspacioControlador.this,tipo);
                    listViewReservas.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Horario>> call, Throwable t) {
                listViewReservas.setVisibility(View.VISIBLE);
                Log.e("API_ERROR", "Fallo en la petición: " + t.getMessage(), t);
                Toast.makeText(EspacioControlador.this, "Error al cargar los espacios", Toast.LENGTH_SHORT).show();
            }
        });
    }


}