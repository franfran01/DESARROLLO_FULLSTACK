package com.duoc.atenciones.controller;

import com.duoc.atenciones.datos.MemoriaDatos;
import com.duoc.atenciones.model.Consulta;
import com.duoc.atenciones.model.MensajeError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsultaController {

    private final MemoriaDatos memoriaDatos;

    public ConsultaController(MemoriaDatos memoriaDatos) {
        this.memoriaDatos = memoriaDatos;
    }

    @GetMapping("/consultas")
    public ResponseEntity<?> obtenerConsultas(@RequestParam(required = false) Integer pacienteId) {
        if (pacienteId == null) {
            return ResponseEntity.ok(memoriaDatos.getConsultas());
        }
        if (pacienteId < 1) {
            return ResponseEntity.badRequest()
                    .body(new MensajeError("El pacienteId debe ser un numero positivo"));
        }
        if (memoriaDatos.buscarPacientePorId(pacienteId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Consulta> consultas = memoriaDatos.buscarConsultasPorPaciente(pacienteId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/consultas/{id}")
    public ResponseEntity<Consulta> obtenerConsultaPorId(@PathVariable int id) {
        return memoriaDatos.buscarConsultaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
