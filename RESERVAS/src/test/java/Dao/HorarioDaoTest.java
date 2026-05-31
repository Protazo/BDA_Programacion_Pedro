package Dao;

import Model.Horario;
import Model.diaSemana;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class HorarioDaoTest {

    HorarioDao horarioDao = new HorarioDao();

    diaSemana dia = diaSemana.Lunes;
    LocalTime inicio = LocalTime.of(12, 00, 15);
    LocalTime fin = LocalTime.of(13, 00, 15);
    Horario horario = new Horario(dia, inicio, fin);

    diaSemana dia2 = diaSemana.Martes;
    LocalTime inicio2 = LocalTime.of(2, 00, 15);
    LocalTime fin2 = LocalTime.of(16, 00, 15);

    @Test
    void altaHorario() {
        try {
            //LA BASE DE DATOS LA REESTABLEZCO DESDE CERO AQUÍ, EN LOS DEMAS PASOS NO HACE FALTA
            assertEquals(6, horarioDao.altaHorario(dia, inicio, fin));
            horarioDao.bajaHorario(6);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void bajaHorario() {
        try {
            int id = horarioDao.altaHorario(dia, inicio, fin);
            assertTrue(horarioDao.bajaHorario(id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void modificarHorario() {
        try {
            int id = horarioDao.altaHorario(dia, inicio, fin);
            assertEquals("id=" + id + ", dia=Martes, horaInicio=02:00:15, horaFin=16:00:15", horarioDao.modificarHorario(id, dia2, inicio2, fin2).toString());
            horarioDao.bajaHorario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void buscarPorIdHorario() {
        try {
            int id = horarioDao.altaHorario(dia2, inicio2, fin2);
            assertEquals("id=" + id + ", dia=Martes, horaInicio=02:00:15, horaFin=16:00:15", horarioDao.buscarPorIdHorario(id).toString());
            horarioDao.bajaHorario(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}