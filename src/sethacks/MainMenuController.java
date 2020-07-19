package sethacks;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainMenuController extends Screens implements Initializable, ControlledScreen {

	@FXML
	Canvas iconCanvas;
	@FXML
	Canvas pfpCanvas;
	@FXML
	Canvas slideCanvas;	
	@FXML 
	Text name;
	@FXML 
	Text score;
	@FXML 
	Text xp;

	GraphicsContext gcSlide;
	GraphicsContext gcIcon;
	GraphicsContext gcPfp;
	
	Image moneyIcon;
	Image pfpIcon;
	Image slideImage;
	
	ArrayList<String> slideshowPictures;

	int imageNumber = 0;
	
	int fileCount;

	long startTime = System.nanoTime()/1_000_000_000;
	long timeSwitched;
	long time;
	
	boolean switched = false;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		name.setText(Screens.name);
		score.setText(Integer.toString(Screens.score));
		xp.setText(Screens.xp);
		
		gcIcon = iconCanvas.getGraphicsContext2D();
		gcPfp = pfpCanvas.getGraphicsContext2D();
		gcSlide = slideCanvas.getGraphicsContext2D();
		
		slideshowPictures = new ArrayList<String>();
		
		File directory = new File("C:\\Users\\Joe\\Desktop\\Computer Science From Highschool\\Grade 12 CompSci\\Workspaces\\SETHacks\\src\\images\\slideshowImages");
		
	    fileCount = directory.list().length;

		    
	    for (String f: directory.list()) {
	    	slideshowPictures.add(f);
	    }
	    	
	    loop();
	}
	
	public void loop() {

		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {	
				time = (currentNanoTime / 1_000_000_000) - startTime; //update the game time 

				//update everything
				update(currentNanoTime);
				
				//draw everything
				draw();
			}			
		}.start();
	}
	
	public void update (long currentNanoTime) {
		if (time % 2 == 0 && switched == false) {
			imageNumber +=1;
			timeSwitched = time;
			switched = true;
		}
		
		if(time-timeSwitched >=1) {
			switched = false;
		}
		
		if(imageNumber >= fileCount) {
			imageNumber = 0;
		}
		
		String image = "images/slideshowImages/" + slideshowPictures.get(imageNumber);
		slideImage = new Image(image);
		moneyIcon = new Image("images/coin.png"); //setting the background image
		pfpIcon = new Image(pfpImage);
		
		

		
	}
	
	public void draw() {
		gcSlide.setFill(Color.rgb(37,36,34));
		gcSlide.fillRect(0, 0, slideCanvas.getWidth(), slideCanvas.getHeight());
		
		gcPfp.drawImage(pfpIcon, 0, 0);
		gcIcon.drawImage(moneyIcon, 0, iconCanvas.getHeight()/2 - moneyIcon.getHeight()/2);
		gcSlide.drawImage(slideImage, slideCanvas.getWidth()/2 - slideImage.getWidth()/2, slideCanvas.getHeight()/2 - slideImage.getHeight()/2);
	}
}
