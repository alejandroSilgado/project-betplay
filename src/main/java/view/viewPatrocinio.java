package view;

import java.util.Scanner;
import controlador.main;
import model.patrocinioManager;

public class viewPatrocinio {
    @SuppressWarnings("resource")
    public static void menuPatrocinio() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------Gestión de Patrocinios---------------");
            System.out.println("1. Agregar patrocinio");
            System.out.println("2. Editar patrocinio");
            System.out.println("3. Eliminar patrocinio");
            System.out.println("4. Listar patrocinios");
            System.out.println("5. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    patrocinioManager.agregarPatrocinio(scanner);
                    break;
                case 2:
                    patrocinioManager.editarPatrocinio(scanner);
                    break;
                case 3:
                    patrocinioManager.eliminarPatrocinio(scanner);
                    break;
                case 4:
                    patrocinioManager.listarPatrocinios();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
            System.out.println("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }
}
