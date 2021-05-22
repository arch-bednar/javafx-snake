package Snake;

import java.util.Random;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;

public class Food extends Ellipse{

    private int x,y;
    private int type;
    
    public Food(int x, int y){
	super(4.2, 4.2, 4.2, 4.2);
	this.setVisible(false);
	this.setLocation(x, y);
	this.setFill(Color.BLUE);
    }
    
    private void setLocation(int x, int y){
	//Random generator = new Random();
	//this.x = generator.nextInt()%80;
	//this.y = generator.nextInt()%80;
	this.x = x;
	this.y = y;
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public void setFoodVisible(boolean visible){
	this.setVisible(visible);
    }
}
