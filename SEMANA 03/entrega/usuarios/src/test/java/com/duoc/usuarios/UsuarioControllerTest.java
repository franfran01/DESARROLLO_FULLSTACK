package com.duoc.usuarios;

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
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarUsuariosDevuelveJsonConAlMenosOchoRegistros() throws Exception {
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(8)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].nombre").exists())
                .andExpect(jsonPath("$[0].apellido").exists())
                .andExpect(jsonPath("$[0].correo").exists())
                .andExpect(jsonPath("$[0].rolId").exists())
                .andExpect(jsonPath("$[0].activo").exists())
                .andExpect(jsonPath("$[0].clave").doesNotExist());
    }

    @Test
    void obtenerUsuarioPorIdDevuelveElDetalle() throws Exception {
        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.correo").value("ana.soto@tienda.cl"));
    }

    @Test
    void obtenerUsuarioInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/usuarios/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerDireccionesDeUsuarioDevuelveLista() throws Exception {
        mockMvc.perform(get("/usuarios/1/direcciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$[0].usuarioId").value(1))
                .andExpect(jsonPath("$[0].comuna").exists());
    }

    @Test
    void obtenerDireccionesDeUsuarioInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/usuarios/99/direcciones"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listarRolesDevuelveAlMenosTresRegistros() throws Exception {
        mockMvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(3)))
                .andExpect(jsonPath("$[0].nombre").exists());
    }

    @Test
    void obtenerRolInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/roles/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listarDireccionesDevuelveAlMenosOchoRegistros() throws Exception {
        mockMvc.perform(get("/direcciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(greaterThanOrEqualTo(8)))
                .andExpect(jsonPath("$[0].calle").exists())
                .andExpect(jsonPath("$[0].codigoPostal").exists());
    }

    @Test
    void obtenerDireccionInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/direcciones/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void loginExitosoDevuelveSesionSinClave() throws Exception {
        mockMvc.perform(get("/login")
                        .param("correo", "ana.soto@tienda.cl")
                        .param("clave", "clave123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioId").value(1))
                .andExpect(jsonPath("$.correo").value("ana.soto@tienda.cl"))
                .andExpect(jsonPath("$.mensaje").value("Inicio de sesion exitoso"))
                .andExpect(jsonPath("$.clave").doesNotExist());
    }

    @Test
    void loginSinParametrosDevuelve400() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    void loginConCorreoInvalidoDevuelve400() throws Exception {
        mockMvc.perform(get("/login")
                        .param("correo", "correo-invalido")
                        .param("clave", "clave123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void loginConClaveIncorrectaDevuelve401() throws Exception {
        mockMvc.perform(get("/login")
                        .param("correo", "ana.soto@tienda.cl")
                        .param("clave", "otra"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void loginDeUsuarioInactivoDevuelve401() throws Exception {
        mockMvc.perform(get("/login")
                        .param("correo", "felipe.nunez@tienda.cl")
                        .param("clave", "clave123"))
                .andExpect(status().isUnauthorized());
    }
}
