
package javafxapplication2;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class GenethicAlgorithm {
    private LinkedList<Coordinates> miasta;
    private Populacja rodzice;
    private Populacja dzieci;
    private Osobnik najlepszy;
    private Double najlepszaTrasa;
    private Double czas;

    public Double getNajlepszaTrasa() {
        return najlepszaTrasa;
    }

    public void setNajlepszaTrasa(Double najlepszaTrasa) {
        this.najlepszaTrasa = najlepszaTrasa;
    }

    public Double getCzas() {
        return czas;
    }

    public void setCzas(Double czas) {
        this.czas = czas;
    }
    

    public LinkedList<Coordinates> getMiasta() {
        return miasta;
    }

    public void setMiasta(LinkedList<Coordinates> miasta) {
        this.miasta = miasta;
    }

    public Populacja getRodzice() {
        return rodzice;
    }

    public void setRodzice(Populacja rodzice) {
        this.rodzice = rodzice;
    }

    public Populacja getDzieci() {
        return dzieci;
    }

    public void setDzieci(Populacja dzieci) {
        this.dzieci = dzieci;
    }

    public Osobnik getNajlepszy() {
        return najlepszy;
    }

    public void setNajlepszy(Osobnik najlepszy) {
        this.najlepszy = najlepszy;
    }

    public GenethicAlgorithm() {
        miasta = new LinkedList<Coordinates>();
        this.najlepszaTrasa = this.czas = 0.0;
    }
    
    public GenethicAlgorithm(LinkedList<Coordinates> miasta) {
        this.miasta = miasta;
        this.najlepszaTrasa = this.czas = 0.0;
    }
    
    public ArrayList<Coordinates> uruchomAlgorytm(){
        LocalTime localTime = LocalTime.now();
        najlepszy = new Osobnik();
        najlepszy.getGenom().utworzGenom(miasta);
        najlepszy.ocenGenom(miasta);
        rodzice = new Populacja(50,50);
        dzieci = new Populacja(50,50);
        rodzice.utworzPopulacje(najlepszy);
        System.out.println("Populacja rodzicielska: " + rodzice.getOsobnicy().size());
        rodzice.ocenPopulacje(miasta);
        rodzice.znajdzLepszegoOsobnika(najlepszy);
        for(int i = 0; i <1000; ++i){
            System.out.println(i);
            rodzice.utworzPopulacjeDziecieca(dzieci);
            System.out.println("Utworzyłem populacje dziecieca");
            dzieci.mutujPopulacje();
            System.out.println("Zmutowalem populacje ");
            dzieci.ocenPopulacje(miasta);
            System.out.println("Oceniłem populacje ");
            dzieci.znajdzLepszegoOsobnika(najlepszy);
            System.out.println("Znalazłem najlepszego osobnika ");
            rodzice.kopiuj(dzieci);
            System.out.println("Skopiowałem ");
            dzieci.getOsobnicy().clear();
        }
        this.najlepszaTrasa = najlepszy.getTrasa();
        LocalTime stop = LocalTime.now();
        this.czas = (double)(stop.getSecond() - localTime.getSecond());
        return getRozwiazanie();
    }
    
    public ArrayList<Coordinates> getRozwiazanie(){
        ArrayList<Coordinates> rozwiazanie = new ArrayList<Coordinates>();
        for(long index: najlepszy.getGenom().getIndeksy()){
            rozwiazanie.add(najlepszy.getMiasto(miasta, index));
        }
        return rozwiazanie;
    }
}
