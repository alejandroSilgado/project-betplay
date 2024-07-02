package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;
import controlador.main;

public class lesionesManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
        + "databaseName=betplay;"
        + "user=alejoDev_SQLLogin_1;"
        + "password=m1000@suck;"
        + "encrypt=true;"
        + "trustServerCertificate=true;";

    public static void seguimientoRendimiento(Scanner scanner) {
        main.limpiarPantalla();
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT * FROM Rendimiento";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
    
                System.out.printf("%-5s %-15s %-15s %-20s %-5s %-10s %-15s %-10s%n", 
                                  "ID", "Jugador ID", "Partido ID", "Minutos Jugados", 
                                  "Goles", "Asistencias", "Tarjetas Amarillas", "Tarjetas Rojas");
                System.out.println("-----------------------------------------------------------------------------------------");
    
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int jugadorId = rs.getInt("jugador_id");
                    int partidoId = rs.getInt("partido_id");
                    int minutosJugados = rs.getInt("minutosJugados");
                    int goles = rs.getInt("goles");
                    int asistencias = rs.getInt("asistencias");
                    int tarjetasAmarillas = rs.getInt("tarjetasAmarillas");
                    int tarjetasRojas = rs.getInt("tarjetasRojas");
    
                    System.out.printf("%-5d %-15d %-15d %-20d %-5d %-10d %-15d %-10d%n", 
                                      id, jugadorId, partidoId, minutosJugados, 
                                      goles, asistencias, tarjetasAmarillas, tarjetasRojas);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar rendimiento: " + e.getMessage());
        }
    }
    
    public static void crearLesiones(Scanner scanner) {
        main.limpiarPantalla();
        jugadorManager.imprimirJugadores();
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese ID del jugador:");
            int jugadorId = scanner.nextInt();
            scanner.nextLine();
    
            System.out.println("Ingrese tipo de lesión:");
            String tipo = scanner.nextLine();
    
            System.out.println("Ingrese gravedad de la lesión:");
            String gravedad = scanner.nextLine();
    
            System.out.println("Ingrese fecha de inicio de la lesión (yyyy-mm-dd):");
            String fechaInicio = scanner.nextLine();
    
            System.out.println("Ingrese fecha de fin de la lesión (yyyy-mm-dd) (opcional):");
            String fechaFin = scanner.nextLine();
    
            String sql = "INSERT INTO Lesion (jugador_id, tipo, gravedad, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, jugadorId);
                pstmt.setString(2, tipo);
                pstmt.setString(3, gravedad);
                pstmt.setDate(4, Date.valueOf(fechaInicio));
                if (fechaFin.isEmpty()) {
                    pstmt.setNull(5, Types.DATE);
                } else {
                    pstmt.setDate(5, Date.valueOf(fechaFin));
                }
    
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Lesión registrada exitosamente.");
                } else {
                    System.out.println("No se pudo registrar la lesión.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar lesión: " + e.getMessage());
        }
    }
    
    public static void editarLesiones(Scanner scanner) {
        main.limpiarPantalla();
        imprimirLesiones();
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese ID de la lesión a editar:");
            int id = scanner.nextInt();
            scanner.nextLine();
    
            String checkSql = "SELECT * FROM Lesion WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next()) {
                    System.out.println("Lesión no encontrada.");
                    return;
                }
            }
    
            System.out.println("Ingrese nuevo tipo de lesión:");
            String tipo = scanner.nextLine();
    
            System.out.println("Ingrese nueva gravedad de la lesión:");
            String gravedad = scanner.nextLine();
    
            System.out.println("Ingrese nueva fecha de inicio de la lesión (yyyy-mm-dd):");
            String fechaInicio = scanner.nextLine();
    
            System.out.println("Ingrese nueva fecha de fin de la lesión (yyyy-mm-dd) (opcional):");
            String fechaFin = scanner.nextLine();
    
            String sql = "UPDATE Lesion SET tipo = ?, gravedad = ?, fechaInicio = ?, fechaFin = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, tipo);
                pstmt.setString(2, gravedad);
                pstmt.setDate(3, Date.valueOf(fechaInicio));
                if (fechaFin.isEmpty()) {
                    pstmt.setNull(4, Types.DATE);
                } else {
                    pstmt.setDate(4, Date.valueOf(fechaFin));
                }
                pstmt.setInt(5, id);
    
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Lesión editada exitosamente.");
                } else {
                    System.out.println("No se pudo editar la lesión.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar lesión: " + e.getMessage());
        }
    }
    
    public static void eliminarLesiones(Scanner scanner) {
        main.limpiarPantalla();
        imprimirLesiones();
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese ID de la lesión a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();
    
            String sql = "DELETE FROM Lesion WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
    
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Lesión eliminada exitosamente.");
                } else {
                    System.out.println("No se pudo eliminar la lesión. Verifique si el ID existe.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar lesión: " + e.getMessage());
        }
    }    

    public static void imprimirLesiones() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT Lesion.id, Jugador.nombre, Lesion.tipo, Lesion.gravedad, Lesion.fechaInicio, Lesion.fechaFin " +
                         "FROM Lesion " +
                         "JOIN Jugador ON Lesion.jugador_id = Jugador.id";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
    
                System.out.printf("%-5s %-25s %-20s %-15s %-15s %-15s%n", 
                                  "ID", "Nombre del Jugador", "Tipo", "Gravedad", 
                                  "Fecha Inicio", "Fecha Fin");
                System.out.println("---------------------------------------------------------------------------------------------");
    
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombreJugador = rs.getString("nombre");
                    String tipo = rs.getString("tipo");
                    String gravedad = rs.getString("gravedad");
                    Date fechaInicio = rs.getDate("fechaInicio");
                    Date fechaFin = rs.getDate("fechaFin");
    
                    System.out.printf("%-5d %-25s %-20s %-15s %-15s %-15s%n", 
                                      id, nombreJugador, tipo, gravedad, 
                                      fechaInicio, fechaFin != null ? fechaFin.toString() : "N/A");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar lesiones: " + e.getMessage());
        }
    }
    
}
