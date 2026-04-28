package Dao;

import Model.Recurso;
import Model.Usuario;
import Model.Usuario_administrador;
import Model.tipoUsu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public int altaUsuario(Usuario usuario) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "INSERT INTO USUARIO (correo_electronico, contrasena, nombre, fecha_nacimiento, tipo_usuario) VALUES ( ?, ?, ?, ?, ? )";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, usuario.getCorreoElectronico());
        ps.setString(2, usuario.getContrasenya());
        ps.setString(3, usuario.getNombre());
        ps.setString(4, usuario.getFecnac());

        if (usuario.getTipo().equals(tipoUsu.USUARIO_NORMAL)) {
            ps.setString(5, "Normal");
        } else if (usuario.getTipo().equals(tipoUsu.ADMINISTRADOR)) {
            ps.setString(5, "Administrador");
        }

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        ps.close();
        cnx.close();

        if (rs.next()) {
            int id = rs.getInt(1);
            return id;
        }

        return 0;
        
    }

    public boolean bajaUsuario(int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "delete from USUARIO where id_usuario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, id);

        ps.executeUpdate();

        ps.close();
        cnx.close();
        return true;
    }

    public Usuario modificarUsuario(Usuario usuario) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "update RECURSO set nombre = ?, descripcion = ?, ubicacion = ?, capacidad = ? where id_recurso = ? ";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setString(1, usuario.getCorreoElectronico());
        ps.setString(2, usuario.getContrasenya());
        ps.setString(3, usuario.getNombre());
        ps.setString(4, usuario.getFecnac());
        ps.executeUpdate();



        ps.close();
        cnx.close();
        return buscarUsuarioPorNombre(usuario.getNombre());
    }

    public List<Usuario> listarRecursos() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from USUARIO";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        List<Usuario> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String tipo_usuario = rs.getString("tipo_usuario");
            Usuario usuario = new Usuario(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.USUARIO_NORMAL);
            lista.add(usuario);
        }
        ps.close();
        cnx.close();
        return lista;
    }

    public Usuario buscarUsuarioPorNombre(String nombre) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from USUARIO where nombre = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String tipo_usuario = rs.getString("tipo_usuario");

            if (tipo_usuario.equals(tipoUsu.USUARIO_NORMAL)) {
                Usuario usuario = new Usuario(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.USUARIO_NORMAL);
                return usuario;
            }
            if (tipo_usuario.equals(tipoUsu.ADMINISTRADOR)) {
                Usuario usuario = new Usuario(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.ADMINISTRADOR);
                return usuario;
            }
        }
        ps.close();
        cnx.close();
        return null;
    }

    public Usuario buscarUsuarioPorCorreo(String correo) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from USUARIO where correo_electronico = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, correo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String tipo_usuario = rs.getString("tipo_usuario");

            if (tipo_usuario.equals(tipoUsu.USUARIO_NORMAL)) {
                Usuario usuario = new Usuario(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.USUARIO_NORMAL);
                return usuario;
            }
            if (tipo_usuario.equals(tipoUsu.ADMINISTRADOR)) {
                Usuario usuario = new Usuario(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.ADMINISTRADOR);
                return usuario;
            }
        }
        ps.close();
        cnx.close();
        return null;
    }
}
