
package botecofx;

import static botecofx.Botecofx.LimpaTela;
import botecofx.db.dal.DALCategoria;
import botecofx.db.dal.DALUnidade;
import botecofx.db.entidades.Categoria;
import com.jfoenix.controls.JFXButton;
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


public class FXMLCadCategoriaController implements Initializable {

    @FXML
    private SplitPane PaneCadCategoria;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnApagar;
    @FXML
    private AnchorPane PaneDados;
    @FXML
    private VBox PanePesquisa;
    @FXML
    private JFXTextField TxtPesquisa;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private TableView<Object> TabelaPesquisa;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private TableColumn<Categoria, String> colcod;
    @FXML
    private TableColumn<Categoria, String> colnome;
    @FXML
    private JFXTextField txtcod;
    @FXML
    private JFXTextField txtnome;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            colcod.setCellValueFactory(new PropertyValueFactory<>("id"));
            colnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
           
            estadoOriginal();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }   
    }    
    private void estadoOriginal()
    {
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(true);
        BtnConfirmar.setDisable(true);
        btnCancelar.setDisable(false);
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
        DALCategoria dal = new DALCategoria();
        List<Categoria> res = dal.get(filtro);
        ObservableList<Object> modelo;
        modelo = FXCollections.observableArrayList(res);
        TabelaPesquisa.setItems(modelo);
    }
    @FXML
    private void clkBtnNovo(ActionEvent event) {
        
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(false);
        BtnConfirmar.setDisable(false);
        btnCancelar.setDisable(false);
        btnApagar.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(false);
        txtnome.setDisable(false);

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
        btnCancelar.setDisable(false);
        btnApagar.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(true);
        estadoEdicao();
        Categoria c = new Categoria(Integer.parseInt(txtcod.getText()),txtnome.getText());
        DALCategoria dal = new DALCategoria();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Confirma a alteração");
        if (a.showAndWait().get() == ButtonType.OK) {

            if (dal.alterar(c)) {
                a.setContentText("Categoria alterada com Sucesso");
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
        btnCancelar.setDisable(false);
        btnApagar.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(true);
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão");
        if (a.showAndWait().get() == ButtonType.OK) {
            DALCategoria dal = new DALCategoria();
            Categoria c;
            c = (Categoria) TabelaPesquisa.getSelectionModel().getSelectedItem();
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
        btnCancelar.setDisable(false);
        btnApagar.setDisable(false);
        btnAlterar.setDisable(false);
        btnNovo.setDisable(true);
        Categoria c = new Categoria(txtnome.getText());
        DALCategoria dal = new DALCategoria();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
       
            if (dal.gravar(c)) 
                a.setContentText("Categoria gravada com Sucesso");
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
            Categoria c = (Categoria) TabelaPesquisa.getSelectionModel().getSelectedItem();
            // JOptionPane.showMessageDialog(null, p.getDescr());
            txtcod.setText("" + c.getId());
            txtnome.setText(c.getNome());         
            PanePesquisa.setDisable(false);
            PaneDados.setDisable(false);
            BtnConfirmar.setDisable(true);
            btnCancelar.setDisable(false);
            btnApagar.setDisable(false);
            btnAlterar.setDisable(false);
            btnNovo.setDisable(true);
            txtnome.setDisable(false);
         }
    }
    
}
