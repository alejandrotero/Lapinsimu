package database;
import java.net.HttpURLConnection;

import java.net.*;
import java.io.*;

public class ecriteur {
        private static HttpURLConnection client;
    URL input;

<<<<<<< HEAD
    public void ecrire(String nomdb, Integer valeur,Long time){
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
=======


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
>>>>>>> dae1ca5362a4f30348920af8786f746b4f214d5d

}
 