package view;

import java.util.Scanner;
import controlador.main;

public class viewEstadisticas {
    public static void menuEstadisticas(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------------Estadisticas----------------");
            System.out.println("1. Rendimiento Equipos");
            System.out.println("2. Rendimiento Jugadores ");
            System.out.println("3. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextInt();

            switch (opcion) {
                case 1:
                    rendimientoEquipos(scanner);
                case 2:
                    rendimientoJugadores(scanner);
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                     System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    private static void rendimientoEquipos(Scanner scanner){

    }
    private static void rendimientoJugadores(Scanner scanner){
        
    }
}
