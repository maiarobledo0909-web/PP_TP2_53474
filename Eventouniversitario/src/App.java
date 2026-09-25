import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  SISTEMA DE GESTIÓN DE EVENTOS UNIVERSITARIOS");
        System.out.println("==================================================");

        Estudiante e1 = new Estudiante("43333", "Ana Gómez");
        Estudiante e2 = new Estudiante("44444", "Carlos Pérez");
        Estudiante e3 = new Estudiante("55555", "Lucía Fernández");
        Estudiante e4 = new Estudiante("66666", "Marcos Díaz");

        Eventouniversitario evento = new Eventouniversitario("EVT-01", "Congreso de Sistemas", 4000.0, false);
        Sala sala1 = new Sala(1, "Anfiteatro A");
        evento.asignarSala(sala1);

        Taller taller1 = new Taller(101, "Taller de Programación Concurrente", 2, true); // Cupo: 2
        Charla charla1 = new Charla(102, "Arquitectura de Software", 50, "Dr. Martínez");
        Curso curso1 = new Curso(103, "Curso de Patrones de Diseño", 25, 2);

        evento.agregarActividad(taller1);
        evento.agregarActividad(charla1);
        evento.agregarActividad(curso1);

        System.out.println("\n--------------------------------------------------");
        System.out.println(" EJERCICIO 1: EXCEPCIÓN Y PERSISTENCIA (SERIALIZACIÓN)");
        System.out.println("--------------------------------------------------");

        Inscripcion ins1 = null;
        Inscripcion ins2 = null;
        Inscripcion ins3 = null;

        try {
            System.out.println("Inscribiendo estudiantes en Taller (cupo: 2)...");
            ins1 = taller1.inscribir(e1);
            System.out.println("✓ Inscrito: " + e1.getNombre());

            ins2 = taller1.inscribir(e2);
            System.out.println("✓ Inscrito: " + e2.getNombre());

            System.out.println("Intentando inscribir un 3er alumno para provocar CupoExcedidoException...");
            taller1.inscribir(e3);

        } catch (CupoExcedidoException ex) {
            System.err.println("✗ [EXCEPCIÓN ATRAPADA] " + ex.getMessage());
        }

        try {
            charla1.inscribir(e3);
            ins3 = curso1.inscribir(e4);
        } catch (CupoExcedidoException ex) {
            System.err.println("✗ Error: " + ex.getMessage());
        }

        // Flujo try-catch-finally para persistencia
        try {
            System.out.println("\nGuardando evento en archivo binario...");
            evento.persistirEvento();
            System.out.println("✓ Evento persistido con éxito.");

            System.out.println("Leyendo evento desde archivo binario...");
            Eventouniversitario recuperado = Eventouniversitario.recuperarEvento(evento.getId());
            System.out.println("✓ Evento recuperado correctamente: " + recuperado.getTitulo());

        } catch (FileNotFoundException ex) {
            System.err.println("✗ Error: Archivo no encontrado. " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("✗ Error: Clase no encontrada al deserializar. " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("✗ Error de E/S: " + ex.getMessage());
        } finally {
            System.out.println("[FINALLY] Verificación de persistencia completada.");
        }

        System.out.println("\n--------------------------------------------------");
        System.out.println(" EJERCICIO 2: CERTIFICADOS (INTERFACES)");
        System.out.println("--------------------------------------------------");
        for (Actividad act : evento.getActividades()) {
            if (act instanceof Certificable) {
                Certificable cert = (Certificable) act;
                for (Inscripcion inc : act.getInscripciones()) {
                    System.out.println(cert.generarCertificado(inc.getEstudiante()));
                }
            } else {
                System.out.println("  [NO CERTIFICABLE] La actividad '" + act.getTitulo() + "' no emite certificados.");
            }
        }

        System.out.println("\n--------------------------------------------------");
        System.out.println(" EJERCICIO 3: GENÉRICOS Y WILDCARDS");
        System.out.println("--------------------------------------------------");
        List<Charla> charlas = evento.filtrarActividadesPorTipo(Charla.class);
        List<Taller> talleres = evento.filtrarActividadesPorTipo(Taller.class);
        List<Curso> cursos = evento.filtrarActividadesPorTipo(Curso.class);

        System.out.println("Charlas encontradas: " + charlas.size() + " | Costo materiales: $" + evento.calcularCostoMateriales(charlas));
        System.out.println("Talleres encontrados: " + talleres.size() + " | Costo materiales: $" + evento.calcularCostoMateriales(talleres));
        System.out.println("Cursos encontrados:   " + cursos.size() + " | Costo materiales: $" + evento.calcularCostoMateriales(cursos));
        System.out.println("Costo total de materiales del evento: $" + evento.calcularCostoMateriales(evento.getActividades()));

        System.out.println("\n--------------------------------------------------");
        System.out.println(" EJERCICIO 4: CLASE ANIDADA E HILOS CONCURRENTES");
        System.out.println("--------------------------------------------------");

        // Confirmamos algunas inscripciones para que tengan Ticket
        if (ins1 != null) ins1.confirmarInscripcion();
        if (ins2 != null) ins2.confirmarInscripcion();
        if (ins3 != null) ins3.confirmarInscripcion();

        EnvioTicketsThread hiloEnvio = new EnvioTicketsThread(evento);
        hiloEnvio.start();

        System.out.println("[HILO PRINCIPAL] Mostrando datos del evento en paralelo:");
        evento.mostrarDatos();

        try {
            hiloEnvio.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n==================================================");
        System.out.println("Programa finalizado. Eventos creados: " + Eventouniversitario.getCantidadEventos());
        System.out.println("==================================================");
    }
}