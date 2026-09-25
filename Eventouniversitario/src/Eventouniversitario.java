import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Eventouniversitario implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private String titulo;
    private double costobase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    public Eventouniversitario(String id, String titulo, double costobase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costobase = costobase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public Eventouniversitario(Eventouniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costobase = otro.costobase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
        cantidadEventos++;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public double getCostobase() { return costobase; }
    public void setCostobase(double costobase) { this.costobase = costobase; }
    public boolean isGratuito() { return gratuito; }
    public void setGratuito(boolean gratuito) { this.gratuito = gratuito; }
    public static int getCantidadEventos() { return cantidadEventos; }

    public void asignarSala(Sala sala) { this.sala = sala; }
    public Sala getSala() { return sala; }

    public void agregarActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public List<Actividad> getActividades() { return this.actividades; }

    public double calcularCostoEstimado() {
        return this.gratuito ? 0.0 : this.costobase;
    }

    // Persistencia (Ejercicio 1)
    public boolean persistirEvento() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.id + ".dat"))) {
            oos.writeObject(this);
            return true;
        }
    }

    public static Eventouniversitario recuperarEvento(String id) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(id + ".dat"))) {
            return (Eventouniversitario) ois.readObject();
        }
    }

    // Genéricos y Wildcards (Ejercicio 3)
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Actividad act : actividades) {
            if (tipo.isInstance(act)) {
                resultado.add(tipo.cast(act));
            }
        }
        return resultado;
    }

    public double calcularCostoMateriales(List<? extends Actividad> actividadesList) {
        double total = 0.0;
        for (Actividad act : actividadesList) {
            total += act.calcularCostoMateriales();
        }
        return total;
    }

    public void mostrarDatos() {
        System.out.println("ID: " + this.id);
        System.out.println("Título: " + this.titulo);
        System.out.println("Costo Base: $" + this.costobase);
        System.out.println("Gratuito: " + this.gratuito);
        System.out.println("Sala: " + (sala != null ? sala.getNombre() : "Sin asignar"));
        System.out.println("--- Actividades Registradas ---");
        for (Actividad act : actividades) {
            act.mostrarIdentificacion();
            act.mostrarInscripciones();
        }
    }
}