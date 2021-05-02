import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.geometry.Rectangle2D;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;

public class Menu{

    public boolean toBegin = false;
    
    public void menu(Stage mainStage){
	double div = 0.2;
	
	Button start = new Button("New game");
	Button quit = new Button("Quit");
	
	start.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event){
		    toBegin = true;
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
}
