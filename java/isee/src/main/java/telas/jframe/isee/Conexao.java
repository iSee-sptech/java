package telas.jframe.isee;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private JdbcTemplate connection;
    private JdbcTemplate connectionMySql;

    // Exemplo de configuração utilizando H2
    // Obs. O código comentado é um exemplo de como se conectar ao mysql
    public Conexao() {
        BasicDataSource datasource = new BasicDataSource();
        BasicDataSource datasourceMySql = new BasicDataSource();

        datasourceMySql.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        
        datasource.setUrl("jdbc:sqlserver://svr-isee.database.windows.net:1433;database=isee");

        datasource.setUsername("grupo8@svr-isee");

        datasource.setPassword("Projetoisee123");

        datasourceMySql.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //datasourceMySql.setUrl("jdbc:mysql://localhost:3360/image_v3");
        datasourceMySql.setUrl("jdbc:mysql://localhost:3360/isee");
        //datasourceMySql.setUsername("root");
        datasourceMySql.setUsername("localhost");
        datasourceMySql.setPassword("root");
        connection = new JdbcTemplate(datasource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }
    
    public JdbcTemplate getConnectionMySql() {
        return connectionMySql;
    }

    public Connection conectaBD() {
        Connection conn = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://svr-isee.database.windows.net:1433;database=isee;user=grupo8@svr-isee;password=Projetoisee123";
            conn = DriverManager.getConnection(url);
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Conexao Login" + erro.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public Connection conectaBDMySql() {
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/iSee?user=localhost&password=root";
            conn = DriverManager.getConnection(url);
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Conexao Login MySql" + erro.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
