import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO = 5;
    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (inscripciones.size() < cupoMaximo) {
            Inscripcion inscripcion = new Inscripcion(estudiante);
            inscripciones.add(inscripcion);
            return inscripcion;
        } else {
            throw new CupoExcedidoException("Cupo excedido en actividad [" + id + " - " + titulo +
                    "]. Cupo máximo: " + cupoMaximo);
        }
    }

    public void mostrarInscripciones() {
        System.out.println("  Inscriptos en " + titulo + " (" + inscripciones.size() + "/" + cupoMaximo + "):");
        if (inscripciones.isEmpty()) {
            System.out.println("    Sin alumnos inscriptos.");
        } else {
            for (Inscripcion inc : inscripciones) {
                System.out.println("    - " + inc.getEstudiante().getNombre() +
                        " | Legajo: " + inc.getEstudiante().getLegajo() +
                        " | Fecha: " + inc.getFecha() +
                        " | Estado: " + inc.getEstado());
            }
        }
    }

    public final void mostrarIdentificacion() {
        System.out.println("  [ID: " + id + "] Tipo: " + getTipo() + " | Título: " + titulo +
                " | Costo Materiales: $" + calcularCostoMateriales());
    }

    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getCupoMaximo() { return cupoMaximo; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}