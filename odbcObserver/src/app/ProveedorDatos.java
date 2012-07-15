package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class ProveedorDatos extends JPanel {

    private Conexion conecxion;     
    private Statement statement;
    private ResultSet resultSet;
    private PD pd = new PD();
    
    public ProveedorDatos() 
    {
        super();
        this.setSize(20, 20);
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
    }
   
    public void runSQL(String strSQL)
    {
        if (statement != null)        
            pd.runSQL(strSQL);            
    }
    
    public void addObserver(Observer o) {
        pd.addObserver(o);    
    }
    
    
    public ResultSet getaResultSet()
    {
        return resultSet;
    }    
    public void setaResultSet(ResultSet rs)
    {
        this.resultSet = rs;
    }


    public Conexion getaConecxion() {
        return conecxion;
    }
    public void setaConecxion(Conexion conecxion) {
        
        this.conecxion = conecxion;
        
        try {
                statement = (Statement) conecxion.getConn().createStatement();
            } 
        catch (SQLException ex) {
            System.out.println("SQL Error " + ex.getMessage());
        }        
    }

   
          //------------------ Inner Class ------------------------------
          class PD extends java.util.Observable
          {   
                public void runSQL(String strSQL)
                {
                    try {
                            resultSet = statement.executeQuery(strSQL);
                            setChanged();
                            notifyObservers(resultSet);            
                        } 
                    catch (SQLException ex) 
                        {
                            System.out.println("SQL Error " + ex.getMessage());
                        }
                }                 
          }
          //--------------------------------------------------------------
          
}
