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
import java.util.concurrent.TimeUnit;
import java.util.Random;

import Snake.*;


public class Start extends Application{

    public boolean toBegin = false;
    public Snake snake;
    public Board board;
    private Thread moving;
    private boolean isPause = true;
    boolean isDead = false;
    
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

	Group root = new Group();
	root.getChildren().addAll(bck, buttons);

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

	//board.setGridLinesVisible(true);

	snake = new Snake();
	
	BorderPane root = new BorderPane();
	root.setCenter(board);

	Pane score = new Pane();
	root.setBottom(score);

	double res = Screen.getPrimary().getBounds().getWidth();
	
	Scene scene = new Scene(root, res*0.4 + res*0.1, res*0.4+120);
	createKeyEvent(scene);


	myGame.setScene(scene);
	myGame.show();

	
	Engine moving = new Engine(myGame);
	moving.start();
	
    }

    ////////////////////////////////////////////
    //Private class for engine
    //moving snake and generate food
    private class Engine extends Thread{

	Stage myGame;
	
	Engine(Stage scene){
	    this.myGame = scene;
	}
	
	public void run(){

	    int lastX, lastY;
	    
	    while(!snake.isDead && myGame.isShowing()){

		while(isPause && myGame.isShowing()){
		    drawSnake();
		    isEaten();
		}

		if(!myGame.isShowing())
		    continue;
		
		lastX = snake.getLastX();
		lastY = snake.getLastY();
		board.clearCell(lastX, lastY);
		
		snake.changePos();
		drawSnake();
		
		isEaten();
		
		
		if(snake.isCollision()){
		    System.out.println("Koniec gry");
		    
		    board.drawDeadHead(snake.getX(), snake.getY());
		    
		    snake.isDead = true;
		    continue;
		}
	    }
	}

	private void drawSnake(){

	    for(int i=0; i<snake.body.size(); i++){
		
		board.setBckg(snake.body.get(i).get(0),
			      snake.body.get(i).get(1));
		
	    }
	}

	private void isEaten(){
	    if(board.getFoodVisibility(snake.getX(), snake.getY()) == true){
		board.paneArr.get(snake.getX()).get(snake.getY()).getChildren().get(0).setVisible(false);
		snake.resizeBody();
		this.generateFood();
	    }
	}

	private void generateFood(){
	    Random generator = new Random();
	    int x,y;

	    do{

		x = generator.nextInt(79);
		y = generator.nextInt(79);
		if(x == 0) { x = 1; }
		if(y == 0) { y = 1; }
		
	    }while(!isCorrect(x, y));

	    board.paneArr.get(x).get(y).getChildren().get(0).setVisible(true);
	}

	private boolean isCorrect(int x, int y){
	    for( int part=0; part < snake.body.size(); part++){
		if(snake.body.get(part).get(0) == x &&
		   snake.body.get(part).get(1) == y) {
		    return false;
		}
	    }
	    return true;
	}
    }
    /////////////////////////////////
	
	
    public void createKeyEvent(Scene myScene){
	myScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent keyEvent){
		    
		    if(!snake.isDead){

			int lastX, lastY;
			lastX = snake.getLastX();
			lastY = snake.getLastY();
			
			if(keyEvent.getCode() == KeyCode.A){

			    if(snake.getTowardX() != 1 && snake.getTowardY() != 0){
				snake.setToward(-1, 0);
			    }
			    
			}else if(keyEvent.getCode() == KeyCode.D){

			    if(snake.getTowardX() != -1 && snake.getTowardY() != 0){
				snake.setToward(1, 0);
			    }
			    
			}else if(keyEvent.getCode() == KeyCode.W){

			    if(snake.getTowardX() != 0 && snake.getTowardY() != 1){
				snake.setToward(0, -1);
			    }
			    
			}else if(keyEvent.getCode() == KeyCode.S){

			    if(snake.getTowardX() != 0 && snake.getTowardY() != -1){
				snake.setToward(0, 1);
			    }
			}else if(keyEvent.getCode() == KeyCode.ENTER){
			    if( isPause == false){
				isPause = true;
			    }else{
				isPause = false;
			    }
			}
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
