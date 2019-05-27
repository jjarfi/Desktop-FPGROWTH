/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Controller.Application.Property.AccountSetting;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Configuration.BootInitializable;
import USTJ.Function.Function;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pace
 */
@Component
public class AccountSettingController implements BootFormInitializable {
    
    private ApplicationContext springContext;

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
            loader.setLocation(getClass().getResource(Function.ACCOUNTSETTING));
            loader.setController(springContext.getBean(this.getClass()));
            return loader.load();
        } catch (IOException ex) {
            System.err.print("Error di = " + ex);
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) throws BeansException {
       this.springContext = springContext;
    }

    @Override
    public void initValidator() {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
