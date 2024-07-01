package view;

import java.util.Scanner;
import controlador.main;

public class viewLesiones {
    public static void menuLesiones() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("---------------- Gestor de Lesiones y Rendimiento  ----------------------");
            System.out.println("1. Registrar lesiones");
            System.out.println("2. Seguimiento de rendimiento");
            System.out.println("3. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarLesiones(scanner);
                    break;
                case 2:
                    seguimientoRendimiento(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void registrarLesiones(Scanner scanner) {
        while (true) {
        
            main.limpiarPantalla();
            System.out.println("----------------Lesiones----------------");
            System.out.println("1. Crear lesiones");
            System.out.println("2. Editar lesiones");
            System.out.println("3. Eliminar lesiones");
            System.out.println("4. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearLesiones(scanner);
                    break;
                case 2:
                    editarLesiones(scanner);
                    break;
                case 3:
                    eliminarLesiones(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void seguimientoRendimiento(Scanner scanner) {
        // Implementa la lógica para el seguimiento de rendimiento
    }

    private static void crearLesiones(Scanner scanner) {
        // Implementa la lógica para crear lesiones
    }

    private static void editarLesiones(Scanner scanner) {
        // Implementa la lógica para editar lesiones
    }

    private static void eliminarLesiones(Scanner scanner) {
        // Implementa la lógica para eliminar lesiones
    }
}
