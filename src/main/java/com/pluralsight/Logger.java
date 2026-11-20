package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Logger {
    public static void main(String[] args) throws IOException {
        try {
            Scanner s = new Scanner(System.in);
            StringBuilder output = new StringBuilder();
            LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("America/New_York"));
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss");
            output.append(currentTime.format(timeFormat)).append(" launch").append("\n");

            String menu = """
                    Enter term to search for.
                    Press X to exit.
                    """;
            boolean exitFlag = false;
            do {
                currentTime = LocalDateTime.now(ZoneId.of("America/New_York"));
                String userInput = getInputFromConsole(menu, s, false);
                if (userInput.equalsIgnoreCase("x")) {
                    output.append(currentTime.format(timeFormat)).append(" exit");
                    exitFlag = true;
                } else {
                    output.append(currentTime.format(timeFormat)).append(" search: ").append(userInput).append("\n");
                }
            } while (!exitFlag);
            s.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/logs.txt"));
            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }

    public static String getInputFromConsole(String display, Scanner s, boolean clearBuffer) {
        if (clearBuffer) s.skip("\n");
        System.out.println(display);
        return s.nextLine().trim();
    }
}
