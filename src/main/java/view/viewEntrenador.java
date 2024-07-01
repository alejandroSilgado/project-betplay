package view;

import java.util.Scanner;
import controlador.main;

public class viewEntrenador {
    public static void menuEntrenador(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("----------Bienvenido entrenador Betplay---------------");
            System.out.println("1. Gestión de Equipos y Jugadores ");
            System.out.println("2. Programación y Resultados de Partidos ");
            System.out.println("3. Gestión de Lesiones y Rendimiento ");
            System.out.println("4. Entrenamientos y Convocatorias");
            System.out.println("5. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    viewEquipo.menuEquiposJugadores();
                case 2:
                    viewPartidos.menuPartidos();
                case 3:
                    viewLesiones.menuLesiones();
                case 4:
                    viewEntrenamientos.menuEntrenamientos();
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
