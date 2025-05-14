package Vista;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import Controlador.CONEXION;
import Controlador.Navegador;
import Modelo.Estilos;

public class Anadir extends JFrame {

    private JPanel contentPane;

    private String tipoEntidad;

    private HashMap<String, JTextField> campos = new HashMap<>();

    public Anadir(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
        setTitle("Añadir " + tipoEntidad);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        Estilos.colorpanel(contentPane);
        setContentPane(contentPane);

        // Crear un título bonito arriba
        JLabel titulo = new JLabel("Formulario para añadir " + tipoEntidad, JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        contentPane.add(titulo, BorderLayout.NORTH);

        JPanel formularioPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        Estilos.colorpanel(formularioPanel);

        // Llamar al método que crea automáticamente los campos según la clase del modelo
        generarCamposDinamicamente(tipoEntidad, formularioPanel);
        //Meter el formulario en el panel principal (en el centro)
        contentPane.add(formularioPanel, BorderLayout.CENTER);

        // Panel para los botones (abajo del todo)
        JPanel botonesPanel = new JPanel(new BorderLayout());
        Estilos.colorpanel(botonesPanel);

        // Botón para volver atrás
        JButton btnVolver = new JButton("Volver");
        Estilos.colorboton(btnVolver);
        btnVolver.addActionListener(e -> {
            Navegador.Dispatcher("Principal", true);
            dispose();  
        });
        botonesPanel.add(btnVolver, BorderLayout.WEST);

        JButton btnGuardar = new JButton("Guardar " + tipoEntidad);
        Estilos.colorboton(btnGuardar);
        btnGuardar.addActionListener(e -> guardarDatos());
        botonesPanel.add(btnGuardar, BorderLayout.EAST);

        contentPane.add(botonesPanel, BorderLayout.SOUTH);
    }

    private void generarCamposDinamicamente(String tipoEntidad, JPanel panel) {
        try {
            // Obtener la clase del modelo (ej: Modelo.Cliente, Modelo.Producto, etc.)
            String claseFullName = "Modelo." + tipoEntidad;
            Class<?> clase = Class.forName(claseFullName);

            // Recorrer todos los atributos de la clase (id, nombre, precio, etc.)
            for (Field field : clase.getDeclaredFields()) {
                String nombreCampo = field.getName();

                // Crear etiqueta (ej: "nombre:")
                JLabel lbl = new JLabel(nombreCampo + ":");
                lbl.setForeground(Color.WHITE);

                // Crear la caja de texto donde el usuario escribirá el valor
                JTextField txt = new JTextField();

                // Añadir al panel del formulario
                panel.add(lbl);
                panel.add(txt);

                // Guardar la referencia del campo de texto en el HashMap (clave: nombre del campo)
                campos.put(nombreCampo.toLowerCase(), txt);
            }

        } catch (ClassNotFoundException e) {
            // Si la clase no existe, mostrar un error y cerrar la ventana
            JOptionPane.showMessageDialog(this, "Clase no encontrada: " + tipoEntidad, "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }

    // Método para guardar los datos en la base de datos (INSERT en SQL)
    public void guardarDatos() {
        try {
            StringBuilder columnas = new StringBuilder();  
            StringBuilder valores = new StringBuilder();

            // Obtener la clase del modelo (igual que antes)
            String claseFullName = "Modelo." + tipoEntidad;
            Class<?> clase = Class.forName(claseFullName);

            // Por cada campo en la clase
            for (Field field : clase.getDeclaredFields()) {
            	
                String nombreCampo = field.getName().toLowerCase();
                
                if (nombreCampo.equals("id")) continue;

                columnas.append(nombreCampo).append(",");
                valores.append("?").append(",");
            }
            if (columnas.length() > 0) columnas.setLength(columnas.length() - 1);
            if (valores.length() > 0) valores.setLength(valores.length() - 1);

            String query = "INSERT INTO " + tipoEntidad.toLowerCase() + " (" + columnas + ") VALUES (" + valores + ")";

            try (Connection conn = CONEXION.getConexion();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                int index = 1;  // Para llevar la cuenta de los ?

                // Recorremos otra vez los atributos para obtener sus valores desde los campos de texto
                for (Field field : clase.getDeclaredFields()) {
                    String nombreCampo = field.getName().toLowerCase();

                    // Otra vez, saltamos el ID
                    if (nombreCampo.equals("id")) continue;

                    // Obtenemos el texto que escribió el usuario en ese campo
                    String valor = campos.get(nombreCampo).getText().trim();

                    // Si está vacío, ponemos NULL en la base de datos
                    if (valor.isEmpty()) {
                        stmt.setNull(index++, java.sql.Types.NULL);
                        continue;
                    }

                    // Dependiendo del tipo del atributo, metemos el dato correctamente
                    Class<?> tipo = field.getType();

                    if (tipo == String.class) {
                        stmt.setString(index++, valor);
                    } else if (tipo == int.class || tipo == Integer.class) {
                        stmt.setInt(index++, Integer.parseInt(valor));
                    } else if (tipo == double.class || tipo == Double.class) {
                        stmt.setDouble(index++, Double.parseDouble(valor));
                    } else if (tipo == java.math.BigDecimal.class) {
                        stmt.setBigDecimal(index++, new java.math.BigDecimal(valor));
                    } else if (tipo == java.sql.Date.class) {
                            // Intentar detectar si viene como DD-MM-YYYY y transformarlo
                        if (valor.matches("\\d{2}-\\d{2}-\\d{4}")) {
                            String[] partes = valor.split("-");
                            valor = partes[2] + "-" + partes[1] + "-" + partes[0];  // Reordenar a YYYY-MM-DD
                        }
                        stmt.setDate(index++, java.sql.Date.valueOf(valor));
                    } else {
                        stmt.setObject(index++, valor);
                    }
                }

                int filas = stmt.executeUpdate();
                
                if(Navegador.IsCreated("Ver "+tipoEntidad)) {
                	Ver ventanaVer = (Ver) Navegador.getVentana("Ver "+tipoEntidad);
                	ventanaVer.recargarTabla();
                }

                if (filas > 0) {
                    JOptionPane.showMessageDialog(this, tipoEntidad + " guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    Navegador.Dispatcher("Principal", true);
                    Navegador.Dispatcher(this.getTitle() , false);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo guardar.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error de formato: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
