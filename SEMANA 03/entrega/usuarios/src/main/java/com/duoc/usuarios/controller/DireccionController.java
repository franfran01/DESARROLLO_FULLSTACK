package com.duoc.usuarios.controller;

import com.duoc.usuarios.datos.MemoriaDatos;
import com.duoc.usuarios.model.DireccionDespacho;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DireccionController {

    private final MemoriaDatos memoriaDatos;

    public DireccionController(MemoriaDatos memoriaDatos) {
        this.memoriaDatos = memoriaDatos;
    }

    @GetMapping("/direcciones")
    public List<DireccionDespacho> obtenerDirecciones() {
        return memoriaDatos.getDirecciones();
    }

    @GetMapping("/direcciones/{id}")
    public ResponseEntity<DireccionDespacho> obtenerDireccionPorId(@PathVariable int id) {
        return memoriaDatos.buscarDireccionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
