package view;

import java.util.Scanner;
import controlador.main;
import model.aficionadoManager;
import model.calendarioManager;

public class viewAficionado {
    @SuppressWarnings("resource")
    public static void menuAficionados() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------Bienvenido Aficionado---------------");
            System.out.println("1. Compra de Entradas ");
            System.out.println("2. Modulo de estadisticas ");
            System.out.println("3. Consultar calendario de partidos");
            System.out.println("4. Salir");

            System.out.println("Opcion:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    aficionadoManager.comprarEntrada(scanner, opcion);
                    break;
                case 2:
                    viewEstadisticas.menuEstadisticas(scanner);
                case 3:
                    calendarioManager.consultarCalendarioPartidos(scanner);
                    break;
                case 4:
                     return;
                default:
                     System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }
}
