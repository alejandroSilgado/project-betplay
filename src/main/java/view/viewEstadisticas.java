package view;

import java.util.Scanner;

import controlador.main; // Asumiendo que controlador.main tiene métodos como limpiarPantalla
import model.estadisticasManager;

public class viewEstadisticas {

    public static void menuEstadisticas(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("---------------- Estadísticas ----------------");
            System.out.println("1. Estadísticas por jugador");
            System.out.println("2. Estadísticas por equipo");
            System.out.println("3. Estadísticas por temporada");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (opcion) {
                case 1:
                    estadisticasManager.estadisticasPorJugador(scanner);
                    break;
                case 2:
                    estadisticasManager.estadisticasPorEquipo(scanner);
                    break;
                case 3:
                    estadisticasManager.estadisticasPorTemporada(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }

            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }
}
