package isen.contactApp.view;

import isen.contactApp.App;
import isen.contactApp.entities.Contact;
import isen.contactApp.entities.ListContacts;
import isen.contactApp.service.ContactService;
import isen.contactApp.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Date;


public class ContactManagerController {

    @FXML
    private TableView<Contact> contactsTable;

    @FXML
    private TableColumn<Contact, String> lastNameColumn;

    @FXML
    private TableColumn<Contact, String> firstNameColumn;

    @FXML
    private TableColumn<Contact, Date> birthDateColumn;


    @FXML
    private TableColumn<Contact, String> phoneNumberColumn;

    @FXML
    private TableColumn<Contact, String> addressColumn;

    @FXML
    private TableColumn<Contact, String> nickNameColumn;

    @FXML
    private TableColumn<Contact, String> emailAddressColumn;



    @FXML
    private TableView<ListContacts> listsTable;


    @FXML
    public void refreshList(){
        contactsTable.refresh();
        contactsTable.getSelectionModel().clearSelection();
    }


    @FXML
    public void populateList(){
        contactsTable.setItems(ContactService.getContacts());
        refreshList();
    }


    @FXML
    private void initialize(){
        addressColumn.setCellValueFactory(new addressValueFactory());
        birthDateColumn.setCellValueFactory(new birthDateValueFactory());
        firstNameColumn.setCellValueFactory(new firstNameValueFactory());
        emailAddressColumn.setCellValueFactory(new emailAddressValueFactory());
        firstNameColumn.setCellValueFactory(new firstNameValueFactory());
        lastNameColumn.setCellValueFactory(new lastNameValueFactory());
        nickNameColumn.setCellValueFactory(new nickNameValueFactory());
        phoneNumberColumn.setCellValueFactory(new phoneNumberValueFactory());

        populateList();
        resetView();
    }


    @FXML
    public void resetView(){
        refreshList();
    }

    @FXML
    public void handleClickAddContact() {
        App.showView("AddContact");
    }

}
