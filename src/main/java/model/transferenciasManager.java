package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controlador.main;

public class transferenciasManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void registrarTransferencia(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            main.limpiarPantalla();
            jugadorManager.imprimirJugadores();
            System.out.println("Ingrese el ID del jugador: ");
            int jugadorId = scanner.nextInt();
            scanner.nextLine();

            equiposManager.imprimirEquipos(scanner);

            System.out.println("Ingrese el ID del equipo de origen: ");
            int equipoOrigenId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el ID del equipo de destino: ");
            int equipoDestinoId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el monto de la transferencia: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Ingrese la fecha de la transferencia (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();

            String sql = "INSERT INTO Transferencia (jugador_id, equipoOrigen_id, equipoDestino_id, monto, fecha) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, jugadorId);
                pstmt.setInt(2, equipoOrigenId);
                pstmt.setInt(3, equipoDestinoId);
                pstmt.setDouble(4, monto);
                pstmt.setString(5, fecha);
                pstmt.executeUpdate();
                System.out.println("Transferencia registrada con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar transferencia: " + e.getMessage());
        }
    }

    public static void editarTransferencia(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            main.limpiarPantalla();
            listarTransferencias();

            System.out.println("Ingrese el ID de la transferencia a editar: ");
            int transferenciaId = scanner.nextInt();
            scanner.nextLine();
            jugadorManager.imprimirJugadores();
            System.out.println("Ingrese el nuevo ID del jugador: ");
            int jugadorId = scanner.nextInt();
            scanner.nextLine();
            equiposManager.imprimirEquipos(scanner);
            System.out.println("Ingrese el nuevo ID del equipo de origen: ");
            int equipoOrigenId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el nuevo ID del equipo de destino: ");
            int equipoDestinoId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el nuevo monto de la transferencia: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Ingrese la nueva fecha de la transferencia (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();

            String sql = "UPDATE Transferencia SET jugador_id = ?, equipoOrigen_id = ?, equipoDestino_id = ?, monto = ?, fecha = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, jugadorId);
                pstmt.setInt(2, equipoOrigenId);
                pstmt.setInt(3, equipoDestinoId);
                pstmt.setDouble(4, monto);
                pstmt.setString(5, fecha);
                pstmt.setInt(6, transferenciaId);
                pstmt.executeUpdate();
                System.out.println("Transferencia editada con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al editar transferencia: " + e.getMessage());
        }
    }

    public static void eliminarTransferencia(Scanner scanner) {

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            main.limpiarPantalla();
            listarTransferencias();
            System.out.println("Ingrese el ID de la transferencia a eliminar: ");
            int transferenciaId = scanner.nextInt();
            scanner.nextLine();

            String sql = "DELETE FROM Transferencia WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, transferenciaId);
                pstmt.executeUpdate();
                System.out.println("Transferencia eliminada con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar transferencia: " + e.getMessage());
        }
    }

    public static void listarTransferencias() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT t.id, j.nombre AS nombreJugador, eo.nombre AS nombreEquipoOrigen, ed.nombre AS nombreEquipoDestino, t.monto, t.fecha "
                    + "FROM Transferencia t "
                    + "JOIN Jugador j ON t.jugador_id = j.id "
                    + "JOIN Equipo eo ON t.equipoOrigen_id = eo.id "
                    + "JOIN Equipo ed ON t.equipoDestino_id = ed.id";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                System.out.println("Transferencias registradas:");
                System.out.println("ID\tJugador\t\tEquipo Origen\t\tEquipo Destino\t\tMonto\t\tFecha");
                System.out.println("--------------------------------------------------------------------------------------------");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombreJugador = rs.getString("nombreJugador");
                    String nombreEquipoOrigen = rs.getString("nombreEquipoOrigen");
                    String nombreEquipoDestino = rs.getString("nombreEquipoDestino");
                    double monto = rs.getDouble("monto");
                    String fecha = rs.getString("fecha");

                    System.out.printf("%d\t%-15s\t%-20s\t%-20s\t%.2f\t%s%n", id, nombreJugador, nombreEquipoOrigen, nombreEquipoDestino, monto, fecha);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar transferencias: " + e.getMessage());
        }
    }
}
