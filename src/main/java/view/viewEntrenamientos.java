package view;

import java.util.Scanner;
import controlador.main;

public class viewEntrenamientos {
    public static void menuEntrenamientos() {

    while (true) {
        main.limpiarPantalla();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("----------------Gestor de entrenamientos y convocatorias----------------");
        System.out.println("1. Registar sesiones de entrenamiento");
        System.out.println("2. Gestión de nombramientos para partidos");
        System.out.println("3. Salir");

        System.out.println("Opcion:");
        int opcion = scanner.nextInt();
        scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarEntrenamientos(scanner);
                case 2:
                    realizarNombramientos(scanner);
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    private static void registrarEntrenamientos(Scanner scanner) {

    }
    private static void realizarNombramientos(Scanner scanner) {

    }

}

