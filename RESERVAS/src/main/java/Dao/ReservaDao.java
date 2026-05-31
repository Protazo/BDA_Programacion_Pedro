package Dao;

import Model.Reserva;
import Model.Usuario;
import Model.tipoUsu;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {

    public int siguienteIdReservaLocal(int idRecurso) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "SELECT COALESCE(MAX(id_reserva_local), 0) FROM RESERVA WHERE id_recurso = ?";

        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, idRecurso);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) + 1;
        }

        return 1;
    }

    public int[] altaReserva(Reserva reserva) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "INSERT INTO RESERVA (id_recurso, id_reserva_local, id_usuario, fecha, hora_inicio, hora_fin, coste, numero_plazas, motivo, observaciones) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        int cantidad = siguienteIdReservaLocal(reserva.getIdRecurso());

        ps.setInt(1, reserva.getIdRecurso());
        ps.setInt(2, cantidad);
        ps.setInt(3, reserva.getIdUsuario());
        ps.setDate(4, java.sql.Date.valueOf(reserva.getFecha()));
        ps.setTime(5, java.sql.Time.valueOf(reserva.getHoraInicio()));
        ps.setTime(6, java.sql.Time.valueOf(reserva.getHoraFin()));
        ps.setDouble(7, reserva.getCoste());
        ps.setInt(8, reserva.getnPlazas());
        ps.setString(9, reserva.getMotivo());
        ps.setString(10, reserva.getObservaciones());

        ps.executeUpdate();

        ps.close();
        cnx.close();

        int[] codigos = new int[3];
        codigos[0] = reserva.getIdRecurso();
        codigos[1] = reserva.getIdUsuario();
        codigos[2] = cantidad;
        return codigos;
    }

    public int cantidadReservas(Reserva reserva) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "SELECT count(*) as cantidad FROM RESERVA where id_recurso = ? and id_usuario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, reserva.getIdRecurso());
        ps.setInt(2, reserva.getIdUsuario());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int cantidad = rs.getInt(1);
            return cantidad;
        }
        return 0;
    }

    public boolean borrarReserva(int id1, int id2, int id3) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "delete from RESERVA where id_recurso = ? and id_usuario = ? and id_reserva_local = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setInt(1, id1);
        ps.setInt(2, id2);
        ps.setInt(3, id3);

        ps.executeUpdate();

        ps.close();
        cnx.close();
        return true;
    }

    public int[] modificarReserva(Reserva reserva) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "update RESERVA set fecha = ?, hora_inicio = ?, hora_fin = ?, coste = ?, numero_plazas = ?, motivo = ?, observaciones = ? where id_recurso = ? AND " +
                "id_usuario = ? and id_reserva_local = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setDate(1, java.sql.Date.valueOf(reserva.getFecha()));
        ps.setTime(2, java.sql.Time.valueOf(reserva.getHoraInicio()));
        ps.setTime(3, java.sql.Time.valueOf(reserva.getHoraFin()));
        ps.setDouble(4, reserva.getCoste());
        ps.setInt(5, reserva.getnPlazas());
        ps.setString(6, reserva.getMotivo());
        ps.setString(7, reserva.getObservaciones());
        ps.setInt(8, reserva.getIdRecurso());
        ps.setInt(9, reserva.getIdUsuario());
        ps.setInt(10, reserva.getCantidadDeReservas());


        ps.executeUpdate();

        ps.close();
        cnx.close();
        int[] codigos = new int[3];
        codigos[0] = reserva.getIdRecurso();
        codigos[1] = reserva.getIdUsuario();
        codigos[2] = reserva.getCantidadDeReservas();
        return codigos;
    }

    public List<Reserva> listarReservas() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from RESERVA order by id_recurso";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        List<Reserva> lista = new ArrayList<>();
        while (rs.next()) {
            int id_recurso = rs.getInt("id_recurso");
            int id_usuario = rs.getInt("id_usuario");
            int id_reserva = rs.getInt("id_reserva_local");
            LocalDate fecha = rs.getDate("fecha").toLocalDate();
            LocalTime inicio = rs.getTime("hora_inicio").toLocalTime();
            LocalTime fin = rs.getTime("hora_fin").toLocalTime();
            double coste = rs.getDouble("coste");
            int numero_plazas = rs.getInt("numero_plazas");
            String motivo = rs.getString("motivo");
            String observaciones = rs.getString("observaciones");

            Reserva reserva = new Reserva(id_recurso, id_usuario, fecha, inicio, fin, coste, numero_plazas, motivo, observaciones);
            reserva.setCantidadDeReservas(id_reserva);
            lista.add(reserva);
        }
        ps.close();
        cnx.close();
        return lista;
    }

    public Reserva buscarReservaID(int id_recurso, int id_usuario, int id_reserva_local) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from RESERVA where id_recurso = ? and id_usuario = ? and id_reserva_local = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, id_recurso);
        ps.setInt(2, id_usuario);
        ps.setInt(3, id_reserva_local);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            id_usuario = rs.getInt("id_usuario");
            id_recurso = rs.getInt("id_recurso");
            id_reserva_local = rs.getInt("id_reserva_local");
            LocalDate fecha = rs.getDate("fecha").toLocalDate();
            LocalTime inicio = rs.getTime("hora_inicio").toLocalTime();
            LocalTime fin = rs.getTime("hora_fin").toLocalTime();
            double coste =  rs.getDouble("coste");
            int plazas = rs.getInt("numero_plazas");
            String motivo = rs.getString("motivo");
            String observaciones = rs.getString("observaciones");

            Reserva res = new Reserva(id_recurso, id_usuario, fecha, inicio, fin, coste, plazas, motivo, observaciones);
            res.setCantidadDeReservas(id_reserva_local);
            return res;
        }
        return null;
    }

    public List<Reserva> buscarReservaUsuario(int id_usuario) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from RESERVA where id_usuario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, id_usuario);

        ResultSet rs = ps.executeQuery();

        List<Reserva> lista = new ArrayList<>();
        while (rs.next()) {
            id_usuario = rs.getInt("id_usuario");
            int id_recurso = rs.getInt("id_recurso");
            int id_reserva_local = rs.getInt("id_reserva_local");
            LocalDate fecha = rs.getDate("fecha").toLocalDate();
            LocalTime inicio = rs.getTime("hora_inicio").toLocalTime();
            LocalTime fin = rs.getTime("hora_fin").toLocalTime();
            double coste =  rs.getDouble("coste");
            int plazas = rs.getInt("numero_plazas");
            String motivo = rs.getString("motivo");
            String observaciones = rs.getString("observaciones");

            Reserva res = new Reserva(id_recurso, id_usuario, fecha, inicio, fin, coste, plazas, motivo, observaciones);
            res.setCantidadDeReservas(id_reserva_local);
            lista.add(res);
        }
        return lista;
    }
}
