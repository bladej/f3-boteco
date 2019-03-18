package botecofx;

import static botecofx.Botecofx.LimpaTela;
import botecofx.db.dal.DALCategoria;
import botecofx.db.dal.DALProduto;
import botecofx.db.dal.DALUnidade;
import botecofx.db.entidades.Categoria;
import botecofx.db.entidades.Produto;
import botecofx.db.entidades.Unidade;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FXMLCadProdutoController implements Initializable {

    @FXML
    private AnchorPane PaneDados;
    @FXML
    private VBox PanePesquisa;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnApagar;
    @FXML
    private JFXTextField TxtPesquisa;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private JFXButton BtnCancelar;
    @FXML
    private SplitPane PaneCadProduto;
    @FXML
    private TableColumn<Produto, String> colcod;
    @FXML
    private TableColumn<Produto, String> colnome;
    @FXML
    private TableColumn<Produto, String> colpreco;
    @FXML
    private TableColumn<Produto, String> coldescr;
    @FXML
    private JFXComboBox<Categoria> CbCategoria;
    @FXML
    private JFXComboBox<Unidade> CbUnidade;
    @FXML
    private TableView<Object> TabelaPesquisa;
    @FXML
    private JFXTextField txtcod;
    @FXML
    private JFXTextField txtnome;
    @FXML
    private JFXTextField txtpreco;
    @FXML
    private JFXTextField txtdescr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            colcod.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
            colnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colpreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
            coldescr.setCellValueFactory(new PropertyValueFactory<>("descr"));
            estadoOriginal();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        estadoOriginal();
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
    private void estadoOriginal() {
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
        DALProduto dal = new DALProduto();
        List<Produto> ListProd = dal.get(filtro);
        ObservableList<Object> modelo;
        modelo = FXCollections.observableArrayList(ListProd);
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
        
        DALCategoria dalcat = new DALCategoria();
        DALUnidade daluni = new DALUnidade();
        List<Categoria> cat = dalcat.get("");
        List<Unidade> uni = daluni.get("");
        ObservableList <Categoria> catModelo = FXCollections.observableArrayList(cat);;
        ObservableList <Unidade> catUnidade = FXCollections.observableArrayList(uni);;
       
        CbCategoria.setItems(catModelo);
        CbUnidade.setItems(catUnidade);
        
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
        Produto p = new Produto(Integer.parseInt(txtcod.getText()),txtnome.getText(), txtdescr.getText(), Double.parseDouble(txtpreco.getText()), CbCategoria.getValue(), CbUnidade.getValue());
        DALProduto dal = new DALProduto();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Confirma a alteração");
        if (a.showAndWait().get() == ButtonType.OK) {

            if (dal.alterar(p)) {
                a.setContentText("Alterado com Sucesso");
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
            DALProduto dal = new DALProduto();
            Produto p;
            p = (Produto) TabelaPesquisa.getSelectionModel().getSelectedItem();
            dal.apagar(p);
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
        
       
        Produto p = new Produto(txtnome.getText(), txtdescr.getText(), Double.parseDouble(txtpreco.getText()), CbCategoria.getValue(), CbUnidade.getValue());
        DALProduto dal = new DALProduto();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
       
            if (dal.gravar(p)) 
                a.setContentText("Produto gravado com Sucesso");
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
    private void clkTabela(javafx.scene.input.MouseEvent event) {
        if (event.getClickCount() == 2) {
            Produto p = (Produto) TabelaPesquisa.getSelectionModel().getSelectedItem();
            // JOptionPane.showMessageDialog(null, p.getDescr());
            txtcod.setText("" + p.getProd_id());
            txtnome.setText(p.getNome());
            txtpreco.setText("" + p.getPreco());
            txtdescr.setText(p.getDescr());
            DALCategoria dalcat = new DALCategoria();
            DALUnidade daluni = new DALUnidade();
            List<Categoria> cat = dalcat.get("");
            List<Unidade> uni = daluni.get("");
            ObservableList<Categoria> catModelo = FXCollections.observableArrayList(cat);
            ObservableList<Unidade> catUnidade = FXCollections.observableArrayList(uni);

            CbCategoria.setItems(catModelo);
            CbUnidade.setItems(catUnidade);
            
            CbCategoria.setValue(p.getCat());
            CbUnidade.setValue(p.getUni());

            
            PanePesquisa.setDisable(false);
            PaneDados.setDisable(false);
            BtnConfirmar.setDisable(true);
            BtnCancelar.setDisable(false);
            btnApagar.setDisable(false);
            btnAlterar.setDisable(false);
            btnNovo.setDisable(true);
         }
    }

}
