module isen.contactapp {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires org.xerial.sqlitejdbc;

    exports isen.contactApp;
    opens isen.contactApp to javafx.fxml;
    exports isen.contactApp.view;
    opens isen.contactApp.view to javafx.fxml;
    exports isen.contactApp.model;
    opens isen.contactApp.model to javafx.fxml;
}