package isen.contactApp.util;

import isen.contactApp.entities.Contact;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class ContactsChangeListener implements ChangeListener<Contact> {

	@Override
	public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
		handleNewValue(newValue);

	}

	public abstract void handleNewValue(Contact newValue);

}
