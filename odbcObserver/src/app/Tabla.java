package app;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Observable;
//import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Tabla extends JTable implements java.util.Observer {

    private DefaultTableModel modelo = new DefaultTableModel();
   
    public Tabla() 
    {
        super();
        setModel(modelo);
    }

    @Override
    public void update(Observable o, Object arg) {
        
        if( arg instanceof ResultSet)
        {
            try {
                    ResultSet rs = (ResultSet) arg;
                    construirModelo(rs);
                } 
            catch (SQLException ex) 
                {
                    Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
                }
        }           
    }
    
    
    public void construirModelo(ResultSet rs) throws SQLException
    {
        ResultSetMetaData rsMd = rs.getMetaData();
        int nColumnas = rsMd.getColumnCount();
        modelo = new DefaultTableModel();
        
        for (int i = 1; i <= nColumnas; i++) {            
            modelo.addColumn(rsMd.getColumnLabel(i));
        }
        
        while (rs.next()) {
            Object[] fila = new Object[nColumnas];
            for (int i = 0; i < nColumnas; i++) {
                fila[i]=rs.getObject(i+1);
            }
            modelo.addRow(fila);
        }
        
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               setModel(modelo);
            }
        });                
    }

    
    public ProveedorDatos getaProveedorDatos() {
        return null;
    }
    public void setaProveedorDatos(ProveedorDatos proveedorDatos) {
        proveedorDatos.addObserver(this);
    }

    
    public DefaultTableModel getaModelo() {
        return modelo;
    }
    public void setaModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    
}
