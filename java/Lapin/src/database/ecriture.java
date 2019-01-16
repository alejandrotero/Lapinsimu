package database;
import java.net.HttpURLConnection;

import java.net.*;
import java.io.*;
import java.util.*;

public class ecriture {
/*
Class de test pour moi,
utiliser plutot les fonctions ecrire et lire de ecriteur
bisous a tous
*/
    public static void main(String[] args) {
        ecriteur e = new ecriteur();
        System.out.println("creation");
        e.creerDB("mydbfinal");

        System.out.println("ecriture");
        e.ecrire("express_response_db", 10, 10);

        System.out.println("lecture");
        e.lire("express_response_db");
        System.out.println("fin");


            /*
            `
                select * from pression
                where host = ${Influx.escape.stringLit(os.hostname())}
                order by time desc
                limit 1
              `)
        //*/


    }
}