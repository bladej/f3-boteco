/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import static botecofx.Botecofx.LimpaTela;
import botecofx.db.dal.DALTipoPagto;
import botecofx.db.entidades.Comanda.Pagamento;
import botecofx.db.entidades.TipoPagto;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class FXMLPagamentoController implements Initializable {

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
    private TableView<Pagamento> TabelaPesquisa;
    @FXML
    private SplitPane PaneCadPagamento;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private JFXButton BtnCancelar;
    @FXML
    private TableColumn<Pagamento, String> colcod;
    @FXML
    private TableColumn<Pagamento, String> colcomanda;
    @FXML
    private TableColumn<Pagamento, String> coltipo;
    @FXML
    private TableColumn<Pagamento, String> colvalor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
       
        LimpaTela(PaneDados);
        LimpaTela(PanePesquisa);
    
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

    }

    @FXML
    private void clkBtnAlterar(ActionEvent event) {
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(false);
        BtnConfirmar.setDisable(false);
        BtnCancelar.setDisable(false);
        btnApagar.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(true);
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
    }

    @FXML
    private void clkBtnCancelar(ActionEvent event) {
         LimpaTela(PaneDados);
         LimpaTela(PanePesquisa);
         estadoOriginal();
    }

    @FXML
    private void clkBtnPesquisar(ActionEvent event) {
    }
    
}
