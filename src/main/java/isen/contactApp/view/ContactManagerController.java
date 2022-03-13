package isen.contactApp.view;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import isen.contactApp.App;
import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.entities.Contact;
import isen.contactApp.entities.ListContacts;
import isen.contactApp.service.ContactService;
import isen.contactApp.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Quentin DUMESGE, Hugo DEMENEZ, Alban DUHAMEL
 *
 */
public class ContactManagerController {

    @FXML
    private ComboBox<String> filter;

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
    private ProgressIndicator indicator;

    @FXML
    private DialogPane toastPane;

    @FXML
    private TextField searchField;

    // Refresh the table with contacts and clear selection
    @FXML
    public void refreshContactTable(){
        // Contact table refresh
        contactsTable.refresh();
        contactsTable.getSelectionModel().clearSelection();

    }






    // Populate list with contacts inside the database
    @FXML
    public void populateContactTable(){
        contactsTable.setItems(ContactService.updateContacts(filter.getValue()));
        refreshContactTable();
    }

    // Method called on load of the controller
    @FXML
    private void initialize(){

        // Setting up the columns
        addressColumn.setCellValueFactory(new addressValueFactory());
        birthDateColumn.setCellValueFactory(new birthDateValueFactory());
        firstNameColumn.setCellValueFactory(new firstNameValueFactory());
        emailAddressColumn.setCellValueFactory(new emailAddressValueFactory());
        firstNameColumn.setCellValueFactory(new firstNameValueFactory());
        lastNameColumn.setCellValueFactory(new lastNameValueFactory());
        nickNameColumn.setCellValueFactory(new nickNameValueFactory());
        phoneNumberColumn.setCellValueFactory(new phoneNumberValueFactory());

        populateContactTable();

        contactsTable.getSelectionModel().selectedItemProperty().addListener(new ContactsChangeListener() {
            @Override
            public void handleNewValue(Contact newValue) {
                // There was an error because view was not fully loaded
                // So we created a new loader
                FXMLLoader loader = App.FXMLloader("AddContact");


                try {
                    // Load the AddContact FXML
                    App.getMainLayout().setCenter(loader.load());

                    // Get the controller linked with the FXML
                    AddContactController controller = loader.getController();

                    // Access to the method described inside the controller
                    controller.initializeContactData(newValue);

                } catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });

        initialize_filter();



        //Initial search list
        searchField.textProperty().addListener((observable,oldValue,newValue)->{
                contactsTable.setItems(ContactService.getContacts().filtered(contact -> {
                        //If no search value is entered then it displays everything
                        if(newValue.isEmpty() || newValue.isBlank() || newValue==null) {
                            return true;
                        }

                        String searchKeyword = newValue.toLowerCase();
                        if(contact.getLastname().toLowerCase().contains(searchKeyword)) {
                            return true; //Found a match in Lastname
                        } else if(contact.getFirstname().toLowerCase().contains(searchKeyword)) {
                            return true; //Found a match in Firstname
                        }else if(contact.getNickname().toLowerCase().contains(searchKeyword)) {
                            return true; //Found a match in Nickname
                        }else if(contact.getPhone_number().toLowerCase().contains(searchKeyword)) {
                            return true; //Found a match in PhoneNumber
                        }else {
                            return false; //no match found
                        }
                    })
                );
            refreshContactTable();

            }
        );


    }

    public void initialize_filter(){
        filter.getItems().add("All");

        for(String listName : ContactsDAOs.getContactListsFromDb()){
            if (!filter.getItems().contains(listName)) {
                filter.getItems().add(listName);
            }
        }

    }


    // Methode called when clicking on the Add button
    @FXML
    public void handleClickAddContact() {
        App.showView("AddContact");
    }



    // Method to export all contacts inside the contact table in a VCard formatted file and opens up the file explorer to locate the file
    @FXML
    public void handleClickExportContacts(){
        List<VCard> vcards = new ArrayList<VCard>();


        // Checking list size, if empty toast the user
        if(contactsTable.getItems().isEmpty()){
            Toast.makeText(
                    toastPane,
                    "Export Error",
                    "There is not contact to export !",
                    1500,
                    500,
                    500);
        }

        for(Contact contact : contactsTable.getItems()){
            VCard vcard = contact.generateVcard();
            vcards.add(vcard);
        }


        // Setting up the path name
        File file = new File("contacts.vcf");
        try {
            // Generating the VCard
            Ezvcard.write(vcards).prodId(false).go(file);

            // Setting up the indicator to 100 %
            indicator.setProgress(1F);

            // Opens Finder / Explorer when export is finished
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("./"));
            }

        } catch (IOException e) {
            // If there is an exception we toast the user
            Toast.makeText(
                    toastPane,
                    "Export Error",
                    "Unable to export contact",
                    1500,
                    500,
                    500);
        }
    }


    // Change view to the FXML linked to the controller ContactManager
    public static void goTo(){
        App.showView("ContactManager");
    }


}
