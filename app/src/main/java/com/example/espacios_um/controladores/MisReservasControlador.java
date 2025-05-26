package com.example.espacios_um.controladores;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.example.espacios_um.api.ReservaApi;
import com.example.espacios_um.modelos.Espacio;
import com.example.espacios_um.modelos.Horario;
import com.example.espacios_um.modelos.Reserva;
import com.example.espacios_um.modelos.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisReservasControlador extends AppCompatActivity implements OnEspaciosClickListener, OnHorarioClickListener {
    private String tipos;
    TextView txtLunes;
    TextView txtMartes;
    TextView txtMiercoles;
    TextView txtJueves;
    TextView txtViernes;
    TextView txtSabado;
    TextView txtNombreEspacio;
    ListView listEspacios;

    ConstraintLayout layout;
    private EspacioApi espacioApi;
    private HorarioApi horarioApi;

    private ReservaApi reservaApi;
    ListView listViewReservas;
    private Usuario usuario;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        tipos = getIntent().getStringExtra("nombre_tipos");
        usuario= (Usuario) getIntent().getSerializableExtra("usuario_logueado");
        setContentView(R.layout.activity_mis_reservas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        txtNombreEspacio= findViewById(R.id.txtNombreEspacio);

        txtNombreEspacio.setText(tipos);
        listViewReservas=findViewById(R.id.listViewReservas);


        txtLunes = findViewById(R.id.txtLunes);
        txtMartes= findViewById(R.id.txtMartes);
        txtMiercoles= findViewById(R.id.txtMiercoles);
        txtJueves= findViewById(R.id.txtJuevas);
        txtViernes= findViewById(R.id.txtViernes);
        txtSabado= findViewById(R.id.txtSabado);
        listEspacios= findViewById(R.id.listEspacio);
        layout=findViewById(R.id.main);

        layout.setOnTouchListener((v, event) -> {

            listViewReservas.setVisibility(View.GONE);
            return false;
        });

        reservasUsuario();






    }

    /**
     * @param espacio
     */
    @Override
    public void onEspacioClick(Espacio espacio) {
        listViewReservas.setVisibility(View.VISIBLE);
    }

    /**
     * @param espacio
     */
    @Override
    public void onEspacioReportarClick(Espacio espacio) {

    }

    /**
     * @param horario
     */
    @Override
    public void onHorarioClick(Horario horario) {

    }

    /**
     * @param horario
     */
    @Override
    public void onHorarioCancelarClick(Horario horario) {

    }

    public void reservasUsuario(){
        reservaApi = ApiClient.getRetrofit().create(ReservaApi.class);
        Call<List<Reserva>> call = reservaApi.buscarPorIdEstudiante(Long.valueOf(usuario.getID()));
        call.enqueue(new Callback<List<Reserva>>() {
            @Override
            public void onResponse(Call<List<Reserva>> call, Response<List<Reserva>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Reserva> reservas = response.body();


                    espaciosUsuario(reservas);

                }
            }

            @Override
            public void onFailure(Call<List<Reserva>> call, Throwable t) {
                Toast.makeText(MisReservasControlador.this, "Error al cargar las reservas", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void espaciosUsuario(List<Reserva> reservas){

        espacioApi = ApiClient.getRetrofit().create(EspacioApi.class);

        Set<Long> espacioIds = new HashSet<>();
        for (Reserva r : reservas) {
            if (r.getidEspacio() != null) {
                espacioIds.add(r.getidEspacio());
            } else {
                // Puedes loguear para saber qué reservas llegaron sin espacio
                Log.w("ReservaNull", "Una reserva no tiene espacio asociado: ID = " + r.getID());
            }
        }

        List<Espacio> espaciosEncontrados = new ArrayList<>();
        AtomicInteger pendientes = new AtomicInteger(espacioIds.size());

        for (Long id : espacioIds) {
            Call<Espacio> call = espacioApi.buscar(id);
            call.enqueue(new Callback<Espacio>() {
                @Override
                public void onResponse(Call<Espacio> call, Response<Espacio> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        espaciosEncontrados.add(response.body());
                    }
                    verificarFinalizado();
                }

                @Override
                public void onFailure(Call<Espacio> call, Throwable t) {
                    Toast.makeText(MisReservasControlador.this, "Error cargando espacio con ", Toast.LENGTH_SHORT).show();
                    verificarFinalizado();
                }

                private void verificarFinalizado() {
                    if (pendientes.decrementAndGet() == 0) {
                        EspacioAdapter adapter = new EspacioAdapter(MisReservasControlador.this, espaciosEncontrados, MisReservasControlador.this, tipos);
                        listEspacios.setAdapter(adapter);
                    }
                }
            });
        }

    }
}

