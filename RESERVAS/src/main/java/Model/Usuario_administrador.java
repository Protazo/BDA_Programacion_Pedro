package Model;

public class Usuario_administrador extends Usuario {
    String telefonoGuardia;

    public Usuario_administrador(String correoElectronico, String contrasenya, String nombre, String fecnac, tipoUsu tipo, String telefonoGuardia) {
        super(correoElectronico, contrasenya, nombre, fecnac, tipo);
        this.telefonoGuardia = telefonoGuardia;
    }

    public String getTelefonoGuardia() {
        return telefonoGuardia;
    }

    public void setTelefonoGuardia(String telefonoGuardia) {
        this.telefonoGuardia = telefonoGuardia;
    }

    @Override
    public String toString() {
        return  "nombre='" + nombre + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", fecnac='" + fecnac + '\'' +
                ", tipo=" + tipo +
                ", telefonoGuardia=" + telefonoGuardia;
    }
}
