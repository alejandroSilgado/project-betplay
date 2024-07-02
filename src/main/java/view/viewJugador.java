package view;

import java.util.Scanner;

import controlador.main;
import model.jugadorManager;

public class viewJugador {
    public static void menuJugador() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            main.limpiarPantalla();
            System.out.println("---------------- Jugadores ----------------------");
            System.out.println("1. Registrar Jugadores");
            System.out.println("2. Editar Jugadores");
            System.out.println("3. Eliminar Jugadores");
            System.out.println("4. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    jugadorManager.registrarJugadores(scanner);
                case 2:
                    jugadorManager.editarJugadores(scanner);
                case 3:
                    jugadorManager.eliminarJugadores(scanner);
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
