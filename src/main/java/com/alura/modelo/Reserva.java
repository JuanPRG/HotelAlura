package com.alura.modelo;

import java.sql.Date;

public class Reserva {
    private Integer idReserva;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int valor;
    private String formaPago;

    public Reserva(Date fechaEntrada, Date fechaSalida, int valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Reserva(Integer idReserva, String fechaEntrada, String fechaSalida, int valor, String formaPago) {
        this.idReserva = idReserva;
        this.fechaEntrada = Date.valueOf(fechaEntrada);
        this.fechaSalida = Date.valueOf(fechaSalida);
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Override
    public String toString() {
        return String.format(
                "{ idReserva: %d, fechaEntrada: %s, fechaSalida: %s, valor: %d, formaPago: %s }",
                this.idReserva, this.fechaEntrada, this.fechaSalida, this.valor, this.formaPago);
    }
}
