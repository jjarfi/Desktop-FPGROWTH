/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Controller.Application;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Configuration.BootInitializable;
import USTJ.Controller.Application.Property.AccountSetting.AccountSettingController;
import USTJ.Controller.Application.Property.DashboardController;
import USTJ.Controller.Application.Property.GraduatesController;
import USTJ.Controller.Application.Property.QuesionareController;
import USTJ.Controller.Application.Property.ReportPrintController;
import USTJ.Controller.Application.Property.SettingsController;
import USTJ.Controller.Application.Property.TracerStudyController;
import USTJ.Controller.Application.Property.WebsiteController;
import USTJ.Controller.Application.SideMenu.SideMenuController;
import USTJ.Function.Function;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
public class MainSceneController implements BootFormInitializable {

    private ApplicationContext springContext = null;

    @Autowired
    private DashboardController dashCont;

    @Autowired
    private GraduatesController graduatesCont;

    @Autowired
    private SideMenuController sideMenu;

    @Autowired
    private TracerStudyController tracerStudyCont;

    @Autowired
    private ReportPrintController reportprintCont;

    @Autowired
    private SettingsController settingsCont;

    @Autowired
    private WebsiteController websiteCont;

    @Autowired
    private AccountSettingController accSettingCont;
    
    @Autowired
    private QuesionareController quest;

    @FXML
    private StackPane mains;

    @FXML
    private BorderPane frame;

    @FXML
    private BorderPane menuFrame;

    public void setNode(Node node) {
        this.frame.getChildren().clear();
        this.frame.setCenter(node);
        this.frame.autosize();
    }

    public void setSideMenu(Node node) {
        this.menuFrame.setCenter(node);
        this.menuFrame.autosize();
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
            loader.setLocation(getClass().getResource(Function.MAINAPP));
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
    public void showDashboard() {
        setNode(dashCont.initView());
        dashCont.initConstruct();

    }

    @FXML
    public void showGraduates() {
        setNode(graduatesCont.initView());
        graduatesCont.initConstruct();

    }
     @FXML
    public void showQuesionare() {
        setNode(quest.initView());
        quest.initConstruct();

    }

    @FXML
    public void showReportPrint() {
        setNode(reportprintCont.initView());
        reportprintCont.initConstruct();

    }

    @FXML
    public void showSettings() {
        setNode(settingsCont.initView());
        settingsCont.initConstruct();

    }

    @FXML
    public void showTracerStudy() {
        setNode(tracerStudyCont.initView());
        tracerStudyCont.initConstruct();

    }

    @FXML
    public void showWebsite() {
        setNode(websiteCont.initView());
        websiteCont.initConstruct();

    }

    @FXML
    public void showSideMenu() {
        setSideMenu(sideMenu.initView());
        sideMenu.initConstruct();

    }

    @FXML
    public void showAccountSetting() {
        setSideMenu(accSettingCont.initView());
        accSettingCont.initConstruct();

    }

    @Override
    public void initValidator() {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
