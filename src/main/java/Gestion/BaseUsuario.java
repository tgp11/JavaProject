package Gestion;

public class BaseUsuario {
    protected String nombreUsuario;
    protected String contrasena;
    protected int permiso; // 0 = Usuario, 1 = Administrador

    public BaseUsuario(String nombreUsuario, String contrasena, int permiso) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.permiso = permiso;
    }

    // Getters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getPermiso() {
        return permiso;
    }

    // Métodos comunes
    public void cambiarContrasena(String nuevaContrasena) {
        this.contrasena = nuevaContrasena;
        System.out.println("Contraseña cambiada correctamente.");
    }

    public void verPerfil() {
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Tipo: " + (permiso == 1 ? "Administrador" : "Usuario"));
    }

    // Métodos protegidos para administración solo accesibles desde la subclase Administrador
    protected void setNombreUsuario(String nuevoNombre) {
        this.nombreUsuario = nuevoNombre;
    }

    protected void setPermiso(int nuevoPermiso) {
        this.permiso = nuevoPermiso;
    }
}
