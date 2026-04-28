package Model;

public class Usuario_normal extends Usuario {
    String direccion;
    int telefonoMovil;
    String fotografia;

    public Usuario_normal(String correoElectronico, String contrasenya, String nombre, String fecnac, tipoUsu tipo, String direccion, int telefonoMovil, String fotografia) {
        super(correoElectronico, contrasenya, nombre, fecnac, tipo);
        this.direccion = direccion;
        this.telefonoMovil = telefonoMovil;
        this.fotografia = fotografia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(int telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
}
