package Dao;

import Model.Usuario;
import Model.Usuario_administrador;
import Model.tipoUsu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario_administradorDao {

    public int altaUsuario(Usuario_administrador usuario) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "INSERT INTO USUARIO (id_usuario, correo_electronico, contrasena, nombre, fecha_nacimiento, tipo_usuario, telefono_guardia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setString(1, usuario.getCorreoElectronico());
        ps.setString(2, usuario.getContrasenya());
        ps.setString(3, usuario.getNombre());
        ps.setString(4, usuario.getFecnac());
        ps.setString(5, usuario.getTelefonoGuardia());

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

        return true;
    }

    public void modificarUsuario(Usuario_administrador usuario) throws SQLException {

    }

    public void buscarUsuarioPorNombre(Usuario_administrador usuario) throws SQLException {

    }

    public void buscarUsuarioPorCorreo(Usuario_administrador usuario) throws SQLException {

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
}
