package isen.contactApp.view;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import isen.contactApp.App;
import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.entities.Contact;
import isen.contactApp.service.ContactService;
import isen.contactApp.util.Toast;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneId;
import java.util.Locale;

/**
 * @author Quentin DUMESGE, Hugo DEMENEZ, Alban DUHAMEL
 *
 */
public class AddContactController {
    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField phoneNumber;


    @FXML
    private DatePicker birthDate;

    @FXML
    ChoiceBox<String> gender;

    @FXML
    private TextField address;

    @FXML
    private TextField nickName;

    @FXML
    private Button addButton;

    @FXML
    private ImageView avatar;

    private Contact contact;

    @FXML
    private ProgressIndicator indicator;

    @FXML
    private ComboBox<String> filter;


    @FXML
    private AnchorPane toastPane;

    @FXML
    public void initialize(){

        // Setting up filter options
        for(String listName : ContactsDAOs.getContactListsFromDb()){
            if (!filter.getItems().contains(listName)) {
                filter.getItems().add(listName);
            }
        }

        gender.getItems().add("Man");
        gender.getItems().add("Woman");
        gender.getItems().add("Unset");
        gender.setValue("Man");

        gender.getSelectionModel()
                .selectedItemProperty()
                //.addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> System.out.println("/isen/contactApp/view/user_"+newValue.toLowerCase(Locale.ROOT)+".png"));
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (newValue!=null){
                    avatar.setImage(new Image("/isen/contactApp/images/user_"+newValue.toLowerCase(Locale.ROOT)+".png"));
                    }
                });
    }

    // Method to fill all the known information from contact
    @FXML
    public void initializeContactData(Contact contact){
        this.contact = contact;
        lastName.setText(contact.getLastname());
        firstName.setText(contact.getFirstname());
        emailAddress.setText(contact.getEmail_address());
        phoneNumber.setText(contact.getPhone_number());
        birthDate.setValue(contact.getBirth_date().toLocalDate());
        address.setText(contact.getAddress());
        nickName.setText(contact.getNickname());
        addButton.setText("Update");
        gender.setValue(contact.getGender());
        filter.setValue(contact.getFilter());
    }


    // Function to call when clicking on export button
    public void handleClickExportVCard() throws IOException {

        // If the contact is created we are able to create its vCard otherwise we toast the user
        if(contact!=null) {
            VCard vcard = contact.generateVcard();
            //write vCard
            File file = new File(contact.getLastname() + contact.getFirstname() + ".vcf");
            Ezvcard.write(vcard).version(VCardVersion.V3_0).go(file);

            // Setting progress indicator to 100%
            indicator.setProgress(1F);

            // Opens Finder / Explorer when export is finished
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("./"));
            }
        }
        else{
            Toast.makeText(toastPane,"Export error" ,"Contact not created yet !", 1500, 500, 500);
        }
    }

    // Function to call when clicking on delete button
    public void handleClickDelete(){
        // Setting cursor on the first element
        lastName.requestFocus();

        // Clearing fields
        lastName.clear();
        firstName.clear();
        emailAddress.clear();
        phoneNumber.clear();
        birthDate.setValue(null);
        address.clear();
        nickName.clear();



        // Remove contact from the db if we have selected a contact
        if (contact !=null) {
            ContactService.removeContact(contact);
            ContactManagerController.goTo();
        }

        Toast.makeText(toastPane,"Deletion success" ,"Contact successfully deleted !", 1500, 500, 500);

    }


    // Function to call when clicking on image
    public void handleClickChangeImage(){

        /* Not used yet, instead (changed image depending on gender)

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(App.stage);
        System.out.println(selectedFile.getAbsolutePath());
        avatar.setImage(new Image(selectedFile.getAbsolutePath()));
         */
    }


    // Function to call when clicking on add button
    public void handleClickAddContact(){

        try{
            // If contact instantiation doesn't work, we can't continue
            Contact newContact = new Contact(
                    lastName.getText(),
                    firstName.getText(),
                    nickName.getText(),
                    phoneNumber.getText(),
                    address.getText(),
                    emailAddress.getText(),
                    new java.sql.Date(Date.from(birthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()),
                    gender.getValue(),
                    filter.getValue()
            );
            // If contact not null we are deleting the existing contact
            if(contact!=null){
                // First remove contact
                ContactService.removeContact(contact);
                // Then do the same as adding a new contact
            }

            // Collecting fields to instantiate contact Class to pass into addContact from ContactService class

            // Add contact with the service (database + contact instance) with the fully loaded contact (generated id + contact data)
            ContactService.addContact(newContact);
            // Change view
            goToContactManager();

        }
        catch(Exception exception){
            Toast.makeText(
                    toastPane,
                    "Contact creation issue",
                    "Unable to create contact due to missing field",
                    1500,
                    500,
                    500
            );
        }

    }


    public void goToContactManager() {
        ContactManagerController.goTo();
    }

}
