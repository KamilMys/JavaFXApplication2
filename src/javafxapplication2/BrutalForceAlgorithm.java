package javafxapplication2;

import java.util.ArrayList;
import java.util.LinkedList;

public class BrutalForceAlgorithm {
    
    private ArrayList<Coordinates> arrayList;
    private double operationTime;
    private double roadLenght;
    
    public BrutalForceAlgorithm(LinkedList <Coordinates> linkedList){
        arrayList = new ArrayList<Coordinates>();
        arrayList.addAll(linkedList);
        operationTime = 0;
        roadLenght = 0;
    }
    
    
    
}
