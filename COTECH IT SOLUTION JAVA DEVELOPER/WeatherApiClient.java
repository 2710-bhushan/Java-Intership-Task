import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApiClient {

    // Base URL for OpenWeatherMap API
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "your_api_key_here"; // Replace with your OpenWeatherMap API key

    public static void main(String[] args) {
        // Example city to fetch weather data for
        String city = "London";
        
        try {
            // Build the complete API URL
            String urlString = API_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";

            // Create a URL object
            URL url = new URL(urlString);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) { // HTTP OK
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                // Parse and display the response
                parseAndDisplayWeather(response.toString());
            } else {
                System.out.println("Error: Unable to fetch weather data. HTTP response code: " + responseCode);
            }

            // Disconnect the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseAndDisplayWeather(String jsonResponse) {
        try {
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Extract data from the JSON object
            String cityName = jsonObject.getString("name");
            JSONObject main = jsonObject.getJSONObject("main");
            double temperature = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
            String description = weather.getString("description");

            // Display the weather data
            System.out.println("Weather Data for " + cityName + ":");
            System.out.println("Temperature: " + temperature + " °C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Condition: " + description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
