package ui;

import java.io.FileNotFoundException;

/**
 * Main class
 * Launches Game UI
 */
// credit: GitHub "JsonSerializationDemo"
public class Main {
    public static void main(String[] args) {
        try {
            new Game();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}