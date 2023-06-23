package gui;

import java.io.File;

import engine.Game;
import engine.Player;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameRegulationsView  {

	private static final int HEIGHT = 731;
	private static final int WIDTH = 1300;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Stage menuStage;
	private Player player1;
	private Player player2;

	public GameRegulationsView(Player player1,Player player2)  {

		this.player1=player1;
		this.player2=player2;
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("Avengers Assemble");
		mainStage.setResizable(false);
		Image backgroundImage = new Image(new File("src/resources/Untitled-3.jpg").toURI().toString());
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, null);
		mainPane.setBackground(new Background(background));
		//mainStage.show();
		PauseTransition delay=new PauseTransition(Duration.seconds(6));
		delay.setOnFinished(event -> {mainStage.hide();
		PickChampViewManager gameManager=new PickChampViewManager( /*currentGame,*/player1,player2);
		gameManager.createNewGame(mainStage,player1,player2);
		});
		delay.play();
		
			
		

	}

	public void createNewGame(Stage menuStage, Player Player1, Player Player2) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		mainStage.show();

	}

}
