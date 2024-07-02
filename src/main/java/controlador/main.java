package controlador;

import java.util.Scanner;
import view.viewAdministrador;
import view.viewAficionado;
import view.viewEntrenador;
import view.viewEquipo;
import view.viewJugador;
import view.viewPeriodista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        while (true) {
            limpiarPantalla();
            Scanner scanner = new Scanner(System.in);
            System.out.println("---------------Sistema de ingreso plataforma de Betplay--------------------------");
            System.out.println("1. Ingresar con cuenta existente");
            System.out.println("2. Crear cuenta ");
            System.out.println("3. Salir");
            System.out.println("Ingrese una opción: " );
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    sistemaIngreso(scanner);
                    break;
                case 2:
                    crearCuenta (scanner);
                case 3:
                    System.exit(0);
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

        switch (email) {
            case "admin":
                if (password.equals("1234567")) {
                    viewAdministrador.menuAdministradores();
                }
                break;
            case "aficionado@gmail.com":
                if (password.equals("1234567")) {
                    viewAficionado.menuAficionados();
                }
                break;
            case "entrenador@gmail.com":
                if (password.equals("1234567")) {
                    viewEntrenador.menuEntrenador(scanner);
                }
                break;
            case "equipo@gmail.com":
                if (password.equals("1234567")) {
                    viewEquipo.menuEquiposJugadores();
                }
                break;
            case "jugador@gmail.com":
                if (password.equals("1234567")) {
                    viewJugador.menuJugador();
                }
                break;
            case "periodista@gmail.com":
                if (password.equals("1234567")) {
                    viewPeriodista.menuPeriodista();
                }
                break;
            default:
                System.out.println("Credenciales incorrectas.");
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

        System.out.println("Ingrese su rol: ");
        String rol = scanner.nextLine();

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


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/betplay", "root", "karol@1014198153");
            String sql = "INSERT INTO usuarios (nombre, edad, email, rol, contraseña) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, rol);
            preparedStatement.setString(5, contraseña);
            preparedStatement.executeUpdate();
            System.out.println("Cuenta creada con éxito");
        } catch (SQLException e) {
            e.printStackTrace();
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
