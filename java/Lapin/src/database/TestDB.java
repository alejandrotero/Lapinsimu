package database;
import java.net.HttpURLConnection;

import java.net.*;
import java.io.*;
import java.util.*;

public class TestDB {
/*
Class de test pour moi,
utiliser plutot les fonctions ecrire et lire de ecriteur
bisous a tous
*/
    public static void main(String[] args) {

        int date = (int) System.currentTimeMillis();

        Scribe e = new Scribe();
        System.out.println("creation");
        //e.creerDB("PressionA");
        System.out.println("");
        Double a = 11.00;
        System.out.println("ecriture");
        e.ecrire("PressionA", a, date);
        System.out.println("");

        System.out.println("lecture");
        System.out.println(e.lire("PressionA"));



        System.out.println("fin");
    }
}