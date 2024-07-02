package view;

import java.util.Scanner;

import controlador.main;
import model.ComunicadoManager;

public class viewRelacionesPublicas {
    public static void menuRelacionesPublicas(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("----- Gestión de Relaciones Públicas -----");
            System.out.println("1. Agregar Comunicado");
            System.out.println("2. Listar Comunicados");
            System.out.println("3. Editar Comunicado");
            System.out.println("4. Eliminar Comunicado");
            System.out.println("5. Volver al Menú Principal");
            System.out.println("Seleccione una opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcion) {
                case 1:
                    ComunicadoManager.agregarComunicado(scanner);
                    break;
                case 2:
                    ComunicadoManager.listarComunicados();
                    break;
                case 3:
                    ComunicadoManager.editarComunicado(scanner);
                    break;
                case 4:
                    ComunicadoManager.eliminarComunicado(scanner);
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
    }
}
