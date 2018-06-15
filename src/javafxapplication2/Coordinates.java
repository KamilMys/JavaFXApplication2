package javafxapplication2;

public class Coordinates{
    
    private double x;
    private double y;
    private int wartosc;
    
    public Coordinates(double x, double y, int wartosc){
        this.x = x;
        this.y = y;
        this.wartosc = wartosc;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public int getWartosc(){
        return wartosc;
    }
    
    public void setWartosc(int wartosc){
        this.wartosc = wartosc;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }
    
    public double getValueBeetwen(Coordinates second){
        return (wartosc+second.getWartosc())/GreedyAlgorithm.countRoad(this, second);
    }
    
}
