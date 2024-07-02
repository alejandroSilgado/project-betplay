package view;

import java.util.Scanner;
import model.arbitroManager;
import controlador.main;
public class viewCrearArbitro {

    public static void menuArbitro() {
        Scanner scanner = new Scanner(System.in);
        main.limpiarPantalla();

        while (true) {
            System.out.println("------------ Gestión de Árbitros ------------");
            System.out.println("1. Agregar Árbitro");
            System.out.println("2. Editar Árbitro");
            System.out.println("3. Eliminar Árbitro");
            System.out.println("4. Listar Árbitros");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (opcion) {
                case 1:
                    agregarArbitro(scanner);
                    break;
                case 2:
                    editarArbitro(scanner);
                    break;
                case 3:
                    eliminarArbitro(scanner);
                    break;
                case 4:
                    listarArbitros();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }

    private static void agregarArbitro(Scanner scanner) {
        System.out.print("Ingrese el nombre del árbitro: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la experiencia del árbitro (años): ");
        int experiencia = scanner.nextInt();
        scanner.nextLine(); // consume newline

        arbitroManager.agregarArbitro(nombre, experiencia);
    }

    private static void editarArbitro(Scanner scanner) {
        System.out.print("Ingrese el ID del árbitro a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Ingrese el nuevo nombre del árbitro: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la nueva experiencia del árbitro (años): ");
        int experiencia = scanner.nextInt();
        scanner.nextLine(); // consume newline

        arbitroManager.editarArbitro(id, nombre, experiencia);
    }

    private static void eliminarArbitro(Scanner scanner) {
        System.out.print("Ingrese el ID del árbitro a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        arbitroManager.eliminarArbitro(id);
    }

    private static void listarArbitros() {
        arbitroManager.listarArbitros();
    }
}
