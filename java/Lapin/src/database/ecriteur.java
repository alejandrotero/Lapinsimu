package database;
import java.net.HttpURLConnection;

import java.net.*;
import java.io.*;

public class ecriteur {
        private static HttpURLConnection client;
    URL input;



    public boolean ecrire(int valeur,int time){
        try {
            input = new URL("http", "localhost", 8086, "/write?db=mydb");
            client = (HttpURLConnection) input.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);

            //double thermals = rn.nextDouble();
            String s = "cpu_temperature value=" + 600000000;

            try (OutputStreamWriter writer =
                    new OutputStreamWriter(client.getOutputStream())) {
                writer.write(s);
            }

            return true;
        }catch(MalformedURLException error) {
            System.out.println("Malformed URL!");
            return false;

    }catch(SocketTimeoutException error) {
        System.out.println("Socket Timeout Exception!");
        return false;

    } catch (IOException error) {
        System.out.println("IOException!");
        System.out.println(error);
        return false;
    }

}

public void ecrire2(int valeur,int time){
    try {
        URL input;
        
            input = new URL("http", "localhost", 8086, "/write?db=mydbbi");
            client = (HttpURLConnection) input.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);

            double thermals = valeur;//rn.nextDouble();
            String s = "cpu_temperature value=" + thermals;

            try (OutputStreamWriter writer =
                    new OutputStreamWriter(client.getOutputStream())) {
                writer.write(s);
            }
            //*
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                        client.getInputStream()));
            String decodedString;
            while ((decodedString = in.readLine()) != null) {
                System.out.println(decodedString);
            }
            in.close();
            //*/
        System.out.println(s); // for debugging
        

        } catch(MalformedURLException error) {
            System.out.println("Malformed URL!");
        } catch(SocketTimeoutException error) {
            System.out.println("Socket Timeout Exception!");
        } catch (IOException error) {
            System.out.println("IOException!");
            System.out.println(error);
       // } catch(InterruptedException e) {
       //     System.out.println("InterruptedException!");
        } finally {
            if(client != null) { // Make sure the connection is not null.
                client.disconnect();
            }
        }
}

public String lire(){
    try {
        
            input = new URL("http", "localhost", 8086, "/write?db=mydb");
            client = (HttpURLConnection) input.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);
            //*
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                        client.getInputStream()));
            String decodedString;
            decodedString = in.readLine();
            //return in.readLine();
            //*
            while (decodedString != null) {
                System.out.println(decodedString);
                return decodedString;
            }
            //*/ 
            in.close();        

        } catch(MalformedURLException error) {
            System.out.println("Malformed URL!");
            return "Malformed URL!";
        } catch(SocketTimeoutException error) {
            System.out.println("Socket Timeout Exception!");
            return "Socket Timeout Exception!";
        } catch (IOException error) {
            System.out.println("IOException!");
            System.out.println(error);
            return "IOException!";
       // } catch(InterruptedException e) {
       //     System.out.println("InterruptedException!");
        } finally {
            if(client != null) { // Make sure the connection is not null.
                client.disconnect();
            }
        }
        return " t";
}




public void creerDB(String nomdb){
    try {
        URL input;
         // /query --data-urlencode "q=CREATE DATABASE foofoo"
            input = new URL("http", "localhost", 8086, "q=CREATE DATABASE "+nomdb);
            client = (HttpURLConnection) input.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);

        

        } catch(MalformedURLException error) {
            System.out.println("Malformed URL!");
        } catch(SocketTimeoutException error) {
            System.out.println("Socket Timeout Exception!");
        } catch (IOException error) {
            System.out.println("IOException!");
            System.out.println(error);
       // } catch(InterruptedException e) {
       //     System.out.println("InterruptedException!");
        } finally {
            if(client != null) { // Make sure the connection is not null.
                client.disconnect();
            }
        }
}

}
 