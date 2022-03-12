package isen.contactApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Quentin DUMESGE, Hugo DEMENEZ, Alban DUHAMEL
 *
 */
public class App extends Application {

	private static Scene scene;
	public static Stage stage;
	private static BorderPane mainLayout;

	@Override
	public void start(Stage stage) throws IOException {

		// Init the app stage
		App.stage = stage;

		stage.setResizable(false);

		// Loading custom fonts accessed with the css but not fully working (didn't figure out why)
		Font.loadFont(getClass().getResourceAsStream("isen/contactApp/view/fonts/Hellix-Regular.otf"), 10);
		Font.loadFont(getClass().getResourceAsStream("isen/contactApp/view/fonts/Hellix-ExtraBold.otf"), 10);
		Font.loadFont(getClass().getResourceAsStream("isen/contactApp/view/fonts/Hellix-Bold.otf"), 10);
		Font.loadFont(getClass().getResourceAsStream("isen/contactApp/view/fonts/Hellix-SemiBold.otf"), 10);

		// Setting up app icon
		stage.getIcons().add(new Image("isen/contactApp/images/group_add.png"));

		// Setting up app title
		stage.setTitle("The Best Contact App");


		// Load the main layout from file
		mainLayout = loadFXML("HeaderBar");

		// Defining main Scene with its layout
		scene = new Scene(mainLayout, 1200, 600);

		// Setting up the scene to the app / stage
		stage.setScene(scene);

		// Update the stage
		stage.show();

		// Update the center of the layout
		App.showView("ContactManager");
	}


	// Method to load the fxml file named with the parameter name
	private static <T> T loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/isen/contactApp/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}


	// Method to return the FXMLLoader with the FXML file named with the parameter name
	public static FXMLLoader FXMLloader(String fxml){
		return new FXMLLoader(App.class.getResource("/isen/contactApp/view/" + fxml + ".fxml"));
	}


	// Method to access to the current scene
	public static Scene getScene() {
		return scene;
	}


	// Method to access the mainlayout
	public static BorderPane getMainLayout() {
		return mainLayout;
	}


	// Method to start the app
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
