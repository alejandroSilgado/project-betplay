package controlador;

import java.util.Scanner;

import view.viewAdministrador;
import view.viewAficionado;
import view.viewEntrenador;
import view.viewEquipo;
import view.viewJugador;
import view.viewPeriodista;

public class main {
    public static void main(String[] args) {
        while (true) {
            limpiarPantalla();
            Scanner scanner = new Scanner(System.in);
            System.out.println("---------------Sistema de ingreso plataforma de Betplay--------------------------");
            System.out.println("1. Ingresar con cuenta existente");
            System.out.println("2. Crear cuenta ");
            System.out.println("2. Salir");
            System.out.println("Ingrese una opción: " );
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    sistemaIngreso(scanner);
                    break;
                case 2:
                    System.exit(0);
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
            case "administrador@gmail.com":
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
