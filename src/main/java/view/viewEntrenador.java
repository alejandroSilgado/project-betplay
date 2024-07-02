package view;

import java.util.Scanner;
import controlador.main;

public class viewEntrenador {
    public static void menuEntrenador(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("----------Bienvenido entrenador Betplay---------------");
            System.out.println("1. Gestión de Jugadores ");
            System.out.println("2. Programación y Resultados de Partidos ");
            System.out.println("3. Gestión de Lesiones y Rendimiento ");
            System.out.println("4. Entrenamientos y Convocatorias");
            System.out.println("5. Estadisticas de Jugadores, Equipos y partidos");
            System.out.println("6. Gestor de trasnferencias de jugadores");
            System.out.println("7. Generación de Informes ");
            System.out.println("8. Gestión de Equipamiento ");
            System.out.println("9. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    viewJugador.menuJugador();
                    break;
                case 2:
                    viewPartidos.menuPartidos();
                    break;
                case 3:
                    viewLesiones.menuLesiones();
                    break;
                case 4:
                    viewEntrenamientos.menuEntrenamientos();
                    break;
                case 5:
                    viewEstadisticas.menuEstadisticas(scanner);
                    break;
                case 6:
                    viewTransferencias.menuTransferencias();
                    break;
                case 7:
                    viewInforme.menuInformes(scanner);
                    break;
                case 8:
                    viewEquipamiento.menuEquipamiento(scanner);
                    break;
                case 9:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }
}
