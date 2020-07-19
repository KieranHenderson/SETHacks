package sethacks;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FindController extends Screens implements Initializable, ControlledScreen  {
	
	@FXML
	AnchorPane root;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		File directory = new File("C:\\Users\\Joe\\Desktop\\Computer Science From Highschool\\Grade 12 CompSci\\Workspaces\\SETHacks\\src\\images\\charityPFP");
		
	    int fileCount = directory.list().length;
	    
	    ScrollPane sp = new ScrollPane();
	    sp.setPrefSize(480, 740);
	    sp.setPrefViewportWidth(480);
	    sp.setPannable(true);
	    sp.setHbarPolicy(ScrollBarPolicy.NEVER);
	    sp.setVbarPolicy(ScrollBarPolicy.NEVER);
	    sp.setFitToWidth(true);
	    
    	VBox vBox = new VBox();
    	vBox.setPrefWidth(root.getPrefWidth());
    	
    	Button back = new Button();
    	back.setText("Back");
    	back.setLayoutX(15);
    	back.setLayoutY(760);
    	back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonClickHandler(event);
            }
        });
	    
    	root.getChildren().addAll(sp, back);
    	root.getStylesheets().add("sethacks/application.css");
    	sp.setContent(vBox);
    	    	
    	int rewardAmount = 20;
    	
	    for (int i = 0; i < fileCount; i++) {
	    	
	    	String fileName = directory.list()[i].substring(0, directory.list()[i].length()-4);
	    	String[] fileInfo = new String[] {"/images/charityPFP/"+directory.list()[i].toString(), "CompanyInfo/"+fileName+".txt", "images/maps/"+directory.list()[i].toString()};
	    	Screens.companyInfo.put(fileName, fileInfo);
	    	
	    	HBox hBox = new HBox();
	    	hBox.getStylesheets().add("sethacks/application.css");
	    	hBox.setId("hbox");
	    	hBox.setPrefHeight(100);
	    	hBox.setAlignment(Pos.CENTER_LEFT);
	    	
	    	Canvas pictureCanvas = new Canvas();
	    	pictureCanvas.setHeight(100);
	    	pictureCanvas.setWidth(100);
	    	
	    	Hyperlink name = new Hyperlink();
	    	name.setText(fileName);
	    	name.setId("hyperlink");
	    	name.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                hyperlinkClickHandler(event);
	            }
	        });
	    	
	    	Canvas moneyIcon = new Canvas();
	    	moneyIcon.setWidth(30);
	    	moneyIcon.setHeight(30);
	    	
	    	Text reward = new Text();
	    	reward.setId("text");
	    	reward.setText(Integer.toString(rewardAmount));
	    	reward.setTextAlignment(TextAlignment.RIGHT);
	    	rewardAmount += 20;
	    	
	    	GraphicsContext pictureGc = pictureCanvas.getGraphicsContext2D();
	    	GraphicsContext iconGc = moneyIcon.getGraphicsContext2D();
	    	
	    	Image pfp = new Image("/images/charityPFP/"+directory.list()[i]);
	    	pictureGc.drawImage(pfp, 0, pictureCanvas.getHeight()/2 - pfp.getHeight()/2);
	    	
	    	Image icon = new Image("/images/coin.png");
	    	iconGc.drawImage(icon, 0, moneyIcon.getHeight()/2 - icon.getHeight()/2);
	
	    	vBox.getChildren().add(hBox);
	    	hBox.getChildren().addAll(pictureCanvas,name,moneyIcon,reward);
	    }
		
	}

}
