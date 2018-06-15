
package javafxapplication2;

public class Osobnik {
    private Genom genom;
    private Double wartosc;

    public Genom getGenom() {
        return genom;
    }

    public void setGenom(Genom genom) {
        this.genom = genom;
    }

    public Double getWartosc() {
        return wartosc;
    }

    public void setWartosc(Double wartosc) {
        this.wartosc = wartosc;
    }

    public Osobnik() {
        this.wartosc = 0.0;
        genom = new Genom(0);
    }
    
    
    
}
