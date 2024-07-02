package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class aficionadoManager {
  private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void comprarEntrada(Scanner scanner, int usuarioId) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            mostrarPartidosDisponibles(conn);

            System.out.println("Seleccione el ID del partido al que desea asistir: ");
            int partidoId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese la cantidad de entradas: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese la ubicación en el estadio (Ej: 'VIP', 'General', 'Tribuna Este', etc.): ");
            String ubicacion = scanner.nextLine();

            System.out.println("Ingrese el precio total: ");
            double precioTotal = scanner.nextDouble();
            scanner.nextLine();

            if (procesarPago(scanner, precioTotal)) {
                String sql = "INSERT INTO Entrada (partido_id, comprador_id, fechaCompra, cantidad, precioTotal, ubicacion) VALUES (?, ?, GETDATE(), ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, partidoId);
                    pstmt.setInt(2, usuarioId);
                    pstmt.setInt(3, cantidad);
                    pstmt.setDouble(4, precioTotal);
                    pstmt.setString(5, ubicacion);
                    pstmt.executeUpdate();
                    System.out.println("Compra realizada con éxito. Sus entradas han sido emitidas.");
                }
            } else {
                System.out.println("Pago no realizado. Intente nuevamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al comprar entrada: " + e.getMessage());
        }
    }

    private static void mostrarPartidosDisponibles(Connection conn) throws SQLException {
        String sql = "SELECT p.id, el.nombre AS equipoLocal, ev.nombre AS equipoVisitante, p.fecha, p.hora, p.estadio "
                + "FROM Partido p "
                + "JOIN Equipo el ON p.equipoLocal_id = el.id "
                + "JOIN Equipo ev ON p.equipoVisitante_id = ev.id";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Partidos disponibles:");
            System.out.println("ID\tEquipo Local\tEquipo Visitante\tFecha\t\tHora\t\tEstadio");
            System.out.println("------------------------------------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String equipoLocal = rs.getString("equipoLocal");
                String equipoVisitante = rs.getString("equipoVisitante");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");
                String estadio = rs.getString("estadio");

                System.out.printf("%d\t%s\t%s\t%s\t%s\t%s%n", id, equipoLocal, equipoVisitante, fecha, hora, estadio);
            }
        }
    }

    private static boolean procesarPago(Scanner scanner, double monto) {
        System.out.println("Procesando pago de $" + monto);
        System.out.println("Ingrese los detalles de su tarjeta de crédito.");

        System.out.println("Número de tarjeta: ");
        String numeroTarjeta = scanner.nextLine();

        System.out.println("Fecha de vencimiento (MM/YY): ");
        String fechaVencimiento = scanner.nextLine();

        System.out.println("Código de seguridad: ");
        String codigoSeguridad = scanner.nextLine();

        // Por simplicidad, asumiremos que el pago siempre es exitoso.
        System.out.println("Pago realizado con éxito.");
        return true;
    }
}
