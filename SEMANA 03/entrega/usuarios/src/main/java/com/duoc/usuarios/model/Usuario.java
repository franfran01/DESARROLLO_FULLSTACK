package com.duoc.usuarios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private String correo;

    @JsonIgnore
    private String clave;

    private int rolId;
    private boolean activo;
}
