package cadastrobd.model.util;

import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;


public class ConectorBD {

    public static ConectorBD instance;
    public DataSource dataSource;

    public ConectorBD() {
        // Configurar o DataSource
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        basicDataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=loja;ssl=require;trustServerCertificate=true");
        basicDataSource.setUsername("admin");
        basicDataSource.setPassword("felipe011");
        this.dataSource = basicDataSource;
    }

    public static synchronized ConectorBD getInstance() {
        if (instance == null) {
            instance = new ConectorBD();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
}

    public DataSource getDataSource() {
        return dataSource;
    }

    public void closeConnection(Connection connection) throws IOException, SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
