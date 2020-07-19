package sethacks;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Group;
import javafx.scene.Scene;
	

public class Main extends Application {
	
	public static String mainName = "main";
	public static String mainFile = "mainMenu.fxml";
	
	public static String findName = "find";
	public static String findFile = "findMenu.fxml";
	
	public static String postingName = "posting";
	public static String postingFile = "PostingDetails.fxml";
	
	@Override
	public void start(Stage primaryStage) {
		
		ScreenController mainController = new ScreenController();
		
		mainController.loadScreen(Main.mainName, Main.mainFile, primaryStage);
		mainController.loadScreen(Main.findName, Main.findFile, primaryStage);
		mainController.loadScreen(Main.postingName, Main.postingFile, primaryStage);
		
		mainController.setScreen(Main.mainName, Main.mainFile);
		
		Group root = new Group();
		root.getChildren().addAll(mainController);
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
