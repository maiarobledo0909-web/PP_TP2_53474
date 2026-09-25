# Trabajo Práctico Nº 2:
* Paradigmas de Programación.
* Maia Robledo.
* Legajo:53474.

## 1. Descripción del Proyecto
El sistema modela la administración de eventos universitarios, actividades académicas e inscripciones de estudiantes, resolviendo los cuatro ejercicios planteados en la guía de trabajo práctico.

* Ejercicio 1 Manejo de Excepciones y Persistencia.
* Ejercicio 2 Interfaces y Polimorfismo.
* Ejercicio 3 Genéricos y Wildcards.
* Ejercicio 4 Clases Anidadas y Concurrencia.
---

## 2. Estructura de Clases Implementada
* Actividad (abstracta): Superclase base con identificación, cupos y lista polimórfica de inscripciones.
* Charla: Subclase concreta con disertante, no requiere insumos físicos .
* Taller: Subclase derivada de Actividad que emite certificados institucionales a los participantes .
* Curso: Subclase concreta que implementa Certificable y calcula costos por nivel.
* EventoUniversitario: Entidad gestora que compone las actividades, asigna salas, serializa el evento y ejecuta los filtros genéricos.
* Inscripcion: Vincula a un Estudiante con la actividad y guarda la clase anidada TicketDeAcceso.
* EnvioTicketsThread: Hilo concurrente heredado de Thread.
* App: Clase principal que ejecuta los 4 ejercicios.

---

## 3. Instrucciones de Ejecución
1. Abrir el proyecto en IntelliJ IDEA.
2. Comprobar la compatibilidad con Java 17 o superior.
3. Ejecutar el método main ubicado en la clase App.java.
4. El programa imprimirá secuencialmente las pruebas de excepciones, serialización, emisión de certificados, cálculo de costos genéricos y el envío concurrente de tickets en consola.



## 4. Evidencia de Ejecución
* La captura solicitada en el punto 2.3 de la entrega se encuentra adjunta en este repositorio bajo el nombre:
consola( formato pdf)
