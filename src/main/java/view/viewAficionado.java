package view;

import java.util.Scanner;
import controlador.main;

public class viewAficionado {
    public static void menuAficionados() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------Bienvenido Aficionado---------------");
            System.out.println("1. Compra de Entradas ");
            System.out.println("2. Patrocinios de aficionados ");
            System.out.println("3. Salir");

            System.out.println("Opcion:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    comprarEntradas(scanner);
                case 2:
                    patrociniosAficionados(scanner);
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                     System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void comprarEntradas(Scanner scanner) {
        
    }

    private static void patrociniosAficionados(Scanner scanner) {

    }
}
