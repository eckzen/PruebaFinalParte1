package com.mycompany.vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("marca"),
                    rs.getInt("potencia"),
                    rs.getDate("fecha_compra")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    public Vehiculo obtenerVehiculoPorId(int id) {
        String sql = "SELECT * FROM vehiculos WHERE id = ?";
        Vehiculo vehiculo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                vehiculo = new Vehiculo(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("marca"),
                    rs.getInt("potencia"),
                    rs.getDate("fecha_compra")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculo;
    }

    public void insertarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (tipo, marca, potencia, fecha_compra) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vehiculo.getTipo());
            pstmt.setString(2, vehiculo.getMarca());
            pstmt.setInt(3, vehiculo.getPotencia());
            pstmt.setDate(4, new java.sql.Date(vehiculo.getFechaCompra().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVehiculoPorId(int id) {
        String sql = "DELETE FROM vehiculos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET tipo = ?, marca = ?, potencia = ?, fecha_compra = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vehiculo.getTipo());
            pstmt.setString(2, vehiculo.getMarca());
            pstmt.setInt(3, vehiculo.getPotencia());
            pstmt.setDate(4, new java.sql.Date(vehiculo.getFechaCompra().getTime()));
            pstmt.setInt(5, vehiculo.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double obtenerPotenciaMediaPorTipo(String tipo) {
        String sql = "SELECT AVG(potencia) FROM vehiculos WHERE tipo = ?";
        double potenciaMedia = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                potenciaMedia = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return potenciaMedia;
    }

    public int obtenerPotenciaMaximaPorTipo(String tipo) {
        String sql = "SELECT MAX(potencia) FROM vehiculos WHERE tipo = ?";
        int potenciaMaxima = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                potenciaMaxima = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return potenciaMaxima;
    }
}
