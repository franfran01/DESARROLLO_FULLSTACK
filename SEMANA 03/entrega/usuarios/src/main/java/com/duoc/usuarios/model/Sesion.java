package com.duoc.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sesion {

    private int usuarioId;
    private String nombre;
    private String apellido;
    private String correo;
    private int rolId;
    private String mensaje;
}
