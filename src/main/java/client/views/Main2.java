package client.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("game-view2.fxml"));
        primaryStage.setTitle("player 2");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}
