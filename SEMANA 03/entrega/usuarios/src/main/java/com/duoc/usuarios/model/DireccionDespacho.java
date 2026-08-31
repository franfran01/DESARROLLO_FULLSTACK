package com.duoc.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDespacho {

    private int id;
    private int usuarioId;
    private String calle;
    private String numero;
    private String comuna;
    private String region;
    private String codigoPostal;
}
