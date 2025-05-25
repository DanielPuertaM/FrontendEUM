package com.example.espacios_um.adapters;

import com.example.espacios_um.modelos.Espacio;
import com.example.espacios_um.modelos.Horario;

public interface OnHorarioClickListener {
    void onHorarioClick(Horario horario);

    void onHorarioCancelarClick(Horario horario);
}
