package client.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        primaryStage.setTitle("Azul");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        primaryStage.show();

    }

}
