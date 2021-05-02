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

public class Board extends GridPane{

    public Board(Stage stage){
	this.setStyle("-fx-border-color: black;");
	
	for(int i = 0; i<10; i++){
	    this.getColumnConstraints().add(new ColumnConstraints(15));
	}

	for(int i=0; i<10; i++){
	    this.getRowConstraints().add(new RowConstraints(15));
	}

	for (int i=0; i<10; i++){
	    for (int j=0; j<10; j++){
		Pane pane = new Pane();
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		this.add(pane, i, j);
	    }
	}
    }

}
