package model;

class Partido {
    private int id;
    private String equipoLocal;
    private String equipoVisitante;
    private List<Gol> goles;
    private List<Tarjeta> tarjetas;
    private List<Incidente> incidentes;
    private Resultado resultado;
    private Estadio estadio;
    private int entradasDisponibles;
    private List<Entrada>

    public Partido(int id, String equipoLocal, String equipoVisitante, Estadio estadio) {
    this.id = id;
    this.equipoLocal = equipoLocal;
    this.equipoVisitante = equipoVisitante;
    this.goles = new ArrayList<>();
    this.tarjetas = new ArrayList<>();
    this.incidentes = new ArrayList<>();
    this.estadio = estadio;
    this.entradasDisponibles = estadio.getcapacidad;
    this.entradasVendidas = new ArrayList<>();
    }
    // Métodos para agregar goles, tarjetas, incidentes y establecer resultado
    public void agregarGol(Gol gol) {
    goles.add(gol);
    }
    public void agregarTarjeta(Tarjeta tarjeta) {
    tarjetas.add(tarjeta);
    }
    public void agregarIncidente(Incidente incidente) {
    incidentes.add(incidente);
    }
    public void establecerResultado(Resultado resultado) {
    this.resultado = resultado;
    }
    // Getters
    public int getId() {
    return id;
    }
    public String getEquipoLocal() {
    return equipoLocal;
    }
    public String getEquipoVisitante() {
    return equipoVisitante;
    }
    public List<Gol> getGoles() {
    return goles;
    }
    public List<Tarjeta> getTarjetas() {
    return tarjetas;
    }
    public List<Incidente> getIncidentes() {
    return incidentes;
    }
    public Resultado getResultado() {
    return resultado;
    }
    public Estadio getEstadio() {
    return estadio;
    }
    public int getEntradasDisponibles() {
    return entradasDisponibles;
    }
    public List<Entrada> getEntradasVendidas() {
    return entradasVendidas;
    }
}