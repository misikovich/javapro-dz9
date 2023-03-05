package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;

public class Main {
    static final int PORT = 8081;
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер прогрівся");
            Socket accept = serverSocket.accept();
            System.out.println("Хтось прийшов");
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            printWriter.println("Привіт");
            beatTheNaziIfHeIs("Що по погоді?", printWriter ,bufferedReader);
            beatTheNaziIfHeIs("Як справи?", printWriter ,bufferedReader);
            bufferedReader.readLine();
            printWriter.println("Бувай!");

            printWriter.close();
            bufferedReader.close();

        }
        catch (SocketException e) {
            System.out.println("Хтось від'єднався");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void beatTheNaziIfHeIs(String serverResponseMessage, PrintWriter printWriter, BufferedReader bufferedReader) throws IOException {
        final String naziResponse = "Що таке паляниця?";
        final String antiNaziKey = "хлібина така";
        String clientMessage = bufferedReader.readLine();
        if (RussianNaziDetector.getRussianNaziDetector().analyze(clientMessage)) {
            System.out.println("Росіянина виявлено, переконуюся..");
            printWriter.println(naziResponse);
            if (bufferedReader.readLine().toLowerCase().equals(antiNaziKey)) {
                System.out.println("Хибна тривога, наш");
                printWriter.println(LocalDateTime.now());
                throw new SocketException();
            }
            else {

                printWriter.println("Ще не вмерла України і слава, і воля, Ще нам, браття молодії, усміхнеться доля. Згинуть наші воріженьки, як роса на сонці. Запануєм і ми, браття, у своїй сторонці. Душу й тіло ми положим за нашу свободу, І покажем, що ми, браття, козацького роду.");
                throw new SocketException();
            }
        }
        else {
            printWriter.println(serverResponseMessage);
        }

    }
}