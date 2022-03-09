package isen.contactApp.view;

import isen.contactApp.App;

import isen.db.entities.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class ContactManagerController {

    public void addContact() {
        App.showView("AddContact");
    }

}
