/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package utspbol;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author David Beckham - 2022130019
 */
public class RPGCraftItemController implements Initializable {
    
    DBRaw dtraw = new DBRaw();
    DBProc dtproc = new DBProc();
    DBItem dtitem = new DBItem();

    @FXML
    private TableView<RawModel> tbvraw;
    @FXML
    private TableView<ProcModel> tbvproc;
    @FXML
    private TableView<ItemModel> tbvitem;
    @FXML
    private TableColumn<RawModel, String> colidraw;
    @FXML
    private TableColumn<RawModel, String> colnameraw;
    @FXML
    private TableColumn<RawModel, String> colamountraw;
    @FXML
    private TableColumn<ProcModel, String> colidproc;
    @FXML
    private TableColumn<ProcModel, String> colnameproc;
    @FXML
    private TableColumn<ProcModel, String> colamountproc;
    @FXML
    private TableColumn<ItemModel, String> coliditem;
    @FXML
    private TableColumn<ItemModel, String> colnameitem;
    @FXML
    private TableColumn<ItemModel, String> colamountitem;
    @FXML
    private Label label;
    
    @FXML
    public void showdata(){
        ObservableList<RawModel> dataRaw = dtraw.Load();
        ObservableList<ProcModel> dataProc = dtproc.Load();
        ObservableList<ItemModel> dataItem = dtitem.Load();
        
        if(dataRaw!=null){
            colidraw.setCellValueFactory(rawData -> new SimpleStringProperty(rawData.getValue().getIdRaw()));
            colnameraw.setCellValueFactory(rawData -> new SimpleStringProperty(rawData.getValue().getNameRaw()));
            colamountraw.setCellValueFactory(rawData -> new SimpleStringProperty(rawData.getValue().getAmountRaw()));
            tbvraw.setItems(dataRaw);
        } else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvraw.getScene().getWindow().hide();
        }
        
        if(dataProc!=null){
            colidproc.setCellValueFactory(procData -> new SimpleStringProperty(procData.getValue().getIdProc()));
            colnameproc.setCellValueFactory(procData -> new SimpleStringProperty(procData.getValue().getNameProc()));
            colamountproc.setCellValueFactory(procData -> new SimpleStringProperty(procData.getValue().getAmountProc()));
            tbvproc.setItems(dataProc);
        } else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvproc.getScene().getWindow().hide();
        }
        
        if(dataItem!=null){
            coliditem.setCellValueFactory(itemData -> new SimpleStringProperty(itemData.getValue().getIdItem()));
            colnameitem.setCellValueFactory(itemData -> new SimpleStringProperty(itemData.getValue().getNameItem()));
            colamountitem.setCellValueFactory(itemData -> new SimpleStringProperty(itemData.getValue().getAmountItem()));
            tbvitem.setItems(dataItem);
        } else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvitem.getScene().getWindow().hide();
        }
        
        tbvraw.getSelectionModel().selectFirst();
        
        
    }
    
    @FXML
    private void procClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Processing.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void craftClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Craft.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    

    @FXML
    private void deleteItem(ActionEvent event) {
        ItemModel im= new ItemModel();       
        im=tbvitem.getSelectionModel().getSelectedItem();
        
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Discard this item?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(dtitem.delete(im.getIdItem())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Item discard success!", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Item discard failed!", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();
        }
    }
}
