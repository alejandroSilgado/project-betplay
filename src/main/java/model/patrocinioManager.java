package model;

import java.sql.*;
import java.util.Scanner;

public class patrocinioManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarPatrocinio(Scanner scanner) {
        System.out.println("Ingrese la empresa patrocinadora: ");
        String empresa = scanner.nextLine();

        System.out.println("Ingrese el monto del patrocinio: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese la duración del patrocinio en años: ");
        int duracion = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "INSERT INTO Patrocinio (empresa, monto, duracion) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, empresa);
                pstmt.setDouble(2, monto);
                pstmt.setInt(3, duracion);
                pstmt.executeUpdate();
                System.out.println("Patrocinio agregado con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el patrocinio: " + e.getMessage());
        }
    }

    public static void editarPatrocinio(Scanner scanner) {
        listarPatrocinios();
        System.out.println("Ingrese el ID del patrocinio a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese la nueva empresa patrocinadora: ");
        String empresa = scanner.nextLine();

        System.out.println("Ingrese el nuevo monto del patrocinio: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese la nueva duración del patrocinio en años: ");
        int duracion = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "UPDATE Patrocinio SET empresa = ?, monto = ?, duracion = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, empresa);
                pstmt.setDouble(2, monto);
                pstmt.setInt(3, duracion);
                pstmt.setInt(4, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Patrocinio actualizado con éxito.");
                } else {
                    System.out.println("No se encontró el patrocinio con el ID especificado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar el patrocinio: " + e.getMessage());
        }
    }

    public static void eliminarPatrocinio(Scanner scanner) {
        listarPatrocinios();
        System.out.println("Ingrese el ID del patrocinio a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DELETE FROM Patrocinio WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Patrocinio eliminado con éxito.");
                } else {
                    System.out.println("No se encontró el patrocinio con el ID especificado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el patrocinio: " + e.getMessage());
        }
    }

    public static void listarPatrocinios() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT id, empresa, monto, duracion FROM Patrocinio";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("Lista de Patrocinios:");
                System.out.println("ID\tEmpresa\t\t\t\tMonto\tDuración (años)");
                System.out.println("------------------------------------------------------------");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String empresa = rs.getString("empresa");
                    double monto = rs.getDouble("monto");
                    int duracion = rs.getInt("duracion");

                    System.out.printf("%d\t%-30s\t$%.2f\t%d%n", id, empresa, monto, duracion);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar patrocinios: " + e.getMessage());
        }
    }
}
