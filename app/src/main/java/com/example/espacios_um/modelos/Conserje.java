package com.example.espacios_um.modelos;

import java.util.List;

public class Conserje extends Usuario{

    private List<Reporte> reportes;

    public Conserje(int ID, String nombre, String codigo, String email, String identificacion, List<Reporte> reportes) {
        super(ID, nombre, codigo, email, identificacion);
        this.reportes = reportes;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    @Override
    public String toString() {
        return "Conserje{" +
                "identificacion='" + identificacion + '\'' +
                ", email='" + email + '\'' +
                ", codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", ID=" + id +
                ", reportes=" + reportes +
                '}';
    }
}
