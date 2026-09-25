public class Taller extends Actividad implements Certificable {
    private static final long serialVersionUID = 1L;
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000.0 : 2000.0;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "  [CERTIFICADO] Emitido por " + ENTIDAD_EMISORA +
                " | Estudiante: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")" +
                " | Por asistencia al Taller: " + getTitulo() + ".";
    }

    public boolean isRequiereNotebook() { return requiereNotebook; }
}