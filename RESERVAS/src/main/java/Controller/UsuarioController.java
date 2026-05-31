package Controller;

import Dao.UsuarioDao;
import Dao.Usuario_administradorDao;
import Dao.Usuario_normalDao;
import Model.Usuario;
import Model.Usuario_administrador;
import Model.Usuario_normal;
import Model.tipoUsu;

import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    UsuarioDao usuarioDao = new UsuarioDao();
    Usuario_normalDao usuario_normalDao = new Usuario_normalDao();
    Usuario_administradorDao usuario_administradorDao = new Usuario_administradorDao();

    public int altaUsuario(String nombre, String email, String contrasenya, String fecha, tipoUsu tipo) throws SQLException {
        Usuario usuario = new Usuario(email, contrasenya, nombre, fecha, tipo);
        return usuarioDao.altaUsuario(usuario);
    }

    public void altaUsuarioNormal(int id, String nombre, String email, String contrasenya, String fecha, tipoUsu tipo, String direccion, String movil, String foto) throws SQLException {
        Usuario_normal usuario_normal = new Usuario_normal(email, contrasenya, nombre, fecha, tipo, direccion, movil, foto);
        usuario_normalDao.altaUsuario(usuario_normal, id);
    }

    public void altaUsuarioAdmin(int id, String nombre, String email, String contrasenya, String fecha, tipoUsu tipo, String telefono) throws SQLException {
        Usuario_administrador usuario_administrador = new Usuario_administrador(email, contrasenya, nombre, fecha, tipo, telefono);
        usuario_administradorDao.altaUsuario(usuario_administrador, id);
    }

    public boolean bajaUsuarioNormal(int id) throws SQLException {
        usuario_normalDao.bajaUsuario(id);
        return usuarioDao.bajaUsuario(id);
    }

    public boolean bajaUsuarioAdmin(int id) throws SQLException {
        usuario_administradorDao.bajaUsuario(id);
        return usuarioDao.bajaUsuario(id);
    }

    public Usuario modificarUsuarioNormal(Usuario_normal usuario) throws SQLException {
        usuario_administradorDao.bajaUsuario(usuario.getId());
        usuario_normalDao.modificarUsuario(usuario);
        return usuarioDao.modificarUsuario(usuario);

    }

    public Usuario modificarUsuarioAdmin(Usuario_administrador usuario) throws SQLException {
        usuario_normalDao.bajaUsuario(usuario.getId());
        usuario_administradorDao.modificarUsuario(usuario);
        return usuarioDao.modificarUsuario(usuario);
    }

    public void listarUsuarios(String opcion) throws SQLException {
        switch (opcion) {
            case "todos" -> {
                List<Usuario> lista = usuarioDao.listarUsuarios();
                lista.forEach(System.out::println);
            }

            case "administrador" -> {
                List<Usuario_administrador> lista = usuario_administradorDao.listarUsuariosAdministradores();
                lista.forEach(System.out::println);
            }

            case "normal" -> {
                List<Usuario_normal> lista = usuario_normalDao.listarUsuariosNormales();
                lista.forEach(System.out::println);
            }
        }

        usuarioDao.listarUsuarios();
    }

    public Usuario buscarNombre(String nombre) throws SQLException {
        Usuario usuario = usuarioDao.buscarNombre(nombre);
        if (usuario != null) {
            if (usuario.getTipo().equals(tipoUsu.ADMINISTRADOR)) {
                Usuario_administrador usuario_administrador = usuario_administradorDao.buscarNombre(nombre);
                if (usuario_administrador == null) {
                    return usuario;
                }
                return usuario_administrador;
            }
            if (usuario.getTipo().equals(tipoUsu.USUARIO_NORMAL)) {
                Usuario_normal usuario_normal = usuario_normalDao.buscarNombre(nombre);
                if (usuario_normal == null) {
                    return usuario;
                }
                return usuario_normal;
            }
        }
        return null;
    }
    public Usuario buscarCorreo(String correo) throws SQLException {
        Usuario usuario = usuarioDao.buscarCorreo(correo);
        if (usuario != null) {
            if (usuario.getTipo().equals(tipoUsu.ADMINISTRADOR)) {
                Usuario_administrador usuario_administrador = usuario_administradorDao.buscarCorreo(correo);
                if (usuario_administrador == null) {
                    return usuario;
                }
                return usuario_administrador;
            }
            if (usuario.getTipo().equals(tipoUsu.USUARIO_NORMAL)) {
                Usuario_normal usuario_normal = usuario_normalDao.buscarCorreo(correo);
                if (usuario_normal == null) {
                    return usuario;
                }
                return usuario_normal;
            }
        }
        return null;
    }

    public Usuario buscarID(int cod) throws SQLException {
        Usuario usuario = usuarioDao.buscarID(cod);
        if (usuario != null) {
            if (usuario.getTipo().equals(tipoUsu.ADMINISTRADOR)) {
                Usuario_administrador usuario_administrador = usuario_administradorDao.buscarID(cod);
                if (usuario_administrador == null) {
                    return usuario;
                }
                return usuario_administrador;
            }
            if (usuario.getTipo().equals(tipoUsu.USUARIO_NORMAL)) {
                Usuario_normal usuario_normal = usuario_normalDao.buscarID(cod);
                if (usuario_normal == null) {
                    return usuario;
                }
                return usuario_normal;
            }
        }
        return null;
    }
}
