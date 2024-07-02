package view;

import java.util.Scanner;
import model.estadioManager;
import controlador.main;

public class viewEstadio {
    public static void menuEstadio() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            main.limpiarPantalla();
            System.out.println("----------Gestión de Estadios---------------");
            System.out.println("1. Agregar estadio");
            System.out.println("2. Editar estadio");
            System.out.println("3. Eliminar estadio");
            System.out.println("4. Listar estadios");
            System.out.println("5. Volver al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarEstadio(scanner);
                    break;
                case 2:
                    editarEstadio(scanner);
                    break;
                case 3:
                    eliminarEstadio(scanner);
                    break;
                case 4:
                    listarEstadios();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }

    private static void agregarEstadio(Scanner scanner) {
        System.out.println("Ingrese el nombre del estadio: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la capacidad del estadio: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese la ubicación del estadio: ");
        String ubicacion = scanner.nextLine();

        estadioManager.agregarEstadio(nombre, capacidad, ubicacion);
    }

    private static void editarEstadio(Scanner scanner) {
        listarEstadios();
        System.out.println("Ingrese el ID del estadio que desea editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nuevo nombre del estadio: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la nueva capacidad del estadio: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese la nueva ubicación del estadio: ");
        String ubicacion = scanner.nextLine();

        estadioManager.editarEstadio(id, nombre, capacidad, ubicacion);
    }

    private static void eliminarEstadio(Scanner scanner) {
        estadioManager.listarEstadios();
        System.out.println("Ingrese el ID del estadio que desea eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        estadioManager.eliminarEstadio(id);
    }

    private static void listarEstadios() {
        estadioManager.listarEstadios();
    }
}
