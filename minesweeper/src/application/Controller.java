package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private GridPane gridpane;
	@FXML
	public void startgame(ActionEvent event) {
		System.out.println("hiii");
	}
	private int colums=5;
	private int rows=5;
	public void initialize() {
		List<Button> buttons;
		List<Label> labels;
		Model m=new Model(rows, colums, 2);
        buttons = new ArrayList<>();
        labels=new ArrayList<>();
        for (int i = 0; i < rows; i++) {
        	for(int j=0;j<colums;j++) {
        			Button button = new Button();
        			Label label=new Label(m.getLabel(i, j)); 
        			
        			button.setOnAction(event->{
        				if(m.nextMove(GridPane.getRowIndex(button), GridPane.getColumnIndex(button)).equals("BOMB")) {
        					final Stage dialog = new Stage();
        	                dialog.initModality(Modality.APPLICATION_MODAL);
        	                Parent root = null;
							try {
								root = FXMLLoader.load(getClass().getResource("Over.fxml"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
        	                Scene dialogScene = new Scene(root, 300, 200);
        	                dialog.setScene(dialogScene);
        	                dialog.setResizable(false);
        	                dialog.show();
        	                gridpane.getChildren().retainAll(gridpane.getChildren().get(0));
        	                initialize();
        					
        					
        				}else if(m.nextMove(GridPane.getRowIndex(button), GridPane.getColumnIndex(button)).equals("WON")) {
        					button.setVisible(false);
        					final Stage dialog = new Stage();
        	                dialog.initModality(Modality.APPLICATION_MODAL);
        	                Parent root = null;
							try {
								root = FXMLLoader.load(getClass().getResource("won.fxml"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
        	                Scene dialogScene = new Scene(root, 300, 200);
        	                dialog.setScene(dialogScene);
        	                dialog.setResizable(false);
        	                dialog.show();
        	                gridpane.getChildren().retainAll(gridpane.getChildren().get(0));
        	                initialize();
        					
        				}else {
        					for(Button b:buttons)
        					{
        						if(m.isvisible(GridPane.getRowIndex(b), GridPane.getColumnIndex(b))) {
        							b.setVisible(false);
        							
        						}
        					}
        				}
        				
        			});
        			labels.add(label);
        			
        			buttons.add(button);
        			gridpane.add(label, j, i);
        			gridpane.add(button, j, i);
        	}
        }
    }
	

}
