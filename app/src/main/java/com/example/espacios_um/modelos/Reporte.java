package com.example.espacios_um.modelos;

public class Reporte {

    private int ID;
    private String descripcion;
    private int hora;
    private int dia;
    private int mes;
    private int anio;

    private Long idEspacio;

    public Reporte() {
    }

    public Reporte(int ID, String descripcion, int hora, int dia, int mes, int anio, Long idEspacio) {
        this.ID = ID;
        this.descripcion = descripcion;
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.idEspacio = idEspacio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Long getEspacio() {
        return idEspacio;
    }

    public void setEspacio(Long idEspacio) {
        this.idEspacio = idEspacio;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "ID=" + ID +
                ", descripcion='" + descripcion + '\'' +
                ", hora=" + hora +
                ", dia=" + dia +
                ", mes=" + mes +
                ", anio=" + anio +
                ", espacio=" + idEspacio +
                '}';
    }
}
