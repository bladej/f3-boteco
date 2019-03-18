
package botecofx;

import static botecofx.Botecofx.LimpaTela;
import botecofx.db.dal.DALComanda;
import botecofx.db.dal.DALGarcon;
import botecofx.db.entidades.Comanda;
import botecofx.db.entidades.Garcon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class FXMLCadComandaController implements Initializable {

    @FXML
    private SplitPane PaneCadComanda;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnApagar;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private JFXButton BtnCancelar;
    @FXML
    private AnchorPane PaneDados;
    @FXML
    private JFXTextField txtcod;
    @FXML
    private JFXTextField txtnome;
    @FXML
    private JFXTextField txtvalor;
    @FXML
    private JFXTextField txtdescr;
    @FXML
    private JFXComboBox<Garcon> CbGarcon;
    @FXML
    private JFXTextField txtnumero;
    @FXML
    private JFXDatePicker datacom;
    @FXML
    private JFXTextField txtstatus;
    @FXML
    private VBox PanePesquisa;
    @FXML
    private JFXTextField TxtPesquisa;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private TableView<Object> TabelaPesquisa;
    @FXML
    private TableColumn<Comanda, String> colcod;
    @FXML
    private TableColumn<Comanda, String> colgar;
    @FXML
    private TableColumn<Comanda, String> colnum;
    @FXML
    private TableColumn<Comanda, String> colnome;
    @FXML
    private TableColumn<Comanda, String> colvalor;
    @FXML
    private TableColumn<Comanda, String> coldata;
    @FXML
    private TableColumn<Comanda, String> coldescr;
    @FXML
    private TableColumn<Comanda, String> colstatus;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            colcod.setCellValueFactory(new PropertyValueFactory<>("com_cod"));      
            colgar.setCellValueFactory(new PropertyValueFactory<>("gar_id"));
            colnome.setCellValueFactory(new PropertyValueFactory<>("com_nome"));
            colvalor.setCellValueFactory(new PropertyValueFactory<>("com_valor"));
            coldescr.setCellValueFactory(new PropertyValueFactory<>("com_desc"));
            colnum.setCellValueFactory(new PropertyValueFactory<>("com_numero"));
            coldata.setCellValueFactory(new PropertyValueFactory<>("com_data"));
            colstatus.setCellValueFactory(new PropertyValueFactory<>("com_status"));
          
            estadoOriginal();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        estadoOriginal();
    }    

    private void estadoOriginal()
    {
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnCancelar.setDisable(false);
        btnApagar.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(false);
        txtcod.setDisable(true);
        TabelaPesquisa.setVisible(false);

        LimpaTela(PaneDados);
        LimpaTela(PanePesquisa);

        carregaTabela("");
    
     }
    
    private void carregaTabela(String filtro) {
        DALComanda dal = new DALComanda();
        List<Comanda> res = dal.get(filtro);
        ObservableList<Object> modelo;
        modelo = FXCollections.observableArrayList(res);
        TabelaPesquisa.setItems(modelo);
    }
    @FXML
    private void clkBtnNovo(ActionEvent event) {
        
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(false);
        BtnConfirmar.setDisable(false);
        BtnCancelar.setDisable(false);
        btnApagar.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(false);
        txtnome.setDisable(false);
        
        DALGarcon dalgar = new DALGarcon();      
        List<Garcon> gar = dalgar.get("");
        ObservableList <Garcon> GarModelo = FXCollections.observableArrayList(gar);;
       
        CbGarcon.setItems(GarModelo);
     

    }
    private void estadoEdicao()
    {
       PanePesquisa.setDisable(true);
       PaneDados.setDisable(false);
       BtnConfirmar.setDisable(false);
       btnApagar.setDisable(true);
       btnAlterar.setDisable(true);
       txtnome.requestFocus();    

    }

    @FXML
    private void clkBtnAlterar(ActionEvent event) {
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(false);
        BtnConfirmar.setDisable(true);
        BtnCancelar.setDisable(false);
        btnApagar.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(true);
        estadoEdicao();
        Comanda c = new Comanda();
        DALComanda dal = new DALComanda();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Confirma a alteração");
        if (a.showAndWait().get() == ButtonType.OK) {

            if (dal.alterar(c)) {
                a.setContentText("Comanda alterada com Sucesso");
                estadoOriginal();
            } else {
                a.setContentText("Problemas ao Alterar");
            }
            a.showAndWait();
        } else {
            estadoOriginal();
        }
    }

    @FXML
    private void clkBtnApagar(ActionEvent event) {
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(false);
        BtnConfirmar.setDisable(false);
        BtnCancelar.setDisable(false);
        btnApagar.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(true);
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão");
        if (a.showAndWait().get() == ButtonType.OK) {
            DALComanda dal = new DALComanda();
            Comanda c;
            c = (Comanda) TabelaPesquisa.getSelectionModel().getSelectedItem();
            dal.apagar(c);
            estadoOriginal();
        }
        else
            estadoOriginal();
    }

    @FXML
    private void clkBtnConfirmar(ActionEvent event) {
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(true);
        BtnConfirmar.setDisable(true);
        BtnCancelar.setDisable(false);
        btnApagar.setDisable(false);
        btnAlterar.setDisable(false);
        btnNovo.setDisable(true);
        Comanda c = new Comanda();
        DALComanda dal = new DALComanda();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
       
            if (dal.gravar(c)) 
                a.setContentText("Comanda gravada com Sucesso");
            else 
                a.setContentText("Problemas ao Gravar");                   
            
        a.showAndWait();    
        estadoOriginal();
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) {
           LimpaTela(PaneDados);
           LimpaTela(PanePesquisa);
           estadoOriginal();
    }

    @FXML
    private void clkBtnPesquisar(ActionEvent event) {
         TabelaPesquisa.setVisible(true);
        carregaTabela(TxtPesquisa.getText());
    }

    @FXML
    private void clkTabela(MouseEvent event) {
        if (event.getClickCount() == 2) 
        {
            Comanda c = (Comanda) TabelaPesquisa.getSelectionModel().getSelectedItem();
            // JOptionPane.showMessageDialog(null, p.getDescr());
            txtcod.setText("" + c.getCod());
            txtnome.setText(c.getNome());  
            txtdescr.setText("" + c.getCod());
            txtnumero.setText(c.getNome()); 
            txtvalor.setText("" + c.getCod());
            txtstatus.setText(""+c.getStatus());
            txtnome.setText(c.getNome()); 
            
            DALGarcon dalgar = new DALGarcon();        
            List<Garcon> gar = dalgar.get("");
          
            ObservableList<Garcon> garModelo = FXCollections.observableArrayList(gar);
            CbGarcon.setItems(garModelo);        
         
            
            
            
            
            
            PanePesquisa.setDisable(false);
            PaneDados.setDisable(false);
            BtnConfirmar.setDisable(true);
            BtnCancelar.setDisable(false);
            btnApagar.setDisable(false);
            btnAlterar.setDisable(false);
            btnNovo.setDisable(true);
            txtnome.setDisable(false);
         }
    }
    
}
