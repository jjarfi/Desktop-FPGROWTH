/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ.Configuration;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSourceAware;

/**
 *
 * @author pace
 */
public interface BootInitializable extends Initializable, ApplicationContextAware, MessageSourceAware {
    
    public void initConstruct();
    
    public void stage(Stage primaryStage);
    
    public Node initView();

}
