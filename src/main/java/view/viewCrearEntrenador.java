package view;

import java.util.Scanner;
import controlador.main;
import model.entrenadorManager;

public class viewCrearEntrenador {
    @SuppressWarnings("resource")
    public static void menuEntrenadores() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------------Gestión de Entrenadores----------------");
            System.out.println("1. Agregar Entrenador");
            System.out.println("2. Editar Entrenador");
            System.out.println("3. Eliminar Entrenador");
            System.out.println("4. Listar Entrenadores");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    entrenadorManager.agregarEntrenador(scanner);
                    break;
                case 2:
                    entrenadorManager.editarEntrenador(scanner);
                    break;
                case 3:
                    entrenadorManager.eliminarEntrenador(scanner);
                    break;
                case 4:
                    entrenadorManager.listarEntrenadores();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }
}
