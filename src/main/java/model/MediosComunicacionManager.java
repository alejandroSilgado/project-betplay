package model;

import java.sql.*;
import java.util.Scanner;

public class MediosComunicacionManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarMedioComunicacion(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese el nombre del medio de comunicación:");
            String nombreMedio = scanner.nextLine();

            System.out.println("Ingrese el tipo de medio (TV, Radio, Periódico, Online, Otro):");
            String tipoMedio = scanner.nextLine();

            System.out.println("Ingrese la cantidad de periodistas acreditados:");
            int periodistasAcreditados = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Insertar el medio de comunicación en la base de datos
            String sql = "INSERT INTO MediosComunicacion (nombre, tipo, periodistas_acreditados) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombreMedio);
                pstmt.setString(2, tipoMedio);
                pstmt.setInt(3, periodistasAcreditados);
                pstmt.executeUpdate();
                System.out.println("Medio de comunicación agregado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el medio de comunicación: " + e.getMessage());
        }
    }

    public static void editarMedioComunicacion(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese el ID del medio de comunicación que desea editar:");
            int idMedio = scanner.nextInt();
            scanner.nextLine(); // Consume el newline character

            System.out.println("Ingrese el nuevo nombre del medio de comunicación:");
            String nuevoNombre = scanner.nextLine();

            System.out.println("Ingrese el nuevo tipo de medio (TV, Radio, Periódico, Online, Otro):");
            String nuevoTipo = scanner.nextLine();

            System.out.println("Ingrese la nueva cantidad de periodistas acreditados:");
            int nuevosPeriodistas = scanner.nextInt();
            scanner.nextLine(); // Consume el newline character

            // Actualizar el medio de comunicación en la base de datos
            String sql = "UPDATE MediosComunicacion SET nombre = ?, tipo = ?, periodistas_acreditados = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nuevoNombre);
                pstmt.setString(2, nuevoTipo);
                pstmt.setInt(3, nuevosPeriodistas);
                pstmt.setInt(4, idMedio);
                int filasActualizadas = pstmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Medio de comunicación actualizado correctamente.");
                } else {
                    System.out.println("No se encontró el medio de comunicación con ID " + idMedio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar el medio de comunicación: " + e.getMessage());
        }
    }

    public static void eliminarMedioComunicacion(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese el ID del medio de comunicación que desea eliminar:");
            int idMedio = scanner.nextInt();
            scanner.nextLine(); // Consume el newline character

            // Eliminar el medio de comunicación de la base de datos
            String sql = "DELETE FROM MediosComunicacion WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idMedio);
                int filasEliminadas = pstmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Medio de comunicación eliminado correctamente.");
                } else {
                    System.out.println("No se encontró el medio de comunicación con ID " + idMedio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el medio de comunicación: " + e.getMessage());
        }
    }

    public static void consultarMediosComunicacion() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT id, nombre, tipo, periodistas_acreditados FROM MediosComunicacion";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Lista de Medios de Comunicación:");
                System.out.println("ID\tNombre\t\tTipo\t\tPeriodistas Acreditados");
                System.out.println("---------------------------------------------------");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String tipo = rs.getString("tipo");
                    int periodistas = rs.getInt("periodistas_acreditados");
                    System.out.printf("%d\t%s\t\t%s\t\t%d%n", id, nombre, tipo, periodistas);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los medios de comunicación: " + e.getMessage());
        }
    }
}
