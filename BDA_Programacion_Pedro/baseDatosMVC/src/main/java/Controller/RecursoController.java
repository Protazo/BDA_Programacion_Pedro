package Controller;

import Dao.RecursoDao;
import Model.Recurso;

import java.sql.SQLException;
import java.util.List;

public class RecursoController {

    RecursoDao recursoDao = new RecursoDao();

    public void altaRecurso(String nombre, String desc, String ubicacion, int capacidad) {
        recursoDao.altaRecurso(nombre, desc, ubicacion, capacidad);
    }

    public void bajaRecurso(int id) {
        recursoDao.bajaRecurso(id);
    }

    public Recurso modificarRecurso(int id, String nombre, String descripcion, String ubicacion, int capacidad) {
        return recursoDao.modificarRecurso(id, nombre,descripcion,ubicacion,capacidad);
    }

    public List<Recurso> listarRecursos() {
        try {
            List<Recurso> lista = recursoDao.listarRecursos();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Recurso buscarPorNombreRecurso(String nombre) {
        try {
            return recursoDao.buscarPorNombreRecurso(nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscarPorIdRecurso(int id) {
        try {
            recursoDao.buscarPorIdRecurso(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
