package telas.jframe.isee;


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

        //datasource.setDriverClassName("com.mysql.cj.jdbc.Driver"); MYSQL
        datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        
        datasource.setUrl("jdbc:sqlserver://svr-isee.database.windows.net:1433;database=isee");

        datasource.setUsername("grupo8@svr-isee");

        datasource.setPassword("Projetoisee123");

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
           // String url = "jdbc:mysql://localhost:3306/iSee?user=root&password=KingOfNothing000"; MYSQL
            String url = "jdbc:sqlserver://svr-isee.database.windows.net:1433;database=isee;user=grupo8@svr-isee;password=Projetoisee123";
            conn = DriverManager.getConnection(url);
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Conexao" + erro.getMessage());
        }
        return conn;
    }

}
