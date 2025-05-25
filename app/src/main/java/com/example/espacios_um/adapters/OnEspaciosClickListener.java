package com.example.espacios_um.adapters;

import com.example.espacios_um.modelos.Espacio;
import com.example.espacios_um.modelos.Horario;

public interface OnEspaciosClickListener {
    void onEspacioClick(Espacio espacio);
    void onEspacioCerrarClick(Espacio espacio);
}
