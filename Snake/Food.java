import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Food{

    private int x,y;
    private int type;
    private boolean visibility = false;
    private Image[] image = new Image[5];
    
    public Food(){
	for(int img=0; image.size(); img++){
	    image[img] = new Image();
	}
    }
    
    public void setVisibility(boolean visibility){
	this.visibility = visibility;
    }

    public void setLocation(){
	Random generator = new Random();
	this.x = generator.nextInt()%256;
	this.y = generator.nextInt()%256;
	this.type = generator.nextInt()%5;
    }
}
