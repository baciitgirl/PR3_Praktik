import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZeitFromServer {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Name des Standard-Zeitservers (Time Protocol nach RFC 868, Port 37)
        final String DEFAULT_TIME_SERVER = "time.nist.gov"; // Alternative: "ptbtime1.ptb.de"

        // Datumsformatierung für die Ausgabe
        final SimpleDateFormat DATUMFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Zeitdifferenz in Sekunden zwischen 1900 (Start des Time Protocols) und 1970 (Unix-Zeit)
        final long SEKUNDEN_1900_1970 = 2208988800L;

        Socket so = null;         // Socket zur Verbindung mit dem Zeitserver
        InputStream in = null;    // Eingabestrom für die Zeitdaten
        long time = 0;            // Variable zur Speicherung des Zeitwerts

        try {
            // Verbindung zum Zeitserver auf Port 37 (Time Protocol)
            so = new Socket("time.nist.gov", 37);

            // Eingabestrom öffnen
            in = so.getInputStream();

            // Der Server sendet 4 Bytes (32 Bit) als Zeitwert (Sekunden seit 1900)
            // Die 4 Bytes werden byteweise gelesen und zu einem long zusammengefügt
            for (int i = 3; i >= 0; i--) {
                time ^= (long) in.read() << i * 8; // Byte an der richtigen Stelle einfügen
            }

            // Umrechnung: Zeitwert in Sekunden von 1900 → Millisekunden seit 1970 (Unix-Zeit)
            long unixMillis = (time - SEKUNDEN_1900_1970) * 1000;

            // Ausgabe des formatierten Datums
            System.out.println(DATUMFORMAT.format(new Date(unixMillis)));

        } catch (Exception ex) {
            // Fehlerausgabe bei z. B. Netzwerkproblemen
            System.out.println(ex);
        } finally {
            // Ressourcen freigeben – Eingabestrom und Socket schließen
            if (in != null) try { in.close(); } catch (IOException ex) { /* Ignoriert */ }
            if (so != null) try { so.close(); } catch (IOException ex) { /* Ignoriert */ }
        }
    }
}

