package isen.contactApp.view;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.StructuredName;
import isen.contactApp.App;
import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.entities.Contact;
import isen.contactApp.service.ContactService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.Date;
import java.time.ZoneId;

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
    public void initialize(){
        gender.getItems().add("Man");
        gender.getItems().add("Woman");
        gender.getItems().add("Unset");
        gender.setValue("Man");
    }

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
    }


    // Function to call when clicking on export button
    public void handleClickExportVCard(){
        VCard vcard = new VCard();

        StructuredName n = new StructuredName();
        n.setFamily(lastName.getText());
        n.setGiven(firstName.getText());
        n.getPrefixes().add(gender.getValue());
        vcard.setStructuredName(n);

        vcard.setFormattedName(lastName.getText()+firstName.getText());

        String str = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
    }

    // Function to call when clicking on delete button
    public void handleClickDelete(){
        lastName.clear();
        firstName.clear();
        emailAddress.clear();
        phoneNumber.clear();
        birthDate.requestFocus();
        address.clear();
        nickName.clear();
        addButton.setText("Add");
        gender.hide();


        // Remove contact from the db if we have selected a contact
        if (contact !=null) {
            ContactService.removeContact(contact);
            ContactManagerController.goTo();
        }

    }


    // Function to call when clicking on image
    public void handleClickChangeImage(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(App.stage);
        System.out.println(selectedFile.getAbsolutePath());
        avatar.setImage(new Image(selectedFile.getAbsolutePath()));
    }


    // Function to call when clicking on add button
    public void handleClickAddContact(){


        // Collecting fields to instantiate contact Class to pass into addContactToDb from ContactsDAOs class
        java.util.Date date = Date.from(birthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                // Add contact to database through the DAO


        // Add contact with the service (database + contact instance) with the fully loaded contact (generated id + contact data)
        ContactService.addContact(new Contact(lastName.getText(), firstName.getText(), nickName.getText(), phoneNumber.getText(),
                address.getText(), emailAddress.getText(), new java.sql.Date(date.getTime()), gender.getValue()));

        // Change view
        goToContactManager();
    }


    public void goToContactManager() {
        ContactManagerController.goTo();
    }



}
