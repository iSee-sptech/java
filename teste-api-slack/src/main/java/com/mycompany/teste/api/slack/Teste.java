package com.mycompany.teste.api.slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import java.io.IOException;

public class Teste {

    private static String webHookUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4280815226007-4311674802775-IdjNv5Ck3FKQRrYxm0iNfql8";
    private static String slackChannel = "alertas-da-maquina";

    public static void main(String[] args) {
        mandarMensagemParaSlack("testee");
    }

    public static void mandarMensagemParaSlack(String mensagem){
        try {
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(mensagem);
            Payload payload = Payload.builder().channel(slackChannel).text(msgBuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHookUrl, payload);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
