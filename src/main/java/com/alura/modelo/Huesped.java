package com.alura.modelo;

import java.sql.Date;

public class Huesped {
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private int telefono;
    private int idReserva;

    public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, int telefono, int idReserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public Huesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad, int telefono, int idReserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = Date.valueOf(fechaNacimiento);
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return String.format(
                "{ nombre: %s, apellido: %s, fechaNacimiento: %s, nacionalidad: %s, telefono: %d, idReserva: %d }",
                this.nombre, this.apellido, this.fechaNacimiento, this.nacionalidad, this.telefono, this.idReserva);
    }
}

