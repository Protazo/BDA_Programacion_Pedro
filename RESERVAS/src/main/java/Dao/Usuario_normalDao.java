package Dao;

import Model.Usuario;
import Model.Usuario_normal;
import Model.tipoUsu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario_normalDao {

    public void altaUsuario(Usuario_normal usuario) throws SQLException {

    }

    public boolean bajaUsuario(int id) throws SQLException {
    return true;
    }

    public void modificarUsuario(Usuario_normal usuario) throws SQLException {

    }

    public void buscarUsuarioPorNombre(Usuario_normal usuario) throws SQLException {

    }

    public void buscarUsuarioPorCorreo(Usuario_normal usuario) throws SQLException {

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
}
