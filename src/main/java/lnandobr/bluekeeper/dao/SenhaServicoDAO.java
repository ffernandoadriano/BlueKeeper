package lnandobr.bluekeeper.dao;

import lnandobr.bluekeeper.model.SenhaServico;
import lnandobr.bluekeeper.util.CryptoException;
import lnandobr.bluekeeper.util.CryptoUtils;

import java.util.Base64;
import java.util.List;

/**
 * Interface de DAO, para acesso e armazenamento das informações da aplicação
 */
public interface SenhaServicoDAO {
    /**
     * Chave simétrica para ser usada na criptografia
     * cada letra sem acentuação contém 1 byte e cada byte tem 8 bits
     * Uma chave simétrica AES pode conter 128, 192 ou 256 bits.
     */
    byte[] SECRET_KEY = "LDJGOGDLKJFSDYFK".getBytes(); // Chave mestra de 128 bits


    /**
     * Retorna todas as senhas de serviço armazenadas
     *
     * @return Todas as senhas de serviço cadastradas
     */
    List<SenhaServico> load();

    /**
     * Armazena todas as senhas de serviço passadas como parâmetro. Este método substitui
     * senhas de serviço já cadastradas pelas novas fornecidas.
     *
     * @param senhasServico Senhas de serviço a serem armazenadas
     */
    void store(List<SenhaServico> senhasServico);

    /**
     * Retorna senhas de serviço cujo nome do serviço ou site ou login contenham o texto
     * passado como parâmetro
     *
     * @param text Texto para filtrar os resultados da lista
     * @return Senhas de serviço filtradas
     */
    List<SenhaServico> filter(String text);

    /**
     * Gera um novo ID para uma senha de serviço
     *
     * @return ID gerado
     */
    int generateId();

    /**
     * Criptografa uma senha
     *
     * @param senha Senha a ser criptografada
     * @return Senha criptografada
     */
    default String encrypt(String senha) {
        try {
            // Criptografa
            byte[] encBytes = CryptoUtils.encryptAES(SECRET_KEY, senha.getBytes());

            // Converte para codificação Base64
            byte[] base64Bytes = Base64.getEncoder().encode(encBytes);

            // Retorna como String
            return new String(base64Bytes);

        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * Descriptografa um senha
     *
     * @param senha Senha a ser descriptografada
     * @return Senha descriptografada
     */
    default String decrypt(String senha) {
        try {
            // Lê os bytes
            byte[] base64Bytes = senha.getBytes();

            // Decodifica o padrão Base64
            byte[] encBytes = Base64.getDecoder().decode(base64Bytes);

            // Descriptografa
            byte[] decBytes = CryptoUtils.decryptAES(SECRET_KEY, encBytes);

            // Retorna como String
            return new String(decBytes);

        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

}
