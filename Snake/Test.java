import java.util.ArrayList;

public class Test{
    public ArrayList<ArrayList<Integer>> i = new ArrayList<ArrayList<Integer>>();

    public Test(){
	xD();
    }
    public void xD(){
	for(int j=0; j<10; j++){
	    i.add(new ArrayList<Integer>());
	    i.get(j).add(j);
	    i.get(j).add(j);
	}
    }

    public static void main(String[] args){
	Test t = new Test();
	for(int j=0; j<10; j++)
	    System.out.println(t.i.get(j).get(0) + " " + t.i.get(j).get(1));
	
    }
}
