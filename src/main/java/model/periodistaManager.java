package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class periodistaManager {
    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void menuNoticias(Scanner scanner) {
        int opcion;
        
        do {
            System.out.println("\nGestión de Noticias y Comunicados:");
            System.out.println("1. Agregar noticia/comunicado");
            System.out.println("2. Editar noticia/comunicado");
            System.out.println("3. Eliminar noticia/comunicado");
            System.out.println("4. Listar noticias/comunicados");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del nextInt()

            switch (opcion) {
                case 1:
                    agregarNoticia(scanner);
                    break;
                case 2:
                    editarNoticia(scanner);
                    break;
                case 3:
                    eliminarNoticia(scanner);
                    break;
                case 4:
                    listarNoticias();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void agregarNoticia(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese el contenido de la noticia/comunicado:");
            String contenido = scanner.nextLine();

            String sql = "INSERT INTO Noticias (contenido) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, contenido);
                pstmt.executeUpdate();
                System.out.println("Noticia/comunicado agregado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar noticia/comunicado: " + e.getMessage());
        }
    }

    private static void editarNoticia(Scanner scanner) {
        listarNoticias();
        System.out.println("Ingrese el ID de la noticia/comunicado a editar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            System.out.println("Ingrese el nuevo contenido de la noticia/comunicado:");
            String nuevoContenido = scanner.nextLine();

            String sql = "UPDATE Noticias SET contenido = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nuevoContenido);
                pstmt.setInt(2, id);
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Noticia/comunicado actualizado correctamente.");
                } else {
                    System.out.println("No se encontró la noticia/comunicado con ID " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al editar noticia/comunicado: " + e.getMessage());
        }
    }

    private static void eliminarNoticia(Scanner scanner) {
        listarNoticias();
        System.out.println("Ingrese el ID de la noticia/comunicado a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del nextInt()

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DELETE FROM Noticias WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Noticia/comunicado eliminado correctamente.");
                } else {
                    System.out.println("No se encontró la noticia/comunicado con ID " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar noticia/comunicado: " + e.getMessage());
        }
    }

    private static void listarNoticias() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT id, contenido FROM Noticias";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                System.out.println("Listado de Noticias/Comunicados:");
                System.out.println("ID\tContenido");
                System.out.println("--------------------------------------");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String contenido = rs.getString("contenido");
                    System.out.println(id + "\t" + contenido);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar noticias/comunicados: " + e.getMessage());
        }
    }
}
