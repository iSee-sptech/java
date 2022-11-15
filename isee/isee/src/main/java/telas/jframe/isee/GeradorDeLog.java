package telas.jframe.isee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeradorDeLog {

    public static void gerarLog(String tipo) throws IOException {

        LocalDateTime agora = LocalDateTime.now();

        DateTimeFormatter data = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataForm = data.format(agora);

        DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFom = hora.format(agora);

        Path path = Paths.get("Log");

        if (!Files.exists(path)) {

            Files.createDirectory(path);

        }

        File log = new File("Log/logs.txt");

        if (!log.exists()) {

            log.createNewFile();

        }

        FileWriter fw = new FileWriter(log, true);
        BufferedWriter bw = new BufferedWriter(fw);

        if (tipo.equalsIgnoreCase("loginRealizado")) {
            bw.write("Usuário efetuou login com sucesso" + dataForm + " - " + horaFom);
            bw.write("__________________________________________________________________________________________");
            bw.newLine();

            bw.close();
            fw.close();
        } else if (tipo.equalsIgnoreCase("loginErro")) {
            bw.write("Erro de login - Não foi possivel efetuar o login pois o email ou a senha está incorreta" + dataForm + " - " + horaFom);
            bw.write("__________________________________________________________________________________________");
            bw.newLine();

            bw.close();
            fw.close();
        } else if (tipo.equalsIgnoreCase("maquinaCadastrada")) {
            bw.write("Cadastro de maquina realizado com sucesso" + dataForm + " - " + horaFom);
            bw.write("__________________________________________________________________________________________");
            bw.newLine();

            bw.close();
            fw.close();
        } else if (tipo.equalsIgnoreCase("bdErro")) {
            bw.write("Erro no Banco - Banco de dados não conectado" + dataForm + " - " + horaFom);
            bw.write("__________________________________________________________________________________________");
            bw.newLine();

            bw.close();
            fw.close();
        }

    }

}
