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
            System.out.println("2. Editar partidos");
            System.out.println("3. Eliminar partidos");
            System.out.println("4. Registrar resultados de partidos");
            System.out.println("5. Salir");

            System.out.println("Opción:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    partidosManager.programarPartido(scanner);
                    break;
                case 2:
                    partidosManager.editarPartido(scanner);
                    break;
                case 3:
                    partidosManager.eliminarPartido(scanner);
                    break;
                case 4:
                    partidosManager.registrarResultados(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
    }
}
