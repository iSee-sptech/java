
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.*;
import java.util.List;

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
        Long tempoAtividade = dados.getTempoDeAtividade();
        Long TamanhoTotal = dados.getTamanhoTotal();
        Long Total = dados.getTotal();
        Long frequencia = dados.getFrequencia();

        con.update(insertMaquina, so, fabricante, arquitetura, tempoAtividade, TamanhoTotal, Total, frequencia);

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
        Long Disponivel = dados2.getDisponivel();
        Double Uso = dados2.getUso();
        // int idMaquina = selectMaquina();

        con.update(insertHistorico, Disponivel, Uso, TempoDeTransferencia, 1);
    }

    public Boolean loginUsuario() {

        String senha = login.getjTextField3();
        String email = login.getjTextField4();
        Boolean check = false;

        List login = con.queryForList("SELECT * FROM usuarios WHERE emailUsuario = ?",
        new BeanPropertyRowMapper(TelaLogin.class), email);
        
        // Exibindo o resultado
        
        if (!login.isEmpty()) {
            check = true;
        }
        else {
            check = false;
        }
        return check;       
    }
}
