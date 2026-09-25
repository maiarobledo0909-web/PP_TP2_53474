public class EnvioTicketsThread extends Thread {
    private Eventouniversitario evento;

    public EnvioTicketsThread(Eventouniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("\n  >> [HILO SECUNDARIO INICIADO] Procesando tickets del evento: " + evento.getTitulo());

        for (Actividad act : evento.getActividades()) {
            for (Inscripcion inc : act.getInscripciones()) {
                if ("CONFIRMADA".equalsIgnoreCase(inc.getEstado()) && inc.getTicket() != null) {
                    inc.getTicket().enviarTicket();
                }
            }
        }

        System.out.println("  >> [HILO SECUNDARIO FINALIZADO] Envío de tickets terminado.\n");
    }
}