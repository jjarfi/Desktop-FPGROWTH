/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Controller.Application.SideMenu;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Configuration.BootInitializable;
import USTJ.Controller.App.LoginController;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
public class SideMenuController implements BootFormInitializable {

    private ApplicationContext springContext;

    @Autowired
    private MainSceneController msc;

    @Autowired
    private LoginController locon;

    @Autowired
    private RepositoryAdmin ra;

    @FXML
    public Label logaccess;

    private DataAdmin akun;

    public DataAdmin getAkun() {
        return akun;
    }

    @Override
    public void initConstruct() {
        this.akun = new DataAdmin();

    }

    @Override
    public void stage(Stage primaryStage) {

    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Function.SIDEMENU));
            loader.setController(springContext.getBean(this.getClass()));
            return loader.load();
        } catch (IOException ex) {
            System.err.print("Error di = " + ex);
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.logaccess.setText(locon.txtusername.getText());
        difinder();

    }

    @Override
    public void setApplicationContext(ApplicationContext springContext) throws BeansException {

        this.springContext = springContext;

    }

    public void difinder() {
        DataAdmin findById = ra.findByUsername(logaccess.getText());
        this.logaccess.setText(findById.getFullname());
    }

    @FXML
    public void LogOut(ActionEvent event) {

    }

    @FXML
    private void showDashboard(ActionEvent event) {
        this.msc.showDashboard();
    }

    @FXML
    private void showGraduates(ActionEvent event) {
        this.msc.showGraduates();
    }

    @FXML
    private void showReportPrint(ActionEvent event) {
        this.msc.showReportPrint();
    }

    @FXML
    private void showSetting(ActionEvent event) {
        this.msc.showSettings();
    }

    @FXML
    private void showTracerStudy(ActionEvent event) {
        this.msc.showTracerStudy();
    }

    @FXML
    private void showQuesionare(ActionEvent event) {
        this.msc.showQuesionare();
    }

    @FXML
    private void showWebsite(ActionEvent event) {
        this.msc.showWebsite();
    }

    @Override
    public void initValidator() {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
