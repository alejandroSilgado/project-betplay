package model;
import java.util.List;
import java.util.ArrayList;

public class Equipo {
    private int id;
    private String nombre;
    private String ciudad;
    private String estadio;
    private String entrenador;
    private List<Jugador> jugadores;
    private List<Partido> partidos;

    public Equipo(int id, String nombre, String ciudad, String estadio, String entrenador) {
    this.id = id;
    this.nombre = nombre;
    this.ciudad = ciudad;
    this.estadio = estadio;
    this.entrenador = entrenador;
    this.jugadores = new ArrayList<>();
    this.partidos = new ArrayList<>();
    }
    
    public void agregarJugador(Jugador jugador) {
    jugadores.add(jugador);
    }
    public List<Jugador> getJugadores() {
    return jugadores;
    }
    public void agregarPartido(Partido partido) {
    partidos.add(partido);
    }
    public List<Partido> getPartidos() {
    return partidos;
    }
    public int getId() {
    return id;
    }
    public void setId(int id) {
    this.id = id;
    }
    public String getNombre() {
    return nombre;
    }
    public void setNombre(String nombre) {
    this.nombre = nombre;
    }
    public String getCiudad() {
    return ciudad;
    }
    public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
    }
    public String getEstadio() {
    return estadio;
    }
    public void setEstadio(String estadio) {
    this.estadio = estadio;
    }
    public String getEntrenador() {
    return entrenador;
    }
    public void setEntrenador(String entrenador) {
    this.entrenador = entrenador;
    }
    }
    
