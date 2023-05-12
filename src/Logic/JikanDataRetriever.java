/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Andrei
 */
public class JikanDataRetriever {
    
    
    private final String API_URL = "https://api.jikan.moe/v4";
    
    
    public int countAnimeBySeason() {
    int animeCount = 0;
    String endpoint = "/seasons/now";
    String urlStr = API_URL + endpoint;

    try {
        String response = sendGetRequest(urlStr);
        JSONObject jsonObject = new JSONObject(response);

        animeCount = jsonObject.getJSONObject("pagination").getJSONObject("items").getInt("total");;
        
    } catch (IOException e) {
        e.printStackTrace();
    }
        return animeCount;
}
    
    public String getAnimeSeason() {
    String tmp = "";
    String endpoint = "/seasons/now";
    String urlStr = API_URL + endpoint;

    try {
        String response = sendGetRequest(urlStr);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray array = jsonObject.getJSONArray("data");

        tmp = array.getJSONObject(0).getString("season");
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    return tmp;
        
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String sendGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                    System.out.println(line);
                }
            }
            connection.disconnect();
            return response.toString();
        } else {
            connection.disconnect();
            throw new IOException("Request failed" );
        }
    }
}
    
    
 
