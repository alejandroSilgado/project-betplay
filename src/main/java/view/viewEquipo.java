package view;

import java.util.Scanner;

import controlador.main;
import model.equiposManager;
public class viewEquipo {

    public static void menuEquiposJugadores() {
        main.limpiarPantalla();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------- ¿Qué gestión desea realizar? --------------");
            System.out.println("1. Equipos");
            System.out.println("2. Jugadores");
            System.out.println("3. Salir");

            System.out.println("Opción:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuEquipos(scanner);
                    break;
                case 2:
                    viewJugador.menuJugador();
                    break;
                case 3:
                    System.out.println("Saliendo....");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void menuEquipos(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("---------------- Equipos ----------------");
            System.out.println("1. Registrar Equipos");
            System.out.println("2. Editar Equipos");
            System.out.println("3. Eliminar Equipos");
            System.out.println("4. Salir");

            System.out.println("Opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    equiposManager.registrarEquipos(scanner);
                case 2:
                    equiposManager.editarEquipos(scanner);
                    break;
                case 3:
                    equiposManager.eliminarEquipos(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo....");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
