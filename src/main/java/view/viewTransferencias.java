package view;

import java.util.Scanner;
import controlador.main;

public class viewTransferencias {
    public static void menuTransferencias() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------------Transferencias----------------");
            System.out.println("1. Registrar transferencia de jugador");
            System.out.println("2. Editar transferencia de jugador");
            System.out.println("3. Eliminar transferencia de jugador");
            System.out.println("4. Listar transferencias");
            System.out.println("5. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarTransferencia(scanner);
                    break;
                case 2:
                    editarTransferencia(scanner);
                    break;
                case 3:
                    eliminarTransferencia(scanner);
                    break;
                case 4:
                    listarTransferencias(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        }
    }

    private static void registrarTransferencia(Scanner scanner) {
    }

    private static void editarTransferencia(Scanner scanner) {
    }

    private static void eliminarTransferencia(Scanner scanner) {
    }

    private static void listarTransferencias(Scanner scanner) {
    }
}
