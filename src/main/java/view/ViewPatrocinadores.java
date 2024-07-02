package view;

import java.util.Scanner;

import controlador.main;
import model.PatrocinadorManager;

public class ViewPatrocinadores{
    public static void menuPatrocinadores(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("----- Gestión de Patrocinadores -----");
            System.out.println("1. Agregar Patrocinador");
            System.out.println("2. Listar Patrocinadores");
            System.out.println("3. Editar Patrocinador");
            System.out.println("4. Eliminar Patrocinador");
            System.out.println("5. Volver al Menú Principal");
            System.out.println("Seleccione una opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    PatrocinadorManager.agregarPatrocinador(scanner);
                    break;
                case 2:
                    PatrocinadorManager.listarPatrocinadores();
                    break;
                case 3:
                    PatrocinadorManager.editarPatrocinador(scanner);
                    break;
                case 4:
                    PatrocinadorManager.eliminarPatrocinador(scanner);
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
