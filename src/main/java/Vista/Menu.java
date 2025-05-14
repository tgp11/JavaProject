package Vista;

import java.awt.*;
import javax.swing.*;

import Controlador.Navegador;
import Gestion.BaseUsuario;
import Modelo.Estilos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame {
    private JPanel contentPane;
    private static BaseUsuario a;
    public Menu(BaseUsuario u) {
    	a = u;
        setTitle("Principal");
        Navegador.CrearVentana(this);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            Estilos.configurarColoresUI();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        Estilos.colorpanel(contentPane);

        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(1, 0));
        Estilos.colorbarramenu(menuBar);

        String[] secciones = { "Pelicula", "Actor", "Director", "Cine", "Distribuidor", "Genero" };
        
        for (String seccion : secciones) {
            JMenu menu = new JMenu(seccion);
            if(u.getPermiso() == 0) {
            	addMenuItems(menu, new String[] { "Buscar", "Ver" }, u);
            }else {
            	addMenuItems(menu, new String[] { "Añadir", "Eliminar", "Buscar", "Ver" }, u);
            }
            Estilos.colormenu(menu);
            menuBar.add(menu);
        }

        setJMenuBar(menuBar);

        // Panel central con botones
        JPanel buttonPanel = new JPanel(); // Ahora con 4 botones
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Estilos.colorpanel(buttonPanel);

        JButton gestionButton = new JButton("GESTIONAR VALORACIONES");
        gestionButton.setBounds(20, 11, 544, 63);
        JButton fichajesButton = new JButton("ADMINISTRAR FICHAJES");
        fichajesButton.setBounds(20, 108, 544, 63);
        JButton premiosButton = new JButton("PREMIOS");
        premiosButton.setBounds(20, 208, 544, 63);
        JButton cerrarSesionButton = new JButton("CERRAR SESIÓN"); // Nuevo botón
        cerrarSesionButton.setBounds(427, 284, 137, 30);

        Estilos.colorboton(premiosButton);
        Estilos.colorboton(fichajesButton);
        Estilos.colorboton(gestionButton);
        Estilos.colorboton(cerrarSesionButton);

        gestionButton.addActionListener(e -> abrirGestionValoraciones());
        fichajesButton.addActionListener(e -> abrirAdministrarFichajes());
        premiosButton.addActionListener(e -> abrirPremios());
        cerrarSesionButton.addActionListener(e -> a = cerrarSesion());
        buttonPanel.setLayout(null);

        buttonPanel.add(gestionButton);
        buttonPanel.add(fichajesButton);
        buttonPanel.add(premiosButton);
        buttonPanel.add(cerrarSesionButton);

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        JLabel statusBar = new JLabel(" Sistema de gestión de cine - Listo");
        statusBar.setForeground(Color.WHITE);
        statusBar.setBackground(new Color(28, 28, 28));
        statusBar.setOpaque(true);
        contentPane.add(statusBar, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosed(WindowEvent e) {
    			Navegador.Dispatcher("Iniciar Sesión",true);
    			Navegador.Dispatcher(getTitle(), false);
    		}
    	});
    }

    private void addMenuItems(JMenu menu, String[] items, BaseUsuario u) {
        for (String item : items) {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(e -> handleMenuAction(menu.getText(), item, u));
            Estilos.estiloMenuItem(menuItem);
            menu.add(menuItem);
        }
    }

    private void handleMenuAction(String menu, String item, BaseUsuario u) {
        switch (item) {
            case "Buscar":
                abrirVentana(new Buscar(menu), "Buscar " + menu);
                break;
            case "Ver":
                abrirVentana(new Ver(menu), "Ver " + menu);
                break;
            case "Añadir":
                if (u.getPermiso() == 0) {
                    JOptionPane.showMessageDialog(this, "Solo el administrador puede añadir", "Error Permisos", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Llamar a generarMenuBar solo cuando el permiso sea el adecuado y se quiera añadir
                    generarMenuBar(u);  // Asegura que se actualicen las opciones del menú dinámicamente

                    abrirVentana(new Anadir(menu), "Añadir " + menu);
                }
                break;
            case "Eliminar":
                if (u.getPermiso() == 0) {
                    JOptionPane.showMessageDialog(this, "Solo el administrador puede eliminar", "Error Permisos", JOptionPane.ERROR_MESSAGE);
                } else {
                    abrirVentana(new Eliminar(menu), "Eliminar " + menu);
                }
                break;
        }
        
        
    }

    private void abrirVentana(JFrame ventana, String titulo) {
        if (Navegador.IsCreated(titulo)) {
            Navegador.Dispatcher(titulo, true);
        } else {
            ventana.setVisible(true);
            Navegador.CrearVentana(ventana);
        }
        this.setVisible(false);
    }

    private void abrirGestionValoraciones() {
        JOptionPane.showMessageDialog(this, "Abriendo gestión de valoraciones");
    }

    private void abrirAdministrarFichajes() {
        JOptionPane.showMessageDialog(this, "Abriendo administración de fichajes");
    }

    private void abrirPremios() {
        JOptionPane.showMessageDialog(this, "Abriendo módulo de premios");
    }

    private BaseUsuario cerrarSesion() {
        Navegador.Dispatcher(getTitle(), false);
        Navegador.Dispatcher("Iniciar Sesión", true);
        return null;
        
    } 
    
    public void generarMenuBar(BaseUsuario u) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(1, 0));
        Estilos.colorbarramenu(menuBar);

        String[] secciones = { "Pelicula", "Actor", "Director", "Cine", "Distribuidor", "Genero" };

        for (String seccion : secciones) {
            JMenu menu = new JMenu(seccion);

            if (u.getPermiso() == 0) {
                addMenuItems(menu, new String[] { "Buscar", "Ver" }, u);
            } else {
                addMenuItems(menu, new String[] { "Añadir", "Eliminar", "Buscar", "Ver" }, u);
            }

            Estilos.colormenu(menu);
            menuBar.add(menu);
        }

        setJMenuBar(menuBar);
    }
    
}
