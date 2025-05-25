package com.example.espacios_um.modelos;

import java.io.Serializable;

public class Usuario implements Serializable {

    protected int id;
    protected String nombre;
    protected String codigo;
    protected String email;
    protected String identificacion;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String codigo, String email, String identificacion) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.email = email;
        this.identificacion = identificacion;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ID=" + id +
                ", nombre='" + nombre + '\'' +
                ", codigo=" + codigo +
                ", email='" + email + '\'' +
                ", identificacion='" + identificacion + '\'' +
                '}';
    }
}
