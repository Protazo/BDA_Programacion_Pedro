package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    int idRecurso;
    int cantidadDeReservas;
    int idUsuario;
    LocalDate fecha;
    LocalTime horaInicio;
    LocalTime horaFin;
    double coste;
    int nPlazas;
    String motivo;
    String observaciones;

    public Reserva(int idRecurso, int idUsuario, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, double coste, int nPlazas, String motivo, String observaciones) {
        this.idRecurso = idRecurso;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.coste = coste;
        this.nPlazas = nPlazas;
        this.motivo = motivo;
        this.observaciones = observaciones;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getCantidadDeReservas() {
        return cantidadDeReservas;
    }

    public void setCantidadDeReservas(int cantidadDeReservas) {
        this.cantidadDeReservas = cantidadDeReservas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public int getnPlazas() {
        return nPlazas;
    }

    public void setnPlazas(int nPlazas) {
        this.nPlazas = nPlazas;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return  "idRecurso=" + idRecurso +
                ", cantidadDeReservas=" + cantidadDeReservas +
                ", idUsuario=" + idUsuario +
                ", fecha=" + fecha +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", coste=" + coste +
                ", nPlazas=" + nPlazas +
                ", motivo='" + motivo + '\'' +
                ", observaciones='" + observaciones;
    }
}
