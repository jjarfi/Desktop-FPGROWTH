/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Controller.App;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Configuration.BootInitializable;
import USTJ.Function.Function;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.dialog.ExceptionDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class WellcomeController implements BootFormInitializable {

    private final Logger loger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginController formLogin;
    @Autowired
    private SignupController formSignup;
    @Autowired
    private ForgotController formForgot;

    private ApplicationContext springContext;
    @FXML
    private AnchorPane jendela;

    @FXML
    private BorderPane wellroot;

    public void setNode(Node node) {
        this.jendela.getChildren().clear();
        this.jendela.getChildren().add(node);
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

    }

    @Override
    public void stage(Stage primaryStage) {

    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Function.WELCOME));
            loader.setController(springContext.getBean(this.getClass()));
            return loader.load();
        } catch (IOException ex) {
            System.err.print("Error di = " + ex);
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) {
        this.springContext = springContext;

    }

    @FXML
    public void showLoginForm() {
        try {
            setNode(formLogin.initView());
            formLogin.initConstruct();
        } catch (Exception e) {
            loger.error("Tidak dapat menampilkan form login", e);

            ExceptionDialog ex = new ExceptionDialog(e);
            ex.setTitle("Login form");
            ex.setHeaderText("Tidak dapat menampilkan form login!");
            ex.setContentText(e.getMessage());
            ex.show();
        }
    }

    @FXML
    public void showSigupForm() {
        try {
            setNode(formSignup.initView());
            formSignup.initConstruct();
        } catch (Exception e) {
            loger.error("Tidak dapat menampilkan form login", e);
        }
    }

    @FXML
    public void showForgotForm() {
        try {
            setNode(formForgot.initView());
            formForgot.initConstruct();
        } catch (Exception e) {
            loger.error("Tidak dapat menampilkan form forgot", e);

        }
    }

    @FXML
    public void wellClose() {
        Stage s = (Stage) wellroot.getScene().getWindow();
        s.close();
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }

    @Override
    public void initValidator() {

    }
}
