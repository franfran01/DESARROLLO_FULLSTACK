package com.duoc.atenciones.controller;

import com.duoc.atenciones.datos.MemoriaDatos;
import com.duoc.atenciones.model.Atencion;
import com.duoc.atenciones.model.MensajeError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AtencionController {

    private final MemoriaDatos memoriaDatos;

    public AtencionController(MemoriaDatos memoriaDatos) {
        this.memoriaDatos = memoriaDatos;
    }

    @GetMapping("/atenciones")
    public ResponseEntity<?> obtenerAtenciones(@RequestParam(required = false) Integer pacienteId) {
        if (pacienteId == null) {
            return ResponseEntity.ok(memoriaDatos.getAtenciones());
        }
        if (pacienteId < 1) {
            return ResponseEntity.badRequest()
                    .body(new MensajeError("El pacienteId debe ser un numero positivo"));
        }
        if (memoriaDatos.buscarPacientePorId(pacienteId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Atencion> atenciones = memoriaDatos.buscarAtencionesPorPaciente(pacienteId);
        return ResponseEntity.ok(atenciones);
    }

    @GetMapping("/atenciones/{id}")
    public ResponseEntity<Atencion> obtenerAtencionPorId(@PathVariable int id) {
        return memoriaDatos.buscarAtencionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
