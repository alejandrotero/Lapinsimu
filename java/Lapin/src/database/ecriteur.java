package database;
import java.net.HttpURLConnection;

import generationDonnees.Courbe;
import generationDonnees.Events;
import generationDonnees.FonctionQuadratique;

import java.net.*;
import java.io.*;

public class ecriteur {
    private static HttpURLConnection client;
    URL input;
    Events etatCourant = null;
    Double valeurInitial;
    Courbe adrenaline;

    public void ecrire(String nomdb, double valeurAecrire,Long time){
        //http://localhost:8086/write?db=express_response_db' --data-binary 'pression,host=server01,timey=200 value=64'
        try{
        URL POST_URL2 = new URL("http", "localhost", 8086, "/write?db="+nomdb);

        String POST_PARAMS2 = "pression,timey="+time+" valeur=13i ";
        //+ time
        //<measurement>,<tag>[,<tags>] <field>[,<field>] <timestamp>
        //String POST_PARAMS2 = "timey= "+time+", value="+valeur+", 1434055562000000000";
        try{
            sendPOST(POST_PARAMS2 , POST_URL2);
        }catch (IOException error) {
            System.out.println("IOException!");
            System.out.println(error);
        }
        }catch (MalformedURLException error) {
            System.out.println("MalformedURLException!");
            System.out.println(error);
        }
            
    }

    public String lire(String nomdb){

        try{
            URL POST_URL3 = new URL("http", "localhost", 8086, "/query?db="+nomdb);

            // 'http://localhost:8086/query?db=mydb' --data-urlencode 'q=SELECT * FROM "mymeas"'
            //ORDER BY \"time\" DESC LIMIT 10
            String POST_PARAMS3 = "q=SELECT * FROM \"pression\" order by time desc limit 1 ";
        try{
            //return sendGet(POST_URL3, POST_PARAMS3);
            return sendPOST(POST_PARAMS3 , POST_URL3);
        }catch (IOException error) {
            System.out.println("IOException!");
            return error.toString();
        }catch(Exception error){
            System.out.println("Exception!");
            return error.toString();
        }
        }catch (MalformedURLException error) {
            System.out.println("MalformedURLException!");
            return error.toString();
        }
    }


    public void creerDB(String nomdb){
        try{
            URL POST_URL = new URL("http", "localhost", 8086, "/query");

        
            //String POST_URL = "http://localhost:8086/query";
            String POST_PARAMS = "q=CREATE DATABASE "+nomdb;
            try{
                sendPOST(POST_PARAMS , POST_URL);
            }catch (IOException error) {
                System.out.println("IOException!");
                System.out.println(error);
            }
        }catch (MalformedURLException error) {
                System.out.println("MalformedURLException!");
                System.out.println(error);
            }
    }


    // HTTP GET request
    private String sendGet(URL url) throws Exception {

            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("GET");
            client.setDoOutput(true);

            int responseCode = client.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print result
            return response.toString();


    }

    // HTTP POST request
    private static String sendPOST(String param,URL obj) throws IOException {

        
        //URL obj = new URL(urlvrai);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        // For POST only - START
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(param.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success, 204 is not a sucess because it does not sent response
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            //System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("POST request not worked");
            return "error";
        }
    }

	
    
    public void generePoint(long currentTime, String nomDB, Events event) {
    	//Time in secods
    	currentTime=currentTime/1000;
    	
    	double valeurAecrire = valeurInitial;
		if (etatCourant==null) {
			etatCourant=Events.REPOS;
			valeurInitial = FonctionQuadratique.generateNormalRandomNumber(100, 16.48);
			valeurAecrire=valeurInitial;
		} else if (event!=null && event!=etatCourant) {
			etatCourant=event;
			FonctionQuadratique fMont = FonctionQuadratique.createFonctionMonteAdrenaline(valeurInitial);
			FonctionQuadratique fDesc = FonctionQuadratique.createFonctionDescendAdrenaline(valeurInitial);
			adrenaline = new Courbe(fMont, fDesc, currentTime, valeurInitial);
			valeurAecrire = adrenaline.getValeur(currentTime);
		}else{
			valeurAecrire = adrenaline.getValeur(currentTime);
		}
		ecrire(nomDB, valeurAecrire, currentTime);
	}



    

}
 