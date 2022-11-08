import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

public class SlackApi {

    private static String webHookUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4280815226007-4311674802775-cX1OuR79NsBazTlIVWZrBevf";
    private static String slackChannel = "alertas-da-maquina";

    public static void main(String[] args) {
        mandarMensagemParaSlack("");
    }

    public static void mandarMensagemParaSlack(String mensagem) {
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