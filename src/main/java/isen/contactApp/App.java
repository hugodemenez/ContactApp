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
 * @author Philippe Duval
 * 
 *         This is our main class. It extends Application, which is a JavaFX
 *         class, and thus implements the inherited methods
 *
 */
public class App extends Application {

	private static Scene scene;
	public static Stage stage;
	private static BorderPane mainLayout;

	@Override
	public void start(Stage stage) throws IOException {
		App.stage = stage;
		Font.loadFont(getClass().getResourceAsStream("isen/contactApp/view/fonts/Hellix-Regular.otf"), 10);
		Font.loadFont(getClass().getResourceAsStream("isen/contactApp/view/fonts/Hellix-ExtraBold.otf"), 10);
		Font.loadFont(getClass().getResourceAsStream("isen/contactApp/view/fonts/Hellix-Bold.otf"), 10);
		Font.loadFont(getClass().getResourceAsStream("isen/contactApp/view/fonts/Hellix-SemiBold.otf"), 10);
		stage.getIcons().add(new Image("file:resources/isen.contactApp/images/group_add.png"));
		stage.setTitle("The Best Contact App");
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

	public static FXMLLoader FXMLloader(String fxml){
		return new FXMLLoader(App.class.getResource("/isen/contactApp/view/" + fxml + ".fxml"));
	}

	public static Scene getScene() {
		return scene;
	}

	public static BorderPane getMainLayout() {
		return mainLayout;
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
