package com.example.espacios_um.controladores;

import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.espacios_um.R;
import com.example.espacios_um.modelos.Usuario;


public class Estudiante extends AppCompatActivity {

    Button btnSalones;
    Button btnCanchas;
    Button btnAuditorios;
    Button btnLaboratorios;

    Button btnMisReservas;

    Usuario usuario;

    TextView nombre;

    TextView codigo;

    Button btnCerrarsion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vista_estudiante);
        usuario= (Usuario) getIntent().getSerializableExtra("usuario_logueado");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSalones= findViewById(R.id.btnSalones);
        btnCanchas= findViewById(R.id.btnCanchas);
        btnAuditorios= findViewById(R.id.btnAuditorios);
        btnLaboratorios= findViewById(R.id.btnLaboratorios);
        btnMisReservas= findViewById(R.id.btnListarReservas);
        btnCerrarsion= findViewById(R.id.btnCerrarSesion);

        nombre= findViewById(R.id.txtNombreU);
        codigo = findViewById(R.id.txtCodU);

        nombre.setText(usuario.getNombre());
        if ("Estudiante".equalsIgnoreCase(usuario.getTipo())) {
            codigo.setText(usuario.getCodigo());
        } else if ("Profesor".equalsIgnoreCase(usuario.getTipo())) {
            codigo.setText(usuario.getEmail());
        } else if ("Conserje".equalsIgnoreCase(usuario.getTipo())) {
            btnMisReservas.setVisibility(GONE);
            codigo.setText(usuario.getEmail());
        }

        btnMisReservas.setOnClickListener(view -> {
            irAMisReservas("Mis reservas");
        });

        btnSalones.setOnClickListener(v -> {
            irAEspacio("Salones","Salon");
        });

        btnCanchas.setOnClickListener(v -> {
            irAEspacio("Canchas","Cancha");
        });

        btnAuditorios.setOnClickListener(v -> {
            irAEspacio("Auditorios","Auditorio");
        });

        btnLaboratorios.setOnClickListener(v -> {
            irAEspacio("Laboratorios","Laboratorio");
        });

        btnCerrarsion.setOnClickListener(view -> {
            Intent intent = new Intent(Estudiante.this, IniciarSesion.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

    }

    public void irAEspacio(String tipoS,String tipo) {
        Intent intent = new Intent(this, EspacioControlador.class);

        intent.putExtra("nombre_tipos", tipoS);
        intent.putExtra("nombre_tipo",tipo);
        intent.putExtra("usuario_logueado", usuario);

        startActivity(intent);
    }

    public void irAMisReservas(String tipoS){
        Intent intent = new Intent(this, MisReservasControlador.class);

        intent.putExtra("nombre_tipos", tipoS);
        intent.putExtra("usuario_logueado", usuario);

        startActivity(intent);
    }
}