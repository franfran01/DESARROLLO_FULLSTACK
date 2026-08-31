package com.duoc.atenciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atencion {

    private int id;
    private int pacienteId;
    private int consultaId;
    private String fecha;
    private String diagnostico;
    private String tratamiento;
    private String medico;
}
