package Modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Estilos {

    // Configura colores globales de menú y submenús
    public static void configurarColoresUI() {
        UIManager.put("Menu.selectionBackground", new Color(255, 85, 85)); // Hover del JMenu
        UIManager.put("Menu.selectionForeground", Color.WHITE);

        UIManager.put("MenuItem.selectionBackground", new Color(255, 85, 85)); // Hover del JMenuItem
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);

        UIManager.put("Menu.background", new Color(255, 59, 48));
        UIManager.put("Menu.foreground", Color.WHITE);

        UIManager.put("MenuItem.background", new Color(255, 59, 48));
        UIManager.put("MenuItem.foreground", Color.WHITE);
    }

    public static void colorpanel(JPanel p) {
        p.setBackground(new Color(34, 34, 34));
    }

    public static void colorbarramenu(JMenuBar p) {
        p.setBackground(new Color(28, 28, 28));
    }

    public static void colormenu(JMenu p) {
        p.setBackground(new Color(255, 59, 48));
        p.setForeground(Color.WHITE);
        p.setOpaque(true);
        p.setRolloverEnabled(true);

        // Aplicar estilo a cada subitem del menú
        for (int i = 0; i < p.getItemCount(); i++) {
            JMenuItem item = p.getItem(i);
            if (item != null) {
                estiloMenuItem(item);
            }
        }
    }

    public static void estiloMenuItem(JMenuItem item) {
        item.setBackground(new Color(255, 59, 48));
        item.setForeground(Color.WHITE);
        item.setOpaque(true);
        item.setBorderPainted(false);

        item.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                item.setBackground(new Color(255, 85, 85));
            }

            public void mouseExited(MouseEvent evt) {
                item.setBackground(new Color(255, 59, 48));
            }
        });
    }

    public static void colorboton(JButton p) {
        p.setBackground(new Color(255, 59, 48));
        p.setForeground(Color.WHITE);
        p.setFocusPainted(false);
        p.setBorderPainted(false);
        p.setContentAreaFilled(false);
        p.setOpaque(true);

        p.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                p.setBackground(new Color(255, 85, 85));
            }

            public void mouseExited(MouseEvent evt) {
                p.setBackground(new Color(255, 59, 48));
            }
        });
    }
    
    public static void configurarDialogosUI() {
        // Colores base
        Color fondoOscuro = new Color(34, 34, 34);
        Color rojoPrincipal = new Color(255, 59, 48);
        Color rojoHover = new Color(255, 85, 85);
        
        // Configuración general del JOptionPane
        UIManager.put("OptionPane.background", fondoOscuro);
        UIManager.put("Panel.background", fondoOscuro);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        
        // Botones del JOptionPane
        UIManager.put("Button.background", rojoPrincipal);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 15, 5, 15));
        UIManager.put("Button.select", rojoHover);
        
        // Campo de texto (para InputDialog)
        UIManager.put("TextField.background", new Color(50, 50, 50));
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("TextField.caretForeground", Color.WHITE);
        UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }
    
    public static void estilizarTabla(JTable tabla) {
        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(25);
        tabla.setBackground(new Color(50, 50, 50));
        tabla.setForeground(Color.WHITE);
        tabla.setGridColor(new Color(80, 80, 80));
        tabla.setSelectionBackground(new Color(255, 85, 85));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(255, 59, 48));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setReorderingAllowed(false);
    }
   
}
