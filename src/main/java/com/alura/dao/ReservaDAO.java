package com.alura.dao;

import com.alura.modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaDAO {

    private Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }


    // cambiar railway.tbreservas a tbreservas si se quiere utilizar local host
    public void guardar(Reserva reserva) {
        try {
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO railway.tbreservas "
                            + "(fechaentrada, fechasalida, valor, formapago)"
                            + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, reserva.getFechaEntrada().toString());
                statement.setString(2, reserva.getFechaSalida().toString());
                statement.setInt(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());

                statement.execute();

                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        reserva.setIdReserva(resultSet.getInt(1));

                        System.out.println(String.format("Fue insertado el producto: %s", reserva));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> listar(String textoBusqueda) {
        List<Reserva> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT idreserva, fechaentrada, fechasalida, valor, formapago FROM railway.tbreservas WHERE idreserva=?");

            try (statement) {
                statement.setString(1,textoBusqueda);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Reserva(
                                resultSet.getInt("idreserva"),
                                resultSet.getString("fechaentrada"),
                                resultSet.getString("fechasalida"),
                                resultSet.getInt("valor"),
                                resultSet.getString("formapago")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public int modificarReservas(int idReserva, String fechaEntrada, String fechaSalida, int valor, String formaPago) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE railway.tbreservas SET "
                            + " fechaentrada = ?,"
                            + " fechasalida = ?,"
                            + " valor = ?,"
                            + " formapago = ?"
                            + " WHERE idreserva = ?");

            try (statement) {
                statement.setString(1, String.valueOf(fechaEntrada));
                statement.setString(2, String.valueOf(fechaSalida));
                statement.setInt(3, valor);
                statement.setString(4, formaPago);
                statement.setInt(5, idReserva);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
