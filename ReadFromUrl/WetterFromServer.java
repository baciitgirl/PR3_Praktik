import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

    public class WetterFromServer {
        public static void main(String[] args) {
            // Gib hier deinen API-Key ein
            final String API_KEY = "DEIN_API_KEY_HIER";
            final String CITY = "Graz";
            final String URL_STRING =
                    "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&units=metric&appid=" + API_KEY;

            try {
                // Verbindung zur URL herstellen
                URL url = new URL(URL_STRING);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                // Antwort lesen
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Zeilenweise Antwort sammeln
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Antwort als JSON-Text ausgeben
                System.out.println("Antwort vom Wetterserver:");
                System.out.println(response.toString());

                // → Du kannst hier optional mit org.json oder Gson weiterverarbeiten

            } catch (Exception e) {
                System.out.println("Fehler beim Abrufen der Wetterdaten: " + e.getMessage());
            }
        }
    }


