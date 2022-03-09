package isen.contactApp.view;

import isen.db.daos.ContactsDAOs;
import isen.db.entities.Contact;

public class AddContact {


    public void handleClickAddContact(){
        // Recupération des champs pour créer la classe contact qu'on passe en parametre de la classe ContactsDAOs

        new ContactsDAOs().addContact(new Contact());
    }
}
