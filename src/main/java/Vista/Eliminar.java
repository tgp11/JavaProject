package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controlador.Navegador;
import Modelo.Estilos;
import Controlador.CONEXION;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Eliminar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnBuscarNombre;
    private JButton btnSeleccionarTabla;

    public Eliminar(String item) {
        setTitle("Eliminar " + item);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        Estilos.colorpanel(contentPane);
        contentPane.setLayout(null);

        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBounds(15, 15, 454, 35);
        panelTitulo.setOpaque(false);
        contentPane.add(panelTitulo);

        JLabel lblTitulo = new JLabel("Seleccione método de eliminación");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        // Panel central con botones
        JPanel panelOpciones = new JPanel();
        panelOpciones.setBounds(15, 55, 454, 205);
        panelOpciones.setOpaque(false);
        panelOpciones.setLayout(null);
        contentPane.add(panelOpciones);

        // Botón Buscar por Nombre
        btnBuscarNombre = new JButton("Buscar por Nombre");
        btnBuscarNombre.setBounds(0, 0, 454, 92);
        configurarBoton(btnBuscarNombre);
        btnBuscarNombre.addActionListener(e -> buscarPorNombre());
        panelOpciones.add(btnBuscarNombre);

        // Botón Seleccionar de Lista
        btnSeleccionarTabla = new JButton("Seleccionar de la Lista");
        btnSeleccionarTabla.setBounds(0, 103, 454, 92);
        configurarBoton(btnSeleccionarTabla);
        btnSeleccionarTabla.addActionListener(e -> abrirListaVer());
        panelOpciones.add(btnSeleccionarTabla);

        // Panel inferior con botón Regresar
        JPanel panelInferior = new JPanel();
        panelInferior.setBounds(15, 259, 454, 41);
        panelInferior.setOpaque(false);
        panelInferior.setLayout(null);
        contentPane.add(panelInferior);

        JButton btnRegresar = new JButton("Regresar al Menú");
        btnRegresar.setBounds(334, 0, 120, 35);
        Estilos.colorboton(btnRegresar);
        btnRegresar.addActionListener(e -> {
            Navegador.Dispatcher("Principal", true);
            dispose();
        });
        panelInferior.add(btnRegresar);
    }

    private void configurarBoton(JButton boton) {
        Estilos.colorboton(boton);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        boton.setPreferredSize(new Dimension(300, 60));
    }

    private void buscarPorNombre() {
        String nombre = JOptionPane.showInputDialog(
                this,
                "Introduzca el nombre a eliminar:",
                "Eliminar por Nombre",
                JOptionPane.PLAIN_MESSAGE
        );

        if (nombre != null && !nombre.trim().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que desea eliminar \"" + nombre + "\"?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String tipoEntidad = getTitle().replace("Eliminar ", "").toLowerCase();
                String query = "DELETE FROM " + tipoEntidad + " WHERE nombre = ?";

                try (Connection conn = CONEXION.getConexion();
                     PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setString(1, nombre);

                    int filas = stmt.executeUpdate();

                    if (filas > 0) {
                        JOptionPane.showMessageDialog(this, "Eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                        // Recargar ventana "Ver [Entidad]" si está abierta
                        String ventanaNombre = "Ver " + getTitle().replace("Eliminar ", "");
                        Object ventanaObj = Navegador.getVentana(ventanaNombre);

                        if (ventanaObj instanceof Vista.Ver) {
                            Vista.Ver ventana = (Vista.Ver) ventanaObj;
                            ventana.recargarTabla();
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró ningún registro con ese nombre.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void abrirListaVer() {
        String[] columnas = {"ID", "Nombre", "Detalle"};
        Object[][] datos = {}; // Datos vacíos por defecto

        JFrame frameLista = new JFrame("Seleccione registro a eliminar");
        frameLista.setSize(600, 400);
        frameLista.setLocationRelativeTo(this);

        JTable tabla = new JTable(datos, columnas);
        Estilos.estilizarTabla(tabla);

        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        Estilos.colorboton(btnEliminar);
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int id = (int) tabla.getValueAt(filaSeleccionada, 0);
                System.out.println("Eliminando registro con ID: " + id);
                frameLista.dispose();
            } else {
                JOptionPane.showMessageDialog(frameLista, "Seleccione un registro primero");
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        Estilos.colorpanel(panel);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        Estilos.colorpanel(panelBoton);
        panelBoton.add(btnEliminar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        frameLista.setContentPane(panel);
        frameLista.setVisible(true);
    }
}
