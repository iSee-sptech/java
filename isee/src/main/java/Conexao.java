
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private JdbcTemplate connection;

    // Exemplo de configuração utilizando H2
    // Obs. O código comentado é um exemplo de como se conectar ao mysql
    public Conexao() {
        BasicDataSource datasource = new BasicDataSource();

        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        datasource.setUrl("jdbc:mysql://localhost:3306/iSee");

        datasource.setUsername("aluno");

        datasource.setPassword("sptech");

        //this.datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //this.datasource.setUrl("jdbc:mysql://localhost:3360/meu_banco");
        //this.datasource.setUsername("root");
        //this.datasource.setPassword("teste");
        connection = new JdbcTemplate(datasource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }

    public Connection conectaBD() {
        Connection conn = null;
        
        try {
            String url = "jdbc:mysql://localhost:3306/iSee?user=aluno&password=sptech";
            conn = DriverManager.getConnection(url);
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Conexao" + erro.getMessage());
        }
        return conn;
    }

}
