package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClienteHolaMundo {
    public static void main(String[] args) {
        int port = 5555;
        try (Socket client = new Socket("localhost", port);
             PrintStream toServer = new PrintStream(client.getOutputStream());
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Introduce una palabra: ");
            String word = userInput.readLine();

            // Enviar la palabra al servidor
            toServer.println(word);

            // Recibir la palabra invertida del servidor
            String word2 = fromServer.readLine();
            System.out.println("La palabra invertida es: " + word2);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}