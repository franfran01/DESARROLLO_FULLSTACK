package com.duoc.atenciones.datos;

import com.duoc.atenciones.model.Atencion;
import com.duoc.atenciones.model.Consulta;
import com.duoc.atenciones.model.Paciente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemoriaDatos {

    private final List<Paciente> pacientes = new ArrayList<>();
    private final List<Consulta> consultas = new ArrayList<>();
    private final List<Atencion> atenciones = new ArrayList<>();

    public MemoriaDatos() {
        pacientes.add(new Paciente(1, "11.111.111-1", "Maria", "Gonzalez", "1985-03-12", "+56911111111", "maria.gonzalez@correo.cl"));
        pacientes.add(new Paciente(2, "12.222.222-2", "Pedro", "Soto", "1990-07-21", "+56912222222", "pedro.soto@correo.cl"));
        pacientes.add(new Paciente(3, "13.333.333-3", "Lucia", "Ramirez", "1978-11-04", "+56913333333", "lucia.ramirez@correo.cl"));
        pacientes.add(new Paciente(4, "14.444.444-4", "Andres", "Vega", "2001-01-30", "+56914444444", "andres.vega@correo.cl"));
        pacientes.add(new Paciente(5, "15.555.555-5", "Camila", "Fuentes", "1995-09-18", "+56915555555", "camila.fuentes@correo.cl"));
        pacientes.add(new Paciente(6, "16.666.666-6", "Jorge", "Castillo", "1969-05-08", "+56916666666", "jorge.castillo@correo.cl"));
        pacientes.add(new Paciente(7, "17.777.777-7", "Sofia", "Herrera", "1988-12-25", "+56917777777", "sofia.herrera@correo.cl"));
        pacientes.add(new Paciente(8, "18.888.888-8", "Nicolas", "Paredes", "2010-04-02", "+56918888888", "nicolas.paredes@correo.cl"));

        consultas.add(new Consulta(1, 1, "2026-01-10", "Control de presion arterial", "Dra. Paula Rios", "realizada"));
        consultas.add(new Consulta(2, 1, "2026-03-02", "Dolor de cabeza persistente", "Dra. Paula Rios", "realizada"));
        consultas.add(new Consulta(3, 2, "2026-02-14", "Chequeo anual", "Dr. Tomas Bravo", "realizada"));
        consultas.add(new Consulta(4, 2, "2026-04-20", "Resfriado y fiebre", "Dr. Tomas Bravo", "realizada"));
        consultas.add(new Consulta(5, 3, "2026-01-28", "Control de diabetes", "Dra. Elena Mora", "realizada"));
        consultas.add(new Consulta(6, 4, "2026-05-06", "Lesion en rodilla", "Dr. Ignacio Pino", "realizada"));
        consultas.add(new Consulta(7, 5, "2026-06-11", "Alergia estacional", "Dra. Paula Rios", "realizada"));
        consultas.add(new Consulta(8, 6, "2026-07-03", "Dolor lumbar", "Dr. Ignacio Pino", "realizada"));

        atenciones.add(new Atencion(1, 1, 1, "2026-01-10", "Hipertension leve", "Ajuste de dieta y control en 60 dias", "Dra. Paula Rios"));
        atenciones.add(new Atencion(2, 1, 2, "2026-03-02", "Migrafia tensional", "Analgesico y pausas de descanso", "Dra. Paula Rios"));
        atenciones.add(new Atencion(3, 2, 3, "2026-02-14", "Paciente sano", "Mantener actividad fisica y controles anuales", "Dr. Tomas Bravo"));
        atenciones.add(new Atencion(4, 2, 4, "2026-04-20", "Infeccion respiratoria viral", "Hidratacion y reposo por 5 dias", "Dr. Tomas Bravo"));
        atenciones.add(new Atencion(5, 3, 5, "2026-01-28", "Diabetes tipo 2 controlada", "Continuar metformina y control de glicemia", "Dra. Elena Mora"));
        atenciones.add(new Atencion(6, 4, 6, "2026-05-06", "Esguince de rodilla grado 1", "Inmovilizacion relativa y kinesiologia", "Dr. Ignacio Pino"));
        atenciones.add(new Atencion(7, 5, 7, "2026-06-11", "Rinitis alergica", "Antihistaminico y evitar alergenos", "Dra. Paula Rios"));
        atenciones.add(new Atencion(8, 6, 8, "2026-07-03", "Lumbago mecanico", "Antiinflamatorio y ejercicios de core", "Dr. Ignacio Pino"));
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Atencion> getAtenciones() {
        return atenciones;
    }

    public Optional<Paciente> buscarPacientePorId(int id) {
        return pacientes.stream().filter(paciente -> paciente.getId() == id).findFirst();
    }

    public Optional<Paciente> buscarPacientePorRut(String rut) {
        String normalizado = normalizarRut(rut);
        return pacientes.stream()
                .filter(paciente -> normalizarRut(paciente.getRut()).equalsIgnoreCase(normalizado))
                .findFirst();
    }

    public Optional<Consulta> buscarConsultaPorId(int id) {
        return consultas.stream().filter(consulta -> consulta.getId() == id).findFirst();
    }

    public Optional<Atencion> buscarAtencionPorId(int id) {
        return atenciones.stream().filter(atencion -> atencion.getId() == id).findFirst();
    }

    public List<Consulta> buscarConsultasPorPaciente(int pacienteId) {
        return consultas.stream()
                .filter(consulta -> consulta.getPacienteId() == pacienteId)
                .toList();
    }

    public List<Atencion> buscarAtencionesPorPaciente(int pacienteId) {
        return atenciones.stream()
                .filter(atencion -> atencion.getPacienteId() == pacienteId)
                .toList();
    }

    public static String normalizarRut(String rut) {
        return rut == null ? "" : rut.replace(".", "").trim();
    }

    public static boolean rutTieneFormatoValido(String rut) {
        return normalizarRut(rut).matches("\\d{7,8}-[\\dkK]");
    }
}
