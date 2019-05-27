/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Controller.App;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Configuration.BootInitializable;
import USTJ.Controller.Application.MainSceneController;
import USTJ.Entity.DataAdmin;
import USTJ.Function.Function;
import USTJ.DAO.RepositoryAdmin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author pace
 */
@Component
public class LoginController implements BootFormInitializable {

    private final Logger loger = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext springContext;
    private static String[] argumen;

    @Autowired
    private RepositoryAdmin ra;

    @Autowired
    private WellcomeController well;

    @Autowired
    private MainSceneController MainsCont;

    @FXML
    public TextField txtusername;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Label klickForgot;

    @FXML
    private Label lblRegis;
    private DataAdmin akun;

    public DataAdmin getAkun() {
        return akun;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initConstruct() {
//        this.da = new DataAdmin();
//        this.dal = new DataAdminLogin();

    }

    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Function.LOGIN));
            loader.setController(springContext.getBean(this.getClass()));
            return loader.load();
        } catch (IOException ex) {
            System.err.print("Error di = " + ex);
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) throws BeansException {
        this.springContext = springContext;

    }

    @FXML
    private void regisClick(MouseEvent event) {
        this.well.showSigupForm();

    }

    @FXML
    private void forgotClick(MouseEvent event) {
        this.well.showForgotForm();

    }

    @FXML
    private void loginApps(ActionEvent event) {
        login();

    }

    private void login() {

        DataAdmin account = ra.findByUsernameAndPassword(txtusername.getText(),
                txtpassword.getText());
        if (account != null) {
            this.akun = account;
            this.masuk();

        } else {

        }

    }

    private void masuk() {
        this.loger.info("Running USTJ Tracer Study Application Success ....");
        MainSceneController controller = springContext.getBean(MainSceneController.class);
        Stage apsStage = new Stage();
        Parent parent = (Parent) controller.initView();
        Scene scene = new Scene(parent);
        apsStage.setScene(scene);
        apsStage.setTitle(Function.APPNAME);
        apsStage.getIcons().add(new Image(getClass().getResource(Function.LOGOUSTJ).toExternalForm()));
        apsStage.show();
        this.well.wellClose();

        MainsCont.showDashboard();
        MainsCont.showSideMenu();
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }

    @Override
    public void initValidator() {

    }
}
