package Snake;

import java.util.ArrayList;

public class Snake{

    ArrayList<ArrayList<Integer>> snake = new ArrayList<ArrayList<Integer>>();

    private int[] toward = {-1, 0}; //toward x,y
    
    public Snake(){
	for(int i=0; i<2; i++){
	    snake.add(new ArrayList<Integer>());
	}
    }

    public void changePos(int x, int y){
	
    }

    public int getTowardX(){
	return toward[0];
    }

    public int getTowardY(){
	return toward[1];
    }
}
