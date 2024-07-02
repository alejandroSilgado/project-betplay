package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

import controlador.main;

public class entrenadorManager {

    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarEntrenador(Scanner scanner) {
        System.out.println("Ingrese el nombre del entrenador: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la experiencia del entrenador (en años): ");
        int experiencia = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "INSERT INTO Entrenador (nombre, experiencia) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setInt(2, experiencia);
                preparedStatement.executeUpdate();
                System.out.println("Entrenador agregado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el entrenador: " + e.getMessage());
        }
    }

    public static void editarEntrenador(Scanner scanner) {
        main.limpiarPantalla();
        listarEntrenadores();
        System.out.println("Ingrese el ID del entrenador a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nuevo nombre del entrenador: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la nueva experiencia del entrenador (en años): ");
        int experiencia = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "UPDATE Entrenador SET nombre = ?, experiencia = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setInt(2, experiencia);
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
                System.out.println("Entrenador actualizado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el entrenador: " + e.getMessage());
        }
    }

    public static void eliminarEntrenador(Scanner scanner) {
        main.limpiarPantalla();
        listarEntrenadores();
        System.out.println("Ingrese el ID del entrenador a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DELETE FROM Entrenador WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Entrenador eliminado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el entrenador: " + e.getMessage());
        }
    }

    public static void listarEntrenadores() {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Entrenador";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("Lista de Entrenadores:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    int experiencia = resultSet.getInt("experiencia");
                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Experiencia: " + experiencia + " años");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los entrenadores: " + e.getMessage());
        }
    }
}
