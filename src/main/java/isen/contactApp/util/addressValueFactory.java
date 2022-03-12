package isen.contactApp.util;

import isen.contactApp.entities.Contact;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

/**
 * @author Quentin DUMESGE, Hugo DEMENEZ, Alban DUHAMEL
 *
 */
public class addressValueFactory
		implements Callback<CellDataFeatures<Contact, String>, ObservableValue<String>> {

	@Override
	public ObservableValue<String> call(CellDataFeatures<Contact, String> cellData) {
		return new SimpleStringProperty(cellData.getValue().getAddress());
	}
}
