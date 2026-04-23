package Model;

public class Usuario_administrador extends Usuario {
    int telefonoGuardia;

    public Usuario_administrador(String correoElectronico, String contrasenya, String nombre, String fecnac, tipoUsu tipo, int telefonoGuardia) {
        super(correoElectronico, contrasenya, nombre, fecnac, tipo);
        this.telefonoGuardia = telefonoGuardia;
    }

    public int getTelefonoGuardia() {
        return telefonoGuardia;
    }

    public void setTelefonoGuardia(int telefonoGuardia) {
        this.telefonoGuardia = telefonoGuardia;
    }
}
