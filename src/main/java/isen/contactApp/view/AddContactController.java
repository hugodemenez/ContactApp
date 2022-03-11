package isen.contactApp.view;

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
import java.util.Objects;

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
    private ChoiceBox<String> gender;

    @FXML
    private TextField address;

    @FXML
    private TextField nickName;

    @FXML
    private Button addButton;

    @FXML
    private ImageView avatar;

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
        avatar.setImage(new Image(""));
    }

    public void handleClickChangeImage(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(App.stage);
        System.out.println(selectedFile.getAbsolutePath());
        avatar.setImage(new Image(selectedFile.getAbsolutePath()));
    }

    public void handleClickAddContact(){
        // Collecting fields to instantiate contact Class to pass into addContactToDb from ContactsDAOs class
        java.util.Date date = Date.from(birthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                // Add contact to database through the DAO
        new ContactsDAOs().addContactToDb(new Contact(lastName.getText(), firstName.getText(), nickName.getText(), phoneNumber.getText(),
                address.getText(), emailAddress.getText(), new java.sql.Date(date.getTime()) ));

        gotoHome();
    }

    public void gotoHome() {
        App.showView("ContactManager");
    }



}
