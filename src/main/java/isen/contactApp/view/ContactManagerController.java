package isen.contactApp.view;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.StructuredName;
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
import java.util.Collection;
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
                // There was an error because view was not fully loaded
                // So we created a new loader
                FXMLLoader loader = App.FXMLloader("AddContact");

                try {
                    App.getMainLayout().setCenter(loader.load());
                    AddContactController controller = loader.getController();
                    controller.initializeContactData(newValue);

                } catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });

    }

    @FXML
    public void handleClickAddContact() {
        App.showView("AddContact");
    }




    @FXML
    public void handleClickExportContacts(){
        List<VCard> vcards = new ArrayList<VCard>();

        if(contactsTable.getItems().isEmpty()){
            Toast.makeText(App.stage,"Export Error","There is not contact to export !",1500,500,500);
        }

        for(Contact contact : contactsTable.getItems()){
            VCard vcard = contact.generateVcard();
            vcards.add(vcard);
        }



        File file = new File("contacts.vcf");
        try {
            Ezvcard.write(vcards).prodId(false).go(file);
            indicator.setProgress(1F);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("./"));
            }

        } catch (IOException e) {
            Toast.makeText(App.stage,"Export Error","Unable to export contact",1500,500,500);
        }
    }

    public static void goTo(){
        App.showView("ContactManager");
    }


}
