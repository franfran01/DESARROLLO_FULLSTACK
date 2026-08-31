package com.duoc.usuarios.controller;

import com.duoc.usuarios.datos.MemoriaDatos;
import com.duoc.usuarios.model.DireccionDespacho;
import com.duoc.usuarios.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    private final MemoriaDatos memoriaDatos;

    public UsuarioController(MemoriaDatos memoriaDatos) {
        this.memoriaDatos = memoriaDatos;
    }

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        return memoriaDatos.getUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        return memoriaDatos.buscarUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuarios/{id}/direcciones")
    public ResponseEntity<List<DireccionDespacho>> obtenerDireccionesDeUsuario(@PathVariable int id) {
        if (memoriaDatos.buscarUsuarioPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memoriaDatos.buscarDireccionesPorUsuario(id));
    }
}
