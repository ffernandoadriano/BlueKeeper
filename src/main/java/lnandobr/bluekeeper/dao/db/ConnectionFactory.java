package lnandobr.bluekeeper.dao.db;

import lnandobr.bluekeeper.dao.DAOProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Obtém novas conexões para o banco de dados MySQL
 */
public final class ConnectionFactory {
    /**
     * Construtor privado para a classe não ser instanciad
     */
    private ConnectionFactory() {
    }

    /**
     * Abre uma nova conexão com o banco de dados
     *
     * @return Nova conexão
     */
    public static Connection openConnection() throws SQLException {
        // Cria a URL de conexão com base nas configurações do arquivo
        String url = String.format("jdbc:mysql://%s:%d/%s", DAOProperties.getDBDAOServerName(), DAOProperties.getDBDAOPort(), DAOProperties.getDBDAODBName());

        // Cria uma nova conexão e retorna
        return DriverManager.getConnection(url, DAOProperties.getDBDAOUserName(), DAOProperties.getDNDAOPassword());
    }


}
