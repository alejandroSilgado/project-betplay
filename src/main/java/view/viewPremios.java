package view;

import java.util.Scanner;

import controlador.main;
import model.PremioManager;

public class viewPremios {
    public static void menuPremios(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("----- Gestión de Premios -----");
            System.out.println("1. Agregar Premio");
            System.out.println("2. Listar Premios");
            System.out.println("3. Editar Premio");
            System.out.println("4. Eliminar Premio");
            System.out.println("5. Volver al Menú Principal");
            System.out.println("Seleccione una opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    PremioManager.agregarPremio(scanner);
                    break;
                case 2:
                    PremioManager.listarPremios();
                    break;
                case 3:
                    PremioManager.editarPremio(scanner);
                    break;
                case 4:
                    PremioManager.eliminarPremio(scanner);
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
