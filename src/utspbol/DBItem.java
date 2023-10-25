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
public class DBItem {
    private ItemModel dt=new ItemModel();    
    public ItemModel getItemModel(){return(dt);}
    public void setItemModel(ItemModel s){dt=s;}
    
    public ObservableList<ItemModel> Load() {
        try {
            ObservableList<ItemModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT iditem, nameitem, amountitem FROM item");
            
            int i = 1;
            while (rs.next()) {
                ItemModel d=new ItemModel();
                d.setIdItem(rs.getString("iditem"));                
                d.setNameItem(rs.getString("nameitem"));
                d.setAmountItem(rs.getString("amountitem"));

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
            ResultSet rs = con.statement.executeQuery("SELECT count(*) AS jml FROM item WHERE iditem = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO item (iditem, nameitem, amountitem) VALUES (?,?,?)");
            con.preparedStatement.setString(1, getItemModel().getIdItem());           
            con.preparedStatement.setString(2, getItemModel().getNameItem());
            con.preparedStatement.setString(3, getItemModel().getAmountItem());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM item WHERE iditem  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE item SET nameitem = ?, amountitem = ? WHERE iditem = ? ");
            con.preparedStatement.setString(1, getItemModel().getNameItem());
            con.preparedStatement.setString(2, getItemModel().getAmountItem());
            con.preparedStatement.setString(3, getItemModel().getIdItem());
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
