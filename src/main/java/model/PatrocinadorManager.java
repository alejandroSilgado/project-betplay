package model;

import java.sql.*;
import java.util.Scanner;

public class PatrocinadorManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarPatrocinador(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese los detalles del nuevo patrocinador:");
            System.out.println("Nombre:");
            String nombre = scanner.nextLine();
            System.out.println("Tipo de Patrocinio:");
            String tipo = scanner.nextLine();
            System.out.println("Monto:");
            double monto = scanner.nextDouble();
            scanner.nextLine(); // Consumir salto de línea
            System.out.println("Fecha de Inicio (YYYY-MM-DD):");
            String fechaInicio = scanner.nextLine();
            System.out.println("Fecha de Fin (YYYY-MM-DD):");
            String fechaFin = scanner.nextLine();

            String sql = "INSERT INTO Patrocinador (nombre, tipo, monto, fechaInicio, fechaFin) " +
                         "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, tipo);
                pstmt.setDouble(3, monto);
                pstmt.setString(4, fechaInicio);
                pstmt.setString(5, fechaFin);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Patrocinador agregado correctamente.");
                } else {
                    System.out.println("No se pudo agregar el patrocinador.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar patrocinador: " + e.getMessage());
        }
    }

    public static void listarPatrocinadores() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Patrocinador";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String tipo = rs.getString("tipo");
                    double monto = rs.getDouble("monto");
                    String fechaInicio = rs.getString("fechaInicio");
                    String fechaFin = rs.getString("fechaFin");

                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Tipo: " + tipo);
                    System.out.println("Monto: " + monto);
                    System.out.println("Fecha de Inicio: " + fechaInicio);
                    System.out.println("Fecha de Fin: " + fechaFin);
                    System.out.println("----------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar patrocinadores: " + e.getMessage());
        }
    }

    public static void editarPatrocinador(Scanner scanner) {
        listarPatrocinadores();
        System.out.println("Ingrese el ID del patrocinador que desea editar:");
        int idPatrocinador = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Patrocinador WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idPatrocinador);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Patrocinador encontrado. Ingrese los nuevos datos:");

                    System.out.println("Nuevo Nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Nuevo Tipo:");
                    String tipo = scanner.nextLine();
                    System.out.println("Nuevo Monto:");
                    double monto = scanner.nextDouble();
                    scanner.nextLine(); // Consumir salto de línea
                    System.out.println("Nueva Fecha de Inicio (YYYY-MM-DD):");
                    String fechaInicio = scanner.nextLine();
                    System.out.println("Nueva Fecha de Fin (YYYY-MM-DD):");
                    String fechaFin = scanner.nextLine();

                    sql = "UPDATE Patrocinador SET nombre = ?, tipo = ?, monto = ?, fechaInicio = ?, fechaFin = ? " +
                          "WHERE id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(sql)) {
                        updateStmt.setString(1, nombre);
                        updateStmt.setString(2, tipo);
                        updateStmt.setDouble(3, monto);
                        updateStmt.setString(4, fechaInicio);
                        updateStmt.setString(5, fechaFin);
                        updateStmt.setInt(6, idPatrocinador);

                        int rowsAffected = updateStmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Patrocinador actualizado correctamente.");
                        } else {
                            System.out.println("No se pudo actualizar el patrocinador.");
                        }
                    }
                } else {
                    System.out.println("No se encontró ningún patrocinador con ese ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar patrocinador: " + e.getMessage());
        }
    }

    public static void eliminarPatrocinador(Scanner scanner) {
        listarPatrocinadores();
        System.out.println("Ingrese el ID del patrocinador que desea eliminar:");
        int idPatrocinador = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DELETE FROM Patrocinador WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idPatrocinador);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Patrocinador eliminado correctamente.");
                } else {
                    System.out.println("No se encontró ningún patrocinador con ese ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar patrocinador: " + e.getMessage());
        }
    }
}

