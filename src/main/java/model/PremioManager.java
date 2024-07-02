
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PremioManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarPremio(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese los detalles del nuevo premio:");
            System.out.println("Nombre:");
            String nombre = scanner.nextLine();
            System.out.println("Descripción:");
            String descripcion = scanner.nextLine();
            System.out.println("Criterios:");
            String criterios = scanner.nextLine();
            System.out.println("Destinatarios:");
            String destinatarios = scanner.nextLine();
            System.out.println("Fecha (YYYY-MM-DD):");
            String fecha = scanner.nextLine();

            String sql = "INSERT INTO Premios (nombre, descripcion, criterios, destinatarios, fecha) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, descripcion);
                pstmt.setString(3, criterios);
                pstmt.setString(4, destinatarios);
                pstmt.setString(5, fecha);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Premio agregado correctamente.");
                } else {
                    System.out.println("No se pudo agregar el premio.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar premio: " + e.getMessage());
        }
    }

    public static void listarPremios() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Premios";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    String criterios = rs.getString("criterios");
                    String destinatarios = rs.getString("destinatarios");
                    String fecha = rs.getString("fecha");

                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Descripción: " + descripcion);
                    System.out.println("Criterios: " + criterios);
                    System.out.println("Destinatarios: " + destinatarios);
                    System.out.println("Fecha: " + fecha);
                    System.out.println("----------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar premios: " + e.getMessage());
        }
    }

    public static void editarPremio(Scanner scanner) {
        System.out.println("Ingrese el ID del premio que desea editar:");
        int idPremio = scanner.nextInt();
        scanner.nextLine(); 

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Premios WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idPremio);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Premio encontrado. Ingrese los nuevos datos:");

                    System.out.println("Nuevo Nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Nueva Descripción:");
                    String descripcion = scanner.nextLine();
                    System.out.println("Nuevos Criterios:");
                    String criterios = scanner.nextLine();
                    System.out.println("Nuevos Destinatarios:");
                    String destinatarios = scanner.nextLine();
                    System.out.println("Nueva Fecha (YYYY-MM-DD):");
                    String fecha = scanner.nextLine();

                    sql = "UPDATE Premios SET nombre = ?, descripcion = ?, criterios = ?, destinatarios = ?, fecha = ? "
                            +
                            "WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(sql)) {
                        updateStmt.setString(1, nombre);
                        updateStmt.setString(2, descripcion);
                        updateStmt.setString(3, criterios);
                        updateStmt.setString(4, destinatarios);
                        updateStmt.setString(5, fecha);
                        updateStmt.setInt(6, idPremio);

                        int rowsAffected = updateStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Premio actualizado correctamente.");
                        } else {
                            System.out.println("No se pudo actualizar el premio.");
                        }
                    }
                } else {
                    System.out.println("No se encontró ningún premio con ese ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar premio: " + e.getMessage());
        }
    }

    public static void eliminarPremio(Scanner scanner) {
        System.out.println("Ingrese el ID del premio que desea eliminar:");
        int idPremio = scanner.nextInt();
        scanner.nextLine(); 

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DELETE FROM Premios WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idPremio);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Premio eliminado correctamente.");
                } else {
                    System.out.println("No se encontró ningún premio con ese ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar premio: " + e.getMessage());
        }
    }
}
