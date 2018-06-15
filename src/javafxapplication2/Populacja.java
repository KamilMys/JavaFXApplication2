package javafxapplication2;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Populacja {
    private LinkedList<Osobnik> osobnicy;
    private int mutacja;
    private int krzyzowanie;
    private int liczbaOsobnikow; 

    public LinkedList<Osobnik> getOsobnicy() {
        return osobnicy;
    }

    public void setOsobnicy(LinkedList<Osobnik> osobnicy) {
        this.osobnicy = osobnicy;
    }

    public int getMutacja() {
        return mutacja;
    }

    public void setMutacja(int mutacja) {
        this.mutacja = mutacja;
    }

    public int getKrzyzowanie() {
        return krzyzowanie;
    }

    public void setKrzyzowanie(int krzyzowanie) {
        this.krzyzowanie = krzyzowanie;
    }

    public int getLiczbaOsobnikow() {
        return liczbaOsobnikow;
    }

    public void setLiczbaOsobnikow(int liczbaOsobnikow) {
        this.liczbaOsobnikow = liczbaOsobnikow;
    }

    public Populacja(int mutowanie, int krzyzowka) {
        this.mutacja = mutowanie;
        this.krzyzowanie = krzyzowka;
        osobnicy = new LinkedList<Osobnik>();
    }
    
    public void utworzPopulacje(Osobnik osobnik){
       Osobnik pierwszy = new Osobnik();
       pierwszy.kopiujGenom(osobnik);
       osobnicy.add(pierwszy);
       for(int i = 1; i<liczbaOsobnikow; ++i){
           Osobnik nowyOsobnik = new Osobnik();
           nowyOsobnik.kopiujGenom(osobnicy.get(i-1));
           nowyOsobnik.getGenom().mutuj();
           osobnicy.add(nowyOsobnik);
       }
    }
    
    public void ocenPopulacje(LinkedList<Coordinates> lista){
        for(Osobnik osobnik: osobnicy){
            osobnik.ocenGenom(lista);
        }
    }
    
    public void znajdzLepszegoOsobnika(Osobnik najlepszy){
        for(Osobnik osobnik: osobnicy){
            if(osobnik.czyLepszy(najlepszy)){
                najlepszy.kopiujOsobnika(osobnik);
            }
        }
    }
    
    public Osobnik znajdzPartnera(Osobnik partner){
        while(true){
            int indeks = ThreadLocalRandom.current().nextInt(0, osobnicy.size());
            if(osobnicy.get(indeks) != partner){
                return osobnicy.get(indeks);
            }
        }
    }
    
    public Osobnik krzyzuj(Osobnik tata, Osobnik mama){
        Osobnik dziecko = new Osobnik();
        int start = ThreadLocalRandom.current().nextInt(0, tata.getGenom().getIndeksy().size());
        int stop = ThreadLocalRandom.current().nextInt(start, mama.getGenom().getIndeksy().size());
        for(int i = start; i <= stop; ++i){
            dziecko.getGenom().getIndeksy().add(tata.getGenom().getIndeksy().get(i));
            
        }
        for(int i = 0; i < mama.getGenom().getIndeksy().size(); ++i){
            if(!dziecko.getGenom().getIndeksy().contains(mama.getGenom().getIndeksy().get(i))){
                dziecko.getGenom().getIndeksy().add(mama.getGenom().getIndeksy().get(i));
            }
        }
        return dziecko;
    }
    
    public void utworzPopulacjeDziecieca(Populacja dzieci){
        dzieci.getOsobnicy().clear();
        for(Osobnik rodzic : osobnicy){
            if(this.krzyzowanie >= ThreadLocalRandom.current().nextInt(0, 100)){
                Osobnik partner = new Osobnik();
                partner.kopiujOsobnika(znajdzPartnera(rodzic));
                dzieci.getOsobnicy().add(krzyzuj(partner,rodzic));
                dzieci.getOsobnicy().add(krzyzuj(rodzic,partner));
            }
        }
        dzieci.setKrzyzowanie(krzyzowanie);
        dzieci.setMutacja(mutacja);
        dzieci.setLiczbaOsobnikow(dzieci.getOsobnicy().size());
    }
    
    public void mutujPopulacje(){
        for(Osobnik osobnik: osobnicy){
            if(this.mutacja >= ThreadLocalRandom.current().nextInt(0, 100)){
                osobnik.getGenom().mutuj();
            }
        }
    }
    
    public void kopiujOsobnikow(Populacja populacja){
        osobnicy.clear();
        osobnicy.addAll(populacja.getOsobnicy());
    }
    
    public void kopiuj(Populacja populacja){
        this.mutacja = populacja.getMutacja();
        this.krzyzowanie = populacja.getKrzyzowanie();
        this.liczbaOsobnikow = populacja.getLiczbaOsobnikow();
        kopiujOsobnikow(populacja);
    }
}
