module client.views {
    requires javafx.controls;
    requires javafx.fxml;


    opens client.views to javafx.fxml;
    exports client.views;
}