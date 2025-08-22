import java.util.Scanner;

public class Echo {
    public void start(Scanner sc, String line) {
        IO io = new ScannerIO(sc);
        EchoSession session = new EchoSession(line);
        while (true) {
            String read = io.readLine();
            if (!session.handleCommand(read, io)) break;
        }
    }
}