package isen.contactApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Philippe Duval
 * 
 *         This is our main class. It extends Application, which is a JavaFX
 *         class, and thus implements the inherited methods
 *
 */
public class App extends Application {

	private static Scene scene;

	private static BorderPane mainLayout;

	@Override
	public void start(Stage stage) throws IOException {

		stage.setTitle("Contact App for a better future");
		// Load the main layout from file
		mainLayout = loadFXML("HeaderBar");

		scene = new Scene(mainLayout, 1200, 600);
		stage.setScene(scene);
		stage.show();

		App.showView("ContactManager");
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}


	private static <T> T loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/contactApp/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

	/**
	 * @param rootElement updates the center of our layout with the @rootElement
	 *                    passed in parameter
	 */
	public static void showView(String rootElement) {
		try {

			mainLayout.setCenter(loadFXML(rootElement));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
