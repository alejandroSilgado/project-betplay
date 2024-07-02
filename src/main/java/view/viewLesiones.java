package view;

import java.util.Scanner;
import controlador.main;
import model.lesionesManager;

public class viewLesiones {
    @SuppressWarnings("resource")
    public static void menuLesiones() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
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
                    lesionesManager.seguimientoRendimiento(scanner);
                    break;  
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
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
                    lesionesManager.crearLesiones(scanner);
                    break;
                case 2:
                    lesionesManager.editarLesiones(scanner);
                    break;
                case 3:
                    lesionesManager.eliminarLesiones(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}
