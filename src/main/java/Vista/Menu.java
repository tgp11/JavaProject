package Vista;

import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {
    private JPanel contentPane;
    
    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Tamaño más adecuado
        setLocationRelativeTo(null); // Centrar en pantalla
        
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        
        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();
        
        // Menú Películas
        JMenu peliculaMenu = new JMenu("Películas");
        addMenuItems(peliculaMenu, new String[]{"Añadir", "Eliminar", "Buscar", "Ver"});
        menuBar.add(peliculaMenu);
        
        // Menú Actores
        JMenu actorMenu = new JMenu("Actores");
        addMenuItems(actorMenu, new String[]{"Añadir", "Eliminar", "Buscar", "Ver"});
        menuBar.add(actorMenu);
        
        // Menú Directores
        JMenu directorMenu = new JMenu("Directores");
        addMenuItems(directorMenu, new String[]{"Añadir", "Eliminar", "Buscar", "Ver"});
        menuBar.add(directorMenu);
        
        // Menú Cines
        JMenu cineMenu = new JMenu("Cines");
        addMenuItems(cineMenu, new String[]{"Añadir", "Eliminar", "Buscar", "Ver"});
        menuBar.add(cineMenu);
        
        // Menú Distribuidoras
        JMenu distribuidorMenu = new JMenu("Distribuidoras");
        addMenuItems(distribuidorMenu, new String[]{"Añadir", "Eliminar", "Buscar", "Ver"});
        menuBar.add(distribuidorMenu);
        
        // Menú Géneros
        JMenu generoMenu = new JMenu("Géneros");
        addMenuItems(generoMenu, new String[]{"Añadir", "Eliminar", "Buscar", "Ver"});
        menuBar.add(generoMenu);
        
        setJMenuBar(menuBar);
        
        // Panel principal con botones
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton gestionButton = new JButton("GESTIONAR VALORACIONES");
        JButton fichajesButton = new JButton("ADMINISTRAR FICHAJES");
        JButton premiosButton = new JButton("PREMIOS");
        
        // Añadir acciones a los botones
        gestionButton.addActionListener(e -> abrirGestionValoraciones());
        fichajesButton.addActionListener(e -> abrirAdministrarFichajes());
        premiosButton.addActionListener(e -> abrirPremios());
        
        buttonPanel.add(gestionButton);
        buttonPanel.add(fichajesButton);
        buttonPanel.add(premiosButton);
        
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        
        // Barra de estado
        JLabel statusBar = new JLabel(" Sistema de gestión de cine - Listo");
        contentPane.add(statusBar, BorderLayout.SOUTH);
    }
    
    private void addMenuItems(JMenu menu, String[] items) {
        for (String item : items) {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(e -> handleMenuAction(menu.getText(), item));
            menu.add(menuItem);
        }
    }
    
    private void handleMenuAction(String menu, String item) {
        System.out.println("Seleccionado: " + menu + " > " + item);

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
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Menu frame = new Menu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}