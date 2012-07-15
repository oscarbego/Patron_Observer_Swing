package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Conexion extends JPanel {

    
    private String aDataBase = "database";
    private String aUsu = "root";
    private String aPass = "root";
    private String aServer = "localhost";    
    private Connection connection;
    private boolean axConect;
    
    public Conexion() {
        super();
        this.setSize(20, 20);
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }

    public Connection makeConn() {
        return makeConn(getaServer(), getaDataBase(), getaUsu(), getaPass());
    }

    public Connection makeConn(String Server, String Db, String Usu, String Pass) 
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            setConnection(DriverManager.getConnection(
                          "jdbc:mysql://" + Server + "/" + Db + "?user=" + Usu + "&password=" + Pass));            
        } catch (SQLException ex) {            
            System.out.println("Error SQL " + ex.getMessage());            
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("Error Class " + ex.getMessage());
            return null;
        }
        
        return connection;
    }

 
    public boolean isaxConectado() {
        return axConect;
    }    
    public void setaxConectado(boolean axConect) {
                this.axConect = axConect;
                
        if (axConect == true && this.makeConn() == null) {
            JOptionPane.showMessageDialog(this, "Imposible Conectar");

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    setaxConectado(false);
                }
            });
        }
    }

    
    
    public void setConn(Connection conn) {
        this.setConnection(conn);
    }
    public Connection getConn() {
        return getConnection();
    }    

    
    public String getaUsu() {
        return aUsu;
    }
    public void setaUsu(String usu) {
        this.aUsu = usu;
    }


    public String getaPass() {
        return aPass;
    }
    public void setaPass(String pass) {
        this.aPass = pass;
    }

    
    public String getaServer() {
        return aServer;
    }
    public void setaServer(String server) {
        this.aServer = server;
    }

    
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

     
    
    public String getaDataBase() {
        return aDataBase;
    }
    public void setaDataBase(String aDataBase) {
        this.aDataBase = aDataBase;
    }
 
}
