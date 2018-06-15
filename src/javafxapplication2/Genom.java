
package javafxapplication2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Genom {
    private LinkedList<Long> indeksy;
    private long rozmiar;

    public LinkedList<Long> getIndeksy() {
        return indeksy;
    }

    public void setIndeksy(LinkedList<Long> indeksy) {
        this.indeksy = indeksy;
    }

    public long getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(long rozmiar) {
        this.rozmiar = rozmiar;
    }
    

    public Genom(long rozmiar) {
        this.indeksy = new LinkedList<Long>();
        this.rozmiar = rozmiar;
    }
    
    public void utworzGenom(LinkedList<Coordinates> lista){
        for(Coordinates miasto: lista){
            indeksy.add(miasto.getNumer());
        }
    }
    
    public void kopiujIndeksy(Genom genom){
        this.indeksy.clear();
        this.indeksy.addAll(genom.getIndeksy());
    }
    
    public void wypiszGenom(){
        System.out.println("Genom: " );
        for(Long indeks: indeksy){
            System.out.print(indeks + " ");
        }
    }
    
    public void mutuj(){
        int start = ThreadLocalRandom.current().nextInt(0, indeksy.size());
        int stop = ThreadLocalRandom.current().nextInt(start, indeksy.size());
        Collections.swap(indeksy,start,stop);
    }
    
}
