package model;

public class Tarjeta {
    private Jugador jugador;
    private Partido partido;
    private String tipo;
    private int minuto;
    // Getters, setters, y constructores
    public Jugador getJugador() {
        return jugador;
    }
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public Partido getPartido() {
        return partido;
    }
    public void setPartido(Partido partido) {
         this.partido = partido;
    }
     public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getMinuto() {
        return minuto;
    }
    public void setMinuto(int minuto) {
        this.minuto = minuto;
     }
    
}
