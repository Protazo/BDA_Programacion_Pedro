package Controller;

import Dao.DisponibleenDao;
import Model.Disponibleen;
import Model.Horario;

import java.sql.SQLException;
import java.util.List;

public class DisponibleenController {
    DisponibleenDao  dao = new DisponibleenDao();

    public List<Disponibleen> listarDisponibilidad() throws SQLException {
        return dao.listarDisponibilidad();
    }

    public boolean anyadirDisponibleen(Disponibleen d) throws SQLException {
        return dao.anyadirDisponibleen(d);
    }

    public boolean borrarDisponibilidad(Disponibleen d) throws SQLException {
        return dao.borrarDisponibilidad(d);
    }
}
