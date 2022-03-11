package isen.contactApp.view;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.StructuredName;
import isen.contactApp.App;
import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.entities.Contact;
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



    @FXML
    public void initialize(){
        gender.getItems().add("Man");
        gender.getItems().add("Woman");
        gender.getItems().add("Unset");
    }

    @FXML
    public void initializeContactData(Contact contact){
        lastName.setText(contact.getLastname());
        firstName.setText(contact.getFirstname());
        emailAddress.setText(contact.getEmail_address());
        phoneNumber.setText(contact.getPhone_number());
        birthDate.setValue(contact.getBirth_date().toLocalDate());
        address.setText(contact.getAddress());
        nickName.setText(contact.getNickname());
        addButton.setText("Update");
        avatar.setImage(new Image("/isen/contactApp/images/"+contact.getAvatarName()+".png"));
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

        vcard.setFormattedName("John Doe");

        String str = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
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
        new ContactsDAOs().addContactToDb(new Contact(lastName.getText(), firstName.getText(), nickName.getText(), phoneNumber.getText(),
                address.getText(), emailAddress.getText(), new java.sql.Date(date.getTime()), gender.getValue()));

        gotoHome();
    }



    public void gotoHome() {
        App.showView("ContactManager");
    }



}
