package Snake;

import java.util.ArrayList;

public class Snake{

    ArrayList<ArrayList<Integer>> snake = new ArrayList<ArrayList<Integer>>();

    public Snake(){
	for(int i=0; i<2; i++){
	    snake.add(new ArrayList<Integer>());
	}
    }

    
}
