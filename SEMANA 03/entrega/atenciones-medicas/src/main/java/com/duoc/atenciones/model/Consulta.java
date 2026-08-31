package com.duoc.atenciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    private int id;
    private int pacienteId;
    private String fecha;
    private String motivo;
    private String medico;
    private String estado;
}
