package Dao;

import Model.Usuario_administrador;
import Model.Usuario_normal;
import Model.tipoUsu;

import java.sql.*;

public class Usuario_administradorDao {

    public int altaUsuario(Usuario_administrador usuario) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "INSERT INTO USUARIO (id_usuario, correo_electronico, contrasena, nombre, fecha_nacimiento, tipo_usuario, telefono_guardia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setString(1, usuario.getCorreoElectronico());
        ps.setString(2, usuario.getContrasenya());
        ps.setString(3, usuario.getNombre());
        ps.setString(4, usuario.getFecnac());
        ps.setInt(5, usuario.getTelefonoGuardia());

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

    }

    public void modificarUsuario(Usuario_administrador usuario) throws SQLException {

    }

    public void buscarUsuarioPorNombre(Usuario_administrador usuario) throws SQLException {

    }

    public void buscarUsuarioPorCorreo(Usuario_administrador usuario) throws SQLException {

    }
}
