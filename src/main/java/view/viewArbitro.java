package view;

import java.util.Scanner;

public class viewArbitro {
    @SuppressWarnings("resource")
    public static void menuArbitros() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("----------Bienvenido al sistema de organización para arbitros Betplay---------------");
            System.out.println("1. Programación y Resultados de Partidos ");
            System.out.println("2. Gestión de Incidentes y Sanciones  ");
            System.out.println("3. Salir");

            System.out.println("Opcion:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    viewPartidos.menuPartidos();
                    break;
                case 2:
                    viewIncidenteSanciones.menuIncidenteSanciones();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }
}
