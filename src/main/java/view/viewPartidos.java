package view;

import java.util.Scanner;
import controlador.main;
import model.partidosManager;

public class viewPartidos {
    @SuppressWarnings("resource")
    public static void menuPartidos() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("---------------- Bienvenido al sistema de gestión de Partidos ----------------------");
            System.out.println("1. Programar próximos partidos");
            System.out.println("2. Registrar resultados de partidos");
            System.out.println("3. Salir");

            System.out.println("Opción:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    partidosManager.programarPartido(scanner);
                    break;
                case 2:
                    partidosManager.registrarResultados(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}
