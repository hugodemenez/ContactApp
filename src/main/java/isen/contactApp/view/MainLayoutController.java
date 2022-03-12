package isen.contactApp.view;

import isen.contactApp.App;
import javafx.application.Platform;

/**
 * @author Quentin DUMESGE, Hugo DEMENEZ, Alban DUHAMEL
 *
 */
public class MainLayoutController {

	public void closeApplication() {
		Platform.exit();
	}

	public void gotoHome() {
		ContactManagerController.goTo();
	}


}
