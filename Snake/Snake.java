package Snake;

import java.util.ArrayList;

public class Snake{

    ArrayList<ArrayList<Integer>> body = new ArrayList<ArrayList<Integer>>();

    private int[] toward = {1, 0}; //toward x,y
    
    public Snake(){
	for(int i=0; i<2; i++){
	    body.add(new ArrayList<Integer>());
	}
    }

    public void changePos(){
	int tempX, tempY, tX, tY;
	tempX = body.get(0).get(0);
	tempY = body.get(0).get(1);

	body.get(0).set(0, tempX+toward[0]);
	body.get(0).set(1, tempY+toward[1]);

	for(int row=1; row<body.size(); row++){
	    tX=body.get(row).get(0);
	    tY=body.get(row).get(1);

	    body.get(0).set(0, tempX);
	    body.get(0).set(1, tempY);

	    tempX=tX;
	    tempY=tY;
	}
    }

    public void resizeBody(){
	int lastX, lastY;
	lastX = body.get(body.size()-1).get(0);
	lastY = body.get(body.size()-1).get(1);
	
	changePos();
	
	body.add(new ArrayList<Integer>());
	body.get(body.size()-1).add(lastX);
	body.get(body.size()-1).add(lastY);
    }

    public boolean isCollision(){
	int headX, headY;
	headX = body.get(0).get(0);
	headY = body.get(0).get(1);

	for(int row=1; row<=body.size(); row++){
	    if(headX == body.get(row).get(0) && headY == body.get(row).get(1)){
		return true;
	    }
	}
	return false;
    }
    

    public int getTowardX(){
	return toward[0];
    }

    public int getTowardY(){
	return toward[1];
    }

    public void setTowardX(int x){
	toward[0] = x;
    }

    public void setTowardY(int y){
	toward[1] = y;
    }
}
