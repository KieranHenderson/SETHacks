package sethacks;

import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class Screens extends ScreenController {
		
	ScreenController controller; //my controller object that controls all the screens 
	
	static String pfpImage = "images/defaultPFP.png";
	
	static int score = 0;
	static int xpValue = 0;
	static String xp = "0/100";
	static String name = "Kieran";
	
	static String clickedLink = "";
	static HashMap<String, String[]> companyInfo = new HashMap<String, String[]>();
	
	String currentPage = Main.mainName;
	
	public void hyperlinkClickHandler(ActionEvent e) {
		Hyperlink clickedButton = (Hyperlink) e.getTarget(); //gets the hyperlink that was clicked 
		String buttonLable = clickedButton.getText(); //get the text of the clicked hyperlink

		controller.setScreen(Main.postingName, Main.postingFile);
		currentPage = Main.postingName;
		clickedLink = buttonLable;
	}
	
	public void buttonClickHandler(ActionEvent e) {
		Button clickedButton = (Button) e.getTarget(); //gets the Button that was clicked 
		String buttonLable = clickedButton.getText(); //get the text of the clicked Button
		
		if("Back".equals(buttonLable)) {
			if(currentPage == Main.findName) {
				System.out.println("XD");
				controller.setScreen(Main.mainName, Main.mainFile);
				currentPage = Main.mainName;
			} else {
				controller.setScreen(Main.findName, Main.findFile);
				currentPage = Main.findName;
			}
		} 
		
		else if ("FIND".equals(buttonLable)) {
			controller.setScreen(Main.findName, Main.findFile);
			currentPage = Main.findName;
		}
	}

	/* getters and setters */
	public void getScene(Stage primaryStage) {
		gameScene = primaryStage.getScene();
	}
	
	public void setScreenParent(ScreenController screenPage) {
		controller = screenPage;
	}
}
