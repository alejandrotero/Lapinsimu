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
<<<<<<< HEAD
        
        long date = System.currentTimeMillis();

        ecriteur e = new ecriteur();
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
=======
       /*
        try {
        //*/
        ecriteur e = new ecriteur();
        for(int i = 1; i<= 10; i++){
            
            System.out.println(e.ecrire(10, 10));
            System.out.println(e.lire());
        }
        e.creerDB("mydbbi");
        System.out.println("1");
>>>>>>> dae1ca5362a4f30348920af8786f746b4f214d5d

        e.ecrire2(20, 20);

            /*
`
                select * from pression
                where host = ${Influx.escape.stringLit(os.hostname())}
                order by time desc
                limit 1
              `)

            */
            /*

            Random rn = new Random();
            URL input;
            while(true) {
                input = new URL("http", "localhost", 8086, "/write?db=mydb");
                client = (HttpURLConnection) input.openConnection();
                client.setRequestMethod("POST");
                client.setDoOutput(true);

                double thermals = rn.nextDouble();
                String s = "cpu_temperature value=" + thermals;

                try (OutputStreamWriter writer =
                        new OutputStreamWriter(client.getOutputStream())) {
                    writer.write(s);
                }
                /*
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                            client.getInputStream()));
                String decodedString;
                while ((decodedString = in.readLine()) != null) {
                    System.out.println(decodedString);
                }
                in.close();
                */
                /*
                System.out.println(s); // for debugging
                Thread.sleep(1000); // send data every 1 second
            }

            

        } catch(MalformedURLException error) {
            System.out.println("Malformed URL!");
        } catch(SocketTimeoutException error) {
            System.out.println("Socket Timeout Exception!");
        } catch (IOException error) {
            System.out.println("IOException!");
            System.out.println(error);
        } catch(InterruptedException e) {
            System.out.println("InterruptedException!");
        } finally {
            if(client != null) { // Make sure the connection is not null.
                client.disconnect();
            }
        }
        //*/


    }
}