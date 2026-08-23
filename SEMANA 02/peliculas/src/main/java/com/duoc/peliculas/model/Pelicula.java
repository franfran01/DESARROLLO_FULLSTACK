package com.duoc.peliculas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    private int id;
    private String titulo;

    @JsonProperty("año")
    private int anio;

    private String director;
    private String genero;
    private String sinopsis;
}
