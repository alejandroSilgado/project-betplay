package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class estadioManager {

    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarEstadio(String nombre, int capacidad, String ubicacion) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "INSERT INTO Estadio (nombre, capacidad, ubicacion) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setInt(2, capacidad);
                pstmt.setString(3, ubicacion);
                pstmt.executeUpdate();
                System.out.println("Estadio agregado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el estadio: " + e.getMessage());
        }
    }

    public static void editarEstadio(int id, String nombre, int capacidad, String ubicacion) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "UPDATE Estadio SET nombre = ?, capacidad = ?, ubicacion = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setInt(2, capacidad);
                pstmt.setString(3, ubicacion);
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
                System.out.println("Estadio editado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al editar el estadio: " + e.getMessage());
        }
    }

    public static void eliminarEstadio(int id) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DELETE FROM Estadio WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("Estadio eliminado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el estadio: " + e.getMessage());
        }
    }

    public static void listarEstadios() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT id, nombre, capacidad, ubicacion FROM Estadio";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                System.out.println("ID | Nombre | Capacidad | Ubicación");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int capacidad = rs.getInt("capacidad");
                    String ubicacion = rs.getString("ubicacion");
                    System.out.println(id + " | " + nombre + " | " + capacidad + " | " + ubicacion);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los estadios: " + e.getMessage());
        }
    }
}
