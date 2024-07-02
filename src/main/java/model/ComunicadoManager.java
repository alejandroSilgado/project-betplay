package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ComunicadoManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarComunicado(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese los detalles del nuevo comunicado:");
            System.out.println("Título:");
            String titulo = scanner.nextLine();
            System.out.println("Contenido:");
            String contenido = scanner.nextLine();
            System.out.println("Fecha de Publicación (YYYY-MM-DD):");
            String fechaPublicacion = scanner.nextLine();

            String sql = "INSERT INTO Comunicacion (titulo, contenido, fechaPublicacion) " +
                         "VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                pstmt.setString(2, contenido);
                pstmt.setString(3, fechaPublicacion);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Comunicado agregado correctamente.");
                } else {
                    System.out.println("No se pudo agregar el comunicado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar comunicado: " + e.getMessage());
        }
    }

    public static void listarComunicados() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Comunicacion";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String titulo = rs.getString("titulo");
                    String contenido = rs.getString("contenido");
                    String fechaPublicacion = rs.getString("fechaPublicacion");

                    System.out.println("ID: " + id);
                    System.out.println("Título: " + titulo);
                    System.out.println("Contenido: " + contenido);
                    System.out.println("Fecha de Publicación: " + fechaPublicacion);
                    System.out.println("----------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar comunicados: " + e.getMessage());
        }
    }

    public static void editarComunicado(Scanner scanner) {
        System.out.println("Ingrese el ID del comunicado que desea editar:");
        int idComunicado = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Comunicacion WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idComunicado);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Comunicado encontrado. Ingrese los nuevos datos:");

                    System.out.println("Nuevo Título:");
                    String titulo = scanner.nextLine();
                    System.out.println("Nuevo Contenido:");
                    String contenido = scanner.nextLine();
                    System.out.println("Nueva Fecha de Publicación (YYYY-MM-DD):");
                    String fechaPublicacion = scanner.nextLine();

                    sql = "UPDATE Comunicacion SET titulo = ?, contenido = ?, fechaPublicacion = ? " +
                          "WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(sql)) {
                        updateStmt.setString(1, titulo);
                        updateStmt.setString(2, contenido);
                        updateStmt.setString(3, fechaPublicacion);
                        updateStmt.setInt(4, idComunicado);

                        int rowsAffected = updateStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Comunicado actualizado correctamente.");
                        } else {
                            System.out.println("No se pudo actualizar el comunicado.");
                        }
                    }
                } else {
                    System.out.println("No se encontró ningún comunicado con ese ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar comunicado: " + e.getMessage());
        }
    }

    public static void eliminarComunicado(Scanner scanner) {
        System.out.println("Ingrese el ID del comunicado que desea eliminar:");
        int idComunicado = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DELETE FROM Comunicacion WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idComunicado);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Comunicado eliminado correctamente.");
                } else {
                    System.out.println("No se encontró ningún comunicado con ese ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar comunicado: " + e.getMessage());
        }
    }
}
