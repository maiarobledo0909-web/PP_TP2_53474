public class Curso extends Actividad implements Certificable {
    private static final long serialVersionUID = 1L;
    private int nivel;

    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }

    @Override
    public double calcularCostoMateriales() {
        return nivel * 1500.0;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "  [CERTIFICADO] Emitido por " + ENTIDAD_EMISORA +
                " | Estudiante: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")" +
                " | Por aprobación del Curso: " + getTitulo() + " (Nivel " + nivel + ").";
    }

    public int getNivel() { return nivel; }
}