import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadFromURLAndSaveToFile {
    public static void main(String[] args) {
        try {
            // Startmeldung
            System.out.println("Main starting up...");
            System.out.println("Bitte die gewünschte URL eingeben: ");

            // Liest die Benutzereingabe von der Konsole
            BufferedReader userInput = new BufferedReader(
                    new InputStreamReader(System.in));

            // Testweise wird eine feste URL verwendet – alternativ Zeile darüber aktivieren
//            String user_url = userInput.readLine();
            //String user_url = "https://ipof.me"; // feste Test-URL
            String user_url = "https://www.google.com"; // feste Test-URL

            System.out.println("Habe folgende URL bekommen: '" + user_url + "'");

            // Erstellt ein URL-Objekt mit der angegebenen Adresse
            URL myURL = new URL(user_url);

//            // Verbindung zur URL öffnen
//            HttpURLConnection connection = (HttpURLConnection) myURL.openConnection();
//
//            // User-Agent setzen – so tun, als wäre es ein Browser
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Öffnet einen InputStream zur URL und liest Zeichen von der Webseite
            InputStreamReader isr = new InputStreamReader(myURL.openStream());
            BufferedReader br = new BufferedReader(isr);

            // Erstellt eine Datei-Referenz für die Ausgabedatei
            File f = new File("content.html");

            // FileWriter und BufferedWriter zum Schreiben in die Datei
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

//            Alternative Kurzform:
//            BufferedWriter bw = new BufferedWriter(new FileWriter("content.html"));

            System.out.println("---Output Datei ist hier: " + f.getAbsolutePath());

            String one_line;

            // Liest jede Zeile der Webseite, gibt sie auf der Konsole aus
            // und schreibt sie gleichzeitig in die Datei
            while ((one_line = br.readLine()) != null) {
                System.out.println(one_line);
                bw.write(one_line);
                bw.newLine(); // schreibt einen Zeilenumbruch in die Datei
            }

            // Stellt sicher, dass alle Daten in die Datei geschrieben wurden
            bw.flush();

            // Schließt den BufferedWriter (und damit auch den FileWriter)
            bw.close();

        } catch (IOException e) {
            // Fehlerausgabe bei IO-Problemen
            throw new RuntimeException(e);
        }
    }
}

