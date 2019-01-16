package database;
import java.net.HttpURLConnection;

import java.net.*;
import java.io.*;
import java.net.HttpURLConnection;

public class ecriteur {

public void ecrire(String nomdb, int valeur,int time){
     String POST_URL2 = "http://localhost:8086/write?db="+nomdb;
     String POST_PARAMS2 = "cpu_load_short,host=server01,region=us-west value=0.64 1434055572000000000";

     //String POST_PARAMS2 = "timey= "+time+", value="+valeur+", 1434055562000000000";
    try{
        sendPOST(POST_PARAMS2 , POST_URL2);
    }catch (IOException error) {
        System.out.println("IOException!");
        System.out.println(error);
    }
        
}

   
public String lire(String nomdb){
     String POST_URL3 = "http://localhost:8086/query?db="+nomdb; 
     /*"
     (`
                select * from pression
                where host = ${Influx.escape.stringLit(os.hostname())}
                order by time desc
                limit 1
              `)
"*/
     
     String POST_PARAMS3 = "q=SELECT \"*\" FROM \"pression\" ORDER BY \"time\" DESC LIMIT 1 ";
    try{
        return sendPOST(POST_PARAMS3 , POST_URL3);
    }catch (IOException error) {
        System.out.println("IOException!");
        return error.toString();
    }

}


public void creerDB(String nomdb){

	String POST_URL = "http://localhost:8086/query";
    String POST_PARAMS = "q=CREATE DATABASE "+nomdb;
    try{
        sendPOST(POST_PARAMS , POST_URL);
    }catch (IOException error) {
        System.out.println("IOException!");
        System.out.println(error);
    }
}


private static String sendPOST(String param,String urlvrai) throws IOException {
    URL obj = new URL(urlvrai);
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

    if (responseCode == HttpURLConnection.HTTP_OK) { //success
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        System.out.println(response.toString());
        return response.toString();
    } else {
        System.out.println("POST request not worked");
        return "error";
    }
}

}
 