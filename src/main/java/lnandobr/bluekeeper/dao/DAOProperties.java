package lnandobr.bluekeeper.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

/**
 * Lê os dados do arquivo de configuração dao.properties
 */
public final class DAOProperties {

    /**
     * Armazena as propriedades do arquivo
     */
    private static final Properties props = new Properties();

    static {
        try {
            // Obtém o caminho do arquivo de configuração
            Path path = Path.of(Objects.requireNonNull(DAOProperties.class.getResource("/dao.properties")).toURI());

            // Carrega os dados para o objeto Properties
            try (InputStream is = Files.newInputStream(path)) {
                props.load(is);
            }

        } catch (URISyntaxException | IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Construtor privado para a classe não ser instanciada
     */
    private DAOProperties() {
    }

    /**
     * @return nome da classe DAO que será usada pela aplicação
     */
    public static String getDAOClassName() {
        return props.getProperty("dao.class");
    }

    /**
     * @return nome do servidor onde está o banco dados
     */
    public static String getDBDAOServerName() {
        return props.getProperty("dao.db.serverName");
    }

    /**
     * @return a porta para acessar o banco de dados
     */
    public static int getDBDAOPort() {
        return Integer.parseInt(props.getProperty("dao.db.port"));
    }

    /**
     * @return o nome do banco de dados
     */
    public static String getDBDAODBName() {
        return props.getProperty("dao.db.dbName");
    }

    /**
     * @return o nome do usuário para acessar o banco de dados
     */
    public static String getDBDAOUserName() {
        return props.getProperty("dao.db.userName");
    }

    /**
     * @return a senha para acessar o banco de dados
     */
    public static String getDNDAOPassword() {
        return props.getProperty("dao.db.password");
    }

}
