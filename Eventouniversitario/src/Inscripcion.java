import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TicketDeAcceso ticket;

    public Inscripcion(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
        this.estado = "PENDIENTE";
        this.ticket = null;
    }

    public void confirmarInscripcion() {
        this.estado = "CONFIRMADA";
        if (this.ticket == null) {
            this.ticket = new TicketDeAcceso();
        }
    }

    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public Estudiante getEstudiante() { return estudiante; }
    public TicketDeAcceso getTicket() { return ticket; }

    public class TicketDeAcceso implements Serializable {
        private static final long serialVersionUID = 1L;
        private String idTicket;
        private LocalDate fechaEmision;

        public TicketDeAcceso() {
            this.idTicket = "TCK-" + (int)(Math.random() * 90000 + 10000);
            this.fechaEmision = LocalDate.now();
        }

        public void enviarTicket() {
            System.out.println("    [✉ HILO CONCURRENTE] Enviando Ticket [" + idTicket +
                    "] al estudiante: " + estudiante.getNombre() +
                    " (Legajo: " + estudiante.getLegajo() + ")");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public String getIdTicket() { return idTicket; }
        public LocalDate getFechaEmision() { return fechaEmision; }
    }
}