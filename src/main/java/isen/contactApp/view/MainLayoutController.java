package isen.contactApp.view;

import isen.contactApp.App;
import javafx.application.Platform;

public class MainLayoutController {

	public void closeApplication() {
		Platform.exit();
	}

	public void gotoHome() {
		App.showView("HomeScreen");
	}

	public void gotoContactManager() {
		App.showView("ContactManager");
	}

}
