package view;

import java.util.Scanner;
import model.Equipo;
import model.Jugador;
import controlador.controlador;
import controlador.main;

public class viewEquipo {

    public static void menuEquiposJugadores() {
        main.limpiarPantalla();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------- ¿Qué gestión desea realizar? --------------");
            System.out.println("1. Equipos");
            System.out.println("2. Jugadores");
            System.out.println("3. Salir");

            System.out.println("Opción:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuEquipos(scanner);
                    break;
                case 2:
                    viewJugador.menuJugador();
                    break;
                case 3:
                    System.out.println("Saliendo....");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void menuEquipos(Scanner scanner) {
        while (true) {
            main.limpiarPantalla();
            System.out.println("---------------- Equipos ----------------");
            System.out.println("1. Registrar Equipos");
            System.out.println("2. Editar Equipos");
            System.out.println("3. Eliminar Equipos");
            System.out.println("4. Salir");

            System.out.println("Opción:");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarEquipos(scanner);
                    break;
                case 2:
                    editarEquipos(scanner);
                    break;
                case 3:
                    eliminarEquipos(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo....");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void registrarEquipos(Scanner scanner) {
        Equipo equipo = new Equipo(0, null, null, null, null);

        System.out.println("Ingrese Nombre del equipo:");
        equipo.setNombre(scanner.nextLine());

        System.out.println("Ingrese la ciudad:");
        equipo.setCiudad(scanner.nextLine());

        System.out.println("Ingrese el estadio:");
        equipo.setEstadio(scanner.nextLine());

        System.out.println("Ingrese el nombre del entrenador:");
        equipo.setEntrenador(scanner.nextLine());

        System.out.println("Ingrese los nombres de los jugadores del equipo (separados por comas):");
        String jugadoresStr = scanner.nextLine();
        String[] jugadoresArr = jugadoresStr.split(",");
        for (String jugadorNombre : jugadoresArr) {
            Jugador jugador = new Jugador();
            jugador.setNombre(jugadorNombre.trim());
            equipo.agregarJugador(jugador);
        }

        controlador.addEquipo(equipo);
        System.out.println("Equipo creado exitosamente.");
    }

    private static void editarEquipos(Scanner scanner) {
        controlador.listarEquipos();

        System.out.println("Ingrese el ID del equipo a editar:");
        int idEquipo = scanner.nextInt();
        scanner.nextLine();

        Equipo equipo = controlador.getEquipo(idEquipo);
        if (equipo == null) {
            System.out.println("Equipo no encontrado.");
            return;
        }

        System.out.println("Ingrese el nuevo nombre del equipo (actual: " + equipo.getNombre() + "):");
        equipo.setNombre(scanner.nextLine());

        System.out.println("Ingrese la nueva ciudad (actual: " + equipo.getCiudad() + "):");
        equipo.setCiudad(scanner.nextLine());

        System.out.println("Ingrese el nuevo estadio (actual: " + equipo.getEstadio() + "):");
        equipo.setEstadio(scanner.nextLine());

        System.out.println("Ingrese el nuevo nombre del entrenador (actual: " + equipo.getEntrenador() + "):");
        equipo.setEntrenador(scanner.nextLine());

        System.out.println(
                "Ingrese los nuevos nombres de los jugadores del equipo (actual: " + equipo.getJugadores() + "):");
        String jugadoresStr = scanner.nextLine();
        String[] jugadoresArr = jugadoresStr.split(",");
        for (String jugadorNombre : jugadoresArr) {
            Jugador jugador = new Jugador();
            jugador.setNombre(jugadorNombre.trim());
            equipo.agregarJugador(jugador);
        }
        controlador.updateEquipo(equipo);
        System.out.println("Equipo actualizado exitosamente.");
    }

    private static void eliminarEquipos(Scanner scanner) {
        // LISTAR LOS EQUIPOS EXISTENTES
        controlador.listarEquipos();

        System.out.println("Ingrese el ID del equipo a eliminar:");
        int idEquipo = scanner.nextInt();
        scanner.nextLine();

        Equipo equipo = controlador.getEquipo(idEquipo);
        if (equipo == null) {
            System.out.println("Equipo no encontrado.");
            return;
        }

        controlador.deleteEquipo(idEquipo);
        System.out.println("Equipo eliminado exitosamente.");
    }
}
