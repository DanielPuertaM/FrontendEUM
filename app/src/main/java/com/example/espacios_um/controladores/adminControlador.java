package com.example.espacios_um.controladores;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.espacios_um.R;

public class adminControlador extends AppCompatActivity {

    Button btnGestionEstudiantes;
    Button btnGestionarEspacio;
    Button btnGestionarConserjes;
    Button btnVerReportes;

    Button btnGestionarProfesores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnGestionarEspacio= findViewById(R.id.btnGestionarEspacio);
        btnGestionarConserjes= findViewById(R.id.btnGestionarConserjes);
        btnGestionEstudiantes=findViewById(R.id.btnGestionarEstudiantes);

        btnVerReportes= findViewById(R.id.btnVerReportes);
        btnGestionarProfesores=findViewById(R.id.btnGestionarProfesores);



        btnGestionEstudiantes.setOnClickListener(view -> {
            Intent intent = new Intent(adminControlador.this, gestionUsuariosControlador.class);
            intent.putExtra("tipo","Estudiantes");
            startActivity(intent);

        });

        btnGestionarConserjes.setOnClickListener(view -> {
            Intent intent = new Intent(adminControlador.this, gestionUsuarioConserjeYProfesorControlador.class);
            intent.putExtra("tipos","Conserjes");
            startActivity(intent);
        });

        btnGestionarProfesores.setOnClickListener(view -> {
            Intent intent = new Intent(adminControlador.this, gestionUsuarioConserjeYProfesorControlador.class);
            intent.putExtra("tipos","Profesores");
            startActivity(intent);
        });


        btnGestionarEspacio.setOnClickListener(view -> {
            Intent intent = new Intent(adminControlador.this, gestionDeEspaciosControlador.class);

            startActivity(intent);
        });



    }
}