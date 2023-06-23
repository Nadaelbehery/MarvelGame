package gui;

import java.io.File;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;


public class AbilityUseSubScene extends SubScene {
	private final String BG_IMAGE="src/resources/ia.jpg";
	//private final static String FONT_PATH = "src/gui/kenvector_future.ttf";
	//private boolean isHidden = false;	


	
	
	public AbilityUseSubScene() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		BackgroundImage image = new BackgroundImage(new Image(new File(BG_IMAGE).toURI().toString(), 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		
		setLayoutX(420);
		setLayoutY(200);
		
		
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
	
	
	
	
	
	
	
	
	
	

}
