package com.duoc.usuarios.controller;

import com.duoc.usuarios.datos.MemoriaDatos;
import com.duoc.usuarios.model.Rol;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RolController {

    private final MemoriaDatos memoriaDatos;

    public RolController(MemoriaDatos memoriaDatos) {
        this.memoriaDatos = memoriaDatos;
    }

    @GetMapping("/roles")
    public List<Rol> obtenerRoles() {
        return memoriaDatos.getRoles();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable int id) {
        return memoriaDatos.buscarRolPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
