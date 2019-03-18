/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import static botecofx.Botecofx.LimpaTela;
import botecofx.db.dal.DALTipoPagto;
import botecofx.db.dal.DALUnidade;
import botecofx.db.entidades.TipoPagto;
import botecofx.db.entidades.Unidade;
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

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLCadUnidadeController implements Initializable {

    @FXML
    private SplitPane PaneCadUnidade;
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
    private TableColumn<Unidade, String> colcod;
    @FXML
    private TableColumn<Unidade, String> colnome;
    @FXML
    private JFXTextField txtcod;
    @FXML
    private JFXTextField txtnome;

    /**
     * Initializes the controller class.
     */
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
     private void estadoEdicao()
    {
       PanePesquisa.setDisable(true);
       PaneDados.setDisable(false);
       BtnConfirmar.setDisable(false);
       btnApagar.setDisable(true);
       btnAlterar.setDisable(true);
       txtnome.requestFocus();    

    }
    private void carregaTabela(String filtro) {
        DALUnidade dal = new DALUnidade();
        List<Unidade> res = dal.get(filtro);
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
        Unidade c = new Unidade(Integer.parseInt(txtcod.getText()),txtnome.getText());
        DALUnidade dal = new DALUnidade();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Confirma a alteração");
        if (a.showAndWait().get() == ButtonType.OK) {

            if (dal.alterar(c)) {
                a.setContentText("Unidade alterada com Sucesso");
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
            DALUnidade dal = new DALUnidade();
            Unidade u;
            u = (Unidade) TabelaPesquisa.getSelectionModel().getSelectedItem();
            dal.apagar(u);
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
        Unidade c = new Unidade(txtnome.getText());
        DALUnidade dal = new DALUnidade();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
       
            if (dal.gravar(c)) 
                a.setContentText("Unidade gravada com Sucesso");
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
            Unidade c = (Unidade) TabelaPesquisa.getSelectionModel().getSelectedItem();
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
