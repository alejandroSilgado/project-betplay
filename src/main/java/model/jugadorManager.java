package model;

import java.sql.*;
import java.util.Scanner;
import controlador.main;

public class jugadorManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void imprimirJugadores() {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL);
                Statement stmt = connection.createStatement()) {
            String sql = "SELECT j.id AS JugadorID, j.nombre AS JugadorNombre, e.nombre AS EquipoNombre " +
                    "FROM Jugador j " +
                    "JOIN Equipo e ON j.equipo_id = e.id;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Jugadores Existentes:");
            System.out.println("ID\tNombre Jugador\t\tEquipo");
            System.out.println("------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("JugadorID");
                String nombre = rs.getString("JugadorNombre");
                String equipo = rs.getString("EquipoNombre");

                System.out.printf("%-5d\t%-20s\t%s%n", id, nombre, equipo);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void registrarJugadores(Scanner scanner) {
        main.limpiarPantalla();
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("REGISTRO DE JUGADORES");
            System.out.println(" ");
            System.out.println("Ingrese nombre del jugador:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese la edad del jugador:");
            int edad = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese la posición del jugador:");
            String posicion = scanner.nextLine();

            System.out.println("Ingrese la nacionalidad del jugador:");
            String nacionalidad = scanner.nextLine();

            System.out.println("Ingrese el número de camiseta del jugador:");
            int numeroCamiseta = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el ID del equipo al que pertenece el jugador:");
            int equipoId = scanner.nextInt();
            scanner.nextLine();

            String sql = "INSERT INTO Jugador (nombre, edad, posicion, nacionalidad, numeroCamiseta, equipo_id) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setInt(2, edad);
                pstmt.setString(3, posicion);
                pstmt.setString(4, nacionalidad);
                pstmt.setInt(5, numeroCamiseta);
                pstmt.setInt(6, equipoId);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Jugador registrado exitosamente.");
                } else {
                    System.out.println("No se pudo registrar el jugador.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar jugador: " + e.getMessage());
        }
    }

    public static void editarJugadores(Scanner scanner) {
        main.limpiarPantalla();
        imprimirJugadores(); // Mostrar la lista de jugadores existentes
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese ID del jugador a editar:");
            int id = scanner.nextInt();
            scanner.nextLine();

            String checkSql = "SELECT * FROM Jugador WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next()) {
                    System.out.println("Jugador no encontrado.");
                    return;
                }
            }

            System.out.println("Ingrese nombre del jugador:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese la edad del jugador:");
            int edad = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese la posición del jugador:");
            String posicion = scanner.nextLine();

            System.out.println("Ingrese la nacionalidad del jugador:");
            String nacionalidad = scanner.nextLine();

            System.out.println("Ingrese el número de camiseta del jugador:");
            int numeroCamiseta = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el ID del equipo al que pertenece el jugador:");
            int equipoId = scanner.nextInt();
            scanner.nextLine();

            String sql = "UPDATE Jugador SET nombre = ?, edad = ?, posicion = ?, nacionalidad = ?, numeroCamiseta = ?, equipo_id = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setInt(2, edad);
                pstmt.setString(3, posicion);
                pstmt.setString(4, nacionalidad);
                pstmt.setInt(5, numeroCamiseta);
                pstmt.setInt(6, equipoId);
                pstmt.setInt(7, id);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Jugador editado exitosamente.");
                } else {
                    System.out.println("No se pudo editar el jugador.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar jugador: " + e.getMessage());
        }
    }

    public static void eliminarJugadores(Scanner scanner) {
        main.limpiarPantalla();
        imprimirJugadores();
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese ID del jugador a eliminar:");
            int id = scanner.nextInt();
            scanner.nextLine();

            String sql = "DELETE FROM Jugador WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Jugador eliminado exitosamente.");
                } else {
                    System.out.println("No se pudo eliminar el jugador. Verifique si el ID existe.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar jugador: " + e.getMessage());
        }
    }
}
