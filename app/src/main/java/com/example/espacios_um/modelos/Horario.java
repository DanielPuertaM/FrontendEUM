package com.example.espacios_um.modelos;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Horario {
    private Long id;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Espacio espacio;

    public Horario() {
    }

    public Horario(Long id, LocalDateTime horaInicio, LocalDateTime horaFin, Espacio espacio) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.espacio = espacio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "ID=" + id +
                ", horaInicio=" + horaInicio +
                ", horaFinal=" + horaFin +
                ", espacio=" + espacio +
                '}';
    }
}
