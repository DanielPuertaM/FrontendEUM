package com.example.espacios_um.modelos;

public class Reserva {

    private int ID;
    private Espacio espacio;
    private Usuario usuario;

    public Reserva() {
    }

    public Reserva(int ID, Espacio espacio, Usuario usuario) {
        this.ID = ID;
        this.usuario = usuario;
        this.espacio = espacio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "ID=" + ID +
                ", usuario=" + usuario +
                '}';
    }
}
