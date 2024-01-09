package io.tripled.marsrover;

import java.util.Scanner;

public class MarsRoverApplication {


    public static void main(String[] args) {
        printLogo();
        readInput();
    }

    public static String printLogo() {
        String logo = """
                **************************"
                **    Gunz Rover        **"
                **************************
                """;
        System.out.println(logo);
        return logo;
    }


    public static void readInput() {
        System.out.println("> q to quit");
        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            do {
                input = scanner.nextLine();
                System.out.println("I read :" + input);

            }
            while (!isQuit(input));
        }
        System.out.println("*********END*****************");
    }


    private static boolean isQuit(String input) {
        return "q".equalsIgnoreCase(input);
    }

}
