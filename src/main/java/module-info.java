module com.example.apro2_22l_pro_pt_azulpro {
    requires javafx.controls;
    requires javafx.fxml;


    opens GUI to javafx.fxml;
    exports GUI.GUI;
    opens GUI.GUI to javafx.fxml;
}