package org.example;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.time.LocalDateTime;

@AllArgsConstructor
public class ChatClientConversationWriter {
    private PrintWriter clientOutput;
    private BufferedReader clientInput;

    public void beatTheNaziIfHeIs(String serverResponseMessage) throws IOException {
        final String naziResponse = "Що таке паляниця?";
        final String antiNaziKey = "хлібина така";
        String clientMessage = clientInput.readLine();
        if (RussianNaziDetector.getRussianNaziDetector().analyze(clientMessage)) {
            System.out.println("Росіянина виявлено, переконуюся..");
            clientOutput.println(naziResponse);
            if (clientInput.readLine().equalsIgnoreCase(antiNaziKey)) {
                System.out.println("Хибна тривога, наш");
                clientOutput.println(LocalDateTime.now());
            }
            else {
                clientOutput.println("Ще не вмерла України і слава, і воля, Ще нам, браття молодії, усміхнеться доля. Згинуть наші воріженьки, як роса на сонці. Запануєм і ми, браття, у своїй сторонці. Душу й тіло ми положим за нашу свободу, І покажем, що ми, браття, козацького роду.");
            }
            throw new SocketException();
        }
        else {
            clientOutput.println(serverResponseMessage);
        }

    }
}
