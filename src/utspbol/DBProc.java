/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class DBProc {
    private ProcModel dt=new ProcModel();    
    public ProcModel getProcModel(){return(dt);}
    public void setProcModel(ProcModel s){dt=s;}
    
    public ObservableList<ProcModel> Load() {
        try {
            ObservableList<ProcModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT idproc, nameproc, amountproc FROM processedmaterial");
            
            int i = 1;
            while (rs.next()) {
                ProcModel d=new ProcModel();
                d.setIdProc(rs.getString("idproc"));                
                d.setNameProc(rs.getString("nameproc"));
                d.setAmountProc(rs.getString("amountproc"));
                
                tableData.add(d);
                i++;            
            }
            
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();            
            return null;        
        }
    }
    
    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT count(*) AS jml FROM processedmaterial WHERE idproc = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO processedmaterial (idproc, nameproc, amountproc) VALUES (?,?,?)");
            con.preparedStatement.setString(1, getProcModel().getIdProc());           
            con.preparedStatement.setString(2, getProcModel().getNameProc());
            con.preparedStatement.setString(3, getProcModel().getAmountProc());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM processedmaterial WHERE idproc  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE processedmaterial SET nameproc = ?, amountproc = ? WHERE  idproc = ? ");
            con.preparedStatement.setString(1, getProcModel().getNameProc());
            con.preparedStatement.setString(2, getProcModel().getAmountProc());
            con.preparedStatement.setString(3, getProcModel().getIdProc());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {
            con.tutupKoneksi();            
            return berhasil;        
        }    
    }
}
