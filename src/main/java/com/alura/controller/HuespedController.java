package com.alura.controller;

import com.alura.dao.HuespedDAO;
import com.alura.factory.ConnectionFactory;
import com.alura.modelo.Huesped;
import com.alura.modelo.Reserva;

import java.util.List;

public class HuespedController {
    private HuespedDAO huespedDAO;

    public HuespedController() {
        var factory = new ConnectionFactory();
        this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
    }

    public void guardar(Huesped huesped) {
        huespedDAO.guardar(huesped);
    }

    public List<Huesped> listar(String textoBusqueda) {
        return huespedDAO.listar(textoBusqueda);
    }

    public int modificarHuespedes(String nombre,String apellido,String fechaNacimiento, String nacionalidad, int telefono, int idReserva) {
        return huespedDAO.modificarHuespedes(nombre, apellido, fechaNacimiento,nacionalidad,telefono,idReserva);
    }

    public int eliminar(Integer id) {
        return huespedDAO.eliminar(id);
    }
}
