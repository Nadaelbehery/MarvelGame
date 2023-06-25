package gui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import engine.Game;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.world.Champion;


public class View extends Application {
	String player1;
	String player2;
	

	public void start(Stage s) throws Exception {
		try {
		
			ViewManager manager1=new ViewManager();
			
		
			s=manager1.getMainStage();
			s.show();
			player1=manager1.getPlayer1();
			player2=manager1.getPlayer2();
			//backgroundSound();
			
			
			
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	MediaPlayer mediaPlayer;

	public void music() {
		String s = "_Avengers Assemble_ Official Soundtrack.mp3";

		Media h = new Media(Paths.get(s).toUri().toString());
		mediaPlayer = new MediaPlayer(h);
		mediaPlayer.play();
	}
   private void backgroundSound(){
		
		String musicFile = "gui/_Avengers Assemble_ Official Soundtrack.mp3"; 
		AudioClip soundClip = new AudioClip(new File(musicFile).toURI().toString());
        soundClip.setCycleCount(5);
        soundClip.play();
        mediaPlayer.setVolume(0.2);
	}

	public static void main(String[] args) {
		
		
		
		try {
			Game.loadAbilities("Abilities.csv");
		} catch (IOException e) {
			
			System.out.print("Abilities csv file not available");
		}
		try {
			Game.loadChampions("Champions.csv");
		} catch (IOException e) {
		
			System.out.print("Champions csv file not available");
		}
		launch(args);
		
		
		
		
		
	}
	

}
