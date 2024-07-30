
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtils {
    String db = "actividad_1";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "dahianna";
    String password = "cjX)C_dokwBVi7On";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;

    public DatabaseUtils() {
    }
    
    // Método para iniciar conexión con base de datos
    public Connection conectar() {
        try {
            Class.forName(driver);
            cx=DriverManager.getConnection(url+db, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se conectó a la base de datos.");
        }
        return cx;
    }
    
    // Método para terminar conexión con base de datos
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Desconectado de la base de datos");
        }
    }
    
    public static void main(String[] args) {
        DatabaseUtils conexion = new DatabaseUtils();
        conexion.conectar();
    }

}
