package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
            ChatClientConversationWriter chatClientConversationWriter = new ChatClientConversationWriter(printWriter, bufferedReader);

            printWriter.println("Привіт");
            chatClientConversationWriter.beatTheNaziIfHeIs("Що по погоді?");
            chatClientConversationWriter.beatTheNaziIfHeIs("Як справи?");
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
}