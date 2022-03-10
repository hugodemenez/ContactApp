package isen.contactApp.util;

import isen.contactApp.entities.Contact;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class phoneNumberValueFactory
		implements Callback<CellDataFeatures<Contact, String>, ObservableValue<String>> {

	@Override
	public ObservableValue<String> call(CellDataFeatures<Contact, String> cellData) {
		return new SimpleStringProperty(cellData.getValue().getPhone_number());
	}
}
