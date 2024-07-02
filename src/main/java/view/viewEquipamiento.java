package view;

import java.util.Scanner;

import controlador.main;
import model.EquipamientoManager;

public class viewEquipamiento {

    public static void menuEquipamiento(Scanner scanner) {
       while (true) {
        main.limpiarPantalla();
            System.out.println("----- Módulo de Gestión de Equipamiento -----");
            System.out.println("1. Agregar Equipamiento");
            System.out.println("2. Editar Equipamiento");
            System.out.println("3. Eliminar Equipamiento");
            System.out.println("4. Listar Equipamiento");
            System.out.println("5. Salir");

            System.out.print("Ingrese opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcion) {
                case 1:
                    agregarEquipamiento(scanner);
                    break;
                case 2:
                    editarEquipamiento(scanner);
                    break;
                case 3:
                    eliminarEquipamiento(scanner);
                    break;
                case 4:
                    listarEquipamiento();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }

    private static void agregarEquipamiento(Scanner scanner) {
        System.out.println("Ingrese tipo de equipamiento: ");
        String tipo = scanner.nextLine();

        System.out.println("Ingrese cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Ingrese ID del equipo asociado: ");
        int equipoId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Ingrese fecha de adquisición (YYYY-MM-DD): ");
        String fechaAdquisicion = scanner.nextLine();

        EquipamientoManager.agregarEquipamiento(tipo, cantidad, equipoId, fechaAdquisicion);
    }

    private static void editarEquipamiento(Scanner scanner) {
        listarEquipamiento();
        System.out.println("Ingrese ID del equipamiento a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Ingrese nuevo tipo de equipamiento: ");
        String tipo = scanner.nextLine();

        System.out.println("Ingrese nueva cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Ingrese nuevo ID del equipo asociado: ");
        int equipoId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Ingrese nueva fecha de adquisición (YYYY-MM-DD): ");
        String fechaAdquisicion = scanner.nextLine();

        EquipamientoManager.editarEquipamiento(id, tipo, cantidad, equipoId, fechaAdquisicion);
    }

    private static void eliminarEquipamiento(Scanner scanner) {
        listarEquipamiento();
        System.out.println("Ingrese ID del equipamiento a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        EquipamientoManager.eliminarEquipamiento(id);
    }

    private static void listarEquipamiento() {
        EquipamientoManager.listarEquipamiento();
    }
}
