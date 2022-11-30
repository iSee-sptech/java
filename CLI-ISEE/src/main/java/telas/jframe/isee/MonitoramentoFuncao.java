    package telas.jframe.isee;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.*;
import java.util.List;
import javax.swing.JOptionPane;

public class MonitoramentoFuncao {

    Conexao connection = new Conexao();
    TelaLogin login = new TelaLogin();
    JdbcTemplate con = connection.getConnection();
    //JdbcTemplate conMySql = connection.getConnectionMySql();

    public void registrarMaquina(funcao dados) {

         try {
        
        //String insertMaquina = "INSERT INTO Maquinas (sistemaOperacionalMaquina, fabricanteMaquina, arquiteturaMaquina, tempoDeAtividade, discoMaquina, ramMaquina, processadorMaquina, fkUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        // Parametros para o primeiro insert
        String so = dados.getOP();
        Integer arquitetura = dados.getArquitetura();
        String fabricante = dados.getFabricante();
        Long tempoAtividadeLong = dados.getTempoDeAtividade();
        String tempoAtividadeString = Long.toString(tempoAtividadeLong);
        Integer tempoAtividade = Integer.parseInt(tempoAtividadeString);
        tempoAtividade = tempoAtividade / 3600;
        tempoAtividade = Math.round(tempoAtividade);

        Long TamanhoTotal = dados.getTamanhoTotal();
        String disco = Long.toString(TamanhoTotal);
        if (disco.length() > 12) {
            disco = disco.substring(0, 4);
        } else if(disco.length() > 11) {
             disco = disco.substring(0, 3);
        } else {  
             disco = disco.substring(0, 2);
        }

        Long Total = dados.getTotal();
        String ramString = Long.toString(Total);
        if (ramString.length() > 10) {
            ramString = ramString.substring(0, 2);
        } else {
            ramString = ramString.substring(0, 1);
        }
        Integer ram = Integer.parseInt(ramString);
        if (ram > 10) {
            ram--;
        }

        Long frequencia = dados.getFrequencia();
        String processadorString = Long.toString(frequencia);
        processadorString = processadorString.substring(0, 3);
        Double processadorDouble = Double.parseDouble(processadorString);
        processadorDouble++;
        processadorDouble = processadorDouble / 100;
        String processador = Double.toString(processadorDouble);
        processador = processador.replace(',', '.');
        //String processadorString2 = Double.toString(processadorDouble);
        //float processador = Float.parseFloat(processadorString2);

        String insertMaquina = String.format("INSERT INTO Maquinas (sistemaOperacionalMaquina, fabricanteMaquina, arquiteturaMaquina, tempoDeAtividade, discoMaquina, ramMaquina, processadorMaquina) VALUES ('%s', '%s', '%d', '%d', '%s', '%d', '%s')", so, fabricante, arquitetura, tempoAtividade, disco, ram, processador);
        con.execute(insertMaquina);
        String insertMaquinaMySql = String.format("INSERT INTO Maquinas (sistemaOperacionalMaquina, fabricanteMaquina, arquiteturaMaquina, tempoDeAtividade, discoMaquina, ramMaquina, processadorMaquina) VALUES ('%s', '%s', '%d', '%d', '%s', '%d', '%s')", so, fabricante, arquitetura, tempoAtividade, disco, ram, processador);
        //conMySql.execute(insertMaquinaMySql);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Registrar Maquina: " + erro);
        }
    }

    //public int selectMaquina() {
    // List selectMaquinas = con.queryForList("select idMaquina from Maquinas order by idMaquina desc limit 1");
    // int idMaquina = 1;
    //for (int i = 0; i < selectMaquinas.size(); i++) {
    // idMaquina = (int) selectMaquinas.get(i);
    //   idMaquina = Integer.parseInt(selectMaquinas.get(i).toString());
    //  }
    // return idMaquina;
    //}
    public void registrarHistorico(funcao dados2) {

        String insertHistorico = "INSERT INTO Historico ( usoRamHistorico, usoProcessadorHistorico, usoDiscoHistorico, fkMaquinaHistorico) VALUES ( ?, ?, ?, ?)";
        //String insertHistoricoMySql = "INSERT INTO Historico ( usoRamHistorico, usoProcessadorHistorico, usoDiscoHistorico) VALUES ( '?', '?', '?')";
        
        // Parametros para o primeiro insert
        Long TempoDeTransferencia = dados2.getTempoDeTransferencia();
        String disco = Long.toString(TempoDeTransferencia);
        disco = disco.substring(0, 3);

        Long Disponivel = dados2.getEmUso();
        String ramString = Long.toString(Disponivel);
        ramString = ramString.substring(0, 1);
        int ram = Integer.parseInt(ramString);

        Double Uso = dados2.getUso();
        String processadorString = Double.toString(Uso);
        //processadorString = processadorString.substring(0, 3);
        Double processador = Double.parseDouble(processadorString);
        //processador++;
        //processador = processador / 100;

        String idMaquinaString = identificadorMaquina();
        int idMaquina = Integer.parseInt(idMaquinaString);
        // int idMaquina = selectMaquina();

        con.update(insertHistorico, ram, processador, disco, idMaquina);
        //conMySql.update(insertHistoricoMySql, ram, processador, disco);

    }

    public void registrarAlertas(funcao dados2) {

        // PEGANDO DADOS ESTÁTICOS
        String idMaquinaString = identificadorMaquina();
        int idMaquina = Integer.parseInt(idMaquinaString);

        Long TamanhoTotal = dados2.getTamanhoTotal();
        String discoTotal = Long.toString(TamanhoTotal);
        discoTotal = discoTotal.substring(0, 3);
        Integer discoTotalInteger = Integer.parseInt(discoTotal);

        Long Total = dados2.getTotal();
        String ramString = Long.toString(Total);
        ramString = ramString.substring(0, 2);
        Integer ramTotal = Integer.parseInt(ramString);
        ramTotal--;

        Long frequencia = dados2.getFrequencia();
        String processadorString = Long.toString(frequencia);
        processadorString = processadorString.substring(0, 3);
        Double processadorTotal = Double.parseDouble(processadorString);
        processadorTotal++;
        processadorTotal = processadorTotal / 100 / 100;

        MetricaAlerta metricas = new MetricaAlerta(
                ramTotal * 0.7,
                ramTotal * 0.8,
                processadorTotal * 60,
                processadorTotal * 80,
                discoTotalInteger * 0.7,
                discoTotalInteger * 0.9);

        String insertAlerta = "INSERT INTO Alerta ( fkMaquina,nivelAlerta,componente,dado,datahoraAlerta) VALUES ( ?, ?, ?, ?, ?)";
        //String insertAlertaMySql = "INSERT INTO Alerta (nivelAlerta,componente,dado,datahoraAlerta) VALUES (?, ?, ?, ?)";
        
// DISCO
        Long TempoDeTransferencia = dados2.getTempoDeTransferencia();
        String discoString = Long.toString(TempoDeTransferencia);
        discoString = discoString.substring(0, 3);
        Integer disco = Integer.parseInt(discoString);

        if (disco >= metricas.getDiscoVermelho()) {
            con.update(insertAlerta, idMaquina, "vermelho", "disco", disco.toString(), metricas.getDateTime());
            //conMySql.update(insertAlerta, idMaquina, "vermelho", "disco", disco.toString(), metricas.getDateTime());
            SlackApi.mandarMensagemParaSlack("Uso de Disco acima de 90%", "Uso de Disco", discoString);
        } else if (disco >= metricas.getDiscoAmarelo()) {
            con.update(insertAlerta, idMaquina, "amarelo", "disco", disco.toString(), metricas.getDateTime());
            //conMySql.update(insertAlerta, "amarelo", "disco", disco.toString(), metricas.getDateTime());
        }

        // RAM
        Long Disponivel = dados2.getEmUso();
        String ramString2 = Long.toString(Disponivel);
        ramString2 = ramString2.substring(0, 1);
        Integer ram = Integer.parseInt(ramString2);

        if (ram >= metricas.getRamVermelho()) {
            con.update(insertAlerta, idMaquina, "vermelho", "ram", ram.toString(), metricas.getDateTime());
           // conMySql.update(insertAlerta, idMaquina, "vermelho", "ram", ram.toString(), metricas.getDateTime());
            SlackApi.mandarMensagemParaSlack("Uso de RAM acima de 80%", "Uso de RAM", ramString2);
        } else if (ram >= metricas.getRamAmarelo()) {
            con.update(insertAlerta, idMaquina, "amarelo", "ram", ram.toString(), metricas.getDateTime());
           // conMySql.update(insertAlerta, "amarelo", "ram", ram.toString(), metricas.getDateTime());
        }

        // CPU
        Double Uso = dados2.getUso();
        String processadorString2 = Double.toString(Uso);
        processadorString2 = processadorString2.substring(0, 4);
        Double processador = Double.parseDouble(processadorString2);
        //processador++;
        //processador = processador / 100;

        if (processador >= metricas.getCpuVermelho()) {
            con.update(insertAlerta, idMaquina, "vermelho", "cpu", processador.toString(), metricas.getDateTime());
           // conMySql.update(insertAlerta, idMaquina, "vermelho", "cpu", processador.toString(), metricas.getDateTime());
            SlackApi.mandarMensagemParaSlack("Uso de CPU acima de 80%", "Uso de RAM", processadorString2);
        } else if (processador >= metricas.getCpuAmarelo()) {
            con.update(insertAlerta, idMaquina, "amarelo", "cpu", processador.toString(), metricas.getDateTime());
           // conMySql.update(insertAlerta, "vermelho", "cpu", processador.toString(), metricas.getDateTime());
        }

    }


    Connection conn;

    public ResultSet loginUsuario(LoginDTO objLoginDTO) {
        conn = new Conexao().conectaBD();

        try {
            String selectLogin = "SELECT * FROM Usuarios where emailUsuario = ? AND senhaUsuario = ?";
            PreparedStatement pstm = conn.prepareStatement(selectLogin);
            pstm.setString(1, objLoginDTO.getEmailUsuario());
            pstm.setString(2, objLoginDTO.getSenhaUsuario());

            ResultSet rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioBD: " + erro);
            return null;
        }
    }

    public String identificadorMaquina() {
        conn = new Conexao().conectaBD();

        try {
            String selectIdentificadorMaquina = "select top 1 idMaquina from Maquinas order by idMaquina desc ";
            PreparedStatement pstm = conn.prepareStatement(selectIdentificadorMaquina);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            String id = rs.getString(1);
            return id;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "IdentificadorBD: " + erro);
            return null;
        }
    }
}
