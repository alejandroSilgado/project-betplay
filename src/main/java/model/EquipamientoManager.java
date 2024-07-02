package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipamientoManager {

    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void agregarEquipamiento(String tipo, int cantidad, int equipoId, String fechaAdquisicion) {
        String sql = "INSERT INTO Equipamiento (tipo, cantidad, equipo_id, fecha_adquisicion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipo);
            pstmt.setInt(2, cantidad);
            pstmt.setInt(3, equipoId);
            pstmt.setString(4, fechaAdquisicion);
            pstmt.executeUpdate();
            System.out.println("Equipamiento agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar equipamiento: " + e.getMessage());
        }
    }

    public static void editarEquipamiento(int id, String tipo, int cantidad, int equipoId, String fechaAdquisicion) {
        String sql = "UPDATE Equipamiento SET tipo = ?, cantidad = ?, equipo_id = ?, fecha_adquisicion = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipo);
            pstmt.setInt(2, cantidad);
            pstmt.setInt(3, equipoId);
            pstmt.setString(4, fechaAdquisicion);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Equipamiento editado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al editar equipamiento: " + e.getMessage());
        }
    }

    public static void eliminarEquipamiento(int id) {
        String sql = "DELETE FROM Equipamiento WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Equipamiento eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar equipamiento: " + e.getMessage());
        }
    }

    public static void listarEquipamiento() {
        String sql = "SELECT id, tipo, cantidad, equipo_id, fecha_adquisicion FROM Equipamiento";
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Lista de Equipamiento:");
            System.out.println("ID\tTipo\tCantidad\tEquipo\tFecha Adquisición");
            System.out.println("--------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                int cantidad = rs.getInt("cantidad");
                int equipoId = rs.getInt("equipo_id");
                String fechaAdquisicion = rs.getString("fecha_adquisicion");

                System.out.printf("%d\t%s\t%d\t%d\t%s%n", id, tipo, cantidad, equipoId, fechaAdquisicion);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar equipamiento: " + e.getMessage());
        }
    }
}
