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
    public Snake snake;
    public Board board;
    
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

	board = new Board();

	board.setGridLinesVisible(true);

	snake = new Snake();
	
	//snakes head
	/*
	board.setBckg(snake.body.get(0).get(0),
		      snake.body.get(0).get(1),
		      true);
	
	board.setBckg(snake.body.get(1).get(0),
		      snake.body.get(1).get(1),
		      false);
	*/
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
	createKeyEvent(scene);


	myGame.setScene(scene);
	myGame.show();
    }

    public void createKeyEvent(Scene myScene){
	myScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent keyEvent){

		    int lastX, lastY;
		    lastX = snake.getLastX();
		    lastY = snake.getLastY();

		    if(keyEvent.getCode() == KeyCode.D){

			//temporary comment			
			if(snake.getTowardX() == 0 && snake.getTowardY() == 1){
			    snake.setTowardX(1);
			    snake.setTowardY(0);
			}
			else if(snake.getTowardX() == 1 && snake.getTowardY() == 0){
			    snake.setTowardX(0);
			    snake.setTowardY(1);
			}
			else if(snake.getTowardX() == -1 && snake.getTowardY() == 0){
			    snake.setTowardX(0);
			    snake.setTowardY(-1);
			}
			else if(snake.getTowardX() == 0 && snake.getTowardY() == -1){
			    snake.setTowardX(1);
			    snake.setTowardY(0);
			}
			board.clearCell(lastX, lastY);
			snake.changePos();
		    }
		    else if(keyEvent.getCode() == KeyCode.A){
			if(snake.getTowardX() == 0 && snake.getTowardY() == -1){
			    snake.setTowardX(-1);
			    snake.setTowardY(0);
			}
			else if(snake.getTowardX() == -1 && snake.getTowardY() == 0){
			    snake.setTowardX(0);
			    snake.setTowardY(1);
			}
			else if(snake.getTowardX() == 1 && snake.getTowardY() == 0){
			    snake.setTowardX(0);
			    snake.setTowardY(-1);
			}
			else if(snake.getTowardX() == 0 && snake.getTowardY() == 1){
			    snake.setTowardX(-1);
			    snake.setTowardY(0);
			}
			board.clearCell(lastX, lastY);
			snake.changePos();
			
		    }
		    else if(keyEvent.getCode() == KeyCode.ENTER){
			board.clearCell(lastX, lastY);
			snake.changePos();
		    }
		    
		    //snake.changePos();
		    for(int i=0; i<snake.body.size(); i++){
			if (i==0){
			    board.setBckg(snake.body.get(0).get(0),
					  snake.body.get(0).get(1),
					  true);
			}
			//else if (i == snake.body.size()){
			//    board.clearCell(lastX, lastY);
			//}
			else{
			    board.setBckg(snake.body.get(i).get(0),
					  snake.body.get(i).get(1),
					  false);
			}

		    }
		    
		    System.out.println(" ---- ");
		    System.out.println(snake.getTowardX() + " " + snake.getTowardY());
		    System.out.println(snake.getX() + " " + snake.getY());

		    
			///temporary comment

		    
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
