package Dao;

import Model.Usuario;
import Model.Usuario_administrador;
import Model.Usuario_normal;
import Model.tipoUsu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario_normalDao {

   public int altaUsuario(Usuario_normal usuario, int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "INSERT INTO USUARIONORMAL (id_usuario, direccion, telefono_movil, fotografia) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, usuario.getDireccion());
        ps.setString(3, usuario.getTelefonoMovil());
        ps.setString(4, usuario.getFotografia());


        ps.executeUpdate();

        ps.close();
        cnx.close();
        return id;
    }

    public boolean bajaUsuario(int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "delete from USUARIONORMAL where id_usuario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, id);

        ps.executeUpdate();

        ps.close();
        cnx.close();
        return true;
    }

    public boolean modificarUsuario(Usuario_normal usuario) throws SQLException, SQLIntegrityConstraintViolationException {
        Connection cnx = DBConnection.getConnection();
        String sql = "update USUARIONORMAL set direccion = ?, telefono_movil = ?, fotografia = ? where id_usuario = ? ";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setString(1, usuario.getDireccion());
        ps.setString(2, usuario.getTelefonoMovil());
        ps.setString(3, usuario.getFotografia());
        ps.setInt(4, usuario.getId());

        ps.executeUpdate();

        ps.close();
        cnx.close();
        return true;
    }

    public List<Usuario_normal> listarUsuariosNormales() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from USUARIONORMAL un join USUARIO u on un.id_usuario = u.ID_USUARIO";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        List<Usuario_normal> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono_movil");
            String foto = rs.getString("fotografia");

            Usuario_normal usuario = new Usuario_normal(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.USUARIO_NORMAL, direccion, telefono, foto);

            lista.add(usuario);
        }
        ps.close();
        cnx.close();
        return lista;
    }

    public Usuario_normal buscarNombre(String nombre) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from USUARIONORMAL un join USUARIO u on u.id_usuario = un.id_usuario where nombre = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono_movil");
            String foto = rs.getString("fotografia");
            Usuario_normal usuario = new Usuario_normal(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.USUARIO_NORMAL, direccion, telefono, foto);
            return usuario;
        }
        return null;
    }

    public Usuario_normal buscarCorreo(String correo) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from USUARIONORMAL un join USUARIO u on u.id_usuario = un.id_usuario where correo_electronico = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, correo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono_movil");
            String foto = rs.getString("fotografia");
            Usuario_normal usuario = new Usuario_normal(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.USUARIO_NORMAL, direccion, telefono, foto);
            return usuario;
        }
        return null;
    }

    public Usuario_normal buscarID(int cod) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from USUARIONORMAL un join USUARIO u on u.id_usuario = un.id_usuario where un.id_usuario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, cod);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_usuario");
            String correo_electronico = rs.getString("correo_electronico");
            String contrasena = rs.getString("contrasena");
            String nombre_usuario = rs.getString("nombre");
            String fecha_nacimiento = rs.getString("fecha_nacimiento");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono_movil");
            String foto = rs.getString("fotografia");
            Usuario_normal usuario = new Usuario_normal(correo_electronico, contrasena, nombre_usuario, fecha_nacimiento, tipoUsu.USUARIO_NORMAL, direccion, telefono, foto);
            return usuario;
        }
        return null;
    }
}
