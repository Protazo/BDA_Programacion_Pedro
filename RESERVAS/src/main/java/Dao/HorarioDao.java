package Dao;

import Model.Horario;
import Model.Recurso;
import Model.diaSemana;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioDao {

    public int altaHorario(diaSemana dia, LocalTime inicio, LocalTime fin) throws SQLException{
        Connection cnx = DBConnection.getConnection();
        String sql = "insert into HORARIO (dia_semana, hora_inicio, hora_fin) values (?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, dia.name());
        ps.setTime(2, java.sql.Time.valueOf(inicio));
        ps.setTime(3, java.sql.Time.valueOf(fin));

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

    public boolean bajaHorario(int id) throws SQLException{
        Connection cnx = DBConnection.getConnection();
        String sql = "delete from HORARIO where id_horario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, id);

        ps.executeUpdate();

        ps.close();
        cnx.close();
        return true;
    }

    public Horario modificarHorario(int id, diaSemana dia, LocalTime inicio, LocalTime fin) throws SQLException{
        Connection cnx = DBConnection.getConnection();
        String sql = "update HORARIO set dia_semana = ?, hora_inicio = ?, hora_fin = ? where id_horario = ? ";
        PreparedStatement ps = cnx.prepareStatement(sql);

        ps.setString(1, dia.name());
        ps.setTime(2, java.sql.Time.valueOf(inicio));
        ps.setTime(3, java.sql.Time.valueOf(fin));
        ps.setInt(4, id);
        ps.executeUpdate();

        ps.close();
        cnx.close();
        return buscarPorIdHorario(id);
    }

    public List<Horario> listarHorarios() throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from HORARIO";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        List<Horario> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_horario");
            String diaString = rs.getString("dia_semana");
            LocalTime horaInicio = rs.getTime("hora_inicio").toLocalTime();
            LocalTime horaFin = rs.getTime("hora_fin").toLocalTime();

            diaSemana dia = null;
            if (diaString.equalsIgnoreCase("Lunes")) {
                dia = Model.diaSemana.Lunes;
            } else if (diaString.equalsIgnoreCase("Martes")) {
                dia = Model.diaSemana.Martes;
            } else if (diaString.equalsIgnoreCase("Miércoles")) {
                dia = Model.diaSemana.Miércoles;
            } else if (diaString.equalsIgnoreCase("Miercoles")) {
                dia = Model.diaSemana.Miércoles;
            } else if (diaString.equalsIgnoreCase("Jueves")) {
                dia = Model.diaSemana.Jueves;
            } else if (diaString.equalsIgnoreCase("Viernes")) {
                dia = Model.diaSemana.Viernes;
            } else if (diaString.equalsIgnoreCase("Sábado")) {
                dia = Model.diaSemana.Sábado;
            } else if (diaString.equalsIgnoreCase("Sabado")) {
                dia = Model.diaSemana.Sábado;
            } else if (diaString.equalsIgnoreCase("Domingo")) {
                dia = Model.diaSemana.Domingo;
            }

            Horario horario = new Horario(dia, horaInicio, horaFin);
            horario.setId(id);
            lista.add(horario);
        }
        ps.close();
        cnx.close();
        return lista;
    }

    public Horario buscarPorIdHorario(int id) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        System.out.println(id);
        String sql = "select * from HORARIO where id_horario = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            id = rs.getInt("id_horario");
            String diaString = rs.getString("dia_semana");
            LocalTime horaInicio = rs.getTime("hora_inicio").toLocalTime();
            LocalTime horaFin = rs.getTime("hora_fin").toLocalTime();

            diaSemana dia = null;
            if (diaString.equalsIgnoreCase("Lunes")) {
                dia = Model.diaSemana.Lunes;
            } else if (diaString.equalsIgnoreCase("Martes")) {
                dia = Model.diaSemana.Martes;
            } else if (diaString.equalsIgnoreCase("Miércoles")) {
                dia = Model.diaSemana.Miércoles;
            } else if (diaString.equalsIgnoreCase("Miercoles")) {
                dia = Model.diaSemana.Miércoles;
            } else if (diaString.equalsIgnoreCase("Jueves")) {
                dia = Model.diaSemana.Jueves;
            } else if (diaString.equalsIgnoreCase("Viernes")) {
                dia = Model.diaSemana.Viernes;
            } else if (diaString.equalsIgnoreCase("Sábado")) {
                dia = Model.diaSemana.Sábado;
            } else if (diaString.equalsIgnoreCase("Sabado")) {
                dia = Model.diaSemana.Sábado;
            } else if (diaString.equalsIgnoreCase("Domingo")) {
                dia = Model.diaSemana.Domingo;
            }

            Horario horario = new Horario(dia, horaInicio, horaFin);
            horario.setId(id);

            ps.close();
            cnx.close();
            return horario;
        } else {
            System.out.println("No hay horarios con ese id");
            ps.close();
            cnx.close();
            return null;
        }
    }

    public List<Horario> buscarPorDiaHorario(diaSemana dia) throws SQLException {
        Connection cnx = DBConnection.getConnection();
        String sql = "select * from HORARIO where dia_semana = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, dia.name());
        ResultSet rs = ps.executeQuery();

        List<Horario> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id_horario");
            String diaString = rs.getString("dia_semana");
            LocalTime horaInicio = rs.getTime("hora_inicio").toLocalTime();
            LocalTime horaFin = rs.getTime("hora_fin").toLocalTime();

            Horario horario = new Horario(dia, horaInicio, horaFin);
            horario.setId(id);
            lista.add(horario);

            ps.close();
            cnx.close();
        }
        return lista;
    }

    public boolean comprobarHoraInicio(LocalTime horaInicio, int id_horario) throws SQLException {
        Horario horario = buscarPorIdHorario(id_horario);
        LocalTime horaInicioObjeto = horario.getHoraInicio();
        LocalTime horaFinObjeto = horario.getHoraFin();
        if (horaInicioObjeto.isAfter(horaInicio)) {
            return false;
        }
        if (horaFinObjeto.isBefore(horaInicioObjeto)) {
            return false;
        }
        return true;
    }

    public boolean comprobarHoraFinal(LocalTime horaFin, int id_horario) throws SQLException {
        Horario horario = buscarPorIdHorario(id_horario);
        LocalTime horaFinalObjeto = horario.getHoraFin();
        LocalTime horaInicioObjeto = horario.getHoraInicio();
        if (horaFinalObjeto.isBefore(horaFin)) {
            return false;
        }
        if (horaInicioObjeto.isAfter(horaFin)) {
            return false;
        }
        return true;
    }
}
