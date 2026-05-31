package Model;

public class Disponibleen {
    int id_recurso;
    int id_horario;

    public Disponibleen(int id_recurso, int id_horario) {
        this.id_recurso = id_recurso;
        this.id_horario = id_horario;
    }

    public int getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(int id_recurso) {
        this.id_recurso = id_recurso;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    @Override
    public String toString() {
        return  "id_recurso=" + id_recurso +
                ", id_horario=" + id_horario;
    }
}
