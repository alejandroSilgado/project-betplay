package view;

import java.util.Scanner;

import controlador.controlador;
import model.Jugador;
import model.Equipo;
import controlador.main;

public class viewJugador {
    private static controlador controlador = new controlador();

    public static void menuJugador() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            main.limpiarPantalla();
            System.out.println("---------------- Jugadores ----------------------");
            System.out.println("1. Registrar Jugadores");
            System.out.println("2. Editar Jugadores");
            System.out.println("3. Eliminar Jugadores");
            System.out.println("4. Salir");

            System.out.println("Opcion:");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarJugadores(scanner);
                    break;
                case 2:
                    editarJugadores(scanner);
                    break;
                case 3:
                    eliminarJugadores(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void registrarJugadores(Scanner scanner) {
        Jugador jugador = new Jugador();

        System.out.println("Ingrese nombre del jugador:");
        jugador.setNombre(scanner.nextLine());

        System.out.println("Ingrese la edad del jugador:");
        jugador.setEdad(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Ingrese la posición del jugador:");
        jugador.setPosicion(scanner.nextLine());

        System.out.println("Ingrese la nacionalidad del jugador:");
        jugador.setNacionalidad(scanner.nextLine());

        System.out.println("Ingrese el número de camiseta del jugador:");
        jugador.setNumeroCamiseta(scanner.nextInt());
        scanner.nextLine();

        // Aquí deberías seleccionar el equipo correspondiente y setearlo en el jugador
        // Por simplicidad, asumamos que hay una función que obtiene el equipo por ID
        System.out.println("Ingrese el ID del equipo al que pertenece el jugador:");
        int equipoId = scanner.nextInt();
        scanner.nextLine();
        Equipo equipo = controlador.getEquipoById(equipoId);
        jugador.setEquipo(equipo);

        controlador.addJugador(jugador);
        System.out.println("Jugador registrado exitosamente.");
    }

    public static void editarJugadores(Scanner scanner) {
        System.out.println("Ingrese ID del jugador a editar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Jugador jugador = controlador.getJugadorById(id);

        if (jugador != null) {
            System.out.println("Ingrese nombre del jugador:");
            jugador.setNombre(scanner.nextLine());

            System.out.println("Ingrese la edad del jugador:");
            jugador.setEdad(scanner.nextInt());
            scanner.nextLine();

            System.out.println("Ingrese la posición del jugador:");
            jugador.setPosicion(scanner.nextLine());

            System.out.println("Ingrese la nacionalidad del jugador:");
            jugador.setNacionalidad(scanner.nextLine());

            System.out.println("Ingrese el número de camiseta del jugador:");
            jugador.setNumeroCamiseta(scanner.nextInt());
            scanner.nextLine();

            // Aquí deberías seleccionar el equipo correspondiente y setearlo en el jugador
            System.out.println("Ingrese el ID del equipo al que pertenece el jugador:");
            int equipoId = scanner.nextInt();
            scanner.nextLine();
            Equipo equipo = controlador.getEquipoById(equipoId);
            jugador.setEquipo(equipo);

            controlador.updateJugador(jugador);
            System.out.println("Jugador editado exitosamente.");
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    public static void eliminarJugadores(Scanner scanner) {
        System.out.println("Ingrese ID del jugador a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (controlador.deleteJugador(id)) {
            System.out.println("Jugador eliminado exitosamente.");
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }
}
