package com.duoc.usuarios.datos;

import com.duoc.usuarios.model.DireccionDespacho;
import com.duoc.usuarios.model.Rol;
import com.duoc.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemoriaDatos {

    private final List<Rol> roles = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<DireccionDespacho> direcciones = new ArrayList<>();

    public MemoriaDatos() {
        roles.add(new Rol(1, "ADMIN", "Administrador del sistema de la tienda"));
        roles.add(new Rol(2, "CLIENTE", "Cliente con despacho a domicilio"));
        roles.add(new Rol(3, "DESPACHADOR", "Encargado de preparar y enviar pedidos"));

        usuarios.add(new Usuario(1, "Ana", "Soto", "ana.soto@tienda.cl", "clave123", 1, true));
        usuarios.add(new Usuario(2, "Bruno", "Diaz", "bruno.diaz@tienda.cl", "clave123", 2, true));
        usuarios.add(new Usuario(3, "Carla", "Munoz", "carla.munoz@tienda.cl", "clave123", 2, true));
        usuarios.add(new Usuario(4, "Diego", "Perez", "diego.perez@tienda.cl", "clave123", 3, true));
        usuarios.add(new Usuario(5, "Elena", "Rojas", "elena.rojas@tienda.cl", "clave123", 2, true));
        usuarios.add(new Usuario(6, "Felipe", "Nunez", "felipe.nunez@tienda.cl", "clave123", 2, false));
        usuarios.add(new Usuario(7, "Gabriela", "Silva", "gabriela.silva@tienda.cl", "clave123", 3, true));
        usuarios.add(new Usuario(8, "Hector", "Vargas", "hector.vargas@tienda.cl", "clave123", 2, true));

        direcciones.add(new DireccionDespacho(1, 1, "Av. Providencia", "1234", "Providencia", "Metropolitana", "7500000"));
        direcciones.add(new DireccionDespacho(2, 2, "Calle Larga", "45", "Maipu", "Metropolitana", "9250000"));
        direcciones.add(new DireccionDespacho(3, 3, "Los Aromos", "890", "Vina del Mar", "Valparaiso", "2520000"));
        direcciones.add(new DireccionDespacho(4, 4, "Av. Alemania", "2211", "Temuco", "Araucania", "4780000"));
        direcciones.add(new DireccionDespacho(5, 5, "Arturo Prat", "67", "Concepcion", "Biobio", "4030000"));
        direcciones.add(new DireccionDespacho(6, 6, "Balmaceda", "1500", "La Serena", "Coquimbo", "1700000"));
        direcciones.add(new DireccionDespacho(7, 7, "Av. Libertad", "330", "Rancagua", "OHiggins", "2820000"));
        direcciones.add(new DireccionDespacho(8, 8, "Eleuterio Ramirez", "98", "Osorno", "Los Lagos", "5290000"));
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<DireccionDespacho> getDirecciones() {
        return direcciones;
    }

    public Optional<Rol> buscarRolPorId(int id) {
        return roles.stream().filter(rol -> rol.getId() == id).findFirst();
    }

    public Optional<Usuario> buscarUsuarioPorId(int id) {
        return usuarios.stream().filter(usuario -> usuario.getId() == id).findFirst();
    }

    public Optional<DireccionDespacho> buscarDireccionPorId(int id) {
        return direcciones.stream().filter(direccion -> direccion.getId() == id).findFirst();
    }

    public List<DireccionDespacho> buscarDireccionesPorUsuario(int usuarioId) {
        return direcciones.stream()
                .filter(direccion -> direccion.getUsuarioId() == usuarioId)
                .toList();
    }

    public Optional<Usuario> buscarPorCredenciales(String correo, String clave) {
        return usuarios.stream()
                .filter(usuario -> usuario.getCorreo().equalsIgnoreCase(correo)
                        && usuario.getClave().equals(clave))
                .findFirst();
    }
}
