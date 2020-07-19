package sethacks;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PostingDetails extends Screens implements Initializable, ControlledScreen  {
	@FXML 
	AnchorPane root;
	@FXML
	Canvas pfp;
	@FXML
	Text description;
	@FXML
	Canvas map;
	
	GraphicsContext gcPfp;
	GraphicsContext gcMap;
	
	Image pfpIcon;
	Image mapImage;

	
	List<String> lines; //list from the high scores file when it is read


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lines = Collections.emptyList(); //declare the lines list 
		
		//pfpIcon = new Image(Screens.companyInfo.get(Screens.clickedLink)[0].toString());
		//System.out.println(Screens.companyInfo.get(Screens.clickedLink)[2]);
		//mapImage = new Image(Screens.companyInfo.get(Screens.clickedLink)[2]);

		gcPfp = pfp.getGraphicsContext2D();
		//gcMap = map.getGraphicsContext2D();
		
		loop();
		//description.setText();
	}
	
	public void loop() {
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				update();
				
				draw();

			}			
		}.start();
	}
	
	public void update() { //method that updates the lines by reading the high scores file 
		try {
			if(Screens.clickedLink != "") {
				lines = Files.readAllLines(Paths.get(Screens.companyInfo.get(Screens.clickedLink)[1]), StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw() {
		//root.setBackground(new Background(new BackgroundFill(Color.rgb(1,1,1), CornerRadii.EMPTY, Insets.EMPTY)));
		
		description.setText(lines.toString().substring(1,lines.toString().length()-1));
		
		gcPfp.drawImage(pfpIcon, 0, 0);
		//gcMap.drawImage(mapImage, 0, 0);
	}
}
