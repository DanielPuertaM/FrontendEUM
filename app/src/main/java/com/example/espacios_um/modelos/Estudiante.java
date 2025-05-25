package com.example.espacios_um.modelos;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Usuario {
    List<Reserva> reservas;
    public Estudiante() {
    }

    public Estudiante(int ID, String nombre, String codigo, String correo, String password) {
        super(ID, nombre, codigo, correo, password);
        reservas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "reservas=" + reservas +
                ", ID=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo=" + codigo +
                ", correo='" + email + '\'' +
                ", password='" + identificacion + '\'' +
                '}';
    }
}
