package Controller;

import Dao.ReservaDao;
import Model.Reserva;

import java.sql.SQLException;
import java.util.List;

public class ReservaController {

    ReservaDao reservaDao = new ReservaDao();

    public int[] altaReserva(Reserva reserva) throws SQLException {
        return reservaDao.altaReserva(reserva);
    }

    public boolean borrarReserva(int id1, int id2, int id3) throws SQLException {
        return reservaDao.borrarReserva(id1, id2, id3);
    }

    public int cantidadReservas(Reserva reserva) throws SQLException {
        return reservaDao.cantidadReservas(reserva);
    }

    public int[] modificarReserva(Reserva reserva) throws SQLException {
        return reservaDao.modificarReserva(reserva);
    }

    public List<Reserva> listarReservas() throws  SQLException {
        return reservaDao.listarReservas();
    }

    public Reserva buscarReservaId(int id_recurso, int id_usuario, int id_reserva_local) throws SQLException {
        return reservaDao.buscarReservaID(id_recurso, id_usuario, id_reserva_local);
    }

    public List<Reserva> buscarReservaUsuario(int id_usuario) throws SQLException {
        return reservaDao.buscarReservaUsuario(id_usuario);
    }
}
