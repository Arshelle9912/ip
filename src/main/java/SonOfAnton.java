import java.util.Scanner;

public class SonOfAnton {
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        String logo = "    _    _   _ _______  ____  _   _ \n"
                + "   / \\  | \\ | |__   __|/ __ \\| \\ | |\n"
                + "  / _ \\ |  \\| |  | |  | |  | |  \\| |\n"
                + " / ___ \\| |\\  |  | |  | |__| | |\\  |\n"
                + "/_/   \\_\\_| \\_|  |_|   \\____/|_| \\_|\n";

        System.out.println("Hello from Son of\n" + logo);
        System.out.println(LINE);
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
        Scanner sc = new Scanner(System.in);
        Echo echo = new Echo();
        echo.echo(sc, LINE);
    }
}