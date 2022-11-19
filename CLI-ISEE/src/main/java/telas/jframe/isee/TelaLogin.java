package telas.jframe.isee;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TelaLogin {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean i = true;

        do {
            System.out.println("\nSeja bem-vindo a ISee");
            System.out.println("\nLogin");
            System.out.println("E-mail:");
            String email = scanner.next();
            System.out.println("Senha:");
            String senha = scanner.next();

            try {
                LoginDTO objLoginDTO = new LoginDTO();
                objLoginDTO.setEmailUsuario(email);
                objLoginDTO.setSenhaUsuario(senha);

                MonitoramentoFuncao objMonitoramentoFuncao = new MonitoramentoFuncao();
                ResultSet rsUsuarioDAO = objMonitoramentoFuncao.loginUsuario(objLoginDTO);

                if (rsUsuarioDAO.next()) {

                    System.out.println("Senha válida\n");

                    funcao dados = new funcao();

                    MonitoramentoFuncao func = new MonitoramentoFuncao();
                    func.registrarMaquina(dados);
                    System.out.println("Maquina cadastrada com sucesso!\ntermine o cadastro na Web");
                    System.out.println("Identificador Maquina: " + objMonitoramentoFuncao.identificadorMaquina());

                    System.out.println("\nLigar Histórico: Digite 'Ligar'");
                    System.out.println("Desligar Histórico: Digite 'Desligar'");
                    String historico = scanner.next();

                    Timer timer;
                    do {
                        if (historico.equalsIgnoreCase("Ligar")) {
                            System.out.println("Hitórico Ligado");
                            funcao dados2 = new funcao();
                            timer = new Timer();
                            timer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                    try {
                                        System.out.println("Dado envidado para registro");
                                        func.registrarHistorico(dados2);
                                        func.registrarAlertas(dados2);
                                    } catch (Exception ex) {
                                        System.out.println("Ocorreu uma falhar ao tentar cadastra os dados:");
                                    }
                                    if (historico.equalsIgnoreCase("Desligar")) {
                                        System.out.println("Hitórico Desligado");
                                    }
                                }
                            }, 0, 1000);

                        }
                    } while (historico.equalsIgnoreCase("Ligar"));
                } else {
                    System.out.println("Usuario ou Senha inválido");
                }

            } catch (SQLException erro) {
                System.out.println("Tela Login" + erro);
            }
        } while (i == true);
    }
}
