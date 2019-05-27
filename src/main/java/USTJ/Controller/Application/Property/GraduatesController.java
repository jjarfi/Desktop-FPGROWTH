/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Controller.Application.Property;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Configuration.BootInitializable;
import USTJ.Controller.Application.Property.Graduates.DetailsalumniController;
import USTJ.Controller.Application.SideMenu.SideMenuController;
import USTJ.Entity.DataAdmin;
import USTJ.Entity.DataAlumni;
import USTJ.Function.Function;
import USTJ.DAO.RepositoryAdmin;
import USTJ.DAO.RepositoryAlumni;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pace
 */
@Component
public class GraduatesController implements BootFormInitializable {

    private ApplicationContext springContext;
    private final Logger loger = LoggerFactory.getLogger(this.getClass());

    private DataAlumni da;
    @Autowired
    private RepositoryAlumni ra;

    @Autowired
    private RepositoryAdmin repoadmin;

    @Autowired
    private SideMenuController smc;

    @FXML
    private JFXButton btnsimpan;
    @FXML
    private StackPane stakeroot;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private JFXButton btndelete;

    @FXML
    private TableView<DataAlumni> tableAlumni;

    @FXML
    private TableColumn<DataAlumni, String> kolnpm;

    @FXML
    private TableColumn<DataAlumni, String> kolnama;

    @FXML
    private TableColumn<DataAlumni, String> kolprodi;

    @FXML
    public TextField txtnpm;

    @FXML
    private TextField txtnamalengkap;

    @FXML
    private ComboBox combojk;

    @FXML
    private ComboBox comboprodi, combofilter;

    @FXML
    private Spinner spinbulan;

    @FXML
    private Spinner spintahun;

    private DataAdmin akun;

    public DataAdmin getAkun() {
        return akun;
    }

    @Override
    public void initConstruct() {
        this.da = new DataAlumni();
        refresh();

    }

    @Override
    public void stage(Stage primaryStage) {

    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Function.GRADUATES));
            loader.setController(springContext.getBean(this.getClass()));
            return loader.load();
        } catch (IOException ex) {
            System.err.print("Error di = " + ex);
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            SpinnerValueFactory thn = new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, 2020);
            spintahun.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
            spintahun.setValueFactory(thn);

            ObservableList<String> bulan = FXCollections.observableArrayList("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember");
            SpinnerValueFactory<String> vs = new SpinnerValueFactory.ListSpinnerValueFactory<>(bulan);
            spinbulan.setValueFactory(vs);

            combojk.getItems().addAll("Laki-Laki", "Perempuan");
            combojk.getSelectionModel();

            comboprodi.getItems().addAll("Teknik Informatika S1", "Sistem Informasi S1");
            comboprodi.getSelectionModel();

            combofilter.getItems().addAll("Nama Alumni", "Jenis Kelamin", "Program Studi", "Bulan Wisuda", "Tahun Wisuda");
            combofilter.getSelectionModel();
        } catch (Exception ex) {

        }

    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) throws BeansException {
        this.springContext = springContext;
    }

    @FXML
    private void simpan(ActionEvent event) {
        try {
            save();
        } catch (Exception ex) {
            System.out.println("error di= " + ex);
        }
    }

    private void save() {
        DataAdmin findById = repoadmin.findByFullname(smc.logaccess.getText());
        this.da.setNpm(txtnpm.getText());
        this.da.setFullname(txtnamalengkap.getText());
        this.da.setJk(combojk.getValue().toString());
        this.da.setProdi(comboprodi.getValue().toString());
        this.da.setBulan(spinbulan.getValue().toString());
        this.da.setTahun((Integer) spintahun.getValue());
        this.da.setCreatedate(Timestamp.valueOf(LocalDateTime.now()));
        this.da.setId_admin(findById.getId_admin());
        ra.save(da);
        refresh();
        bersihkan();
    }

    @FXML
    private void update(ActionEvent event) {
        update();
    }

    private void update() {
        try {

            DataAdmin findById = repoadmin.findByFullname(smc.logaccess.getText());
            this.da.setNpm(txtnpm.getText());
            this.da.setFullname(txtnamalengkap.getText());
            this.da.setJk(combojk.getValue().toString());
            this.da.setProdi(comboprodi.getValue().toString());
            this.da.setBulan(spinbulan.getValue().toString());
            this.da.setTahun((Integer) spintahun.getValue());
            this.da.setLastupdate(Timestamp.valueOf(LocalDateTime.now()).toString());
            this.da.setId_admin(findById.getId_admin());
            ra.save(da);
            refresh();
            bersihkan();
        } catch (Exception ex) {

        }
    }

    private void refresh() {
        try {
            tableAlumni.getItems().clear();
            tableAlumni.getItems().addAll(ra.findAll());
            kolnpm.setCellValueFactory(new PropertyValueFactory<>("npm"));
            kolnama.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            kolprodi.setCellValueFactory(new PropertyValueFactory<>("prodi"));
        } catch (Exception ex) {

        }
    }

    private void bersihkan() {
        txtnpm.setText("");
        txtnamalengkap.setText("");

    }

    @FXML
    private void setdaritabel(MouseEvent event) {

        try {
            da = tableAlumni.getItems().get(tableAlumni.getSelectionModel().getSelectedIndex());
            this.txtnpm.setText(da.getNpm());
            this.txtnamalengkap.setText(da.getFullname());
            this.combojk.setValue(da.getJk());
            this.comboprodi.setValue(da.getProdi());
            this.spinbulan.getValueFactory().setValue(da.getBulan());
            this.spintahun.getValueFactory().setValue(da.getTahun());

            this.txtnpm.setEditable(false);
            this.btnsimpan.setDisable(true);
            this.btndelete.setDisable(false);
            this.btnupdate.setDisable(false);
        } catch (Exception ex) {
        }

    }

    @FXML
    private void refreshtable(ActionEvent event) {
        try {
            refresh();
            bersihkan();
            this.txtnpm.setEditable(true);
            this.btnsimpan.setDisable(false);
            this.btndelete.setDisable(true);
            this.btnupdate.setDisable(true);
        } catch (Exception ex) {

        }
    }

    @FXML
    private void delete(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Warning"));
        content.setBody(new Text("Apakah anda yakin ingin menghapus data alumni ?\n"
                + "\n"+txtnpm.getText()
                + "\n"+txtnamalengkap.getText()));
        JFXDialog dialog = new JFXDialog(stakeroot, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Iya");
        button.setTextFill(Color.WHITE);
        
        button.setStyle("-fx-background-color: #0088cc; -fx-border-color: #0088cc");
        button.setOnAction((ActionEvent event1) -> {
            delete();
            dialog.close();
        });
        content.setActions(button);
        dialog.show();
    }

    private void delete() {
        try {
            ra.delete(da);
            refresh();
            bersihkan();
        } catch (Exception ex) {

        }
    }

    @FXML
    private void viewDetail(ActionEvent event) {
        detail();
    }

    private void detail() {
        try {
            this.loger.info("Detail View Openning Success ....");
            DetailsalumniController controller = springContext.getBean(DetailsalumniController.class);
            Stage apsStage = new Stage();
            Parent parent = (Parent) controller.initView();
            Scene scene = new Scene(parent);
            apsStage.setScene(scene);
            apsStage.setTitle(Function.APPNAME);
            apsStage.setResizable(false);
            apsStage.getIcons().add(new Image(getClass().getResource(Function.LOGOUSTJ).toExternalForm()));
            apsStage.show();
        } catch (BeansException ex) {

        }
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }

    @Override
    public void initValidator() {

    }
}
