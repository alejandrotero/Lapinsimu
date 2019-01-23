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

        long date = System.currentTimeMillis();

        Scribe e = new Scribe();
        System.out.println("creation");
        e.creerDB("express_response_db");
        System.out.println("");
        Integer a = 11;
        System.out.println("ecriture");
        e.ecrire("express_response_db", a, date);
        System.out.println("");

        System.out.println("lecture");
        System.out.println(e.lire("express_response_db"));



        System.out.println("fin");
    }
}