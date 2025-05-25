package com.example.espacios_um.modelos;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Horario {

    private Long ID;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;


    private Espacio espacio;

    public Horario() {
    }

    public Horario(Long ID, LocalDateTime horaInicio, LocalDateTime horaFinal, Espacio espacio) {
        this.ID = ID;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.espacio = espacio;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDateTime horaFinal) {
        this.horaFinal = horaFinal;
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
                "ID=" + ID +
                ", horaInicio=" + horaInicio +
                ", horaFinal=" + horaFinal +
                ", espacio=" + espacio +
                '}';
    }


}
