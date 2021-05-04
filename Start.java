import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

import Snake.*;


public class Start extends Application{

    public boolean toBegin = false;
    
    public void menu(Stage mainStage){
	double div = 0.2;
	
	Button start = new Button("New game");
	Button quit = new Button("Quit");
	
	start.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event){
		    mainStage.close();
		    game(mainStage);
		}
	    });


	quit.setCancelButton(true);
	quit.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event){
		    mainStage.close();
		}
	    });

	Rectangle2D screenRes = Screen.getPrimary().getBounds();

	VBox buttons = new VBox(2);
	buttons.getChildren().addAll(start, quit);
	buttons.setAlignment(Pos.CENTER);
	buttons.setPrefWidth(100);
	buttons.setPrefHeight(50);

	buttons.setLayoutX(screenRes.getWidth()*div/2 - buttons.getPrefHeight());
	buttons.setLayoutY(screenRes.getWidth()*div/2 + buttons.getPrefHeight());
	
	
	Image im = new Image("./img/index.png",
			     (int)(screenRes.getWidth() * div),
			     (int)(screenRes.getWidth() * div), 
			     false, true);
	
	ImageView bck = new ImageView(im);

	Button b = new Button();
	b.setAlignment(Pos.CENTER_RIGHT);
	//HBox root = new HBox(100);
	Group root = new Group();
	root.getChildren().addAll(bck, buttons);
	//root.setAlignment(Pos.CENTER_LEFT);

	Scene scene = new Scene(root,
				(int)(screenRes.getWidth() * div),
				(int)(screenRes.getWidth() * div));
	
	mainStage.setScene(scene);
	mainStage.setTitle("Main menu");
	mainStage.show();
    }
    
    public void game(Stage myGame){
	System.out.println("inside");

	Board board = new Board();

	board.setGridLinesVisible(true);

	Snake snake;
	
	//test
	board.setBckg(2,2, false);
	board.setBckg(2,4,false);
	/*
	board.setOnKeyPressed(new EventHandler<KeyEvent>(){
		public void handle(final KeyEvent keyEvent){
		    if(keyEvent.getCode() == KeyCode.ENTER){
			//this.setPressed(keyEvent.getEventType() == KeyEvent.KEY_PRESSED);
			//this.setBckg(2,3, false);
			System.out.println("Enter");
		    }
		}
	    });

	*/
	
	BorderPane root = new BorderPane();
	root.setCenter(board);

	Pane score = new Pane();
	root.setLeft(score);
	Scene scene = new Scene(root, 300, 300);
	createKeyEvent(scene, board);
	myGame.setScene(scene);
	myGame.show();
    }

    public void createKeyEvent(Scene myScene, Board board){
	myScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent keyEvent){
		    if(keyEvent.getCode() == KeyCode.ENTER){
			board.setBckg(2,3, true);
		    }
		    else if(keyEvent.getCode() == KeyCode.A){
			board.setBckg(2,3, false);
		    }
		}
	    });
    }
    
    @Override
    public void start(Stage stage){
	stage.setResizable(false);
	menu(stage);
    }

    

    public static void main(String[] args){
	launch(args);
    }
}
