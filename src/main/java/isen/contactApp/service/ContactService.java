package isen.contactApp.service;

import java.sql.Date;
import java.util.List;

import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.entities.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactService {

	private ObservableList<Contact> contacts;

	private ContactService() {
		contacts = FXCollections.observableArrayList();
		List<Contact> listOfContacts = new ContactsDAOs().getContactsFromDb();
		contacts.addAll(listOfContacts);

		/*
		contacts.add(new Contact(0, "DEMENEZ", "Hugo", "H.DEMENEZ", "0600000000", "5 rue de l'avenue", "hugo.demenez@student.junia.com", Date.valueOf("2022-03-09")));
		contacts.add(new Contact(1, "DUHAMMEL", "Alban", "A.DUHAMMEL", "0610000000", "6 rue de l'avenue", "alban.duhamel@student.junia.com", Date.valueOf("2022-04-09")));
		contacts.add(new Contact(2, "DUMESGE", "Quentin", "Q.DUMESGE", "0620000000", "7 rue de l'avenue", "quentin.dumesge@student.junia.com", Date.valueOf("2022-05-09")));
		*/
	}

	public static ObservableList<Contact> getContacts() {
		return QuestionServiceHolder.INSTANCE.contacts;
	}

	public static void addContact(Contact question) {
		QuestionServiceHolder.INSTANCE.contacts.add(question);
	}

	private static class QuestionServiceHolder {
		private static final ContactService INSTANCE = new ContactService();
	}

}
