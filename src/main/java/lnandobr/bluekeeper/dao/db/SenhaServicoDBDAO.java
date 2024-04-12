package lnandobr.bluekeeper.dao.db;

import lnandobr.bluekeeper.dao.DAOException;
import lnandobr.bluekeeper.dao.SenhaServicoDAO;
import lnandobr.bluekeeper.model.SenhaServico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de DAO voltada para banco de dados MySQL
 */
public class SenhaServicoDBDAO implements SenhaServicoDAO {

    /**
     * @see SenhaServicoDAO#load()
     */
    @Override
    public List<SenhaServico> load() {
        // Delega para o método filter()
        return filter(null);
    }

    /**
     * @see lnandobr.bluekeeper.dao.SenhaServicoDAO#store(List)
     */
    @Override
    public void store(List<SenhaServico> senhasServico) {
        // Abre a conexão
        try (Connection conn = ConnectionFactory.openConnection()) {
            // Inicia uma transação
            conn.setAutoCommit(false);

            // Exclui todas as senhas de serviços
            String deleteSql = "DELETE FROM SENHA_SERVICO";
            try (PreparedStatement stmt = conn.prepareStatement(deleteSql)) {
                stmt.executeUpdate();
            }

            // Insere as senhas de serviço com base na lista passada como parâmetro
            String insertSql = "INSERT INTO SENHA_SERVICO (ID, SERVICO, LOGIN, SENHA, OBSERVACOESs) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                for (SenhaServico senhaServico : senhasServico) {
                    stmt.setInt(1, senhaServico.getId());
                    stmt.setString(2, senhaServico.getServico());
                    stmt.setString(3, senhaServico.getLogin());
                    stmt.setString(4, encrypt(senhaServico.getSenha()));
                    stmt.setString(5, senhaServico.getObservacoes());
                    stmt.executeUpdate();
                }
            }

            // Efetiva a transação
            conn.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * @see lnandobr.bluekeeper.dao.SenhaServicoDAO#filter(String)
     */
    @Override
    public List<SenhaServico> filter(String text) {
        // Abre a conexão
        try (Connection conn = ConnectionFactory.openConnection()) {
            String sql = "SELECT ID, LOGIN, SENHA, SERVICO, OBSERVACOES FROM SENHA_SERVICO";

            // Ser foi fornecido um texto para filtragem, adiciona condição na query
            if (text != null) {
                sql += " WHERE UPPER(LOGIN) LIKE CONCAT('%',?,'%') OR UPPER(SERVICO) LIKE CONCAT('%',?,'%')";
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Se for fornecido um texto para filtragem, define os parâmetros
                if (text != null) {
                    String param = text.toUpperCase();
                    stmt.setString(1, param);
                    stmt.setString(2, param);
                }

                // Executa a busca
                try (ResultSet rs = stmt.executeQuery()) {
                    List<SenhaServico> senhasServico = new ArrayList<>();

                    // Para cada registro encontrado na tabela, cria um objeto SenhaServico e coloca na lista
                    while (rs.next()) {
                        SenhaServico senhaServico = new SenhaServico();
                        senhaServico.setId(rs.getInt("ID"));
                        senhaServico.setLogin(rs.getString("LOGIN"));
                        senhaServico.setSenha(decrypt(rs.getString("SENHA")));
                        senhaServico.setServico(rs.getString("SERVICO"));
                        senhaServico.setObservacoes(rs.getString("OBSERVACOES"));

                        // adiciona na lista
                        senhasServico.add(senhaServico);
                    }

                    // Retorna a lista
                    return senhasServico;
                }
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * @see SenhaServicoDAO#generateId()
     */
    @Override
    public int generateId() {
        // Abre a conexão
        try (Connection conn = ConnectionFactory.openConnection()) {
            String sql = "SELECT MAX(ID) AS ID FROM SENHA_SERVICO";

            // Busca pelo maior ID cadastrado na tabela
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Soma 1 ao maior ID e retorna
                        return rs.getInt("ID") + 1;
                    }

                    // Se nenhum registro está cadastrado, começa com o ID 1
                    return 1;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
