package model;

import java.sql.*;
import java.util.Scanner;

public class informeManager {

    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void generarInforme(int tipoInforme, Scanner scanner) {
        switch (tipoInforme) {
            case 1:
                generarInformeRendimientoEquipos();
                break;
            case 2:
                generarInformeAsistenciaPartidos();
                break;
            case 3:
                generarInformeLesiones(scanner);
                break;
            default:
                System.out.println("Tipo de informe no válido.");
                break;
        }
    }

    public static void generarInformeRendimientoEquipos() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Generando informe de rendimiento de equipos...");

            String sql = "SELECT " +
                         "    e.id AS equipo_id, " +
                         "    e.nombre AS nombre_equipo, " +
                         "    AVG(r.goles) AS promedio_goles, " +
                         "    AVG(r.asistencias) AS promedio_asistencias, " +
                         "    AVG(r.tarjetasAmarillas) AS promedio_tarjetas_amarillas, " +
                         "    AVG(r.tarjetasRojas) AS promedio_tarjetas_rojas " +
                         "FROM " +
                         "    Rendimiento r " +
                         "JOIN " +
                         "    Jugador j ON r.jugador_id = j.id " +
                         "JOIN " +
                         "    Equipo e ON j.equipo_id = e.id " +
                         "GROUP BY " +
                         "    e.id, e.nombre";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int equipoId = rs.getInt("equipo_id");
                    String nombreEquipo = rs.getString("nombre_equipo");
                    double promedioGoles = rs.getDouble("promedio_goles");
                    double promedioAsistencias = rs.getDouble("promedio_asistencias");
                    double promedioTarjetasAmarillas = rs.getDouble("promedio_tarjetas_amarillas");
                    double promedioTarjetasRojas = rs.getDouble("promedio_tarjetas_rojas");

                    System.out.printf("Equipo %d (%s):%n", equipoId, nombreEquipo);
                    System.out.printf("   Promedio de Goles: %.2f%n", promedioGoles);
                    System.out.printf("   Promedio de Asistencias: %.2f%n", promedioAsistencias);
                    System.out.printf("   Promedio de Tarjetas Amarillas: %.2f%n", promedioTarjetasAmarillas);
                    System.out.printf("   Promedio de Tarjetas Rojas: %.2f%n", promedioTarjetasRojas);
                    System.out.println("-------------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al generar informe de rendimiento de equipos: " + e.getMessage());
        }
    }
    
    public static void generarInformeAsistenciaPartidos() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Generando informe de asistencia a partidos...");

            String sql = "SELECT " +
                         "    p.id AS partido_id, " +
                         "    COUNT(e.id) AS cantidad_asistentes " +
                         "FROM " +
                         "    Partido p " +
                         "LEFT JOIN " +
                         "    Entrada e ON p.id = e.partido_id " +
                         "GROUP BY " +
                         "    p.id";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int partidoId = rs.getInt("partido_id");
                    int cantidadAsistentes = rs.getInt("cantidad_asistentes");
                    System.out.printf("Partido %d: Asistentes %d%n", partidoId, cantidadAsistentes);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al generar informe de asistencia a partidos: " + e.getMessage());
        }
    }

    private static void generarInformeLesiones(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Generando informe de lesiones...");
            String sql = "SELECT jugador_id, COUNT(*) AS cantidad_lesiones FROM Lesion GROUP BY jugador_id";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int jugadorId = rs.getInt("jugador_id");
                    int cantidadLesiones = rs.getInt("cantidad_lesiones");
                    System.out.printf("Jugador %d: Lesiones %d%n", jugadorId, cantidadLesiones);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al generar informe de lesiones: " + e.getMessage());
        }
    }
}
