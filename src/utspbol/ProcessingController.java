/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProcessingController implements Initializable {
    
    DBRaw dtraw = new DBRaw();
    DBProc dtproc = new DBProc();
    public int pilihBarisRaw;
    public int pilihBarisProc;
    boolean editdataRaw = false;
    boolean editdataProc = false;

    @FXML
    private TableView<RawModel> tbvraw;
    @FXML
    private TableColumn<RawModel, String> colidraw;
    @FXML
    private TableColumn<RawModel, String> colnameraw;
    @FXML
    private TableColumn<RawModel, String> colamountraw;
    @FXML
    private Label lblstatus;
    @FXML
    private Label lblexpl;
    @FXML
    private Label rawname;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnProc;
    @FXML
    private TableView<ProcModel> tbvproc;
    @FXML
    private TableColumn<ProcModel, String> colidproc;
    @FXML
    private TableColumn<ProcModel, String> colnameproc;
    @FXML
    private TableColumn<ProcModel, String> colamountproc;

    public int getPilihBarisRaw() {
        return pilihBarisRaw;
    }

    public void setPilihBarisRaw(int pilihBarisRaw) {
        this.pilihBarisRaw = pilihBarisRaw;
    }

    public int getPilihBarisProc() {
        return pilihBarisProc;
    }

    public void setPilihBarisProc(int pilihBarisProc) {
        this.pilihBarisProc = pilihBarisProc;
    }

    public void showdata(){
        ObservableList<RawModel> dataRaw = dtraw.Load();
        ObservableList<ProcModel> dataProc = dtproc.Load();
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
    }   

    @FXML
    private void doProcClick(ActionEvent event) {
        RawModel rm = new RawModel();
        ProcModel pm = new ProcModel();
        dtraw.setRawModel(rm);
        dtproc.setProcModel(pm);
        
        setPilihBarisRaw(tbvraw.getSelectionModel().selectedIndexProperty().intValue());
        setPilihBarisProc(tbvproc.getSelectionModel().selectedIndexProperty().intValue());
        
        if(getPilihBarisRaw()!= -1 && getPilihBarisProc()!= -1) {
            rm.setIdRaw(tbvraw.getSelectionModel().getSelectedItem().getIdRaw());
            rm.setNameRaw(tbvraw.getSelectionModel().getSelectedItem().getNameRaw());
            int amountRaw = Integer.valueOf(tbvraw.getSelectionModel().getSelectedItem().getAmountRaw());
            
            pm.setIdProc(tbvproc.getSelectionModel().getSelectedItem().getIdProc());
            pm.setNameProc(tbvproc.getSelectionModel().getSelectedItem().getNameProc());
            int amountProc = Integer.valueOf(tbvproc.getSelectionModel().getSelectedItem().getAmountProc());
            
        if(!rm.getIdRaw().isEmpty()){
          editdataRaw=true;
        }
        if(!pm.getIdProc().isEmpty()){
          editdataProc=true;
        }
            if(amountRaw > 0) {
                boolean processable = false;

                int kodePasanganRaw = 0;
                int kodePasanganProc = 0;
                switch(getPilihBarisProc()) {
                    case 0 : kodePasanganProc = 1; break;
                    case 1 : kodePasanganProc = 2; break;
                    case 2 : kodePasanganProc = 3; break;
                    case 3 : kodePasanganProc = 4; break;
                    case 4 : kodePasanganProc = 5; break;
                    case 5 : kodePasanganProc = 6; break;
                    case 6 : kodePasanganProc = 7; break;
                    case 7 : kodePasanganProc = 8; break;
                }

                switch(getPilihBarisRaw()) {
                    case 0 : processable = true; kodePasanganRaw = 1; break;
                    case 1 : processable = true; kodePasanganRaw = 2; break;
                    case 2 : processable = true; kodePasanganRaw = 3; break;
                    case 3 : processable = true; kodePasanganRaw = 4; break;
                    case 4 : processable = true; kodePasanganRaw = 5; break;
                    case 5 : processable = true; kodePasanganRaw = 6; break;
                    case 6 : processable = true; kodePasanganRaw = 7; break;
                    case 7 : processable = true; kodePasanganRaw = 8; break;
                    case 8 : processable = false; break;
                    case 9 : processable = false; break;
                    case 10 : processable = false; break;
                }

                if(processable) {
                    Random rnd = new Random();
                    int nilaiRandom = rnd.nextInt(2);

                    if(nilaiRandom == 0) {
                        lblstatus.setText("Process Fail!");
                        rawname.setText("");
                        lblexpl.setText("");
                        rm.setAmountRaw(String.valueOf(amountRaw-1));
                        btnProc.setDisable(true);
                    } else {
                        if(kodePasanganRaw == kodePasanganProc) {
                            lblstatus.setText("Process Success!");
                            rawname.setText(pm.getNameProc());
                            lblexpl.setText("has added to inventory");
                            rm.setAmountRaw(String.valueOf(amountRaw-1));
                            pm.setAmountProc(String.valueOf(amountProc+1));
                        } else {
                            lblstatus.setText("Invalid material");
                        }
                        btnProc.setDisable(true);
                    }
                } else if(!processable) {
                    lblstatus.setText("This material can't be processed");
                    rawname.setText("");
                    lblexpl.setText("");
                }
            } else {
                lblstatus.setText("Run out of material");
            }
        } else {
            lblstatus.setText("Select a Material!");
        }
        
        if(editdataRaw){
            if(dtraw.update()){
            } else {}
        }else if(dtraw.validasi(rm.getIdRaw())<=0){
            if(dtraw.insert()){
            }else {}
        }else{}
        
        if(editdataProc){
            if(dtproc.update()){
            } else {}
        }else if(dtproc.validasi(pm.getIdProc())<=0){
            if(dtproc.insert()){
            }else {}
        }else{}
    }
    
    @FXML
    private void backClick(ActionEvent event) {
        btnBack.getScene().getWindow().hide();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }
}
