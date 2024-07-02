package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class entrenamientosManager {
    
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void registrarEntrenamientos(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            equiposManager.imprimirEquipos(scanner);           
            System.out.println("");
            System.out.println("--------------------------------");
            System.out.println("   REGISTRO DE ENTRENAMIENTOS   ");
            System.out.println("--------------------------------");
            System.out.println("");

            System.out.println("Ingrese el ID del equipo para registrar entrenamiento:");

            int equipoId = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese la fecha del entrenamiento (yyyy-MM-dd):");
            String fecha = scanner.nextLine();

            System.out.println("Ingrese la hora del entrenamiento (HH:mm):");
            String hora = scanner.nextLine();

            System.out.println("Ingrese el lugar del entrenamiento:");
            String lugar = scanner.nextLine();

            String sql = "INSERT INTO Entrenamiento (equipo_id, fecha, hora, lugar) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, equipoId);
                pstmt.setDate(2, Date.valueOf(fecha));
                pstmt.setString(3, hora);
                pstmt.setString(4, lugar);

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Entrenamiento registrado correctamente.");
                } else {
                    System.out.println("No se pudo registrar el entrenamiento.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar entrenamiento: " + e.getMessage());
        }
    }

    public static void realizarNombramientos(Scanner scanner ) {
            try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
                partidosManager.imprimirPartidos(scanner);
                System.out.println("--------------------------------");
                System.out.println("REALIZACIÓN DE NOMBRAMIENTOS");
                System.out.println("--------------------------------");
                System.out.println("");
                
                System.out.println("Ingrese el ID del partido para realizar el nombramiento:");
                int partidoId = scanner.nextInt();
                scanner.nextLine();

                jugadorManager.imprimirJugadores();
                for (int i = 0; i < 12; i++) {
                    System.out.println("Ingrese el ID del jugador a convocar (jugador " + (i + 1) + "):");
                    int jugadorId = scanner.nextInt();
                    scanner.nextLine();
    
                    String sql = "INSERT INTO Nombramientos (partido_id, jugadores_nombrados) VALUES (?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, partidoId);
                        pstmt.setString(2, Integer.toString(jugadorId));
    
                        int affectedRows = pstmt.executeUpdate();
                        if (affectedRows > 0) {
                            System.out.println("Nombramiento realizado correctamente para el jugador " + jugadorId);
                        } else {
                            System.out.println("No se pudo realizar el nombramiento para el jugador " + jugadorId);
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al realizar nombramientos: " + e.getMessage());
            }
        }
    

}
