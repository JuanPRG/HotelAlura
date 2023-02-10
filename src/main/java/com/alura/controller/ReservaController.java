package com.alura.controller;

import com.alura.dao.ReservaDAO;
import com.alura.factory.ConnectionFactory;
import com.alura.modelo.Reserva;

import java.util.Date;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        var factory = new ConnectionFactory();
        this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
    }

    public void guardar(Reserva reserva) {
        reservaDAO.guardar(reserva);

    }

    public List<Reserva> listar(String textoBusqueda) {
        return reservaDAO.listar(textoBusqueda);
    }

    public int modificarReservas(int idReserva, String fechaEntrada, String fechaSalida, int valor, String formaPago) {
        return reservaDAO.modificarReservas(idReserva, fechaEntrada, fechaSalida, valor, formaPago);
    }

}
