package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CONEXION {
    private static final String URL = "jdbc:postgresql://localhost:5432/CINE";
    private static final String USER = "postgres"; //
    private static final String PASSWORD = "postgres";

    public static Connection getConexion() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conectado a PostgreSQL correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a PostgreSQL: " + e.getMessage());
        }
        return conn;
    }
}
