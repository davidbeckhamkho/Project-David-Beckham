/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CraftController implements Initializable {
    
    DBProc dtproc = new DBProc();
    DBProc dtproc1 = new DBProc();
    DBProc dtproc2 = new DBProc();
    DBItem dtitem = new DBItem();
    public int pilihBarisItem;
    public int pilihBarisProc;
    public int pilihBarisProc1;
    public int pilihBarisProc2;
    boolean editdataProc = false;
    boolean editdataProc1 = false;
    boolean editdataProc2 = false;
    boolean editdataItem = false;
    
    @FXML
    private Button btnCraft;
    @FXML
    private TableView<ProcModel> tbvproc;
    @FXML
    private TableColumn<ProcModel, String> colidproc;
    @FXML
    private TableColumn<ProcModel, String> colnameproc;
    @FXML
    private TableColumn<ProcModel, String> colamountproc;
    @FXML
    private TableView<ProcModel> tbvproc1;
    @FXML
    private TableColumn<ProcModel, String> colidproc1;
    @FXML
    private TableColumn<ProcModel, String> colnameproc1;
    @FXML
    private TableColumn<ProcModel, String> colamountproc1;
    @FXML
    private TableView<ProcModel> tbvproc2;
    @FXML
    private TableColumn<ProcModel, String> colidproc2;
    @FXML
    private TableColumn<ProcModel, String> colnameproc2;
    @FXML
    private TableColumn<ProcModel, String> colamountproc2;
    @FXML
    private Label lblstatus;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private TableView<ItemModel> tbvitem;
    @FXML
    private TableColumn<ItemModel, String> coliditem;
    @FXML
    private TableColumn<ItemModel, String> colnameitem;
    @FXML
    private TableColumn<ItemModel, String> colamountitem;
    @FXML
    private Label lblexpl;
    @FXML
    private Button btnBack;

    public int getPilihBarisProc() {
        return pilihBarisProc;
    }

    public void setPilihBarisProc(int pilihBarisProc) {
        this.pilihBarisProc = pilihBarisProc;
    }

    public int getPilihBarisProc1() {
        return pilihBarisProc1;
    }

    public void setPilihBarisProc1(int pilihBarisProc1) {
        this.pilihBarisProc1 = pilihBarisProc1;
    }

    public int getPilihBarisProc2() {
        return pilihBarisProc2;
    }

    public void setPilihBarisProc2(int pilihBarisProc2) {
        this.pilihBarisProc2 = pilihBarisProc2;
    }

    public int getPilihBarisItem() {
        return pilihBarisItem;
    }

    public void setPilihBarisItem(int pilihBarisItem) {
        this.pilihBarisItem = pilihBarisItem;
    }
    
    public void showData() {
        ObservableList<ProcModel> dataProc = dtproc.Load();
        ObservableList<ProcModel> dataProc1 = dtproc1.Load();
        ObservableList<ProcModel> dataProc2 = dtproc2.Load();
        ObservableList<ItemModel> dataItem = dtitem.Load();
        
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
        
        if(dataProc1!=null){
            colidproc1.setCellValueFactory(procData1 -> new SimpleStringProperty(procData1.getValue().getIdProc()));
            colnameproc1.setCellValueFactory(procData1 -> new SimpleStringProperty(procData1.getValue().getNameProc()));
            colamountproc1.setCellValueFactory(procData1 -> new SimpleStringProperty(procData1.getValue().getAmountProc()));
            tbvproc1.setItems(dataProc);
        } else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvproc1.getScene().getWindow().hide();
        }
        
        if(dataProc2!=null){
            colidproc2.setCellValueFactory(procData2 -> new SimpleStringProperty(procData2.getValue().getIdProc()));
            colnameproc2.setCellValueFactory(procData2 -> new SimpleStringProperty(procData2.getValue().getNameProc()));
            colamountproc2.setCellValueFactory(procData2 -> new SimpleStringProperty(procData2.getValue().getAmountProc()));
            tbvproc2.setItems(dataProc);
        } else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvproc2.getScene().getWindow().hide();
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
    }
    
    private void setExplText() {
        if(getPilihBarisItem() == 0) {
            lbl1.setText("-1 Stick");
            lbl2.setText("-1 Chiseled Stone");
            lbl3.setText("");
            lblstatus.setText("Craft Success!");
            lblexpl.setText("has added to inventory");
        } else if(getPilihBarisItem() == 1) {
            lbl1.setText("-1 Stick");
            lbl2.setText("-1 Chiseled Obsidian");
            lbl3.setText("");
            lblstatus.setText("Craft Success!");
            lblexpl.setText("has added to inventory");
        } else if(getPilihBarisItem() == 2) {
            lbl1.setText("-1 Stick");
            lbl2.setText("-1 Iron Blade");
            lbl3.setText("");
            lblstatus.setText("Craft Success!");
            lblexpl.setText("has added to inventory");
        } else if(getPilihBarisItem() == 3) {
            lbl1.setText("-5 Stick");
            lbl2.setText("-3 Feather");
            lbl3.setText("-3 Iron Blade");
            lblexpl.setText("has added to inventory");
            lblstatus.setText("Craft Success!");
        } else if(getPilihBarisItem() == 4) {
            lbl1.setText("-1 Gunhir Stone");
            lbl2.setText("-1 Embian Stone");
            lbl3.setText("-1 Minar Stone");
            lblstatus.setText("Craft Success!");
            lblexpl.setText("has added to inventory");
        }
    }
    
    @FXML
    private void doCraftClick(ActionEvent event) {
        ProcModel pm = new ProcModel();
        ProcModel pm1 = new ProcModel();
        ProcModel pm2 = new ProcModel();
        ItemModel im = new ItemModel();
        dtproc.setProcModel(pm);
        dtproc1.setProcModel(pm1);
        dtproc2.setProcModel(pm2);
        dtitem.setItemModel(im);
        
        setPilihBarisProc(tbvproc.getSelectionModel().selectedIndexProperty().intValue());
        setPilihBarisProc1(tbvproc1.getSelectionModel().selectedIndexProperty().intValue());
        setPilihBarisProc2(tbvproc2.getSelectionModel().selectedIndexProperty().intValue());
        setPilihBarisItem(tbvitem.getSelectionModel().selectedIndexProperty().intValue());
        
        if(getPilihBarisProc()!= -1 && getPilihBarisProc1()!= -1 && getPilihBarisProc2()!= -1 && getPilihBarisItem() != -1) {
            pm.setIdProc(tbvproc.getSelectionModel().getSelectedItem().getIdProc());
            pm.setNameProc(tbvproc.getSelectionModel().getSelectedItem().getNameProc());
            int amountProc = Integer.valueOf(tbvproc.getSelectionModel().getSelectedItem().getAmountProc());

            pm1.setIdProc(tbvproc1.getSelectionModel().getSelectedItem().getIdProc());
            pm1.setNameProc(tbvproc1.getSelectionModel().getSelectedItem().getNameProc());
            int amountProc1 = Integer.valueOf(tbvproc1.getSelectionModel().getSelectedItem().getAmountProc());

            pm2.setIdProc(tbvproc2.getSelectionModel().getSelectedItem().getIdProc());
            pm2.setNameProc(tbvproc2.getSelectionModel().getSelectedItem().getNameProc());
            int amountProc2 = Integer.valueOf(tbvproc2.getSelectionModel().getSelectedItem().getAmountProc());

            im.setIdItem(tbvitem.getSelectionModel().getSelectedItem().getIdItem());
            im.setNameItem(tbvitem.getSelectionModel().getSelectedItem().getNameItem());
            int amountItem = Integer.valueOf(tbvitem.getSelectionModel().getSelectedItem().getAmountItem());

            if(!pm.getIdProc().isEmpty()){
              editdataProc=true;
            }
            if(!pm1.getIdProc().isEmpty()){
              editdataProc1=true;
            }
            if(!pm2.getIdProc().isEmpty()){
              editdataProc2=true;
            }
            if(!im.getIdItem().isEmpty()){
              editdataItem=true;
            }
        
            if(amountProc > 0 && amountProc1 > 0 && amountProc2 > 0) {
                int kodeProc = 0;
                int kodeProc1 = 0;
                int kodeProc2 = 0;
                int kodeItem = 0;
                
                switch(getPilihBarisProc()) {
                    case 0 : kodeProc = 1; break;
                    case 1 : kodeProc = 2; break;
                    case 2 : kodeProc = 3; break;
                    case 3 : kodeProc = 4; break;
                    case 4 : kodeProc = 5; break;
                    case 5 : kodeProc = 6; break;
                    case 6 : kodeProc = 7; break;
                    case 7 : kodeProc = 8; break;
                    case 8 : kodeProc = 9; break;
                }
                
                switch(getPilihBarisProc1()) {
                    case 0 : kodeProc1 = 1; break;
                    case 1 : kodeProc1 = 2; break;
                    case 2 : kodeProc1 = 3; break;
                    case 3 : kodeProc1 = 4; break;
                    case 4 : kodeProc1 = 5; break;
                    case 5 : kodeProc1 = 6; break;
                    case 6 : kodeProc1 = 7; break;
                    case 7 : kodeProc1 = 8; break;
                    case 8 : kodeProc1 = 9; break;
                }
                
                switch(getPilihBarisProc2()) {
                    case 0 : kodeProc2 = 1; break;
                    case 1 : kodeProc2 = 2; break;
                    case 2 : kodeProc2 = 3; break;
                    case 3 : kodeProc2 = 4; break;
                    case 4 : kodeProc2 = 5; break;
                    case 5 : kodeProc2 = 6; break;
                    case 6 : kodeProc2 = 7; break;
                    case 7 : kodeProc2 = 8; break;
                    case 8 : kodeProc2 = 9; break;
                }
                
                switch(getPilihBarisItem()) {
                    case 0 : kodeItem = 1; break;
                    case 1 : kodeItem = 2; break;
                    case 2 : kodeItem = 3; break;
                    case 3 : kodeItem = 4; break;
                    case 4 : kodeItem = 5; break;
                }
                
                if(kodeItem == 1 && kodeProc == 1 && kodeProc1 == 2 && kodeProc2 == 9) {
                    setExplText();
                    pm.setAmountProc(String.valueOf(amountProc-1));
                    pm1.setAmountProc(String.valueOf(amountProc1-1));
                    pm2.setAmountProc(String.valueOf(amountProc2-1));
                    im.setAmountItem(String.valueOf(amountItem+1));
                } else if(kodeItem == 2 && kodeProc == 1 && kodeProc1 == 3 && kodeProc2 == 9){
                    setExplText();
                    pm.setAmountProc(String.valueOf(amountProc-1));
                    pm1.setAmountProc(String.valueOf(amountProc1-1));
                    pm2.setAmountProc(String.valueOf(amountProc2-1));
                    im.setAmountItem(String.valueOf(amountItem+1));
                } else if(kodeItem == 3 && kodeProc == 1 && kodeProc1 == 4 && kodeProc2 == 9){
                    setExplText();
                    pm.setAmountProc(String.valueOf(amountProc-1));
                    pm1.setAmountProc(String.valueOf(amountProc1-1));
                    pm2.setAmountProc(String.valueOf(amountProc2-1));
                    im.setAmountItem(String.valueOf(amountItem+1));
                } else if(kodeItem == 4 && kodeProc == 1 && kodeProc1 == 4 && kodeProc2 == 5){
                    setExplText();
                    pm.setAmountProc(String.valueOf(amountProc-1));
                    pm1.setAmountProc(String.valueOf(amountProc1-1));
                    pm2.setAmountProc(String.valueOf(amountProc2-1));
                    im.setAmountItem(String.valueOf(amountItem+1));
                } else if(kodeItem == 5 && kodeProc == 6 && kodeProc1 == 7 && kodeProc2 == 8){
                    setExplText();
                    pm.setAmountProc(String.valueOf(amountProc-1));
                    pm1.setAmountProc(String.valueOf(amountProc1-1));
                    pm2.setAmountProc(String.valueOf(amountProc2-1));
                    im.setAmountItem(String.valueOf(amountItem+1));
                } else {
                    lblstatus.setText("Combination Wrong!");
                    im.setAmountItem(String.valueOf(amountItem));
                }
                btnCraft.setDisable(true);
            } else {
                lblstatus.setText("Run out of material");
            }
        } else {
            lblstatus.setText("Select a Material!");
        }
        
        if(editdataProc){
            if(dtproc.update()){
            } else {}
        }else if(dtproc.validasi(pm.getIdProc())<=0){
            if(dtproc.insert()){
            }else {}
        }else{}
        
        if(editdataProc1){
            if(dtproc1.update()){
            } else {}
        }else if(dtproc1.validasi(pm.getIdProc())<=0){
            if(dtproc1.insert()){
            }else {}
        }else{}
        
        if(editdataProc2){
            if(dtproc2.update()){
            } else {}
        }else if(dtproc2.validasi(pm.getIdProc())<=0){
            if(dtproc2.insert()){
            }else {}
        }else{}
        
        if(editdataItem){
            if(dtitem.update()){
            } else {}
        }else if(dtitem.validasi(im.getIdItem())<=0){
            if(dtitem.insert()){
            }else {}
        }else{}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
    }

    @FXML
    private void backClick(ActionEvent event) {
        btnBack.getScene().getWindow().hide();
    }

}
