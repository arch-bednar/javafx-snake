package Snake;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

public class Board extends GridPane{

    public ArrayList<ArrayList<Pane>> paneArr = new ArrayList<ArrayList<Pane>>();
    
    public Board(){
	//this.setStyle("-fx-border-color: black;");

	Rectangle2D screen = Screen.getPrimary().getBounds();
	
	
	for(int i = 0; i<80; i++){
	    this.getColumnConstraints().add(new ColumnConstraints(screen.getWidth() * 0.4 / 80));
	    //System.out.println(screen.getWidth() * 0.4 / 80);
	}

	for(int i=0; i<80; i++){
	    this.getRowConstraints().add(new RowConstraints(screen.getWidth() * 0.4/ 80));
	}

	for (int i=0; i<80; i++){
	    
	    paneArr.add(new ArrayList<Pane>());

	    for (int j=0; j<80; j++){
		Pane pane = new Pane();

		if(i == 0 || i == 79 || j == 0 || j == 79){
		    pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		}else{
		    pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
		}
		
		this.add(pane, i, j);
		paneArr.get(i).add(pane);
		paneArr.get(i).get(j).getChildren().add(new Food(i, j));
		paneArr.get(i).get(j).getChildren().get(0).setVisible(false);

		if(i == 40 && j == 40){
		    System.out.println(getFoodVisibility(i, j));
		    paneArr.get(i).get(j).getChildren().get(0).setVisible(true);
		}else{
		    paneArr.get(i).get(j).getChildren().get(0).setVisible(false);
		    }
	    }
	}
       

    }

    public void setBckg(int x, int y){
	Background snakeColor = new Background(new BackgroundFill(Color.BLACK, null, null));
	this.setBckg(x, y, snakeColor);
    }

    public void setBckg(int x, int y, Background color){
	
	paneArr.get(x).get(y).setBackground(color);
	this.setConstraints(paneArr.get(x).get(y), x, y);
	
    }
    

    public void clearCell(int x, int y){
	paneArr.get(x).get(y).setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
    }

    public void drawDeadHead(int x, int y){

	Background deadHead = new Background(new BackgroundFill(Color.RED, null, null));
	this.setBckg(x, y, deadHead);
    }

    public boolean getFoodVisibility(int x, int y){
	return paneArr.get(x).get(y).getChildren().get(0).isVisible();
    }

    
}
