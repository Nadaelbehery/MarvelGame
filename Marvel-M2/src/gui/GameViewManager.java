package gui;

import java.util.ArrayList;
import java.awt.Point;
import java.io.File;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Direction;


public class GameViewManager {

	private AnchorPane mainPane;

	private static final int RowNum = 5;
	private static final int ColNum = 5;
	private static final int HEIGHT = 731;
	private static final int WIDTH = 1300;
	// private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Stage menuStage;
	private Game currentGame;
	private ArrayList<Champion> ChampionsPicked = new ArrayList<Champion>();
	private Player player1;
	private Player player2;
	private GridPane root;
	private HBox box;
	private AbilityUseSubScene abilitySubScene;
	private AbilityUseSubScene sceneToHide;
	private GameViewManSubScene subScene;
	private FlowPane rootplayer1;
	private FlowPane rootplayer2;
	private Label label1;
	private Label label2;
	private VBox Cbox;
	

	public GameViewManager(Player player1, Player player2, ArrayList<Champion> ChampionsPicked) {
		this.player1 = player1;
		this.player2 = player2;
		this.ChampionsPicked = ChampionsPicked;
		intializePLayers();
		this.currentGame = new Game(player1, player2);
		

		mainPane = new AnchorPane();

		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("Avengers Assemble");
		mainStage.setResizable(false);
		createBackground();

		subScene = new GameViewManSubScene(currentGame);
		subScene.setLayoutX(420);
		subScene.setLayoutY(180);
		root = subScene.getGrid();
		mainPane.getChildren().add(subScene);
		createBackground();
		// EndTurnButton();
		box = DisplayTurnOrder();
		mainPane.getChildren().add(box);

		label1 = new Label("Player One: " + player1.getName() + ".  Leader ability not used");
		label1.setLayoutY(150);
		label1.setLayoutX(0);

		label1.setFont(new Font("Ariel", 20));
		label1.setTextFill(Color.WHITE);
		mainPane.getChildren().add(label1);

		label2 = new Label("Player two: " + player2.getName() + ".  Leader ability not used");
		label2.setLayoutY(350);

		label2.setLayoutX(0);

		label2.setFont(new Font("Ariel", 20));
		label2.setTextFill(Color.WHITE);
		mainPane.getChildren().add(label2);

		rootplayer1 = DisplayTeams(player1);

		rootplayer2 = DisplayTeams(player2);
		rootplayer1.setLayoutY(200);
		rootplayer2.setLayoutY(400);
		mainPane.getChildren().add(rootplayer1);
		mainPane.getChildren().add(rootplayer2);
		Cbox=currentChampDetails();
		mainPane.getChildren().add(Cbox);


		mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case DOWN: {

					// subScene.removeChamp(currentGame.getCurrentChampion());
					try {
						// System.out.print("Moving");
						// subScene.removeChamp(currentGame.getCurrentChampion());
						int x = currentGame.getCurrentChampion().getLocation().x;
						int y = currentGame.getCurrentChampion().getLocation().y;
						currentGame.move(Direction.DOWN);
						subScene.removeChamp(x, y, currentGame.getCurrentChampion());
						subScene.moveChamp(currentGame.getCurrentChampion());
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);



						

					} catch (NotEnoughResourcesException e) {

						AlertBox.display("error", e.getMessage());
					} catch (UnallowedMovementException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}

					break;
				}
				case UP: {
					try {
						int x = currentGame.getCurrentChampion().getLocation().x;
						int y = currentGame.getCurrentChampion().getLocation().y;
						currentGame.move(Direction.UP);
						subScene.removeChamp(x, y, currentGame.getCurrentChampion());
						subScene.moveChamp(currentGame.getCurrentChampion());
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);
					} catch (NotEnoughResourcesException e) {

						AlertBox.display("error", e.getMessage());
					} catch (UnallowedMovementException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}
					break;
				}
				case LEFT: {
					try {
						int x = currentGame.getCurrentChampion().getLocation().x;
						int y = currentGame.getCurrentChampion().getLocation().y;
						currentGame.move(Direction.LEFT);
						subScene.removeChamp(x, y, currentGame.getCurrentChampion());
						subScene.moveChamp(currentGame.getCurrentChampion());
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);
					} catch (NotEnoughResourcesException e) {

						AlertBox.display("error", e.getMessage());
					} catch (UnallowedMovementException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}
					break;
				}
				case RIGHT: {
					try {
						int x = currentGame.getCurrentChampion().getLocation().x;
						int y = currentGame.getCurrentChampion().getLocation().y;
						currentGame.move(Direction.RIGHT);
						subScene.removeChamp(x, y, currentGame.getCurrentChampion());
						subScene.moveChamp(currentGame.getCurrentChampion());
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);
					} catch (NotEnoughResourcesException e) {

						AlertBox.display("error", e.getMessage());
					} catch (UnallowedMovementException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}
					break;
				}
				case S: {
					try {
						currentGame.attack(Direction.DOWN);
						subScene.RemoveAllChamps();
						subScene.placeChampions1(currentGame);
						subScene.placeCovers();
						checkGameover();
						mainPane.getChildren().remove(rootplayer1);
						mainPane.getChildren().remove(rootplayer2);
						rootplayer1 = DisplayTeams(player1);
						rootplayer2 = DisplayTeams(player2);
						rootplayer1.setLayoutY(200);
						rootplayer2.setLayoutY(400);
						mainPane.getChildren().add(rootplayer1);
						mainPane.getChildren().add(rootplayer2);
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);

					} catch (NotEnoughResourcesException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (ChampionDisarmedException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (InvalidTargetException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}
					break;
				}
				case W: {
					try {
						currentGame.attack(Direction.UP);
						subScene.RemoveAllChamps();
						subScene.placeChampions1(currentGame);
						subScene.placeCovers();
						checkGameover();
						mainPane.getChildren().remove(rootplayer1);
						mainPane.getChildren().remove(rootplayer2);
						rootplayer1 = DisplayTeams(player1);
						rootplayer2 = DisplayTeams(player2);
						rootplayer1.setLayoutY(200);
						rootplayer2.setLayoutY(400);
						mainPane.getChildren().add(rootplayer1);
						mainPane.getChildren().add(rootplayer2);
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);
					} catch (NotEnoughResourcesException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (ChampionDisarmedException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (InvalidTargetException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}
					break;

				}
				case A: {

					try {

						currentGame.attack(Direction.LEFT);
						//System.out.print("Attack RIGHT");
						subScene.RemoveAllChamps();
						subScene.placeChampions1(currentGame);
						subScene.placeCovers();
						checkGameover();
						mainPane.getChildren().remove(rootplayer1);
						mainPane.getChildren().remove(rootplayer2);
						rootplayer1 = DisplayTeams(player1);
						rootplayer2 = DisplayTeams(player2);
						rootplayer1.setLayoutY(200);
						rootplayer2.setLayoutY(400);
						mainPane.getChildren().add(rootplayer1);
						mainPane.getChildren().add(rootplayer2);
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);
					} catch (NotEnoughResourcesException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (ChampionDisarmedException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (InvalidTargetException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}
					break;

				}
				case D: {
					try {
						currentGame.attack(Direction.RIGHT);

						// System.out.print("Attack RIGHT");
						subScene.RemoveAllChamps();
						subScene.placeChampions1(currentGame);
						subScene.placeCovers();
						checkGameover();
						mainPane.getChildren().remove(rootplayer1);
						mainPane.getChildren().remove(rootplayer2);
						rootplayer1 = DisplayTeams(player1);
						rootplayer2 = DisplayTeams(player2);
						rootplayer1.setLayoutY(200);
						rootplayer2.setLayoutY(400);
						mainPane.getChildren().add(rootplayer1);
						mainPane.getChildren().add(rootplayer2);
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);
					} catch (NotEnoughResourcesException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (ChampionDisarmedException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (InvalidTargetException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}
					break;

				}
				case SPACE: {
					currentGame.endTurn();
					mainPane.getChildren().remove(box);
					box = DisplayTurnOrder();
					mainPane.getChildren().add(box);
					mainPane.getChildren().remove(Cbox);
					Cbox=currentChampDetails();
					mainPane.getChildren().add(Cbox);
					break;

				}

				case ENTER: {
					abilitySubScene = new AbilityUseSubScene();
					createCreditsAbilityUse();
					Button done=new Button("Close");
					done.setPrefSize(100, 30);
					done.setLayoutX(500);
					done.setLayoutY(180);
					abilitySubScene.getPane().getChildren().add(done);
					done.setOnAction( new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
						
							 mainPane.getChildren().remove(abilitySubScene);
						}
					});
					
					break;

				}
				case SHIFT: {
					try {
						currentGame.useLeaderAbility();
						subScene.RemoveAllChamps();
						subScene.placeChampions1(currentGame);
						subScene.placeCovers();
						checkGameover();
						mainPane.getChildren().remove(rootplayer1);
						mainPane.getChildren().remove(rootplayer2);
						rootplayer1 = DisplayTeams(player1);
						rootplayer2 = DisplayTeams(player2);
						rootplayer1.setLayoutY(200);
						rootplayer2.setLayoutY(400);
						mainPane.getChildren().add(rootplayer1);
						mainPane.getChildren().add(rootplayer2);
						if (currentGame.getFirstPlayer().getLeader().equals(currentGame.getCurrentChampion())) {
							label1.setText("Player one: " + player1.getName() + ".  Leader ability used");
						} else {
							label2.setText("Player two: " + player2.getName() + ".  Leader ability used");
						}
						mainPane.getChildren().remove(Cbox);
						Cbox=currentChampDetails();
						mainPane.getChildren().add(Cbox);

					} catch (LeaderNotCurrentException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					} catch (LeaderAbilityAlreadyUsedException e) {
						// TODO Auto-generated catch block
						AlertBox.display("error", e.getMessage());
					}
					break;
				}

				default:
					break;

				}
			}

		});
		
		
	}

	private void createBackground() {
		Image backgroundImage = new Image(new File("src/resources/space.jpg").toURI().toString());
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, null);
		mainPane.setBackground(new Background(background));

	}

	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		// this.currentGame=currentGame;
		mainStage.show();

	}

	private void createCreditsAbilityUse() {
		abilitySubScene = new AbilityUseSubScene();
		mainPane.getChildren().add(abilitySubScene);
		Label AvailableAbilities = new Label("  <<<Available Abilities>>>");
		AvailableAbilities.setFont(new Font("Ariel", 30));
		AvailableAbilities.setLayoutX(0);
		AvailableAbilities.setLayoutY(0);
		AvailableAbilities.setTextFill(Color.WHITE);

		// Button close=new Button("close");
		VBox box1 = new VBox();
		box1.setSpacing(20);

		for (int i = 0; i < currentGame.getCurrentChampion().getAbilities().size(); i++) {
			Button ability = new Button(currentGame.getCurrentChampion().getAbilities().get(i).getName());
			Tooltip tip = new Tooltip(currentGame.getCurrentChampion().getAbilities().get(i).AbilitiesDetails());

			Tooltip.install(ability, tip);
			box1.getChildren().add(ability);
			Ability a = currentGame.getCurrentChampion().getAbilities().get(i);

			ability.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (a.getCastArea() == AreaOfEffect.SELFTARGET || a.getCastArea() == AreaOfEffect.TEAMTARGET
							|| a.getCastArea() == AreaOfEffect.SURROUND) {
						try {
							System.out.print("Selffff");
							System.out.print(currentGame.getCurrentChampion().getCurrentHP());
							currentGame.castAbility(a);
							System.out.print(currentGame.getCurrentChampion().getCurrentHP());

							subScene.RemoveAllChamps();
							subScene.placeChampions1(currentGame);
							subScene.placeCovers();
							checkGameover();
							mainPane.getChildren().remove(rootplayer1);
							mainPane.getChildren().remove(rootplayer2);
							rootplayer1 = DisplayTeams(player1);
							rootplayer2 = DisplayTeams(player2);
							rootplayer1.setLayoutY(200);
							rootplayer2.setLayoutY(400);
							mainPane.getChildren().add(rootplayer1);
							mainPane.getChildren().add(rootplayer2);
							mainPane.getChildren().remove(Cbox);
							Cbox=currentChampDetails();
							mainPane.getChildren().add(Cbox);
						   // mainPane.getChildren().remove(abilitySubScene);

						} catch (NotEnoughResourcesException e) {
							// TODO Auto-generated catch block
							AlertBox.display("error", e.getMessage());
						} catch (AbilityUseException e) {
							// TODO Auto-generated catch block
							AlertBox.display("error", e.getMessage());
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							AlertBox.display("error", e.getMessage());
						}
						//mainPane.getChildren().remove(abilitySubScene);
						return;
						// return;
					}
					if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
						Label label = new Label();
						label.setText("Pick a direction using keyboard T F G H keys");
						label.setLayoutX(0);
						label.setLayoutY(300);
						label.setFont(new Font("Ariel", 20));
						label.setTextFill(Color.WHITE);

						abilitySubScene.getPane().getChildren().add(label);

						abilitySubScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

							@Override
							public void handle(KeyEvent event) {
								switch (event.getCode()) {
								case G: {
									try {
										currentGame.castAbility(a, Direction.DOWN);
										subScene.RemoveAllChamps();
										subScene.placeChampions1(currentGame);
										subScene.placeCovers();
										checkGameover();
										mainPane.getChildren().remove(label);
										mainPane.getChildren().remove(rootplayer1);
										mainPane.getChildren().remove(rootplayer2);
										rootplayer1 = DisplayTeams(player1);
										rootplayer2 = DisplayTeams(player2);
										rootplayer1.setLayoutY(200);
										rootplayer2.setLayoutY(400);
										mainPane.getChildren().add(rootplayer1);
										mainPane.getChildren().add(rootplayer2);
										mainPane.getChildren().remove(Cbox);
										Cbox=currentChampDetails();
										mainPane.getChildren().add(Cbox);
										//mainPane.getChildren().remove(abilitySubScene);

									} catch (NotEnoughResourcesException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									} catch (AbilityUseException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									} catch (CloneNotSupportedException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									}
									//mainPane.getChildren().remove(abilitySubScene);
									break;

								}
								case T: {
									try {

										currentGame.castAbility(a, Direction.UP);
										subScene.RemoveAllChamps();
										subScene.placeChampions1(currentGame);
										subScene.placeCovers();
										checkGameover();
										mainPane.getChildren().remove(label);
										mainPane.getChildren().remove(rootplayer1);
										mainPane.getChildren().remove(rootplayer2);
										rootplayer1 = DisplayTeams(player1);
										rootplayer2 = DisplayTeams(player2);
										rootplayer1.setLayoutY(200);
										rootplayer2.setLayoutY(400);
										mainPane.getChildren().add(rootplayer1);
										mainPane.getChildren().add(rootplayer2);
										mainPane.getChildren().remove(Cbox);
										Cbox=currentChampDetails();
										mainPane.getChildren().add(Cbox);
										//mainPane.getChildren().remove(abilitySubScene);

									} catch (NotEnoughResourcesException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									} catch (AbilityUseException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									} catch (CloneNotSupportedException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									}
									//mainPane.getChildren().remove(abilitySubScene);

									break;
								}
								case F: {
									try {
										currentGame.castAbility(a, Direction.LEFT);
										subScene.RemoveAllChamps();
										subScene.placeChampions1(currentGame);
										subScene.placeCovers();
										checkGameover();
										mainPane.getChildren().remove(label);
										mainPane.getChildren().remove(rootplayer1);
										mainPane.getChildren().remove(rootplayer2);
										rootplayer1 = DisplayTeams(player1);
										rootplayer2 = DisplayTeams(player2);
										rootplayer1.setLayoutY(200);
										rootplayer2.setLayoutY(400);
										mainPane.getChildren().add(rootplayer1);
										mainPane.getChildren().add(rootplayer2);
										mainPane.getChildren().remove(Cbox);
										Cbox=currentChampDetails();
										mainPane.getChildren().add(Cbox);

									} catch (NotEnoughResourcesException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									} catch (AbilityUseException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									} catch (CloneNotSupportedException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									}
									//mainPane.getChildren().remove(abilitySubScene);

									break;

								}
								case H: {
									try {
										currentGame.castAbility(a, Direction.RIGHT);
										// System.out.print("attacking right ab");
										subScene.RemoveAllChamps();
										subScene.placeChampions1(currentGame);
										subScene.placeCovers();
										checkGameover();
										mainPane.getChildren().remove(label);
										mainPane.getChildren().remove(abilitySubScene);
										mainPane.getChildren().remove(rootplayer1);
										mainPane.getChildren().remove(rootplayer2);
										rootplayer1 = DisplayTeams(player1);
										rootplayer2 = DisplayTeams(player2);
										rootplayer1.setLayoutY(200);
										rootplayer2.setLayoutY(400);
										mainPane.getChildren().add(rootplayer1);
										mainPane.getChildren().add(rootplayer2);
										mainPane.getChildren().remove(Cbox);
										Cbox=currentChampDetails();
										mainPane.getChildren().add(Cbox);

									} catch (NotEnoughResourcesException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									} catch (AbilityUseException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									} catch (CloneNotSupportedException e) {
										// TODO Auto-generated catch block
										AlertBox.display("error", e.getMessage());
									}
									//mainPane.getChildren().remove(abilitySubScene);

									break;

								}

								default:
									break;
								}
							}
						});
						// mainPane.getChildren().remove(abilitySubScene);
						// return;

					}
					if (a.getCastArea() == AreaOfEffect.SINGLETARGET) {
						// AlertBox.display(" ", "enter x and y coordinates");
						HBox coordinates = new HBox();
						coordinates.setSpacing(10);
						Label label = new Label();
						label.setText("Enter target's x and y coordinates");
						label.setFont(new Font("Ariel", 10));
						label.setTextFill(Color.WHITE);
						TextField fieldx = new TextField();
						TextField fieldy = new TextField();
						coordinates.setLayoutX(0);
						coordinates.setLayoutY(300);

						Button done = new Button("Done");
						coordinates.getChildren().addAll(label, fieldx, fieldy, done);

						abilitySubScene.getPane().getChildren().add(coordinates);
						// int x;
						// int y;
						/*
						 * while (!fieldx.getText().isEmpty() && !fieldy.getText().isEmpty()) {
						 * System.out.print("in"); x = Integer.parseInt(fieldx.getText()); y =
						 * Integer.parseInt(fieldy.getText());
						 * //mainPane.getChildren().remove(coordinates);
						 * mainPane.getChildren().remove(abilitySubScene); break;
						 * 
						 * 
						 * }
						 */
						// return;
						done.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event2) {

								int x = Integer.parseInt(fieldx.getText());
								int y = Integer.parseInt(fieldy.getText());
								try {
									currentGame.castAbility(a, x, y);
									subScene.RemoveAllChamps();
									subScene.placeChampions1(currentGame);
									subScene.placeCovers();
									checkGameover();
									mainPane.getChildren().remove(rootplayer1);
									mainPane.getChildren().remove(rootplayer2);
									rootplayer1 = DisplayTeams(player1);
									rootplayer2 = DisplayTeams(player2);
									rootplayer1.setLayoutY(200);
									rootplayer2.setLayoutY(400);
									mainPane.getChildren().add(rootplayer1);
									mainPane.getChildren().add(rootplayer2);
									mainPane.getChildren().remove(Cbox);
									Cbox=currentChampDetails();
									mainPane.getChildren().add(Cbox);

								} catch (NotEnoughResourcesException e) {
									// TODO Auto-generated catch block
									AlertBox.display("error", e.getMessage());

								} catch (AbilityUseException e) {
									// TODO Auto-generated catch block
									AlertBox.display("error", e.getMessage());

								} catch (InvalidTargetException e) {
									// TODO Auto-generated catch block
									AlertBox.display("error", e.getMessage());

								} catch (CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									AlertBox.display("error", e.getMessage());

								}

								//mainPane.getChildren().remove(abilitySubScene);
								return;

							}
						});
					}

				}

			});

		}
		box1.setLayoutX(100);
		box1.setLayoutY(100);
		abilitySubScene.getPane().getChildren().addAll(AvailableAbilities, box1);

	}

	private void placeChampions() {

	}

	private void intializePLayers() {

		player1.getTeam().add(ChampionsPicked.get(0));
		player1.setLeader(ChampionsPicked.get(0));
		player1.getTeam().add(ChampionsPicked.get(1));
		player1.getTeam().add(ChampionsPicked.get(2));
		player2.getTeam().add(ChampionsPicked.get(3));
		player2.setLeader(ChampionsPicked.get(3));
		player2.getTeam().add(ChampionsPicked.get(4));
		player2.getTeam().add(ChampionsPicked.get(5));

	}

	private void EndTurnButton() {
		Buttons button = new Buttons("End Turn");
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				currentGame.endTurn();
				mainPane.getChildren().remove(box);
				box = DisplayTurnOrder();
				mainPane.getChildren().add(box);

			}

		});

		button.setLayoutX(20);
		button.setLayoutY(300);
		mainPane.getChildren().add(button);

	}

	private HBox DisplayTurnOrder() {
		PriorityQueue temp = new PriorityQueue(6);
		HBox box = new HBox();
		while (!currentGame.getTurnOrder().isEmpty()) {
			Champion c = (Champion) (currentGame.getTurnOrder().remove());
			ImageView ChampionImage = new ImageView(new File(c.getURL()).toURI().toString());
			ChampionImage.setFitHeight(100);
			ChampionImage.setFitWidth(100);
			box.getChildren().add(ChampionImage);
			temp.insert(c);

		}
		while (!temp.isEmpty()) {
			currentGame.getTurnOrder().insert(temp.remove());
		}
		box.setLayoutX(0);
		box.setLayoutY(0);
		return box;
	}

	private void checkGameover() {
		//System.out.print("Checking");
		if (currentGame.checkGameOver() instanceof Player) {
			EndScene last = new EndScene(currentGame);
			last.createNewGame(mainStage);
		}
	}

	private FlowPane DisplayTeams(Player player) {

		FlowPane root = new FlowPane(Orientation.HORIZONTAL, 10, 10);
		root.setPadding(new Insets(5));

		for (int i = 0; i < player.getTeam().size(); i++) {
			ImageView ChampionImage = new ImageView(new File(player.getTeam().get(i).getURL()).toURI().toString() );
			ChampionImage.setFitHeight(100);
			ChampionImage.setFitWidth(100);
			root.getChildren().add(ChampionImage);

		}
		root.setLayoutX(0);
		return root;

	}

	private VBox currentChampDetails() {
		VBox Cbox = new VBox();
		Label text1 = new Label("Current Champion");
		text1.setFont(new Font("Ariel", 30));
		text1.setTextFill(Color.WHITE);

		Cbox.getChildren().add(text1);


		ImageView ChampionImage = new ImageView(new File(currentGame.getCurrentChampion().getURL()).toURI().toString());
		ChampionImage.setFitHeight(100);
		ChampionImage.setFitWidth(100);
		Cbox.getChildren().add(ChampionImage);
		String details = subScene.CurrentChampDetails();
		Label text = new Label(details);
		text.setFont(new Font("Ariel", 10));
		text.setTextFill(Color.WHITE);

		Cbox.getChildren().add(text);
		Cbox.setLayoutX(1000);
		Cbox.setLayoutY(100);

		return Cbox;

	}

	public Stage getMainStage() {
		return mainStage;

	}

}
