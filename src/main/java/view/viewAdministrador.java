package view;

import java.util.Scanner;
import controlador.main;

public class viewAdministrador {
    @SuppressWarnings("resource")
    public static void menuAdministradores() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            main.limpiarPantalla();
            System.out.println("----------Bienvenido al sistema de organización para administradores Betplay---------------");
            System.out.println("1. Gestión de equipos y jugadores "); 
            System.out.println("2. Gestión de entrenadores y arbitros ");
            System.out.println("3. Programación y Resultados de Partidos ");
            System.out.println("4. Gestión de Lesiones y Rendimiento ");
            System.out.println("5. Entrenamientos y Convocatorias"); 
            System.out.println("6. Transferencias de Jugadores "); 
            System.out.println("7. Venta de Entradas y calendario de partidos "); 
            System.out.println("8. Comunicaciones y Relaciones Públicas ");
            System.out.println("9. Estadísticas y Informes "); 
            System.out.println("10. Gestión de Estadios"); 
            System.out.println("11. Gestión de Patrocinios"); 
            System.out.println("12. Generación de Informes ");
            System.out.println("13. Gestión de Incidentes y Sanciones");
            System.out.println("14. Gestión de Equipamiento");
            System.out.println("16. Gestión de Premios y Reconocimientos  ");
            System.out.println("17. Gestión de Patrocinadores y Publicidad  ");
            System.out.println("18. Gestión de Relaciones Públicas  ");
            System.out.println("19. Salir ");

            System.out.println("Opcion:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    viewEquipo.menuEquiposJugadores();
                    break;
                case 2:
                    menuArbitroEntrenadores(scanner);
                    break;
                case 3:
                    viewPartidos.menuPartidos();
                    break;
                case 4:
                    viewLesiones.menuLesiones();
                    break;
                case 5:
                    viewEntrenamientos.menuEntrenamientos();
                    break;
                case 6:
                    viewTransferencias.menuTransferencias();
                    break;
                case 7:
                    viewAficionado.menuAficionados();
                    break;
                case 8:
                    viewPeriodista.menuPeriodista();
                    break;
                case 9:
                    viewEstadisticas.menuEstadisticas(scanner);
                    break;
                case 10:
                    viewEstadio.menuEstadio();
                    break;
                case 11:
                    viewPatrocinio.menuPatrocinio();
                    break;
                case 12:
                    viewInforme.menuInformes(scanner);
                    break;
                case 13:
                    viewIncidenteSanciones.menuIncidenteSanciones();
                    break;
                case 14:
                     viewEquipamiento.menuEquipamiento(scanner);
                    break;
                case 16:
                    viewPremios.menuPremios(scanner);
                    break;
                case 17:
                    viewPatrocinio.menuPatrocinio();
                    break;
                case 18:
                    viewRelacionesPublicas.menuRelacionesPublicas(scanner);
                    break;
                case 19:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();
        }
    }

    private static void menuArbitroEntrenadores(Scanner scanner) {
        while (true) {
            System.out.println("----------Gestión de entrenadores y arbitros---------------");
            System.out.println("1. Gestionar entrenadores");
            System.out.println("2. Gestionar arbitros");
            System.out.println("3. Volver al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    viewCrearEntrenador.menuEntrenadores();
                    break;
                case 2:
                    viewCrearArbitro.menuArbitro();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}
