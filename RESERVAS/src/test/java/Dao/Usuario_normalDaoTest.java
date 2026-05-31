package Dao;

import Model.Usuario;
import Model.Usuario_administrador;
import Model.Usuario_normal;
import Model.tipoUsu;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class Usuario_normalDaoTest {
    Usuario_normalDao usuario2 = new Usuario_normalDao();
    UsuarioDao usuario1 = new UsuarioDao();

    String nombre = "Prueba1";
    String correoElectronico = "prueba1@gmail.com";
    String contrasenya = "1234";
    String fecnac = "2002-2-2";
    String telefono = "123456789";
    String direccion = "Cale Mediodía 1";
    String fotografia = "yeray.jpg";
    Usuario prueba = new Usuario(correoElectronico, contrasenya, nombre, fecnac, tipoUsu.ADMINISTRADOR);

    Usuario_normal prueba1 = new Usuario_normal(correoElectronico, contrasenya, nombre, fecnac, tipoUsu.USUARIO_NORMAL, direccion, telefono, fotografia);

    String nombre2 = "Prueba2";
    String correoElectronico2 = "prueba2@gmail.com";
    String contrasenya2 = "12345";
    String fecnac2 = "2003-3-3";
    String telefono2 = "1";
    String direccion2 = "---------";
    String fotografia2 = "sgafaadbnb.jpg";
    Usuario_normal prueba2 = new Usuario_normal(correoElectronico2, contrasenya2, nombre2, fecnac2, tipoUsu.ADMINISTRADOR,  direccion2, telefono2, fotografia2);

    @Test
    void altaUsuario() {
        try {
            int id = usuario1.altaUsuario(prueba);
            assertEquals(id, usuario2.altaUsuario(prueba1, id));

            usuario2.bajaUsuario(id);
            usuario1.bajaUsuario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void bajaUsuario() {
        try {
            int id = usuario1.altaUsuario(prueba);
            usuario2.altaUsuario(prueba1, id);

            assertTrue(usuario2.bajaUsuario(id));
            usuario1.bajaUsuario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void modificarUsuario() {
        try {
            int id = usuario1.altaUsuario(prueba);
            usuario2.altaUsuario(prueba1, id);

            prueba2.setId(id);

            assertTrue(usuario2.modificarUsuario(prueba2));
            usuario2.bajaUsuario(id);
            usuario1.bajaUsuario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void buscarUsuarioPorNombre() {
        try {
            int id = usuario1.altaUsuario(prueba);
            usuario2.altaUsuario(prueba1, id);
            assertEquals("nombre='Prueba1', correoElectronico='prueba1@gmail.com', contrasenya='1234', fecnac='2002-02-02', tipo=USUARIO_NORMAL, direccion='Cale Mediodía 1', telefonoMovil=123456789, fotografia='yeray.jpg"
                    , usuario2.buscarNombre(nombre).toString());
            usuario2.bajaUsuario(id);
            usuario1.bajaUsuario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}