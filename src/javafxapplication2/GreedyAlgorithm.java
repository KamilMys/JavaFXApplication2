package javafxapplication2;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreedyAlgorithm {
    
    private ArrayList<Coordinates> arrayList;
    private double operationTime;
    private double roadLenght;
    
    public GreedyAlgorithm(LinkedList <Coordinates> linkedList){
        arrayList = new ArrayList<Coordinates>();
        arrayList.addAll(linkedList);
        operationTime = 0;
        roadLenght = 0;
    }
    
    public double getTime(){
        return operationTime;
    }
    
    public double getRoad(){
        return roadLenght;
    }
    
    public static double countRoad(Coordinates coordinatesFirst, Coordinates coordinatesSecond){
        return Math.sqrt((coordinatesFirst.getX() - coordinatesSecond.getX())*(coordinatesFirst.getX() - coordinatesSecond.getX()) + (coordinatesFirst.getY() - coordinatesSecond.getY())*(coordinatesFirst.getY() - coordinatesSecond.getY()));      
    }
    
    public double countValue(Coordinates first, Coordinates second){
        return(first.getWartosc()+second.getWartosc())/countRoad(first,second);
    }
    
    public ArrayList<Coordinates> greedyMethod(){
        LocalTime localTime = LocalTime.now();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GreedyAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Random random = new Random();
        double minRoad, roads;
        int start = random.nextInt(arrayList.size());
        Collections.swap(arrayList, 0, start);
        for(int i = 0; i < arrayList.size()-1; ++i){
            int index = i+1;
            minRoad = countValue(arrayList.get(i), arrayList.get(index));
            roads = countRoad(arrayList.get(i), arrayList.get(index));
            for(int j = i+2; j < arrayList.size(); ++j){
                double localRoad = countValue(arrayList.get(i), arrayList.get(j));
                double localWay = countRoad(arrayList.get(i), arrayList.get(j));
                if(localRoad>minRoad){
                    index = j;
                    minRoad = localRoad;
                    roads = localWay;
                }
            }
            Collections.swap(arrayList, i+1, index);
            roadLenght += roads;     
        }
        LocalTime localTime2 = LocalTime.now();
        operationTime = localTime2.getSecond()-localTime.getSecond();
        return arrayList;
    }
    
   
}
