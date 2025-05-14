package Controlador;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JMenu;

import Gestion.BaseUsuario;
import Modelo.Estilos;

public class Navegador {
    private static ArrayList<JFrame> ventanas = new ArrayList<>();

    public static void CrearVentana(JFrame ventana) {
        ventanas.add(ventana);
    }

    public static void Dispatcher(String nombre, boolean visible) {
        for(JFrame v : ventanas) {
        	if(v.getTitle().equals(nombre)) {
        		v.setVisible(visible);
        		break;
        	}
        }
    }

    public static boolean IsCreated(String nombre) {
    	for(JFrame v : ventanas) {
        	if(v.getTitle().equals(nombre)) {
        		return true;
        	}
        }
    	return false;
    }
    
    public static JFrame getVentana(String nombre) {
        for (JFrame v : ventanas) {
            if (v.getTitle().equals(nombre)) {
                return v;
            }
        }
        return null;
    }
}