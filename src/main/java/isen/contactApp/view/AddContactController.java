package isen.contactApp.view;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.StructuredName;
import isen.contactApp.App;
import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.entities.Contact;
import isen.contactApp.service.ContactService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneId;
import java.util.Locale;

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

    }


    // Function to call when clicking on export button
    public void handleClickExportVCard() throws IOException {
        VCard vcard = contact.generateVcard();
        //write vCard
        File file = new File(contact.getLastname()+contact.getFirstname()+".vcf");
        System.out.println("Writing " + file.getName() + "...");
        Ezvcard.write(vcard).version(VCardVersion.V3_0).go(file);
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

        // If contact not null we are deleting the existing contact
        if(contact!=null){
            // First remove contact
            ContactService.removeContact(contact);
            // Then do the same as adding a new contact
        }


        // Collecting fields to instantiate contact Class to pass into addContact from ContactService class

        // Add contact with the service (database + contact instance) with the fully loaded contact (generated id + contact data)
        ContactService.addContact(new Contact(lastName.getText(), firstName.getText(), nickName.getText(), phoneNumber.getText(),
                address.getText(), emailAddress.getText(), new java.sql.Date(Date.from(birthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()), gender.getValue()));




        // Change view
        goToContactManager();
    }


    public void goToContactManager() {
        ContactManagerController.goTo();
    }



}
