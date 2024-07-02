package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import controlador.main;
public class arbitroManager {

    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarArbitro(String nombre, int experiencia) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "INSERT INTO Arbitro (nombre, experiencia) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setInt(2, experiencia);
                preparedStatement.executeUpdate();
                System.out.println("Árbitro agregado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar árbitro: " + e.getMessage());
        }
    }

    public static void editarArbitro(int id, String nombre, int experiencia) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            main.limpiarPantalla();
            listarArbitros();
            String sql = "UPDATE Arbitro SET nombre = ?, experiencia = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setInt(2, experiencia);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
                System.out.println("Árbitro editado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al editar árbitro: " + e.getMessage());
        }
    }

    public static void eliminarArbitro(int id) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            main.limpiarPantalla();
            listarArbitros();
            String sql = "DELETE FROM Arbitro WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Árbitro eliminado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar árbitro: " + e.getMessage());
        }
    }

    public static void listarArbitros() {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Arbitro";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int experiencia = rs.getInt("experiencia");
                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Experiencia: " + experiencia);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar árbitros: " + e.getMessage());
        }
    }
}
