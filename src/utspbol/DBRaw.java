/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class DBRaw {
    private RawModel dt=new RawModel();    
    public RawModel getRawModel(){return(dt);}
    public void setRawModel(RawModel s){dt=s;}
    
    public ObservableList<RawModel> Load() {
        try {
            ObservableList<RawModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT idraw, nameraw, amountraw FROM rawmaterial");
            
            int i = 1;
            while (rs.next()) {
                RawModel d=new RawModel();
                d.setIdRaw(rs.getString("idraw"));                
                d.setNameRaw(rs.getString("nameraw"));
                d.setAmountRaw(rs.getString("amountraw"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT count(*) AS jml FROM rawmaterial WHERE idraw = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO rawmaterial (idraw, nameraw, amountraw) VALUES (?,?,?)");
            con.preparedStatement.setString(1, getRawModel().getIdRaw());           
            con.preparedStatement.setString(2, getRawModel().getNameRaw());
            con.preparedStatement.setString(3, getRawModel().getAmountRaw());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM rawmaterial WHERE idraw  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE rawmaterial SET nameraw = ?, amountraw = ? WHERE  idraw = ? ");
            con.preparedStatement.setString(1, getRawModel().getNameRaw());
            con.preparedStatement.setString(2, getRawModel().getAmountRaw());
            con.preparedStatement.setString(3, getRawModel().getIdRaw());
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
