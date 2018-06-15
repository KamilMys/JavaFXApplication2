package javafxapplication2;

public class Coordinates{
    
    private double x;
    private double y;
    private int wartosc;
    private long numer;
    
    public Coordinates(double x, double y, int wartosc, long numer){
        this.x = x;
        this.y = y;
        this.wartosc = wartosc;
        this.numer = numer;
    }

    public long getNumer() {
        return numer;
    }

    public void setNumer(long numer) {
        this.numer = numer;
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
