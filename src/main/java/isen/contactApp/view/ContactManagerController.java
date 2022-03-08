package isen.contactApp.view;

import isen.contactApp.model.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class ContactManagerController {

    @FXML
    private TableView<Contact> questionsTable;

    @FXML
    private TableColumn<Contact, String> contactColumn;

    @FXML
    private AnchorPane formPane;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField addressField;

    @FXML
    private Contact currentContact;




}
