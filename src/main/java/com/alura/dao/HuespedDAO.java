package com.alura.dao;

import com.alura.modelo.Huesped;
import com.alura.modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {

    private Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Huesped huesped) {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO TBHUESPEDES "
                            + "(idreserva, nombre, apellido, fechanacimiento, nacionalidad, telefono)"
                            + " VALUES (?, ?, ?, ?, ? ,?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setInt(1, huesped.getIdReserva());
                statement.setString(2, huesped.getNombre());
                statement.setString(3, huesped.getApellido());
                statement.setString(4, huesped.getFechaNacimiento().toString());
                statement.setString(5, huesped.getNacionalidad());
                statement.setString(6, String.valueOf(huesped.getTelefono()));


                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        huesped.setIdReserva(resultSet.getInt(1));

                        System.out.println(String.format("Fue insertado el producto: %s", huesped));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> listar(String textoBusqueda) {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT nombre, apellido, fechanacimiento, nacionalidad, telefono, idreserva FROM tbhuespedes WHERE apellido=?");

            try (statement) {
                statement.setString(1,textoBusqueda);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getString("fechanacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getInt("telefono"),
                                resultSet.getInt("idreserva")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public int modificarHuespedes(String nombre,String apellido,String fechaNacimiento, String nacionalidad, int telefono, int idReserva) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE tbhuespedes SET "
                            + " nombre = ?,"
                            + " apellido = ?,"
                            + " fechanacimiento = ?,"
                            + " nacionalidad = ?,"
                            + " telefono = ?"
                            + " WHERE idreserva = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, String.valueOf(fechaNacimiento));
                statement.setString(4, nacionalidad);
                statement.setInt(5, telefono);
                statement.setInt(6, idReserva);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM tbhuespedes WHERE idreserva = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}