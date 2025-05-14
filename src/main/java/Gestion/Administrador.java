package Gestion;
import java.util.ArrayList;
import java.util.Iterator;

public class Administrador extends BaseUsuario {
    public static ArrayList<BaseUsuario> usuarios;

    public Administrador(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, 1);
        this.usuarios = new ArrayList<>();
    }

    public void crearUsuario(String nombre, String pass) {
        usuarios.add(new Usuario(nombre, pass));
        System.out.println("Usuario creado: " + nombre);
    }

    public void eliminarUsuario(String nombre) {
        Iterator<BaseUsuario> it = usuarios.iterator();
        while (it.hasNext()) {
            BaseUsuario u = it.next();
            if (u.getNombreUsuario().equals(nombre)) {
                it.remove();
                System.out.println("Usuario eliminado: " + nombre);
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    public void listarUsuarios() {
        System.out.println("Lista de usuarios:");
        for (BaseUsuario u : usuarios) {
            System.out.println("- " + u.getNombreUsuario());
        }
    }

    public ArrayList<BaseUsuario> getUsuarios() {
        return usuarios;
    }
}
