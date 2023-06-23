package gui;


import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.effect.Light.Point;
import javafx.geometry.*;

public class AlertBox {
	

    public static void display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
       
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
   
    
    public static void SingleTargetdisplay() {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        //window.setTitle(title);
        window.setMinWidth(250);
       
        Label label = new Label();
        label.setText("Enter target's x and y coordinates");
        Button closeButton = new Button("Close this window");
        TextField fieldx=new TextField("X:");
        TextField fieldy=new TextField("Y:");
        while(!fieldx.getText().isEmpty() && !fieldy.getText().isEmpty() ){
        int x=Integer.parseInt(fieldx.getText());
        int y=Integer.parseInt(fieldy.getText());
        closeButton.setOnAction(e -> window.close());
        }
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton,fieldx,fieldy);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}