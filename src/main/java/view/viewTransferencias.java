package view;

import java.util.Scanner;
import controlador.main;
import model.transferenciasManager;

public class viewTransferencias {
    @SuppressWarnings("resource")
    public static void menuTransferencias() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------------Transferencias----------------");
            System.out.println("1. Registrar transferencia de jugador");
            System.out.println("2. Editar transferencia de jugador");
            System.out.println("3. Eliminar transferencia de jugador");
            System.out.println("4. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    transferenciasManager.registrarTransferencia(scanner);
                    break;
                case 2:
                    transferenciasManager.editarTransferencia(scanner);
                    break;
                case 3:
                    transferenciasManager.eliminarTransferencia(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
    }        
}