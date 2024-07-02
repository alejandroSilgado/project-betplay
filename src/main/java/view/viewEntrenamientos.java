package view;

import java.util.Scanner;
import controlador.main;
import model.entrenamientosManager;

public class viewEntrenamientos {
    @SuppressWarnings("resource")
    public static void menuEntrenamientos() {

    while (true) {
        main.limpiarPantalla();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("----------------Gestor de entrenamientos y convocatorias----------------");
        System.out.println("1. Registar sesiones de entrenamiento");
        System.out.println("2. Gestión de nombramientos para partidos");
        System.out.println("3. Salir");

        System.out.println("Opcion:");
        int opcion = scanner.nextInt();
        scanner.nextLine();

            switch (opcion) {
                case 1:
                    entrenamientosManager.registrarEntrenamientos(scanner);
                    break;
                case 2:
                    entrenamientosManager.realizarNombramientos(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }


}

