package view;

import java.util.Scanner;
import controlador.main;
import model.*;
public class viewPeriodista {
    @SuppressWarnings("resource")
    public static void menuPeriodista() {
    while (true) {
        Scanner scanner = new Scanner(System.in);
        main.limpiarPantalla();
        System.out.println("----------------Bienvenido al sistema de organización para periodistas Betplay----------------");
        System.out.println("1. Sistema de generacion noticias ");
        System.out.println("2. Sistema de estadisticas ");
        System.out.println("3. Sistema de calendario ");
        System.out.println("4. Sistema de medios de comunicacion ");
        System.out.println("5. Gestión de Relaciones Públicas  ");
        System.out.println("6. Salir");

        System.out.println("Opcion:");

        int opcion = scanner.nextInt();
        scanner.nextLine();

            switch (opcion) {
                case 1:
                    periodistaManager.menuNoticias(scanner);
                case 2:
                    viewEstadisticas.menuEstadisticas(scanner);
                case 3:
                    calendarioManager.consultarCalendarioPartidos(scanner);
                case 4:
                    viewMediosComunicacion.menuMediosComunicacion();            
                case 5:
                     viewRelacionesPublicas.menuRelacionesPublicas(scanner);
                case 6:
                    return;
                default:                    
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
    }    
}   
