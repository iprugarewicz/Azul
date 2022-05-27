module com.example.apro2_22l_pro_pt_azulpro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.GUI to javafx.fxml;
    exports com.example.GUI;
}