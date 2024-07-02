package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import controlador.main;

public class equiposManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void imprimirEquipos(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Equipo";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("Equipos Existentes:");
                System.out.println("ID\tNombre\t\tCiudad\t\tEstadio\t\tEntrenador");
                System.out.println("-------------------------------------------------------------------------------");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String ciudad = rs.getString("ciudad");
                    String estadio = rs.getString("estadio");
                    String entrenador = rs.getString("entrenador");
    
                    System.out.printf("%d\t%-15s\t%-15s\t%-22s\t%-15s%n", id, nombre, ciudad, estadio, entrenador);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar equipos: " + e.getMessage());
        }
    }

    public static void registrarEquipos(Scanner scanner) {
        main.limpiarPantalla();

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese nombre del equipo:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese la ciudad del equipo:");
            String ciudad = scanner.nextLine();

            System.out.println("Ingrese el estadio del equipo:");
            String estadio = scanner.nextLine();

            System.out.println("Ingrese el nombre del entrenador del equipo:");
            String entrenador = scanner.nextLine();

            String sql = "INSERT INTO Equipo (nombre, ciudad, estadio, entrenador) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, ciudad);
                pstmt.setString(3, estadio);
                pstmt.setString(4, entrenador);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Equipo registrado exitosamente.");
                } else {
                    System.out.println("No se pudo registrar el equipo.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar equipo: " + e.getMessage());
        }
    }

    public static void editarEquipos(Scanner scanner) {
        main.limpiarPantalla();
        imprimirEquipos(scanner);

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese ID del equipo a editar:");
            int id = scanner.nextInt();
            scanner.nextLine();

            String checkSql = "SELECT * FROM Equipo WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next()) {
                    System.out.println("Equipo no encontrado.");
                    return;
                }
            }

            System.out.println("Ingrese nombre del equipo:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese la ciudad del equipo:");
            String ciudad = scanner.nextLine();

            System.out.println("Ingrese el estadio del equipo:");
            String estadio = scanner.nextLine();

            System.out.println("Ingrese el nombre del entrenador del equipo:");
            String entrenador = scanner.nextLine();

            String sql = "UPDATE Equipo SET nombre = ?, ciudad = ?, estadio = ?, entrenador = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, ciudad);
                pstmt.setString(3, estadio);
                pstmt.setString(4, entrenador);
                pstmt.setInt(5, id);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Equipo editado exitosamente.");
                } else {
                    System.out.println("No se pudo editar el equipo.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar equipo: " + e.getMessage());
        }
    }

    public static void eliminarEquipos(Scanner scanner) {
        main.limpiarPantalla();
        imprimirEquipos(scanner);

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese ID del equipo a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();

            String sql = "DELETE FROM Equipo WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Equipo eliminado exitosamente.");
                } else {
                    System.out.println("No se pudo eliminar el equipo. Verifique si el ID existe.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar equipo: " + e.getMessage());
        }
    }
}
