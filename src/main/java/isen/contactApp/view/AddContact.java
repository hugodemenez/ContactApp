package isen.contactApp.view;

import isen.contactApp.App;
import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.entities.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.ZoneId;

public class AddContact {
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


    public void fillContactFields(Contact contact){


        lastName.setText(contact.getLastname());

    }
}
