package model;

import java.sql.*;
import java.util.Scanner;

public class IncidenteSancionesManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void registrarIncidente(Scanner scanner){
            System.out.println("Registro de Incidente:");
            partidosManager.imprimirPartidos(scanner);
            System.out.print("Ingrese el ID del partido: ");
            int partidoId = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Ingrese la descripción del incidente: ");
            String descripcion = scanner.nextLine();
            System.out.print("Ingrese el minuto del incidente: ");
            int minuto = scanner.nextInt();
            scanner.nextLine(); 

        String sql = "INSERT INTO Incidente (partido_id, descripcion, minuto) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, partidoId);
            pstmt.setString(2, descripcion);
            pstmt.setInt(3, minuto);

            pstmt.executeUpdate();
            System.out.println("Incidente registrado satisfactoriamente:");
            System.out.println("Partido ID: " + partidoId);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Minuto: " + minuto);
        } catch (SQLException e) {
            System.out.println("Error al registrar incidente: " + e.getMessage());
        }
    }

    public static void aplicarSancion(Scanner scanner) {
        System.out.println("Aplicación de Sanción:");
        imprimirIncidentes();
        System.out.print("Ingrese el ID del incidente: ");
        int incidenteId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tipo de sanción: ");
        String tipo = scanner.nextLine();
        System.out.print("Comentario: ");
        String comentario = scanner.nextLine();
        String sql = "INSERT INTO Sancion (incidente_id, tipo, comentario) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, incidenteId);
            pstmt.setString(2, tipo);
            pstmt.setString(3, comentario);

            pstmt.executeUpdate();
            System.out.println("Sanción aplicada correctamente:");
            System.out.println("Incidente ID: " + incidenteId);
            System.out.println("Tipo de sanción: " + tipo);
            System.out.println("Comentario: " + comentario);
        } catch (SQLException e) {
            System.out.println("Error al aplicar sanción: " + e.getMessage());
        }
    }
    public static void imprimirIncidentes() {
        String sql = "SELECT id, partido_id, descripcion, minuto FROM Incidente";

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de Incidentes:");
            System.out.println("ID\tPartido ID\tDescripción\tMinuto");
            System.out.println("--------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                int partidoId = rs.getInt("partido_id");
                String descripcion = rs.getString("descripcion");
                int minuto = rs.getInt("minuto");

                System.out.printf("%d\t%d\t%s\t%d%n", id, partidoId, descripcion, minuto);
            }
        } catch (SQLException e) {
            System.out.println("Error al imprimir incidentes: " + e.getMessage());
        }
    }
}

