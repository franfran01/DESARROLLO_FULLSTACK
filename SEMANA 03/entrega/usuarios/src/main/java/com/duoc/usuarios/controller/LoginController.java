package com.duoc.usuarios.controller;

import com.duoc.usuarios.datos.MemoriaDatos;
import com.duoc.usuarios.model.MensajeError;
import com.duoc.usuarios.model.Sesion;
import com.duoc.usuarios.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    private static final String CORREO_REGEX = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

    private final MemoriaDatos memoriaDatos;

    public LoginController(MemoriaDatos memoriaDatos) {
        this.memoriaDatos = memoriaDatos;
    }

    @GetMapping("/login")
    public ResponseEntity<?> iniciarSesion(
            @RequestParam(required = false) String correo,
            @RequestParam(required = false) String clave) {

        if (correo == null || correo.isBlank() || clave == null || clave.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new MensajeError("Debe indicar correo y clave"));
        }

        if (!correo.matches(CORREO_REGEX)) {
            return ResponseEntity.badRequest()
                    .body(new MensajeError("El formato del correo no es valido"));
        }

        Optional<Usuario> usuarioActivo = memoriaDatos.buscarPorCredenciales(correo, clave)
                .filter(Usuario::isActivo);

        if (usuarioActivo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MensajeError("Correo o clave incorrectos, o usuario inactivo"));
        }

        Usuario usuario = usuarioActivo.get();
        return ResponseEntity.ok(new Sesion(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getRolId(),
                "Inicio de sesion exitoso"
        ));
    }
}
