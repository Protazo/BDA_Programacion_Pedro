import Dao.DBConnection;
import Dao.RecursoDao;
import Model.Recurso;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class RecursoDaoTest {
    RecursoDao recursoDao = new RecursoDao();

    // HAY QUE EJECUTAR PRIMERO EL SCRIPT PARA REESTABLECER LA BASE DE DATOS DESDE 0 Y PROBAR 1 X 1 EN ORDEN,
    // SINO DA ERROR

    @Test
    void altaRecurso() throws SQLException {

        Connection cnx = DBConnection.getConnection();
        String nombre = "Prueba1";
        String descripcion = "Descripcion1";
        String ubicacion = "ubicacion1";
        int capacidad = 1;

        int id = recursoDao.altaRecurso(nombre, descripcion, ubicacion, capacidad);
        Recurso existe = recursoDao.buscarPorIdRecurso(id);
        assertNotNull(existe);
        recursoDao.bajaRecurso(id);
    }

    @Test
    void bajaRecurso() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String nombre = "Prueba1";
        String descripcion = "Descripcion1";
        String ubicacion = "ubicacion1";
        int capacidad = 1;

        int id = recursoDao.altaRecurso(nombre, descripcion, ubicacion, capacidad);
        recursoDao.bajaRecurso(id);
        Recurso existe = recursoDao.buscarPorIdRecurso(id);
        assertNull(existe);
    }

    @Test
    void modificarRecurso() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String nombre = "Prueba12";
        String descripcion = "Descripcion1";
        String ubicacion = "ubicacion1";
        int capacidad = 1;
        int id = recursoDao.altaRecurso(nombre, descripcion, ubicacion, capacidad);
        Recurso r = new Recurso(id, "Mod1", "Modificacion1", "modubi1", 2);
        assertEquals(r.toString(), recursoDao.modificarRecurso(id, "Mod1", "Modificacion1", "modubi1", 2).toString());
    }

    @Test
    void buscarPorNombreRecurso() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String nombre = "Prueba13";
        String descripcion = "Descripcion1";
        String ubicacion = "ubicacion1";
        int capacidad = 1;
        int id = recursoDao.altaRecurso(nombre, descripcion, ubicacion, capacidad);
        Recurso r = new Recurso(id, nombre, descripcion, ubicacion, capacidad);
        assertEquals(r.toString(), recursoDao.buscarPorNombreRecurso(nombre).toString());
        recursoDao.bajaRecurso(id);
    }

    @Test
    void buscarPorIdRecurso() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String nombre = "Prueba13";
        String descripcion = "Descripcion1";
        String ubicacion = "ubicacion1";
        int capacidad = 1;
        int id = recursoDao.altaRecurso(nombre, descripcion, ubicacion, capacidad);
        Recurso r = new Recurso(id, nombre, descripcion, ubicacion, capacidad);
        assertEquals(r.toString(), recursoDao.buscarPorIdRecurso(id).toString());
        recursoDao.bajaRecurso(id);
    }
}