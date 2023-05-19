package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Heuristika {

    private final int n = 500;  //pocet
    private final int r = 350;  //min pocet predmetov v batohu
    private final int k = 9500; //min hmotnost

    ArrayList<Integer> hmotnosti = new ArrayList<Integer>();
    ArrayList<Integer> ceny = new ArrayList<Integer>();
    ArrayList<Predmet> predmety = new ArrayList<Predmet>();

    //Konštruktor s použitými metódami pre zjednodušenie kontroly
    public Heuristika() {
        //Načítava údaje zadaného súboru do zadanej ArrayList
        this.nacitajUdaje("H2_c.txt", ceny);

        //To isté ale s iným vstupným súborom a inou výstupnou ArrayList
        this.nacitajUdaje("H2_a.txt", hmotnosti);

        //Vytvorí inštancie klasy Predmet ktorá ma 3 atribúty: cena, hmotnosť
        // a index ktorý slúži pre skontrolovanie správnosti úlohy
        this.vytvorPredmety(predmety);

        //Zotriedi ArrayList predmety podľa kritérií v tomto poradí: cena, hmotnosť
        this.zotriedPredmety(predmety);

        //Vypisuje indexy vložených predmetov do Batohu ktorý je výsledným riešením úlohy
        this.vkladajDoBatohu();
    }

    private void vytvorPredmety(ArrayList<Predmet> predmety) {
        for (int i = 0; i < ceny.size(); i++) {
            Predmet predmet = new Predmet(ceny.get(i),hmotnosti.get(i),i);
            predmety.add(predmet);
        }
    }

    private void zotriedPredmety(ArrayList<Predmet> predmety) {
        Collections.sort(predmety, new Comparator<Predmet>() {
            @Override
            public int compare(Predmet p1, Predmet p2) {
                int cenaComparison = Integer.valueOf(p1.getCena()).compareTo(p2.getCena());
                if (cenaComparison != 0) {
                    return cenaComparison;
                }
                return Integer.valueOf(p1.getHmotnost()).compareTo(p2.getHmotnost());
            }
        });
    }

    private void vkladajDoBatohu() {
        int cenaBatohu = 0;
        int hmotnostBatohu = 0;

        try {
            FileWriter myWriter = new FileWriter("batoh.txt");
            for (int i = 0; i < r; i++) {
                cenaBatohu = cenaBatohu + predmety.get(i).getCena();
                hmotnostBatohu = hmotnostBatohu + predmety.get(i).getHmotnost();

                myWriter.write("Predmet [" + predmety.get(i).getIndex() + "] \n");
            }

            myWriter.write("______________" + "\n");
            myWriter.write("Cena batohu je: " + cenaBatohu + "\nHmotnosť batohu je: " + hmotnostBatohu + "\nPredmetov v batohu je " + r);
            myWriter.close();

    } catch (IOException e) {
            System.out.println("Nastala chyba!");
            e.printStackTrace();
        }
    }

    private void nacitajUdaje(String subor, ArrayList<Integer> output) {

        try {
            File suborInput = new File(subor);
            Scanner myReader = new Scanner(suborInput);
            while (myReader.hasNextInt()) {
                int data = myReader.nextInt();
                output.add(data);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nastala chyba, súbor sa nenašiel!");
            e.printStackTrace();
        }
    }
}