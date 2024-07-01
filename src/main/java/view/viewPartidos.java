package view;

import java.util.Scanner;
import controlador.main;

public class viewPartidos {

    public static void menuPartidos() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("---------------- Bienvenido al sistema de gestion de Partidos ----------------------");
            System.out.println("1. Programar partidos");
            System.out.println("2. Registrar resultados partidos");
            System.out.println("3. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    programarPartidos(scanner);
                case 2:
                    registrarResultados(scanner);
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void programarPartidos(Scanner scanner) {

    }

    private static void registrarResultados(Scanner scanner) {

    }
}
