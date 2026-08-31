package com.duoc.atenciones;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarPacientesDevuelveJsonConAlMenosOchoRegistros() throws Exception {
        mockMvc.perform(get("/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(8)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].rut").exists())
                .andExpect(jsonPath("$[0].nombre").exists())
                .andExpect(jsonPath("$[0].apellido").exists())
                .andExpect(jsonPath("$[0].fechaNacimiento").exists())
                .andExpect(jsonPath("$[0].telefono").exists())
                .andExpect(jsonPath("$[0].email").exists());
    }

    @Test
    void obtenerPacientePorIdDevuelveElDetalle() throws Exception {
        mockMvc.perform(get("/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Maria"))
                .andExpect(jsonPath("$.rut").value("11.111.111-1"));
    }

    @Test
    void obtenerPacienteInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/pacientes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerHistorialDevuelvePacienteConsultasYAtenciones() throws Exception {
        mockMvc.perform(get("/pacientes/1/historial"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paciente.id").value(1))
                .andExpect(jsonPath("$.consultas.length()").value(greaterThanOrEqualTo(2)))
                .andExpect(jsonPath("$.atenciones.length()").value(greaterThanOrEqualTo(2)));
    }

    @Test
    void obtenerHistorialDePacienteInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/pacientes/99/historial"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPacientePorRutDevuelveElDetalle() throws Exception {
        mockMvc.perform(get("/pacientes/rut/11.111.111-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Maria"));
    }

    @Test
    void buscarPacientePorRutInvalidoDevuelve400() throws Exception {
        mockMvc.perform(get("/pacientes/rut/invalido"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    void buscarPacientePorRutInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/pacientes/rut/99.999.999-9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listarConsultasDevuelveAlMenosOchoRegistros() throws Exception {
        mockMvc.perform(get("/consultas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(8)))
                .andExpect(jsonPath("$[0].motivo").exists())
                .andExpect(jsonPath("$[0].estado").exists());
    }

    @Test
    void filtrarConsultasPorPacienteDevuelveLasDelPaciente() throws Exception {
        mockMvc.perform(get("/consultas").param("pacienteId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(2)))
                .andExpect(jsonPath("$[0].pacienteId").value(1));
    }

    @Test
    void filtrarConsultasConPacienteIdInvalidoDevuelve400() throws Exception {
        mockMvc.perform(get("/consultas").param("pacienteId", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void filtrarConsultasDePacienteInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/consultas").param("pacienteId", "99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerConsultaInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/consultas/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listarAtencionesDevuelveAlMenosOchoRegistros() throws Exception {
        mockMvc.perform(get("/atenciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(8)))
                .andExpect(jsonPath("$[0].diagnostico").exists())
                .andExpect(jsonPath("$[0].tratamiento").exists());
    }

    @Test
    void filtrarAtencionesPorPacienteDevuelveLasDelPaciente() throws Exception {
        mockMvc.perform(get("/atenciones").param("pacienteId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].pacienteId").value(2));
    }

    @Test
    void filtrarAtencionesConPacienteIdInvalidoDevuelve400() throws Exception {
        mockMvc.perform(get("/atenciones").param("pacienteId", "-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obtenerAtencionInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/atenciones/99"))
                .andExpect(status().isNotFound());
    }
}
