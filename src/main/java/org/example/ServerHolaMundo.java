package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHolaMundo {

    public static void main(String[] args) {
        int port = 5555;
        boolean serverRunning = true; // Variable para controlar el bucle

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Se inició el servidor con éxito en el puerto " + port);

            while (serverRunning) {
                try (Socket client = server.accept(); // Conexión entre cliente y servidor
                     BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // Lector
                     PrintStream toClient = new PrintStream(client.getOutputStream())) { // Escritor

                    System.out.println("Cliente se conectó");
                    String result = fromClient.readLine();
                    System.out.println("El cliente envió el mensaje: " + result);

                    // Verificar si el cliente quiere detener el servidor
                    if ("salir".equalsIgnoreCase(result)) {
                        serverRunning = false; // Detener el servidor
                        toClient.println("Servidor detenido.");
                        System.out.println("Servidor detenido por solicitud del cliente.");
                    } else {
                        // Invertir la palabra recibida
                        String palabraInvertida = new StringBuilder(result).reverse().toString();
                        toClient.println(palabraInvertida);
                        System.out.println("Se envió la palabra invertida: " + palabraInvertida);
                    }

                } catch (IOException ex) {
                    System.out.println("Error en la comunicación con el cliente: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al iniciar el servidor: " + ex.getMessage());
        } finally {
            System.out.println("Servidor detenido.");
        }
    }
}