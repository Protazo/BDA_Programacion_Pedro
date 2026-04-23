package Controller;

import Dao.UsuarioDao;
import Dao.Usuario_administradorDao;
import Dao.Usuario_normalDao;
import Model.Usuario;
import Model.Usuario_administrador;
import Model.Usuario_normal;
import Model.tipoUsu;

import java.sql.SQLException;

public class UsuarioController {
    UsuarioDao usuarioDao = new UsuarioDao();
    Usuario_normalDao usuario_normalDao = new Usuario_normalDao();
    Usuario_administradorDao usuario_administradorDao = new Usuario_administradorDao();

    public int altaUsuario(String nombre, String email, String contrasenya, String fecha, tipoUsu tipo) throws SQLException {
        Usuario usuario = new Usuario(email, contrasenya, fecha, nombre, tipo);
        return usuarioDao.altaUsuario(usuario);
    }

    public void altaUsuarioNormal(int id, String nombre, String email, String contrasenya, String fecha, tipoUsu tipo, String direccion, int movil, String foto) throws SQLException {
        Usuario_normal usuario_normal = new Usuario_normal(email, contrasenya, nombre, fecha, tipo, direccion, movil, foto);
        usuario_normalDao.altaUsuario(usuario_normal);
    }

    public void altaUsuarioAdmin(int id, String nombre, String email, String contrasenya, String fecha, tipoUsu tipo, int telefono) throws SQLException {
        Usuario_administrador usuario_administrador = new Usuario_administrador(email, contrasenya, nombre, fecha, tipo, telefono);
        usuario_administradorDao.altaUsuario(usuario_administrador);
    }




    public boolean bajaUsuario(int id) throws SQLException {
        return usuarioDao.bajaUsuario(id);
    }

    public boolean bajaUsuarioNormal(int id) throws SQLException {
        return usuario_normalDao.bajaUsuario(id);
    }

    public boolean bajaUsuarioAdmin(int id) throws SQLException {
        return usuario_administradorDao.bajaUsuario(id);
    }




    public void modificarUsuario(String nombre, String email, String contrasenya, String fecha, tipoUsu tipo) throws SQLException {
        Usuario usuario = new Usuario(email, contrasenya, fecha, nombre, tipo);
        usuarioDao.altaUsuario(usuario);
    }

    public void modificarUsuarioNormal(String nombre, String email, String contrasenya, String fecha, tipoUsu tipo, String direccion, int movil, String foto) throws SQLException {
        Usuario_normal usuario_normal = new Usuario_normal(email, contrasenya, nombre, fecha, tipo, direccion, movil, foto);
        usuario_normalDao.altaUsuario(usuario_normal);
    }

    public void modificarUsuarioAdmin(String nombre, String email, String contrasenya, String fecha, tipoUsu tipo, int telefono) throws SQLException {
        Usuario_administrador usuario_administrador = new Usuario_administrador(email, contrasenya, nombre, fecha, tipo, telefono);
        usuario_administradorDao.altaUsuario(usuario_administrador);
    }
}
