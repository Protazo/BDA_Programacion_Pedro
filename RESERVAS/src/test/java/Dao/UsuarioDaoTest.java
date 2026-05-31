package Dao;

import Model.Usuario;
import Model.Usuario_normal;
import Model.tipoUsu;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {
    UsuarioDao usuario = new UsuarioDao();
    String nombre = "Prueba1";
    String correoElectronico = "prueba1@gmail.com";
    String contrasenya = "1234";
    String fecnac = "2002-2-2";
    Usuario prueba1 = new Usuario(correoElectronico, contrasenya, nombre, fecnac, tipoUsu.ADMINISTRADOR);

    String nombre2 = "Prueba2";
    String correoElectronico2 = "prueba2@gmail.com";
    String contrasenya2 = "12345";
    String fecnac2 = "2003-3-3";
    Usuario prueba2 = new Usuario(correoElectronico2, contrasenya2, nombre2, fecnac2, tipoUsu.ADMINISTRADOR);


    @Test
    void altaUsuario() {

        try {
            int id = usuario.altaUsuario(prueba1);
            System.out.println(usuario.buscarID(id));
            assertEquals("id= " + id + ", nombre='Prueba1', correoElectronico='prueba1@gmail.com', contrasenya='1234', fecnac='2002-02-02', tipo=ADMINISTRADOR", usuario.buscarID(id).toString());
            usuario.bajaUsuario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void bajaUsuario() {
        try {
            int id = usuario.altaUsuario(prueba1);
            assertTrue(usuario.bajaUsuario(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void modificarUsuario() {
        try {
            int id = usuario.altaUsuario(prueba1);
            prueba2.setId(id);

            assertEquals("id= " + id + ", nombre='Prueba2', correoElectronico='prueba2@gmail.com', contrasenya='12345', fecnac='2003-03-03', tipo=ADMINISTRADOR", usuario.modificarUsuario(prueba2).toString());
            usuario.bajaUsuario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void buscarUsuarioPorNombre() {
        try {
            int id = usuario.altaUsuario(prueba1);
            assertEquals("id= " + id + ", nombre='Prueba1', correoElectronico='prueba1@gmail.com', contrasenya='1234', fecnac='2002-02-02', tipo=ADMINISTRADOR", usuario.buscarUsuarioPorNombre(nombre).toString());
            usuario.bajaUsuario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}