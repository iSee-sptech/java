package telas.jframe.isee;


import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SlackApi {

    private static String webHookUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4280815226007-4311674802775-Cw5yfa4M8djFO6e9MLMymRxW";
    private static String slackChannel = "alertas-da-maquina";
    
    public static void mandarMensagemParaSlack(String mensagem, String tipo, String valor) {
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("yyyy.MM.dd_").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH.mm.ss").format(dataHoraAtual);

        String AlertaVermelho = "------------Alerta " + "de" + tipo + "-----------------\n"
                + mensagem + "\n"
                +"Data: " + data +" as  " + hora;
        try {
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(mensagem);
            Payload payload = Payload.builder().channel(slackChannel).text(msgBuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHookUrl, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
