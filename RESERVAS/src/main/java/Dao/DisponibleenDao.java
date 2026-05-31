package Dao;

import Model.Disponibleen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisponibleenDao {

    public List<Disponibleen> listarDisponibilidad() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from DISPONIBLEEN";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Disponibleen> lista = new ArrayList<>();
        while (rs.next()) {
            int id_recurso = rs.getInt("id_recurso");
            int id_horario = rs.getInt("id_horario");
            Disponibleen disp = new Disponibleen(id_recurso, id_horario);
            lista.add(disp);
        }
        return lista;
    }

    public boolean anyadirDisponibleen(Disponibleen d) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "insert into DISPONIBLEEN (id_recurso, id_horario) values (?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, d.getId_recurso());
        ps.setInt(2, d.getId_horario());
        ps.executeUpdate();

        return true;
    }

    public boolean borrarDisponibilidad(Disponibleen d) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "delete from DISPONIBLEEN where id_recurso = ? and id_horario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, d.getId_recurso());
        ps.setInt(2, d.getId_horario());
        ps.executeUpdate();
        return true;
    }

    public int buscarHorarioporRecurso(int id_recurso) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select id_horario from DISPONIBLEEN where id_recurso = ?";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, id_recurso);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id_horario = rs.getInt("id_horario");
            return id_horario;
        }
        return 0;
    }
}
