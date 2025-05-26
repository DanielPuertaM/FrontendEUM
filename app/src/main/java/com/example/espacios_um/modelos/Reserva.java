package com.example.espacios_um.modelos;

public class Reserva {

    private int ID;
    private Long idEspacio;
    private Usuario usuario;

    private Horario horario;

    public Reserva() {
    }

    public Reserva(int ID, Long idEspacio, Usuario usuario, Horario horario) {
        this.ID = ID;
        this.idEspacio = idEspacio;
        this.usuario = usuario;
        this.horario = horario;
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Long getidEspacio() {
        return idEspacio;
    }

    public void setidEspacio(Long idEspacio) {
        this.idEspacio = idEspacio;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "ID=" + ID +
                ", espacio=" + idEspacio +
                ", usuario=" + usuario +
                ", horario=" + horario +
                '}';
    }
}
