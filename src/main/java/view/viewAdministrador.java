package view;
import java.util.Scanner;
import controlador.main;

public class viewAdministrador {
    public static void menuAdministradores() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------Bienvenido al sistema de organización para administradores Betplay---------------");
            System.out.println("1. Gestión de Equipos y Jugadores ");
            System.out.println("2. Programación y Resultados de Partidos ");
            System.out.println("3. Gestión de Lesiones y Rendimiento ");
            System.out.println("4. Entrenamientos y Convocatorias");
            System.out.println("5. Transferencias de Jugadores ");
            System.out.println("6. Venta de Entradas y Patrocinios ");
            System.out.println("7. Comunicaciones y Relaciones Públicas ");
            System.out.println("8. Estadísticas y Informes ");
            System.out.println("9. Salir");

            System.out.println("Opcion:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    viewEquipo.menuEquiposJugadores();
                case 2:
                    viewPartidos.menuPartidos();
                case 3:
                    viewLesiones.menuLesiones();
                case 4:
                    viewEntrenamientos.menuEntrenamientos();
                case 5:
                    viewTransferencias.menuTransferencias();
                case 6:
                    viewAficionado.menuAficionados();
                case 7:
                    viewPeriodista.menuPeriodista();
                case 8:
                    viewEstadisticas.menuEstadisticas();
                case 9:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}
