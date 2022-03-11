package isen.contactApp.view;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import isen.contactApp.App;
import isen.contactApp.entities.Contact;
import isen.contactApp.entities.ListContacts;
import isen.contactApp.service.ContactService;
import isen.contactApp.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
    private ProgressIndicator indicator;

    @FXML
    private TableView<ListContacts> listsTable;


    // Refresh the table with contacts and clear selection
    @FXML
    public void refreshList(){
        contactsTable.refresh();
        contactsTable.getSelectionModel().clearSelection();
    }

    // Populate list with contacts inside the database
    @FXML
    public void populateList(){
        contactsTable.setItems(ContactService.getContacts());
        refreshList();
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

        populateList();

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
            Toast.makeText(App.stage,"Export Error","There is not contact to export !",1500,500,500);
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
            Toast.makeText(App.stage,"Export Error","Unable to export contact",1500,500,500);
        }
    }


    // Change view to the FXML linked to the controller ContactManager
    public static void goTo(){
        App.showView("ContactManager");
    }


}
