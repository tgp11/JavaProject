package Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import Controlador.CONEXION;
import Controlador.Navegador;
import Modelo.Estilos;

public class Buscar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String tipoEntidad;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;

    public Buscar(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
        setTitle("Buscar " + tipoEntidad);

        // Configuración ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 15));
        Estilos.colorpanel(contentPane);

        // Panel superior
        JLabel lblTitulo = new JLabel("Buscador de " + tipoEntidad);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Tabla resultados
        modeloTabla = new DefaultTableModel();
        tablaResultados = new JTable(modeloTabla);
        Estilos.estilizarTabla(tablaResultados);
        JScrollPane scrollPane = new JScrollPane(tablaResultados);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelInferior.setOpaque(false);
        contentPane.add(panelInferior, BorderLayout.SOUTH);

        JButton btnBuscarNombre = new JButton("Buscar por Nombre");
        configurarBotonBusqueda(btnBuscarNombre, "nombre");
        panelInferior.add(btnBuscarNombre);

        JButton btnBuscarId = new JButton("Buscar por Identificador");
        configurarBotonBusqueda(btnBuscarId, "id");
        panelInferior.add(btnBuscarId);

        JButton btnRegresar = new JButton("Regresar al Menú");
        Estilos.colorboton(btnRegresar);
        btnRegresar.addActionListener(e -> dispose());
        panelInferior.add(btnRegresar);

        // Al cerrar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Navegador.Dispatcher("Principal", true);
            }
        });
    }

    private void configurarBotonBusqueda(JButton boton, String tipoBusqueda) {
        Estilos.colorboton(boton);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        boton.addActionListener(e -> {
            Estilos.configurarDialogosUI();

            String mensaje = tipoBusqueda.equals("nombre") ? "Introduzca el nombre:" : "Introduzca el identificador:";
            String titulo = tipoBusqueda.equals("nombre") ? "Búsqueda por nombre" : "Búsqueda por ID";

            String entrada = JOptionPane.showInputDialog(this, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);

            if (entrada != null && !entrada.trim().isEmpty()) {
                buscarEnBD(tipoBusqueda, entrada.trim());
            }
        });
    }

    private void buscarEnBD(String tipoBusqueda, String valor) {
        String query = "";
        String filtro = tipoBusqueda.equals("nombre") ? "nombre" : "id";

        switch (tipoEntidad) {
            case "Pelicula":
                query = filtro.equals("nombre")
                        ? "SELECT id_peli, titulo, clasificacion FROM pelicula WHERE LOWER(titulo) LIKE ?"
                        : "SELECT id_peli, titulo, clasificacion FROM pelicula WHERE id_peli = ?";
                break;
            case "Actor":
                query = filtro.equals("nombre")
                        ? "SELECT a.cod_p, p.nombre, p.apellidos FROM actor a JOIN persona p ON a.cod_p = p.cod_p WHERE LOWER(p.nombre) LIKE ?"
                        : "SELECT a.cod_p, p.nombre, p.apellidos FROM actor a JOIN persona p ON a.cod_p = p.cod_p WHERE a.cod_p = ?";
                break;
            case "Director":
                query = filtro.equals("nombre")
                        ? "SELECT d.cod_p, p.nombre, p.apellidos FROM director d JOIN persona p ON d.cod_p = p.cod_p WHERE LOWER(p.nombre) LIKE ?"
                        : "SELECT d.cod_p, p.nombre, p.apellidos FROM director d JOIN persona p ON d.cod_p = p.cod_p WHERE d.cod_p = ?";
                break;
            case "Cine":
                query = filtro.equals("nombre")
                        ? "SELECT cod_cine, nombre, direccion FROM cine WHERE LOWER(nombre) LIKE ?"
                        : "SELECT cod_cine, nombre, direccion FROM cine WHERE cod_cine = ?";
                break;
            case "Distribuidor":
                query = filtro.equals("nombre")
                        ? "SELECT id_distribuidora, nombre, patrimonio FROM distribuidora WHERE LOWER(nombre) LIKE ?"
                        : "SELECT id_distribuidora, nombre, patrimonio FROM distribuidora WHERE id_distribuidora = ?";
                break;
            case "Genero":
                query = filtro.equals("nombre")
                        ? "SELECT cod_genero, nombre, descripcion FROM genero WHERE LOWER(nombre) LIKE ?"
                        : "SELECT cod_genero, nombre, descripcion FROM genero WHERE cod_genero = ?";
                break;
            default:
                JOptionPane.showMessageDialog(this, "Entidad no reconocida", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        try (Connection conn = CONEXION.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (tipoBusqueda.equals("nombre")) {
                stmt.setString(1, "%" + valor.toLowerCase() + "%");
            } else {
                stmt.setInt(1, Integer.parseInt(valor));
            }

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Columnas dinámicas
            String[] columnas = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnas[i - 1] = metaData.getColumnLabel(i);
            }
            modeloTabla.setColumnIdentifiers(columnas);

            // Limpiar y cargar datos
            modeloTabla.setRowCount(0);
            while (rs.next()) {
                Object[] fila = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    fila[i - 1] = rs.getObject(i);
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
