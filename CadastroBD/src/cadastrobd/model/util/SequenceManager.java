package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    
    private ConectorBD conector;
    
    public SequenceManager(ConectorBD conector) {
        this.conector = conector;
    }
    
    public int getValue(String sequenceName) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int nextValue = -1;
        
        try {
            conn = conector.getDataSource().getConnection();
            String sql = "SELECT NEXT VALUE FOR " + sequenceName;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nextValue = resultSet.getInt(1);
            }
        } finally {
            // Aqui, vamos fechar cada recurso individualmente, na ordem inversa à sua abertura
            // Isso garante que todos os recursos serão fechados, mesmo se ocorrer uma exceção em um dos closes anteriores
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return nextValue;
    }
}
