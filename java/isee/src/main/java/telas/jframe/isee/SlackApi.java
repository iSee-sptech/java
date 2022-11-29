package telas.jframe.isee;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SlackApi {

    private static String webHookUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4280815226007-4311674802775-yqpqCbXJr9KL0DTMpOaXlvnQ";
    private static String slackChannel = "alertas-da-maquina";
    
    public static void mandarMensagemParaSlack(String mensagem, String tipo, String valor) {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("yyyy.MM.dd").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH.mm.ss").format(dataHoraAtual);

        String alertaVermelho = "------------Alerta" + " de " + tipo + "-----------------\n"
                + mensagem + " Valor: "+ valor + "\n"
                +"Data:" + data +" as  " + hora;
        try {
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(alertaVermelho);
            Payload payload = Payload.builder().channel(slackChannel).text(msgBuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHookUrl, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}