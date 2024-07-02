package view;

import java.util.Scanner;

import controlador.main;
import model.IncidenteSancionesManager;

public class viewIncidenteSanciones {
    @SuppressWarnings("resource")
    public static void menuIncidenteSanciones() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----- Módulo de Gestión de Incidentes y Sanciones -----");
            System.out.println("1. Registrar Incidente");
            System.out.println("2. Aplicar Sanción");
            System.out.println("3. Listar Incidentes");

            System.out.println("4. Salir");

            System.out.print("Ingrese opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    IncidenteSancionesManager.registrarIncidente(scanner);
                    break;
                case 2:
                    IncidenteSancionesManager.aplicarSancion(scanner);
                    break;
                case 3:
                    IncidenteSancionesManager.imprimirIncidentes();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
    }        
}

