import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadFromURL {
    public static void main(String[] args) {
        try {
            // Programmstart
            System.out.println("Main starting up...");

            // Benutzeraufforderung zur Eingabe einer URL
            System.out.println("Bitte die gewünschte URL eingeben: ");

            // Eingabe-Stream von der Konsole (Benutzereingabe)
            BufferedReader userInput = new BufferedReader(
                    new InputStreamReader(System.in));

            // Liest eine Zeile von der Konsole (URL)
            String user_url = userInput.readLine();
            System.out.println("Habe folgende URL bekommen: '" + user_url + "'");

            // URL-Objekt wird erstellt basierend auf der Eingabe
            URL myURL = new URL(user_url);

            // Öffnet einen Stream zur URL und liest zeichenweise
            InputStreamReader isr = new InputStreamReader(myURL.openStream());

            // BufferedReader liest zeilenweise aus dem InputStreamReader
            BufferedReader br = new BufferedReader(isr);

            String one_line;

            // Liest jede Zeile von der Webseite und gibt sie auf der Konsole aus
            while ((one_line = br.readLine()) != null) {
                System.out.println(one_line);
            }

        } catch (IOException e) {
            // Fehler beim Lesen der URL oder bei der Benutzereingabe
            throw new RuntimeException(e);
        }
    }
}
