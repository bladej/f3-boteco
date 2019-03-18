
package botecofx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class FXMLPrincipalController implements Initializable {

    @FXML
    private BorderPane PanePrincipal;
    @FXML
    private VBox VBoxProd;
    @FXML
    private MenuItem btnPagar;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
       
   
    @FXML
    private void evtUnidade(ActionEvent event) {
        try {    
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadUnidade.fxml"));
            VBoxProd.getChildren().clear();
            VBoxProd.getChildren().add(root);
        } catch (IOException ex) { }
        
    }

    @FXML
    private void evtProdutos(ActionEvent event) {
        try {    
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadProduto.fxml"));
            VBoxProd.getChildren().clear();
            VBoxProd.getChildren().add(root);
        } catch (IOException ex) { }
    }

    @FXML
    private void evtGarcon(ActionEvent event) {
        try {    
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadGarcon.fxml"));
            VBoxProd.getChildren().clear();
            VBoxProd.getChildren().add(root);
        } catch (IOException ex) { }
    }

    @FXML
    private void evtCategoria(ActionEvent event) {
        try {    
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadCategoria.fxml"));
            VBoxProd.getChildren().clear();
            VBoxProd.getChildren().add(root);
        } catch (IOException ex) { }
    }
    @FXML
    private void evtTipoPagto(ActionEvent event) {
         try {    
            Parent root = FXMLLoader.load(getClass().getResource("FXMLTipoPagto.fxml"));
            VBoxProd.getChildren().clear();
            VBoxProd.getChildren().add(root);
        } catch (IOException ex) { }      
    }
    
    
    
    @FXML
    private void evtClose(ActionEvent event) {
        
    }

    @FXML
    private void clkBtnPagar(ActionEvent event) {
        
        try {    
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPagamento.fxml"));
            VBoxProd.getChildren().clear();
            VBoxProd.getChildren().add(root);
        } catch (IOException ex) { }
    }

    @FXML
    private void evtComanda(ActionEvent event) {
         try {    
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadComanda.fxml"));
            VBoxProd.getChildren().clear();
            VBoxProd.getChildren().add(root);
        } catch (IOException ex) { }
    }

   
    
    
    
    
    
    
}
