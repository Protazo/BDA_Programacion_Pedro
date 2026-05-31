package Dao;

import Model.Usuario;
import Model.Usuario_administrador;
import Model.tipoUsu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario_administradorDao {

    public int altaUsuario(Usuario_administrador usuario, int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "INSERT INTO ADMINISTRADOR (id_usuario, telefono_guardia) VALUES (?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, usuario.getTelefonoGuardia());


        ps.executeUpdate();

        ps.close();
        cnx.close();

        return id;
    }

    public boolean bajaUsuario(int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "delete from ADMINISTRADOR where id_usuario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, id);

        ps.executeUpdate();

        ps.close();
        cnx.close();
        return true;
    }

    public boolean modificarUsuario(Usuario_administrador usuario) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "update ADMINISTRADOR set telefono_guardia = ? where id_usuario = ? ";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setString(1, usuario.getTelefonoGuardia());
        ps.setInt(2, usuario.getId());

        ps.executeUpdate();

        ps.close();
        cnx.close();
        return true;
    }

    public List<Usuario_administrador> listarUsuariosAdministradores() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from ADMINISTRADOR ad join USUARIO u on ad.id_usuario = u.ID_USUARIO";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        List<Usuario_administrador> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String telefono_guardia = rs.getString("telefono_guardia");

            Usuario_administrador usuario = new Usuario_administrador(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.ADMINISTRADOR, telefono_guardia);

            lista.add(usuario);
        }
        ps.close();
        cnx.close();
        return lista;
    }

    public Usuario_administrador buscarNombre(String nombre) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from ADMINISTRADOR a join USUARIO u on u.id_usuario = a.id_usuario where nombre = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String telefono_guardia = rs.getString("telefono_guardia");
            Usuario_administrador usuario = new Usuario_administrador(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.ADMINISTRADOR, telefono_guardia);
            return usuario;
        }
        return null;
    }

    public Usuario_administrador buscarCorreo(String correo) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from ADMINISTRADOR a join USUARIO u on u.id_usuario = a.id_usuario where correo_electronico = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, correo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String telefono_guardia = rs.getString("telefono_guardia");
            Usuario_administrador usuario = new Usuario_administrador(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.ADMINISTRADOR, telefono_guardia);
            return usuario;
        }
        return null;
    }

    public Usuario_administrador buscarID(int cod) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from ADMINISTRADOR a join USUARIO u on u.id_usuario = a.id_usuario where a.id_usuario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, cod);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String telefono_guardia = rs.getString("telefono_guardia");
            Usuario_administrador usuario = new Usuario_administrador(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.ADMINISTRADOR, telefono_guardia);
            return usuario;
        }
        return null;
    }
}
