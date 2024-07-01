package model;

public class Jugador extends Persona {
    private String posicion;
    private String nacionalidad;
    private int numeroCamiseta;
    private Equipo equipo;

    // Getters y setters
    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(int numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    // Constructor
    public Jugador(int id, String nombre, int edad, String posicion, String nacionalidad, int numeroCamiseta, Equipo equipo) {
        super(id, nombre, edad);
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        this.numeroCamiseta = numeroCamiseta;
        this.equipo = equipo;
    }

    public Jugador() {}
}
