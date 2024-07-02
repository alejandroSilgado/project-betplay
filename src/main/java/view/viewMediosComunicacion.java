package view;

import java.util.Scanner;
import controlador.main;
import model.MediosComunicacionManager;

public class viewMediosComunicacion {
    public static void menuMediosComunicacion() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            main.limpiarPantalla();
            System.out.println("----------------Gestión de Medios de Comunicación----------------");
            System.out.println("1. Agregar Medio de Comunicación");
            System.out.println("2. Editar Medio de Comunicación");
            System.out.println("3. Eliminar Medio de Comunicación");
            System.out.println("4. Ver Medios de Comunicación");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcion) {
                case 1:
                    MediosComunicacionManager.agregarMedioComunicacion(scanner);
                    break;
                case 2:
                    MediosComunicacionManager.editarMedioComunicacion(scanner);
                    break;
                case 3:
                    MediosComunicacionManager.eliminarMedioComunicacion(scanner);
                    break;
                case 4:
                    MediosComunicacionManager.consultarMediosComunicacion();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
    }
}
