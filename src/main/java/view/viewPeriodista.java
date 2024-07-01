package view;

import java.util.Scanner;
import controlador.main;

public class viewPeriodista {
    public static void menuPeriodista() {
    while (true) {
        Scanner scanner = new Scanner(System.in);
        main.limpiarPantalla();
        System.out.println("----------------Bienvenido al sistema de organización para periodistas Betplay----------------");
        System.out.println("1. Generar comunicado de prensa ");
        System.out.println("2. Editar comunicados ");
        System.out.println("3. Eliminar comunicado ");
        System.out.println("4. Salir");

        System.out.println("Opcion:");

        int opcion = scanner.nextInt();
        scanner.nextLine();

            switch (opcion) {
                case 1:
                    generarComunicado(scanner);
                case 2:
                    editarComunicado(scanner);
                case 3:
                    eliminarComunicado(scanner);
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:                    
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
    private static void generarComunicado(Scanner scanner) {

    }
    private static void editarComunicado(Scanner scanner) {

    }
    private static void eliminarComunicado(Scanner scanner) {
        
    }
    
}   
