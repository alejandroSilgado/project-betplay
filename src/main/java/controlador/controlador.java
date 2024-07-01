package controlador;
import java.util.HashMap;
import java.util.Map;

import model.Equipo;
import model.Jugador;

public class controlador {
    private Map<Integer, Jugador> jugadores = new HashMap<>();

    public void addJugador(Jugador jugador) {
        jugadores.put(jugador.getId(), jugador);
    }

    public Jugador getJugadorById(int id) {
        return jugadores.get(id);
    }

    public void updateJugador(Jugador jugador) {
        jugadores.put(jugador.getId(), jugador);
    }

    public boolean deleteJugador(int id) {
        return jugadores.remove(id) != null;
    }

    public Equipo getEquipoById(int equipoId) {
        throw new UnsupportedOperationException("Unimplemented method 'getEquipoById'");
    }

    private static Map<Integer, Equipo> equipos = new HashMap<>();
    private static int nextId = 1;

    public static void addEquipo(Equipo equipo) {
        equipo.setId(nextId++);
        equipos.put(equipo.getId(), equipo);
    }

    public static void listarEquipos() {
        for (Equipo equipo : equipos.values()) {
            System.out.println("ID: " + equipo.getId() + ", Nombre: " + equipo.getNombre());
        }
    }

    public static Equipo getEquipo(int id) {
        return equipos.get(id);
    }

    public static void updateEquipo(Equipo equipo) {
        equipos.put(equipo.getId(), equipo);
    }

    public static void deleteEquipo(int id) {
        equipos.remove(id);
    }
}



