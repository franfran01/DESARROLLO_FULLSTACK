package com.duoc.atenciones.controller;

import com.duoc.atenciones.datos.MemoriaDatos;
import com.duoc.atenciones.model.HistorialMedico;
import com.duoc.atenciones.model.MensajeError;
import com.duoc.atenciones.model.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PacienteController {

    private final MemoriaDatos memoriaDatos;

    public PacienteController(MemoriaDatos memoriaDatos) {
        this.memoriaDatos = memoriaDatos;
    }

    @GetMapping("/pacientes")
    public List<Paciente> obtenerPacientes() {
        return memoriaDatos.getPacientes();
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable int id) {
        return memoriaDatos.buscarPacientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pacientes/{id}/historial")
    public ResponseEntity<HistorialMedico> obtenerHistorial(@PathVariable int id) {
        return memoriaDatos.buscarPacientePorId(id)
                .map(paciente -> ResponseEntity.ok(new HistorialMedico(
                        paciente,
                        memoriaDatos.buscarConsultasPorPaciente(id),
                        memoriaDatos.buscarAtencionesPorPaciente(id)
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pacientes/rut/{rut}")
    public ResponseEntity<?> obtenerPacientePorRut(@PathVariable String rut) {
        if (!MemoriaDatos.rutTieneFormatoValido(rut)) {
            return ResponseEntity.badRequest()
                    .body(new MensajeError("El formato del RUT no es valido"));
        }
        return memoriaDatos.buscarPacientePorRut(rut)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
