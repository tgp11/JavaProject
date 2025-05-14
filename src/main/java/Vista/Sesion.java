package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controlador.Navegador;
import Gestion.Administrador;
import Gestion.BaseUsuario;
import Gestion.Usuario;

import java.awt.*;
import Modelo.Estilos;

public class Sesion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public Sesion() {
        setTitle("Iniciar Sesión");
        Navegador.CrearVentana(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        Estilos.colorpanel(contentPane);
        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("Inicio de Sesión", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Panel central con etiquetas y campos
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        Estilos.colorpanel(formPanel);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        txtUsuario = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(Color.WHITE);
        txtContrasena = new JPasswordField();

        formPanel.add(lblUsuario);
        formPanel.add(txtUsuario);
        formPanel.add(lblContrasena);
        formPanel.add(txtContrasena);

        contentPane.add(formPanel, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        Estilos.colorpanel(buttonPanel);

        JButton btnIniciar = new JButton("Iniciar Sesión");
        JButton btnCambiar = new JButton("Cambiar Contraseña");

        btnIniciar.setPreferredSize(new Dimension(140, 25));
        btnCambiar.setPreferredSize(new Dimension(160, 25));

        Estilos.colorboton(btnIniciar);
        Estilos.colorboton(btnCambiar);

        // Acción botón Iniciar Sesión
        btnIniciar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            char[] contrasena = txtContrasena.getPassword();

            Administrador a = new Administrador("Antonio", "1234");
            Usuario prueba = new Usuario("Paco", "1234");

            Administrador.usuarios.add(prueba);
            Administrador.usuarios.add(a);

            BaseUsuario si = buscaruser(usuario, String.valueOf(contrasena));
            if (si != null) {
                txtUsuario.setText("");
                txtContrasena.setText("");
                Estilos.configurarDialogosUI();

                JOptionPane.showMessageDialog(this, "Contraseña CORRECTA", "Verificado", JOptionPane.INFORMATION_MESSAGE);

                Menu menuVentana;
                JFrame posibleMenu = Navegador.getVentana("Principal");

                if (posibleMenu != null && posibleMenu instanceof Menu) {
                    menuVentana = (Menu) posibleMenu;
                    menuVentana.generarMenuBar(si);
                } else {
                    menuVentana = new Menu(si);
                }

                Navegador.Dispatcher(menuVentana.getTitle(), true);
                Navegador.Dispatcher(this.getTitle(), false);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // (btnCambiar podrías añadir su funcionalidad aquí si la necesitas)

        buttonPanel.add(btnCambiar);
        buttonPanel.add(btnIniciar);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Sesion().setVisible(true));
    }

    public BaseUsuario buscaruser(String usuario, String contrasena) {
        for (BaseUsuario u : Administrador.usuarios) {
            if (usuario.equals(u.getNombreUsuario()) && contrasena.equals(u.getContrasena())) {
                return u;
            }
        }
        return null;
    }
}
