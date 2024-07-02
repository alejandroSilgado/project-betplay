package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import controlador.main;

public class partidosManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void programarPartido(Scanner scanner) {
        
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            main.limpiarPantalla();
            equiposManager.imprimirEquipos(scanner);

            System.out.println("");
            System.out.println("--------------------------------");
            System.out.println("SISTEMA DE CREACION DE PARTIDOS");
            System.out.println("--------------------------------");
            System.out.println("");

            System.out.println("Ingrese ID equipo local para agregar:");
            Integer equipoLocal_id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese ID equipo visitante para agregar:");
            Integer equipoVisitante_id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese la fecha para el partido (yyyy-MM-dd):");
            String fecha = scanner.nextLine();

            System.out.println("Ingrese la hora para el partido (HH:mm):");
            String hora = scanner.nextLine();

            System.out.println("Ingrese el estadio para el partido:");
            String estadio = scanner.nextLine();

            String sql = "INSERT INTO Partido (equipoLocal_id, equipoVisitante_id, fecha, hora, estadio) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, equipoLocal_id);
                pstmt.setInt(2, equipoVisitante_id);
                pstmt.setDate(3, Date.valueOf(fecha));
                pstmt.setString(4, hora);
                pstmt.setString(5, estadio);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Partido programado correctamente.");
                } else {
                    System.out.println("No se pudo programar el partido.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al programar partido: " + e.getMessage());
        }
    }

    public static void registrarResultados(Scanner scanner) {
        main.limpiarPantalla();
        imprimirPartidos(scanner);        
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("  SISTEMA DE RESULTADOS DE PARTIDOS ");
        System.out.println("------------------------------------");
        System.out.println("");
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese el ID del partido para registrar resultados:");
            Integer partidoId = scanner.nextInt();

            if (partidoId != null && partidoExiste(conn, partidoId)) {
                System.out.println("Ingrese los goles del equipo local:");
                Integer golesLocal = scanner.nextInt();

                System.out.println("Ingrese los goles del equipo visitante:");
                Integer golesVisitante = scanner.nextInt();

                String sql = "UPDATE Partido SET goles_local = ?, goles_visitante = ? " +
                        "WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, golesLocal);
                    pstmt.setInt(2, golesVisitante);
                    pstmt.setInt(3, partidoId);

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Resultados registrados correctamente.");
                    } else {
                        System.out.println("No se pudo registrar los resultados del partido.");
                    }
                }
            } else {
                System.out.println("El ID del partido no es válido o no existe en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar resultados: " + e.getMessage());
        }
    }

    public static void editarPartido(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            main.limpiarPantalla();
            imprimirPartidos(scanner);
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("   SISTEMA DE EDICION DE PARTIDOS   ");
            System.out.println("------------------------------------");
            System.out.println("");
            System.out.println("Ingrese el ID del partido a editar:");
            int partidoId = scanner.nextInt();
            scanner.nextLine();
    
            System.out.println("Ingrese la nueva fecha para el partido (yyyy-MM-dd):");
            String nuevaFecha = scanner.nextLine();
    
            System.out.println("Ingrese la nueva hora para el partido (HH:mm):");
            String nuevaHora = scanner.nextLine();
    
            System.out.println("Ingrese el nuevo estadio para el partido:");
            String nuevoEstadio = scanner.nextLine();
    
            String sql = "UPDATE Partido SET fecha = ?, hora = ?, estadio = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, Date.valueOf(nuevaFecha));
                pstmt.setString(2, nuevaHora);
                pstmt.setString(3, nuevoEstadio);
                pstmt.setInt(4, partidoId);
    
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Partido actualizado correctamente.");
                } else {
                    System.out.println("No se pudo actualizar el partido.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar partido: " + e.getMessage());
        }
    }
    
    public static void eliminarPartido(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            main.limpiarPantalla();
            imprimirPartidos(scanner);
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println(" SISTEMA DE ELIMINACIÓN DE PARTIDOS ");
            System.out.println("------------------------------------");
            System.out.println("");

            System.out.println("Ingrese el ID del partido a eliminar:");
            int partidoId = scanner.nextInt();
            scanner.nextLine();
    
            String sql = "DELETE FROM Partido WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, partidoId);
    
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Partido eliminado correctamente.");
                } else {
                    System.out.println("No se pudo eliminar el partido.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar partido: " + e.getMessage());
        }
    }
    
    public static void imprimirPartidos(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT p.id, p.equipoLocal_id, p.equipoVisitante_id, p.fecha, p.hora, " +
                    "e_local.nombre AS nombreLocal, e_visitante.nombre AS nombreVisitante " +
                    "FROM Partido p " +
                    "JOIN Equipo e_local ON p.equipoLocal_id = e_local.id " +
                    "JOIN Equipo e_visitante ON p.equipoVisitante_id = e_visitante.id";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("Partidos Existentes:");
                System.out.println("ID\tEquipo Local\t\tEquipo Visitante\t\tFecha\t\tHora");
                System.out.println("-------------------------------------------------------------------------------");

                while (rs.next()) {
                    int partidoId = rs.getInt("id");
                    String nombreLocal = rs.getString("nombreLocal");
                    String nombreVisitante = rs.getString("nombreVisitante");
                    String fecha = rs.getString("fecha");
                    String hora = rs.getString("hora");

                    System.out.printf("%d\t%-20s\t%-20s\t%-10s\t%-10s%n", partidoId, nombreLocal, nombreVisitante,
                            fecha, hora);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar partidos: " + e.getMessage());
        }
    }

    public static void imprimirResultados(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT r.id AS ResultadoID, p.id AS PartidoID, " +
                         "e_local.nombre AS EquipoLocal, e_visitante.nombre AS EquipoVisitante, " +
                         "r.golesEquipoLocal, r.golesEquipoVisitante " +
                         "FROM Resultado r " +
                         "INNER JOIN Partido p ON r.partido_id = p.id " +
                         "INNER JOIN Equipo e_local ON p.equipoLocal_id = e_local.id " +
                         "INNER JOIN Equipo e_visitante ON p.equipoVisitante_id = e_visitante.id";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
    
                System.out.println("Resultados de Partidos:");
                System.out.println("ID\tPartido\t\tEquipo Local\t\tEquipo Visitante\t\tGoles Local\tGoles Visitante");
                System.out.println("------------------------------------------------------------------------------------------------------------");
    
                while (rs.next()) {
                    int resultadoId = rs.getInt("ResultadoID");
                    int partidoId = rs.getInt("PartidoID");
                    String equipoLocal = rs.getString("EquipoLocal");
                    String equipoVisitante = rs.getString("EquipoVisitante");
                    int golesLocal = rs.getInt("golesEquipoLocal");
                    int golesVisitante = rs.getInt("golesEquipoVisitante");
    
                    System.out.printf("%d\t%d\t%-20s\t%-20s\t%d\t%d%n", resultadoId, partidoId, equipoLocal, equipoVisitante, golesLocal, golesVisitante);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al imprimir resultados: " + e.getMessage());
        }
    }
    
    private static boolean partidoExiste(Connection conn, int partidoId) throws SQLException {
        String sql = "SELECT id FROM Partido WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, partidoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
