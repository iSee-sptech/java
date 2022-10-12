package telas.swing;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private JdbcTemplate connection; 
    
    // Exemplo de configuração utilizando H2
    // Obs. O código comentado é um exemplo de como se conectar ao mysql
    public Conexao() {
        BasicDataSource datasource = new BasicDataSource();

        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        datasource.setUrl("jdbc:mysql://localhost:3306/isee");
               
        datasource.setUsername("root");

        datasource.setPassword("KingOfNothing000");
        
        //this.datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        //this.datasource.setUrl("jdbc:mysql://localhost:3360/meu_banco");
        
        //this.datasource.setUsername("root");
        
        //this.datasource.setPassword("teste");
        
        connection = new JdbcTemplate(datasource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }
    
}
