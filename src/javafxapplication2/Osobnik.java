
package javafxapplication2;

import java.util.LinkedList;

public class Osobnik {
    private Genom genom;
    private Double wartosc;
    private Double trasa;
    

    public Double getTrasa() {
        return trasa;
    }

    public void setTrasa(Double trasa) {
        this.trasa = trasa;
    }
    

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
        this.trasa = 0.0;
        genom = new Genom(0);
    }
    
    public static Coordinates getMiasto(LinkedList<Coordinates> lista, long numer){
        for(Coordinates item: lista){
            if(item.getNumer() == numer){
                return item;
            }
        }
        return null;
    }
    
    public void ocenGenom(LinkedList<Coordinates> lista){
        this.wartosc = this.trasa = 0.0;
        int rozmiar = genom.getIndeksy().size();
        for(int i = 0; i<rozmiar; ++i){
            Coordinates first = getMiasto(lista, genom.getIndeksy().get(i));
            Coordinates second = getMiasto(lista, genom.getIndeksy().get((i+1)%rozmiar));
            this.wartosc += GreedyAlgorithm.countValue(first, second);
            this.trasa += GreedyAlgorithm.countRoad(first, second);
        }
    }
    
    public void kopiujOsobnika(Osobnik osobnik){
        this.trasa = osobnik.trasa;
        this.wartosc = osobnik.wartosc;
        this.genom.kopiujIndeksy(osobnik.getGenom());
    }
    
    public boolean czyLepszy(Osobnik osobnik){
        return ((this.trasa/this.wartosc)>(osobnik.getTrasa()/osobnik.getWartosc()));
    }
    
    public void kopiujGenom(Osobnik osobnik){
        this.genom.kopiujIndeksy(osobnik.getGenom());
    }
}
