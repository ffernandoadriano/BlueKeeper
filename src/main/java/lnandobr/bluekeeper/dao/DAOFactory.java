package lnandobr.bluekeeper.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory para criação de objetos DAO
 */
public final class DAOFactory {

    private static final Logger LOGGER = Logger.getLogger(DAOFactory.class.getName());

    /**
     * Construtor privado para a classe não ser instanciada
     */
    private DAOFactory() {}

    /**
     * Instancia uma classe que implementa a interface SenhaServicoDAO. A classe a ser implementada
     * está definida em um arquivo de configurações
     *
     * @return Instância criada
     */
    public static SenhaServicoDAO getSenhaServicoDAO() {
        try {
            // Obtém o nome da classe a ser instanciada
            String daoClass = DAOProperties.getDAOClassName();

            // Instancia o objeto e retorna
            return (SenhaServicoDAO) Class.forName(daoClass).getDeclaredConstructor().newInstance();

        } catch (ReflectiveOperationException e) {
            // Se ocorreu algum erro, mostra o log e retorna null
            LOGGER.log(Level.SEVERE, null, e);
            return null;
        }
    }
}
