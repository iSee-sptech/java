
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

    public void registrarMaquina(funcao dados) {

        String insertMaquina = "INSERT INTO Maquinas (idMaquina, sistemaOperacionalMaquina, fabricanteMaquina, arquiteturaMaquina, tempoDeAtividade, discoMaquina, ramMaquina, processadorMaquina) VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

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
        disco = disco.substring(0, 3);

        Long Total = dados.getTotal();
        String ramString = Long.toString(Total);
        ramString = ramString.substring(0, 2);
        int ram = Integer.parseInt(ramString);
        ram--;

        Long frequencia = dados.getFrequencia();
        String processadorString = Long.toString(frequencia);
        processadorString = processadorString.substring(0, 3);
        Double processador = Double.parseDouble(processadorString);
        processador++;
        processador = processador / 100;
        
        con.update(insertMaquina, so, fabricante, arquitetura, tempoAtividade, disco, ram, processador);

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

        String insertHistorico = "INSERT INTO Historico (idHistorico, usoRamHistorico, usoProcessadorHistorico, usoDiscoHistorico, fkMaquinaHistorico) VALUES (null, ?, ?, ?, ?)";

        // Parametros para o primeiro insert
        Long TempoDeTransferencia = dados2.getTempoDeTransferencia();
        String disco = Long.toString(TempoDeTransferencia);
        disco = disco.substring(0, 3);

        Long Disponivel = dados2.getDisponivel();
        String ramString = Long.toString(Disponivel);
        ramString = ramString.substring(0, 2);
        int ram = Integer.parseInt(ramString);
        ram--;

        Double Uso = dados2.getUso();
        String processadorString = Double.toString(Uso);
        processadorString = processadorString.substring(0, 3);
        Double processador = Double.parseDouble(processadorString);
        processador++;
        processador = processador / 100;

        String idMaquinaString = identificadorMaquina();
        int idMaquina = Integer.parseInt(idMaquinaString);
        // int idMaquina = selectMaquina();

        con.update(insertHistorico, ram, processador, disco, idMaquina);
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
        processadorTotal = processadorTotal / 100;
        

        MetricasAlerta metricas = new MetricasAlerta(
                ramTotal * 0.7, 
                ramTotal * 0.8, 
                processadorTotal * 0.6, 
                processadorTotal * 0.8, 
                discoTotalInteger * 0.7, 
                discoTotalInteger * 0.9);
        String insertAlerta = "INSERT INTO Alerta (fkMaquina,componente,nivelAlerta,dado,datahoraAlerta) VALUES (?, ?, ?, ?)";
        
        // DISCO
        Long TempoDeTransferencia = dados2.getTempoDeTransferencia();
        String disco = Long.toString(TempoDeTransferencia);
        disco = disco.substring(0, 3);
        Integer discoInteger = Integer.parseInt(disco);
        
        if (discoInteger >= metricas.getDiscoVermelho())
            con.update(insertAlerta, idMaquina, "vermelho", disco.toString(), metricas.getDateTime());
        else if (discoInteger >= metricas.getDiscoAmarelo())
            con.update(insertAlerta, idMaquina, "amarelo", disco.toString(), metricas.getDateTime());
        
        // RAM
        Long Disponivel = dados2.getDisponivel();
        String ramEmString = Long.toString(Disponivel);
        ramEmString = ramEmString.substring(0, 2);
        Integer ram = Integer.parseInt(ramEmString);
        ram--;
        
        if (ram >= metricas.getRamVermelho())
            con.update(insertAlerta, idMaquina, "vermelho", ram.toString(), metricas.getDateTime());
        else if (ram >= metricas.getRamAmarelo())
            con.update(insertAlerta, idMaquina, "amarelo", ram.toString(), metricas.getDateTime());
        
        // CPU
        Double Uso = dados2.getUso();
        String processadorEmString = Double.toString(Uso);
        processadorEmString = processadorEmString.substring(0, 3);
        Double processador = Double.parseDouble(processadorEmString);
        processador++;
        processador = processador / 100;
        
        if (processador >= metricas.getCpuVermelho())
            con.update(insertAlerta, idMaquina, "vermelho", processador.toString(), metricas.getDateTime());
        else if (processador >= metricas.getDiscoAmarelo())
            con.update(insertAlerta, idMaquina, "amarelo", processador.toString(), metricas.getDateTime());


        
    }
    
    
    
    
    

    Connection conn;

    public ResultSet loginUsuario(LoginDTO objLoginDTO) {
        conn = new Conexao().conectaBD();

        try {
            String selectLogin = "SELECT * FROM usuarios where emailUsuario = ? AND senhaUsuario = ?";
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
            String selectIdentificadorMaquina = "select idMaquina from Maquinas order by idMaquina desc limit 1";
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
