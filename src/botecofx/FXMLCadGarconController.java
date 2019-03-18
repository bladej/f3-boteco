/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botecofx;

import static botecofx.Botecofx.LimpaTela;
import botecofx.db.dal.DALGarcon;
import botecofx.db.entidades.Garcon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FXMLCadGarconController implements Initializable {

    @FXML
    private SplitPane PaneCadGarcon;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnApagar;
    @FXML
    private AnchorPane PaneDados;
    @FXML
    private JFXButton BtnFoto;
    @FXML
    private VBox PanePesquisa;
    @FXML
    private JFXTextField TxtPesquisa;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private JFXButton BtnConfirmar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private TableColumn<Garcon, String> colcod;
    @FXML
    private TableColumn<Garcon, String> colnome;
    @FXML
    private TableColumn<Garcon, String> colcep;
    @FXML
    private TableColumn<Garcon, String> colcpf;
    @FXML
    private TableColumn<Garcon, String> coluf;
    @FXML
    private TableColumn<Garcon, String> colfone;
    @FXML
    private TableColumn<Garcon, String> colcidade;
    @FXML
    private TableColumn<Garcon, String> colend;
    @FXML
    private TableView<Object> TabelaPesquisa;
    @FXML
    private ImageView ImageGarcon;
    @FXML
    private JFXTextField txtcod;
    @FXML
    private JFXTextField txtnome;
    @FXML
    private JFXTextField txtcpf;
    @FXML
    private JFXTextField txtend;
    @FXML
    private JFXTextField txtcidade;
    @FXML
    private JFXTextField txtuf;
    @FXML
    private JFXTextField txtfone;
    @FXML
    private JFXTextField txtcep;
    private String caminho;
    private FileInputStream f;
    private byte[] imageInByte;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             colcod.setCellValueFactory(new PropertyValueFactory<>("id"));
             colnome.setCellValueFactory(new PropertyValueFactory<>("nome"));
             colcep.setCellValueFactory(new PropertyValueFactory<>("cep"));
             colcpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
             coluf.setCellValueFactory(new PropertyValueFactory<>("uf"));
             colfone.setCellValueFactory(new PropertyValueFactory<>("fone"));
             colcidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
             colend.setCellValueFactory(new PropertyValueFactory<>("endereco"));

           
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
        DALGarcon dal = new DALGarcon();
        List<Garcon> res = dal.get(filtro);
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
        Garcon g = new Garcon(Integer.parseInt(txtcod.getText()),Long.parseLong(txtcpf.getText()),Long.parseLong(txtcep.getText()),txtuf.getText(),
        txtfone.getText(),txtcidade.getText(),txtend.getText(),txtnome.getText());
        DALGarcon dal = new DALGarcon();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Confirma a alteração");
        if (a.showAndWait().get() == ButtonType.OK) {
            InputStream is = null;
            BufferedImage bimg = null;
            try {
                bimg = SwingFXUtils.fromFXImage(ImageGarcon.getImage(), null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bimg, "jpg", baos);
                is = new ByteArrayInputStream(baos.toByteArray());
            } catch (Exception e) {
                is = null;
                bimg = null;
            }
            dal.salvarImagem(g.getId(), bimg);
            
            
            
            if (dal.alterar(g)) {
                a.setContentText("Garcon alterada com Sucesso");
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
            DALGarcon dal = new DALGarcon();
            Garcon g;
            g = (Garcon) TabelaPesquisa.getSelectionModel().getSelectedItem();
            dal.apagar(g);
            estadoOriginal();
        }
        else
            estadoOriginal();
    }
    @FXML
    private void clkBtnConfirmar(ActionEvent event) throws IOException {
        PanePesquisa.setDisable(false);
        PaneDados.setDisable(true);
        BtnConfirmar.setDisable(true);
        btnCancelar.setDisable(false);
        btnApagar.setDisable(false);
        btnAlterar.setDisable(false);
        btnNovo.setDisable(true);

        Garcon g = new Garcon(Long.parseLong(txtcpf.getText()),Long.parseLong(txtcep.getText()),txtuf.getText(),
        txtfone.getText(),txtcidade.getText(),txtend.getText(),txtnome.getText());
        DALGarcon dal = new DALGarcon();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        InputStream is=null;
        BufferedImage bimg = null;
        try {
            bimg = SwingFXUtils.fromFXImage(ImageGarcon.getImage(), null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimg, "jpg", baos);
            is = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            is = null;
            bimg = null;
        }
        dal.salvarImagem(g.getId(), bimg);

        if (dal.gravar(g)) {
            a.setContentText("Garcon gravado com Sucesso");
        } else {
            a.setContentText("Problemas ao Gravar");
        }

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
    private void clkBtnFoto(ActionEvent event) throws FileNotFoundException, IOException {   
        JFileChooser fc = new JFileChooser();
        fc.setToolTipText("Abra Imagem");
        fc.setFileFilter(new FileNameExtensionFilter("Image files", "bmp", "png", "jpg"));
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            caminho = fc.getSelectedFile().getPath();
            File arq = new File(caminho);

            f = new FileInputStream(arq);
            BufferedImage bi = ImageIO.read(f);
            ImageGarcon.setImage(SwingFXUtils.toFXImage(bi, null));               
        }     
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
            Garcon g = (Garcon) TabelaPesquisa.getSelectionModel().getSelectedItem();
            // JOptionPane.showMessageDialog(null, p.getDescr());
            txtcod.setText("" + g.getId());
            txtnome.setText(g.getNome()); 
            txtcpf.setText(""+g.getCpf()); 
            txtuf.setText(g.getUf()); 
            txtend.setText(g.getEndereco()); 
            txtfone.setText(g.getFone()); 
            txtcep.setText(""+g.getCep()); 
            txtcidade.setText(g.getCidade());
            PanePesquisa.setDisable(false);
            PaneDados.setDisable(false);
            BtnConfirmar.setDisable(true);
            btnCancelar.setDisable(false);
            btnApagar.setDisable(false);
            btnAlterar.setDisable(false);
            btnNovo.setDisable(true);
            txtnome.setDisable(false);
            DALGarcon dal = new DALGarcon();
            ImageGarcon.setImage(dal.getFoto(g));
         }
    }
    
}
