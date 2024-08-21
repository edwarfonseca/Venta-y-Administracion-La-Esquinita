package uptc.proyectofx.model;

public class Usuario {
    private String correo;
    private String contraseña;
    private String rol; // "admin" o "emp"

    public Usuario(String correo, String contraseña, String rol) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
