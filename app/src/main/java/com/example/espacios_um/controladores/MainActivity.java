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

public class MainActivity extends AppCompatActivity {
    Button btnIniciarSesion;
    Button btnRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnIniciarSesion = findViewById(R.id.btnLoginMain);
        btnRegistro = findViewById(R.id.btnRegistroMain);

        btnIniciarSesion.setOnClickListener(v -> {
            irALogin();
        });
        btnRegistro.setOnClickListener(v -> {
            irARegistro();
        });
    }

    public void irALogin() {
        Intent intent = new Intent(this, IniciarSesion.class);
        startActivity(intent);
    }

    public void irARegistro() {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }
}