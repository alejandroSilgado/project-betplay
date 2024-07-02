package controlador;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import view.viewAdministrador;
import view.viewAficionado;
import view.viewArbitro;
import view.viewEntrenador;
import view.viewJugador;
import view.viewPeriodista;

public class main {

    private static final String CONNECTION_URL = "jdbc:sqlserver://betplay.mssql.somee.com;"
            + "databaseName=betplay;"
            + "user=alejoDev_SQLLogin_1;"
            + "password=m1000@suck;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static void main(String[] args) {
        while (true) {
            limpiarPantalla();
            Scanner scanner = new Scanner(System.in);
            System.out.println("---------------Sistema de ingreso plataforma de Betplay--------------------------");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Crear cuenta ");
            System.out.println("3. Salir");
            System.out.println("");
            System.out.println("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    sistemaIngreso(scanner);
                    break;
                case 2:
                    crearCuenta(scanner);
                    break;
                case 3:
                    viewPeriodista.menuPeriodista();
                    // System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void sistemaIngreso(Scanner scanner) {
        limpiarPantalla();
        System.out.println("---------------Sistema de usuarios Betplay--------------------------");
        System.out.println("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "SELECT rol FROM Usuario WHERE email = ? AND contraseña = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int rol = rs.getInt("rol");
                    switch (rol) {
                        case 1:
                            viewAdministrador.menuAdministradores();
                            break;
                        case 2:
                            viewEntrenador.menuEntrenador(scanner);
                            break;
                        case 3:
                            viewPeriodista.menuPeriodista();
                            break;
                        case 4:
                            viewJugador.menuJugador();

                            break;
                        case 5:
                            viewAficionado.menuAficionados();
                            break;
                        case 6:
                            viewArbitro.menuArbitros();
                            break;
                        default:
                            System.out.println("Rol no reconocido.");
                    }
                } else {
                    System.out.println("Credenciales incorrectas.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }

    private static void crearCuenta(Scanner scanner) {
        limpiarPantalla();
        System.out.println("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese su email: ");
        String email = scanner.nextLine();

        String contraseña;
        String confirmacion;

        do {
            System.out.println("Ingrese su contraseña: ");
            contraseña = scanner.nextLine();

            System.out.println("Ingrese su contraseña nuevamente: ");
            confirmacion = scanner.nextLine();

            if (!contraseña.equals(confirmacion)) {
                System.out.println("Las contraseñas no coinciden. Inténtelo nuevamente.");
            }
        } while (!contraseña.equals(confirmacion));

        int rol;
        do {
            System.out.println("Seleccione su rol: ");
            System.out.println("1. Administrador");
            System.out.println("2. Entrenador");
            System.out.println("3. Periodista");
            System.out.println("4. Jugador");
            System.out.println("5. Aficionado");
            System.out.println("6. Árbitro");

            System.out.print("Ingrese el número correspondiente a su rol: ");
            rol = scanner.nextInt();
            scanner.nextLine();
        } while (rol < 1 || rol > 6);

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "INSERT INTO Usuario (nombre, edad, email, contraseña, rol) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setInt(2, edad);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, contraseña);
                preparedStatement.setInt(5, rol);
                preparedStatement.executeUpdate();
                System.out.println("Cuenta creada con éxito.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la cuenta: " + e.getMessage());
        }
    }

    public static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}