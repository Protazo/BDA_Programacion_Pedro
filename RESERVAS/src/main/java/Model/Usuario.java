package Model;

public class Usuario {
    String correoElectronico;
    String contrasenya;
    String nombre;
    String fecnac;
    tipoUsu tipo;
    int id;

    public Usuario(String correoElectronico, String contrasenya, String nombre, String fecnac, tipoUsu tipo) {
        this.correoElectronico = correoElectronico;
        this.contrasenya = contrasenya;
        this.nombre = nombre;
        this.fecnac = fecnac;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecnac() {
        return fecnac;
    }

    public void setFecnac(String fecnac) {
        this.fecnac = fecnac;
    }

    public tipoUsu getTipo() {
        return tipo;
    }

    public void setTipo(tipoUsu tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  "id= " + id + ", nombre='" + nombre + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", fecnac='" + fecnac + '\'' +
                ", tipo=" + tipo;
    }
}