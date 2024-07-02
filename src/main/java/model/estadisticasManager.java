package model;

import java.sql.*;
import java.util.Scanner;

public class estadisticasManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void estadisticasPorJugador(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            jugadorManager.imprimirJugadores();
            System.out.println("");
            System.out.println("Ingrese el ID del jugador para consultar sus estadísticas:");
            int jugadorId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            String sql = "SELECT j.nombre AS jugador, r.minutosJugados, r.goles, r.asistencias, r.tarjetasAmarillas, r.tarjetasRojas "
                       + "FROM Rendimiento r "
                       + "JOIN Jugador j ON r.jugador_id = j.id "
                       + "WHERE r.jugador_id = ?";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, jugadorId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("Estadísticas del jugador:");
                    System.out.println("Jugador\t\tMinutos Jugados\tGoles\tAsistencias\tTarjetas Amarillas\tTarjetas Rojas");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    while (rs.next()) {
                        String nombreJugador = rs.getString("jugador");
                        int minutosJugados = rs.getInt("minutosJugados");
                        int goles = rs.getInt("goles");
                        int asistencias = rs.getInt("asistencias");
                        int tarjetasAmarillas = rs.getInt("tarjetasAmarillas");
                        int tarjetasRojas = rs.getInt("tarjetasRojas");

                        System.out.printf("%s\t\t%d\t\t%d\t%d\t\t%d\t\t\t%d%n", nombreJugador, minutosJugados, goles, asistencias, tarjetasAmarillas, tarjetasRojas);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar estadísticas del jugador: " + e.getMessage());
        }
    }

    public static void estadisticasPorEquipo(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            equiposManager.imprimirEquipos(scanner);

            System.out.println("");
            System.out.println("Ingrese el ID del equipo para consultar sus estadísticas:");
            int equipoId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            String sql = "SELECT e.nombre AS equipo, SUM(r.minutosJugados) AS totalMinutos, SUM(r.goles) AS totalGoles, "
                       + "SUM(r.asistencias) AS totalAsistencias, SUM(r.tarjetasAmarillas) AS totalTarjetasAmarillas, "
                       + "SUM(r.tarjetasRojas) AS totalTarjetasRojas "
                       + "FROM Rendimiento r "
                       + "JOIN Jugador j ON r.jugador_id = j.id "
                       + "JOIN Equipo e ON j.equipo_id = e.id "
                       + "WHERE e.id = ? "
                       + "GROUP BY e.nombre";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, equipoId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("Estadísticas del equipo:");
                    System.out.println("Equipo\t\tTotal Minutos\tTotal Goles\tTotal Asistencias\tTotal Tarjetas Amarillas\tTotal Tarjetas Rojas");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    while (rs.next()) {
                        String nombreEquipo = rs.getString("equipo");
                        int totalMinutos = rs.getInt("totalMinutos");
                        int totalGoles = rs.getInt("totalGoles");
                        int totalAsistencias = rs.getInt("totalAsistencias");
                        int totalTarjetasAmarillas = rs.getInt("totalTarjetasAmarillas");
                        int totalTarjetasRojas = rs.getInt("totalTarjetasRojas");

                        System.out.printf("%s\t\t%d\t\t%d\t%d\t\t%d\t\t\t%d%n", nombreEquipo, totalMinutos, totalGoles, totalAsistencias, totalTarjetasAmarillas, totalTarjetasRojas);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar estadísticas del equipo: " + e.getMessage());
        }
    }

    public static void estadisticasPorTemporada(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese la temporada para consultar las estadísticas:");
            String temporada = scanner.nextLine();
            
            String sql = "SELECT e.nombre AS equipo, SUM(r.minutosJugados) AS totalMinutos, SUM(r.goles) AS totalGoles, "
                       + "SUM(r.asistencias) AS totalAsistencias, SUM(r.tarjetasAmarillas) AS totalTarjetasAmarillas, "
                       + "SUM(r.tarjetasRojas) AS totalTarjetasRojas "
                       + "FROM Rendimiento r "
                       + "JOIN Jugador j ON r.jugador_id = j.id "
                       + "JOIN Equipo e ON j.equipo_id = e.id "
                       + "JOIN Partido p ON r.partido_id = p.id "
                       + "WHERE p.temporada = ? "
                       + "GROUP BY e.nombre";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, temporada);
                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("Estadísticas por temporada:");
                    System.out.println("Equipo\t\tTotal Minutos\tTotal Goles\tTotal Asistencias\tTotal Tarjetas Amarillas\tTotal Tarjetas Rojas");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    while (rs.next()) {
                        String nombreEquipo = rs.getString("equipo");
                        int totalMinutos = rs.getInt("totalMinutos");
                        int totalGoles = rs.getInt("totalGoles");
                        int totalAsistencias = rs.getInt("totalAsistencias");
                        int totalTarjetasAmarillas = rs.getInt("totalTarjetasAmarillas");
                        int totalTarjetasRojas = rs.getInt("totalTarjetasRojas");

                        System.out.printf("%s\t\t%d\t\t%d\t%d\t\t%d\t\t\t%d%n", nombreEquipo, totalMinutos, totalGoles, totalAsistencias, totalTarjetasAmarillas, totalTarjetasRojas);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar estadísticas por temporada: " + e.getMessage());
        }
    }
}
