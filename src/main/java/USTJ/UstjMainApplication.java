/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USTJ;

import USTJ.Controller.App.WellcomeController;
import USTJ.Function.Function;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author pace
 */
@SpringBootApplication
public class UstjMainApplication extends Application {

    private final Logger loger = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext springContext = null;

    private static String[] argumen;

    @Override
    public void start(Stage primaryStage) {
        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() {
                springContext = SpringApplication.run(UstjMainApplication.class, argumen);
                return null;
            }

        };
        task.setOnSucceeded(e -> {
            this.loger.info("Running USTJ Tracer Study Application Success ....");
            WellcomeController controller = springContext.getBean(WellcomeController.class);
            Parent parent = (Parent) controller.initView();
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.setTitle(Function.APPNAME);
            primaryStage.getIcons().add(new Image(getClass().getResource(Function.LOGOUSTJ).toExternalForm()));
            primaryStage.setResizable(false);
            primaryStage.show();

            controller.showLoginForm();

        });
        task.setOnFailed(e -> {
            this.loger.error("Application force stop ...");
            System.exit(0);
            Platform.exit();
        });

        task.run();
    }

    public static void main(String[] args) {
        UstjMainApplication.argumen = args;
        launch(args);

    }

}
