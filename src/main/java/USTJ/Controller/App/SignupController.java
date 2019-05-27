/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Controller.App;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Entity.DataAdmin;
import USTJ.Function.Function;
import USTJ.DAO.RepositoryAdmin;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
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
public class SignupController implements BootFormInitializable {

    private ApplicationContext springContext;
    @Autowired
    private WellcomeController backToLog;


    private DataAdmin da;
    private ValidationSupport validation;

    @Autowired
    private RepositoryAdmin rp;

    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnregis;
    @FXML
    private TextField txtfullname;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txthandphone;

    @FXML
    private TextField txtusername;

    @FXML
    private PasswordField txtpassword;

    public SignupController() {
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
        initValidator();
    }

    @Override
    public void initConstruct() {
        this.da = new DataAdmin();
    }

    @Override
    public void stage(Stage primaryStage) {

    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Function.SIGNUP));
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
    private void backToLogin(ActionEvent event) {
        this.backToLog.showLoginForm();
    }

    @FXML
    private void regisAction(ActionEvent event) {
        
          signUp();
      
    }

    public void signUp() {
        try {
            this.da.setFullname(txtfullname.getText());
            this.da.setEmail(txtemail.getText());
            this.da.setHandphone(txthandphone.getText());
            this.da.setUsername(txtusername.getText());
            this.da.setPassword(txtpassword.getText());
            this.da.setCreatedate(Timestamp.valueOf(LocalDateTime.now()));
            ifExit();
           rp.save(da);
            clearText();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void clearText() {
            txtfullname.setText("");
            txtemail.setText("");
            txthandphone.setText("");
            txtusername.setText("");
            txtpassword.setText("");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }

    @Override
    public void initValidator() {
        this.validation = new ValidationSupport();
        this.validation.registerValidator(txtfullname, Validator.createEmptyValidator("Nama lengkap harus di isi", Severity.ERROR));
        this.validation.registerValidator(txtemail, Validator.createEmptyValidator("Email harus di isi", Severity.ERROR));
        this.validation.registerValidator(txtusername, Validator.createEmptyValidator("Username harus di isi", Severity.ERROR));
        this.validation.registerValidator(txtpassword, Validator.createEmptyValidator("Password harus di isi", Severity.ERROR));
        this.validation.registerValidator(txthandphone, (Control c, String value) -> ValidationResult.fromErrorIf(c,
                "Format nomor handphone salah!", !value.matches("\\d[\\d.]*")));
        this.validation.invalidProperty()
                .addListener((ObservableValue<? extends Boolean> values, Boolean oldValue, Boolean newValue) -> {
                    btnregis.setDisable(newValue);
                });
    }
    public void ifExit() {

     rp.existsByFullname(txtfullname.getText());
     if(rp.existsByFullname(txtfullname.getText())){
         System.out.println("Error di " +rp);
     }




//
//         rp.existsByEmail(txtemail.getText());
//
//          rp.existsByhandphone(txthandphone.getText());
//
//           rp.existsByUsername(txtusername.getText());


    }
}
