import java.util.Scanner;

public class Echo {
    public void echo(Scanner sc, String line) {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line);
            System.out.println(" " + input);
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
