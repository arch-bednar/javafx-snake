package Snake;

import java.util.ArrayList;
import java.util.Arrays;

public class Snake{

    public ArrayList<ArrayList<Integer>> body = new ArrayList<ArrayList<Integer>>();

    private int[] toward = {0, 1}; //toward x,y
    public static boolean isDead = false;
    
    public Snake(){
	for(int i=0; i<8; i++){
	    body.add(new ArrayList<Integer>());
	    body.get(i).add(10);
	    body.get(i).add(10-i);
	}

    }

    public synchronized void changePos(){

	int tempX, tempY, tX, tY;
	tempX = body.get(0).get(0);
	tempY = body.get(0).get(1);

	body.set(0, new ArrayList<Integer>(Arrays.asList(tempX+toward[0],
							 tempY+toward[1])));

	//System.out.println(body.get(0).get(1));
	//System.out.println(tempX+toward[0] + " " + (tempY+toward[1]) + " wspo");

	for(int row=1; row<body.size(); row++){
	    tX=body.get(row).get(0);
	    tY=body.get(row).get(1);

	    body.set(row, new ArrayList<Integer>(Arrays.asList(tempX, tempY)));
	    
	    tempX=tX;
	    tempY=tY;
	}

	try{
	    Thread.sleep(100);
	}catch(InterruptedException ex){
	    ex.getMessage();
	}
    }

    public void resizeBody(){
	int lastX, lastY;
	lastX = body.get(body.size()-1).get(0);
	lastY = body.get(body.size()-1).get(1);
	
	//changePos();
	
	body.add(new ArrayList<Integer>());
	body.get(body.size()-1).add(lastX);
	body.get(body.size()-1).add(lastY);
    }

    public boolean isCollision(){
	int headX, headY;
	headX = body.get(0).get(0);
	headY = body.get(0).get(1);

	for(int row=1; row<body.size(); row++){
	    if(headX == body.get(row).get(0) && headY == body.get(row).get(1)){
		return true;
	    }
	}
	if(headX == 0 || headX == 79){
	    return true;
	}else if(headY == 0 || headY == 79){
	    return true;
	}
	
	return false;
    }

    public int getLastX(){
	return body.get(body.size()-1).get(0);
    }

    public int getLastY(){
	return body.get(body.size()-1).get(1);
    }

    public int getX(){
	return body.get(0).get(0);
    }

    public int getY(){
	return body.get(0).get(1);
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

    public synchronized void setToward(int x, int y){

	    this.setTowardX(x);
	    this.setTowardY(y);
    }
}
