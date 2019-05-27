/*
 * The MIT License
 *
 * Copyright 2019 pace.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package USTJ.Controller.Application.Property.Graduates;

import USTJ.Configuration.BootFormInitializable;
import USTJ.Configuration.BootInitializable;
import USTJ.Controller.Application.Property.GraduatesController;
import USTJ.Entity.DataAdmin;
import USTJ.Entity.DataAlumni;
import USTJ.Function.Function;
import USTJ.DAO.RepositoryAdmin;
import USTJ.DAO.RepositoryAlumni;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
 * FXML Controller class
 *
 * @author pace
 */
@Component
public class DetailsalumniController implements BootFormInitializable {

    private ApplicationContext springContext;

    @Autowired
    private RepositoryAdmin repoadmin;

    @Autowired
    private RepositoryAlumni repoalumni;

    @Autowired
    private GraduatesController graduates;

    private DataAdmin dtadmin;

    @FXML
    private Label txtnpm;

    @FXML
    private Label txtnama;

    @FXML
    private Label txtjk;

    @FXML
    private Label txtprodi;

    @FXML
    private Label txtbulan;

    @FXML
    private Label txttahun;

    @FXML
    private Label txtemail;

    @FXML
    private Label txttanggaldaftar;

    @FXML
    private Label txtlastupdate;

    @FXML
    private Label txtdidaftarkanoleh;

    private DataAlumni akun;

    public DataAlumni getAkun() {
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
        this.txtnpm.setText(graduates.txtnpm.getText());
        tampil();
    }

    @Override
    public void initConstruct() {
        this.akun = new DataAlumni();

    }

    @Override
    public void stage(Stage primaryStage) {

    }

    @Override
    public Node initView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Function.DETAILSALUMNI));
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

    private void tampil() {
        try {
            DataAlumni findByNPM = repoalumni.findBynpm(graduates.txtnpm.getText());

            this.txtnpm.setText(findByNPM.getNpm());
            this.txtnama.setText(findByNPM.getFullname());
            this.txtjk.setText(findByNPM.getJk());
            this.txtprodi.setText(findByNPM.getProdi());
            this.txtbulan.setText(findByNPM.getBulan());
            this.txttahun.setText(findByNPM.getTahun().toString());
            this.txtemail.setText(findByNPM.getEmail());
            this.txttanggaldaftar.setText(findByNPM.getCreatedate().toString());
            this.txtlastupdate.setText(findByNPM.getLastupdate().toString());
            this.txtdidaftarkanoleh.setText(findByNPM.getId_admin());

            Optional<DataAdmin> findById = repoadmin.findById(txtdidaftarkanoleh.getText());
            this.txtdidaftarkanoleh.setText(findById.get().getFullname() + "   " + " ( " + findByNPM.getId_admin() + " )");

        } catch (Exception ex) {

        }

    }

    @Override
    public void initValidator() {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
