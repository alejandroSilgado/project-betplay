package model;

import java.sql.*;
import java.util.Scanner;

public class calendarioManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void consultarCalendarioPartidos(Scanner scanner) {
        while (true) {
            try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
                System.out.println("Seleccione la temporada o el rango de fechas para consultar:");
                System.out.println("1. Imprimir todos los partidos");
                System.out.println("2. Rango de fechas específico");
                System.out.println("3. Salir");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                String sql = "SELECT p.id, p.fecha, p.hora, e_local.nombre AS equipoLocal, e_visitante.nombre AS equipoVisitante, p.estadio "
                           + "FROM Partido p "
                           + "JOIN Equipo e_local ON p.equipoLocal_id = e_local.id "
                           + "JOIN Equipo e_visitante ON p.equipoVisitante_id = e_visitante.id ";
                
                if (opcion == 1) {
                    partidosManager.imprimirResultados(scanner);
                } else if (opcion == 2) {
                    System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD): ");
                    String fechaInicio = scanner.nextLine();
                    System.out.println("Ingrese la fecha de fin (YYYY-MM-DD): ");
                    String fechaFin = scanner.nextLine();
                    sql += "WHERE p.fecha BETWEEN ? AND ? ORDER BY p.fecha, p.hora";
                    
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, fechaInicio);
                        pstmt.setString(2, fechaFin);
                        ResultSet rs = pstmt.executeQuery();
                        imprimirPartidos(rs);
                    }
                } else if (opcion == 3) {
                    System.out.println("Saliendo del calendario de partidos...");
                    return;
                } else {
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            } catch (SQLException e) {
                System.out.println("Error al consultar el calendario de partidos: " + e.getMessage());
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }

    private static void imprimirPartidos(ResultSet rs) throws SQLException {
        System.out.println("Calendario de Partidos:");
        System.out.println("ID\tFecha\t\tHora\t\tEquipo Local\t\tEquipo Visitante\t\tEstadio");
        System.out.println("--------------------------------------------------------------------------------------------");
        while (rs.next()) {
            int id = rs.getInt("id");
            String fecha = rs.getString("fecha");
            String hora = rs.getString("hora");
            String equipoLocal = rs.getString("equipoLocal");
            String equipoVisitante = rs.getString("equipoVisitante");
            String estadio = rs.getString("estadio");

            System.out.printf("%d\t%s\t%s\t%-20s\t%-20s\t%s%n", id, fecha, hora, equipoLocal, equipoVisitante, estadio);
        }
    }
}
