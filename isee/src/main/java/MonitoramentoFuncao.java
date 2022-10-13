
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
