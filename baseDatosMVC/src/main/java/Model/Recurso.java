package Model;

public class Recurso {
    int id;
    String nombre;
    String descripcion;
    String ubicacion;
    int capacidad;

    public Recurso(int id, String nombre, String descripcion, String ubicacion, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Recurso\t" +
                "id=" + id +
                "\tnombre='" + nombre + '\'' +
                "\tdescripcion='" + descripcion + '\'' +
                "\tubicacion='" + ubicacion + '\'' +
                "\tcapacidad=" + capacidad;
    }
}
