package com.duoc.peliculas.controller;

import com.duoc.peliculas.model.Pelicula;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PeliculaController {

    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(
                1,
                "Una mujer fantástica",
                2017,
                "Sebastián Lelio",
                "Drama",
                "Marina, una mujer trans, enfrenta el duelo y la discriminación tras la muerte súbita de su pareja."
        ));
        peliculas.add(new Pelicula(
                2,
                "NO",
                2012,
                "Pablo Larraín",
                "Drama histórico",
                "Un publicista lidera la campaña del No en el plebiscito de 1988 que desafió a la dictadura en Chile."
        ));
        peliculas.add(new Pelicula(
                3,
                "Relatos salvajes",
                2014,
                "Damián Szifron",
                "Comedia negra",
                "Seis historias independientes muestran hasta dónde puede llegar la violencia cuando estalla la rabia cotidiana."
        ));
        peliculas.add(new Pelicula(
                4,
                "El secreto de sus ojos",
                2009,
                "Juan José Campanella",
                "Suspenso",
                "Un oficial judicial reabre un crimen impune de los años 70 mientras revive un amor que nunca se atrevió a declarar."
        ));
        peliculas.add(new Pelicula(
                5,
                "El laberinto del fauno",
                2006,
                "Guillermo del Toro",
                "Fantasía",
                "En la posguerra española, una niña descubre un laberinto mágico y tres pruebas que podrían devolverle su verdadero origen."
        ));
        peliculas.add(new Pelicula(
                6,
                "Roma",
                2018,
                "Alfonso Cuarón",
                "Drama",
                "Cleo, trabajadora doméstica en la Ciudad de México de los años 70, acompaña a una familia de clase media en medio de cambios íntimos y sociales."
        ));
    }

    @GetMapping("/peliculas")
    public List<Pelicula> obtenerPeliculas() {
        return peliculas;
    }

    @GetMapping("/peliculas/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(@PathVariable int id) {
        return peliculas.stream()
                .filter(pelicula -> pelicula.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
