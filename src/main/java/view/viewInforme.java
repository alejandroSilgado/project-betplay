package view;

import java.util.Scanner;
import model.informeManager;
import controlador.main;
public class viewInforme {

    public static void menuInformes(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("----------- Generación de Informes -----------");
            System.out.println("1. Informe de Rendimiento de Equipos");
            System.out.println("2. Informe de Asistencia a Partidos");
            System.out.println("3. Informe de Lesiones");
            System.out.println("4. Volver al menú principal");
            System.out.println("Ingrese una opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                informeManager.generarInforme(1, scanner);
                    pausa(scanner);
                    break;
                case 2:
                informeManager.generarInforme(2, scanner);
                    pausa(scanner);
                    break;
                case 3:
                informeManager.generarInforme(3, scanner);
                    pausa(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    pausa(scanner);
            }
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
    }

    private static void pausa(Scanner scanner) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
