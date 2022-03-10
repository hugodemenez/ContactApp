package isen.contactApp.view;

import isen.contactApp.App;
import isen.contactApp.entities.Contact;
import isen.contactApp.entities.ListContacts;
import isen.contactApp.service.ContactService;
import isen.contactApp.util.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
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

        contactsTable.getSelectionModel().selectedItemProperty().addListener(new ContactsChangeListener() {
            @Override
            public void handleNewValue(Contact newValue) {
                App.showView("AddContact");

                // There is an error because view not fully loaded

                /*
                Task<String> task = new Task<String>() {
                    @Override
                    public String call() throws InterruptedException {
                        Thread.sleep(2000);
                        return "page loaded";
                    }
                };

                task.setOnSucceeded(e ->
                        new AddContact().fillContactFields(newValue));
                task.setOnFailed(e ->
                        System.out.println("unable to process"));



                new Thread(task).start();
                */




            }
        });
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
