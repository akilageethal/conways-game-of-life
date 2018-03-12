package main.java.io;

import java.util.Scanner;

public class InputReader {

    public String readInputString() throws Exception {

        String inputString = null;
        // Read the input.
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            inputString = scanner.nextLine();
        }
        scanner.close();
        return  inputString;
    }
}
