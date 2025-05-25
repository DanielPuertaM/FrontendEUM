package com.example.espacios_um.modelos;

import java.util.ArrayList;
import java.util.List;

public class Espacio {

    private int ID;
    private String nombre;
    private int capacidad;
    private  String tipo;
    private List<Reporte> reportes;

    private List<Horario> horarios;

    public Espacio() {
    }

    public Espacio(int ID, String nombre, int capacidad, String tipo) {
        this.ID = ID;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.reportes = new ArrayList<>();
        this.horarios= new ArrayList<>();

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "Espacio{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", tipo='" + tipo + '\'' +
                ", reportes=" + reportes +
                ", horarios=" + horarios +
                '}';
    }
}
