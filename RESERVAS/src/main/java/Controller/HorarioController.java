package Controller;

import Dao.HorarioDao;
import Model.Horario;
import Model.diaSemana;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class HorarioController {

    HorarioDao horario = new HorarioDao();

    public int altaHorario(diaSemana dia, LocalTime inicio, LocalTime fin) throws SQLException {
        return horario.altaHorario(dia, inicio, fin);
    }

    public boolean borrarHorario(int id) throws SQLException {
        return horario.bajaHorario(id);
    }

    public Horario modificarHorario(int id, diaSemana dia, LocalTime inicio, LocalTime fin) throws SQLException {
        return horario.modificarHorario(id, dia, inicio, fin);
    }

    public List<Horario> listarHorarios() throws SQLException {
        return horario.listarHorarios();
    }

    public List<Horario> listarHorariosPorDia(diaSemana dia) throws SQLException {
        return horario.buscarPorDiaHorario(dia);
    }

    public Horario buscarHorarioId(int id) throws SQLException {
        return horario.buscarPorIdHorario(id);
    }
}
