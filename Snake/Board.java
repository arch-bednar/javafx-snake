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

public class Board extends GridPane{

    //private Pane[][] paneArr = new Pane[10][10];
    private ArrayList<ArrayList<Pane>> paneArr = new ArrayList<ArrayList<Pane>>();
    
    public Board(){
	this.setStyle("-fx-border-color: black;");
	
	for(int i = 0; i<20; i++){
	    this.getColumnConstraints().add(new ColumnConstraints(15));
	}

	for(int i=0; i<20; i++){
	    this.getRowConstraints().add(new RowConstraints(15));
	}

	for (int i=0; i<20; i++){
	    paneArr.add(new ArrayList<Pane>());
	    for (int j=0; j<20; j++){
		Pane pane = new Pane();
		pane.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		this.add(pane, i, j);
		//this.paneArr[i][j] = pane;
		paneArr.get(i).add(pane);
	    }
	}

	//setKeyEvent();
    }

    /*    private void setKeyEvent(){
	  this.setOnKeyReleased(new EventHandler<KeyEvent>(){
	  public void handle(final KeyEvent keyEvent){
		    if(keyEvent.getCode() == KeyCode.A){
		    if(Snake.
		    }
		    }
		    });
		    }*/
    
    public void setBckg(int x, int y, boolean isHead){
	//Pane pane = new Pane();
	if(isHead){
	    paneArr.get(x).get(y).setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
	}
	else{
	    paneArr.get(x).get(y).setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
	}

	this.setConstraints(paneArr.get(x).get(y), x, y);
	//this.add(pane, x, y);
    }

}
