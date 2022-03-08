module com.example.contactapp {
    requires javafx.controls;
    requires javafx.fxml;



    exports isen.contactApp;
    opens isen.contactApp to javafx.fxml;
    exports isen.contactApp.view;
    opens isen.contactApp.view to javafx.fxml;
    exports isen.contactApp.model;
    opens isen.contactApp.model to javafx.fxml;
}