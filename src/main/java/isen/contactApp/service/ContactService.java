package isen.contactApp.service;

import java.sql.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import isen.contactApp.daos.ContactsDAOs;
import isen.contactApp.entities.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Quentin DUMESGE, Hugo DEMENEZ, Alban DUHAMEL
 *
 */
public class ContactService {

	private final ObservableList<Contact> contacts;

	private ContactService() {
		contacts = FXCollections.observableArrayList();
		List<Contact> listOfContacts = ContactsDAOs.getContactsFromDb();
		contacts.addAll(listOfContacts);

		/*
		contacts.add(new Contact(0, "DEMENEZ", "Hugo", "H.DEMENEZ", "0600000000", "5 rue de l'avenue", "hugo.demenez@student.junia.com", Date.valueOf("2022-03-09")));
		contacts.add(new Contact(1, "DUHAMMEL", "Alban", "A.DUHAMMEL", "0610000000", "6 rue de l'avenue", "alban.duhamel@student.junia.com", Date.valueOf("2022-04-09")));
		contacts.add(new Contact(2, "DUMESGE", "Quentin", "Q.DUMESGE", "0620000000", "7 rue de l'avenue", "quentin.dumesge@student.junia.com", Date.valueOf("2022-05-09")));
		*/
	}

	public static ObservableList<Contact> getContacts() {

		return ContactServiceHolder.INSTANCE.contacts;
	}

	public static ObservableList<Contact> updateContacts(String filter){
		// If filtering
		if(filter!=null && !filter.equals("All")){
			List<Contact> filteredList = ContactServiceHolder.INSTANCE.contacts.stream()
					.filter(p -> filter.equals(p.getFilter())).toList();
			ObservableList<Contact> filteredContacts = FXCollections.observableArrayList();
			filteredContacts.addAll(filteredList);
			return  filteredContacts;

		}
		return getContacts();
	}


	// Method to add contact to the database and to the contacts instance
	public static void addContact(Contact contact) {
		// Add contact to the database and collect the generated id to add the fully loaded contact to the contact list
		ContactServiceHolder.INSTANCE.contacts.add(ContactsDAOs.addContactToDb(contact));
	}

	// Method to remove contact from the database and from the instance
	public static void removeContact(Contact contact) {
		// Remove contact from the contacts instance
		ContactServiceHolder.INSTANCE.contacts.remove(contact);

		// Remove contact from the database
		ContactsDAOs.removeContactFromDb(contact);
	}

	private static class ContactServiceHolder {
		private static final ContactService INSTANCE = new ContactService();
	}

}
