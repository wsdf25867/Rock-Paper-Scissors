package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        Scanner systemScanner = new Scanner(System.in);
        File file = new File("rating.txt");
        Scanner fileScanner = new Scanner(file);

        int score = 0;

        System.out.print("Enter your name: ");
        String name = systemScanner.nextLine();
        System.out.printf("Hello, %s\n", name);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            if (name.equals(line.split(" ")[0])) {
                score = Integer.parseInt(line.split(" ")[1]);
                break;
            }
        }
        fileScanner.close();
        String[] options = "rock,paper,scissors".split(",");
        String optionsInput = systemScanner.nextLine();
        if (optionsInput.length() > 0) {
            options = optionsInput.split(",");
        }
        System.out.println("Okay, let's start");

        while (true) {
            Random random = new Random();
            String computer = options[random.nextInt(options.length)];
            String user = systemScanner.nextLine();

            if ("!exit".equals(user)) {
                System.out.println("Bye!");
                break;
            }

            if ("!rating".equals(user)) {
                System.out.printf("Your rating: %d\n", score);
                continue;
            }

            if (Arrays.stream(options).noneMatch(o -> o.equals(user))) {
                System.out.println("Invalid input");
                continue;
            }

            int compIdx = Arrays.stream(options).toList().indexOf(computer);
            int userIdx = Arrays.stream(options).toList().indexOf(user);

            if (userIdx > compIdx) {
                userIdx -= options.length;
            }

            if (compIdx == userIdx) {
                System.out.printf("There is a draw (%s)\n",computer);
                score += 50;
            } else if (compIdx - userIdx <= options.length / 2) {
                System.out.printf("Sorry, but computer chose %s\n", computer);
            } else {
                System.out.printf("Well done. Computer chose %s and failed\n", computer);
                score += 100;
            }
        }
    }
}
