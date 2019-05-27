/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Controller.App;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Configuration.BootInitializable;
import USTJ.Function.Function;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
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
public class ForgotController implements BootFormInitializable {

    private ApplicationContext springContext;

    @Autowired
    private WellcomeController backToLog;

    @FXML
    private JFXButton btnBack;

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
            loader.setLocation(getClass().getResource(Function.FORGOT));
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
    private void back(ActionEvent event) {
        this.backToLog.showLoginForm();
    }


    private void reset(){

    }

    @Override
    public void initValidator() {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
