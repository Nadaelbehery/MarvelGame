package gui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class Buttons extends Button {
	private final String Font_Path="file:///C:/Users/DELL/Desktop/SEMESTER%204/Bonus/Marvel-M2/src/resources/kenvector_future.ttf";
	private final String Button_Style_Released="-fx-background-color: transparent; -fx-background-image: url('file:///C:/Users/DELL/Desktop/SEMESTER%204/Bonus/Marvel-M2/src/resources/grey_button02.png')";
	private final String Button_Style_Pressed="-fx-background-color: transparent; -fx-background-image: url('file:///C:/Users/DELL/Desktop/SEMESTER%204/Bonus/Marvel-M2/src/resources/grey_button01.png')";
	public Buttons(String text) {
		setText(text);
		
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(Button_Style_Released);
		initializeButtonListeners();
		
		
	}
	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(Font_Path), 23));
		} catch (FileNotFoundException e) {
			
			setFont(Font.font("Verdana",23));
		}
	}
	private void setButtonReleasedStyle(){
		setStyle(Button_Style_Released);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
	}
	private void setButtonPressedStyle(){
		setStyle(Button_Style_Pressed);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
	}
	private void initializeButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
			
		});
		setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
			
		});
		
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow(50, Color.BLACK));
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
	
	

	
	
}
