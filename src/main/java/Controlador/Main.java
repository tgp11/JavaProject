package Controlador;

import Gestion.Administrador;

public class Main {
	public static void main(String args[]) {
		Administrador a = new Administrador("Antonio", "1234");
		Administrador.usuarios.add(a);
	}
}
