package com.example.espacios_um.modelos;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Usuario {
    List<Reserva> reservas;
    public Profesor() {
    }

    public Profesor(int ID, String nombre, String codigo, String email, String identificacion,String tipo) {
        super(ID, nombre, codigo, email, identificacion,tipo);
        reservas = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Profesor{" +
                "reservas=" + reservas +
                ", ID=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo=" + codigo +
                ", correo='" + email + '\'' +
                ", password='" + identificacion + '\'' +
                '}';
    }
}
