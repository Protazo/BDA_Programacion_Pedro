package Dao;

import Model.Recurso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecursoDao {

    public int altaRecurso(String nombre, String desc, String ubicacion, int capacidad) {
        try {
            Connection cnx = DBConnection.getConnection();
            String sql = "insert into RECURSO (nombre, descripcion, ubicacion, capacidad) values (?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nombre);
            ps.setString(2, desc);
            ps.setString(3, ubicacion);
            ps.setInt(4, capacidad);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            ps.close();
            cnx.close();

            if (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }

            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int bajaRecurso(int id) {
        try {
            Connection cnx = DBConnection.getConnection();
            String sql = "delete from RECURSO where id_recurso = ?";
            PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            cnx.close();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Recurso modificarRecurso(int id, String  nombre, String descripcion, String ubicacion, int capacidad) {

        try {
            Connection cnx = DBConnection.getConnection();
            String sql = "update RECURSO set nombre = ?, descripcion = ?, ubicacion = ?, capacidad = ? where id_recurso = ? ";
            PreparedStatement ps = cnx.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setString(3, ubicacion);
            ps.setInt(4, capacidad);
            ps.setInt(5, id);
            ps.executeUpdate();



            ps.close();
            cnx.close();
            return buscarPorIdRecurso(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Recurso> listarRecursos() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from RECURSO";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        List<Recurso> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_recurso");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String ubicacion = rs.getString("ubicacion");
            int capacidad = rs.getInt("capacidad");
            Recurso r =  new Recurso(id, nombre, descripcion, ubicacion, capacidad);
            lista.add(r);
        }
        ps.close();
        cnx.close();
        return lista;
    }

    public Recurso buscarPorNombreRecurso(String nombre) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from RECURSO where nombre = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id_recurso");
            String descripcion = rs.getString("descripcion");
            String ubicacion = rs.getString("ubicacion");
            int capacidad = rs.getInt("capacidad");

            Recurso r = new Recurso(id, nombre, descripcion, ubicacion, capacidad);
            ps.close();
            cnx.close();
            return r;
        } else {
            System.out.println("No hay recursos con ese nombre");
            ps.close();
            cnx.close();
            return null;
        }

    }

    public Recurso buscarPorIdRecurso(int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        System.out.println(id);
        String sql = "select * from RECURSO where id_recurso = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id_recurso");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String ubicacion = rs.getString("ubicacion");
            int capacidad = rs.getInt("capacidad");

            Recurso r = new Recurso(id, nombre, descripcion, ubicacion, capacidad);

            ps.close();
            cnx.close();
            return r;
        } else {
            System.out.println("No hay recursos con ese id");
            ps.close();
            cnx.close();
            return null;
        }
    }
}
