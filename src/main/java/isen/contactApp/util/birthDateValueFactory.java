package isen.contactApp.util;

import isen.contactApp.entities.Contact;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.sql.Date;

public class birthDateValueFactory
		implements Callback<CellDataFeatures<Contact, Date>, ObservableValue<Date>> {

	@Override
	public ObservableValue<Date> call(CellDataFeatures<Contact, Date> cellData) {
		return new SimpleObjectProperty<Date>(cellData.getValue().getBirth_date());
	}
}
