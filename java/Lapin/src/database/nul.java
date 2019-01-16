package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class nul {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "http://localhost:8086/query";

	private static final String POST_URL = "http://localhost:8086/query";
	private static final String POST_URL2 = "http://localhost:8086/write?db=mydb";
	private static final String POST_URL3 = "http://localhost:8086/query?db=mydb";    
    

    private static final String POST_PARAMS = "q=CREATE DATABASE mydbbi";
    private static final String POST_PARAMS2 = "cpu_load_short,host=server01,region=us-west value=0.64 1434055562000000000";
    private static final String POST_PARAMS3 = "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'";

    //private static final String POST_PARAMS = "pression=20, timey=20";

	public static void main(String[] args) throws IOException {

		//sendGET(POST_URL);
		//System.out.println("GET DONE");
        sendPOST(POST_PARAMS, POST_URL);
        //sendPOST(POST_PARAMS2, POST_URL2);
        sendPOST(POST_PARAMS3, POST_URL3);

		System.out.println("POST DONE");
	}
// --data-urlencode "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'"

	private static void sendGET(String urlv) throws IOException {
		URL obj = new URL(urlv);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
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
		} else {
			System.out.println("GET request not worked");
		}

	}

	private static void sendPOST(String param,String urlvrai) throws IOException {
		URL obj = new URL(urlvrai);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", USER_AGENT);

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
		} else {
			System.out.println("POST request not worked");
		}
	}

}
