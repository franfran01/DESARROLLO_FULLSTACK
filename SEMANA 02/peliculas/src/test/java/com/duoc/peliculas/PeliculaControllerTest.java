package com.duoc.peliculas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarPeliculasDevuelveJsonConAlMenosCincoRegistros() throws Exception {
        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(5)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].titulo").exists())
                .andExpect(jsonPath("$[0].año").exists())
                .andExpect(jsonPath("$[0].director").exists())
                .andExpect(jsonPath("$[0].genero").exists())
                .andExpect(jsonPath("$[0].sinopsis").exists());
    }

    @Test
    void obtenerPeliculaPorIdDevuelveElDetalle() throws Exception {
        mockMvc.perform(get("/peliculas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Una mujer fantástica"))
                .andExpect(jsonPath("$.año").value(2017));
    }

    @Test
    void obtenerPeliculaInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/peliculas/99"))
                .andExpect(status().isNotFound());
    }
}
