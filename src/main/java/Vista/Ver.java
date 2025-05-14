package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Estilos;
import Controlador.CONEXION;
import Controlador.Navegador;

import java.awt.*;
import java.sql.*;

public class Ver extends JFrame {
	
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public Ver(String item) {
        
        setTitle("Ver " + item);
        
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        Estilos.colorpanel(contentPane);
        contentPane.setLayout(null);


        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla);

        Estilos.estilizarTabla(tabla);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(10, 10, 564, 300);
        contentPane.add(scrollPane);


        JButton btnRegresar = new JButton("Regresar al Menú");
        btnRegresar.setBounds(444, 320, 130, 30);
        Estilos.colorboton(btnRegresar);

        btnRegresar.addActionListener(e -> {
            Navegador.Dispatcher("Principal", true);
            dispose();
        });
        contentPane.add(btnRegresar);
        cargarDatosDesdeDBDinamico(item);
    }


    public void cargarDatosDesdeDBDinamico(String item) {
        String query = obtenerQuery(item);
        if (query == null) {
            JOptionPane.showMessageDialog(this, "No hay datos para mostrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try (Connection conn = CONEXION.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Obtenemos los datos (nombres de columnas, etc)
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            //Creamos un array con los nombres de las columnas
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnLabel(i);
            }
            
            // Le decimos al modelo de la tabla cómo se van a llamar las columnas
            modeloTabla.setColumnIdentifiers(columnNames);

            limpiarTabla();

            // Recorremos todas las filas del ResultSet
            while (rs.next()) {
                //Creamos un array para meter los datos de cada fila
                Object[] fila = new Object[columnCount];
                // Rellenamos la fila con los datos de cada columna
                for (int i = 1; i <= columnCount; i++) {
                    fila[i - 1] = rs.getObject(i);
                }
                // Añadimos la fila al modelo de la tabla
                agregarFila(fila);
            }

        } catch (SQLException e) {
            // Si hay cualquier fallo con la base de datos, mostramos un mensaje de error
            JOptionPane.showMessageDialog(this, "Error cargando datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método que devuelve la query SQL según el "item" que queremos ver
    private String obtenerQuery(String item) {
        switch (item) {
            case "Pelicula":
                return "SELECT id_peli AS ID, titulo AS Título, duracion AS Duración, clasificacion AS Clasificación, f_estreno AS Estreno, presupuesto AS Presupuesto, recaudacion AS Recaudación FROM pelicula";
            case "Actor":
                return "SELECT a.cod_p AS ID, p.nombre AS Nombre, p.apellidos AS Apellidos, a.n_premios AS Premios, a.n_actuaciones AS Actuaciones FROM actor a JOIN persona p ON a.cod_p = p.cod_p";
            case "Director":
                return "SELECT d.cod_p AS ID, p.nombre AS Nombre, p.apellidos AS Apellidos, d.n_premios AS Premios, d.n_direcciones AS Direcciones FROM director d JOIN persona p ON d.cod_p = p.cod_p";
            case "Cine":
                return "SELECT cod_cine AS ID, nombre AS Nombre, direccion AS Dirección, telefono AS Teléfono FROM cine";
            case "Distribuidor":
                return "SELECT id_distribuidora AS ID, nombre AS Nombre, patrimonio AS Patrimonio FROM distribuidora";
            case "Genero":
                return "SELECT cod_genero AS ID, nombre AS Nombre, descripcion AS Descripción FROM genero";
            default:
                // Si no hay coincidencia, devolvemos null
                return null;
        }
    }

    // Método que añade una fila al modelo de la tabla
    private void agregarFila(Object[] fila) {
        modeloTabla.addRow(fila);
    }

    // Método que limpia todas las filas de la tabla
    private void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }
    
    public void recargarTabla() {
        cargarDatosDesdeDBDinamico(this.getTitle().replace("Ver ", ""));
    }
}
