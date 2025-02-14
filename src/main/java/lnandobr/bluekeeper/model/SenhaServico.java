package lnandobr.bluekeeper.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Representa os dados de um serviço ou site
 */
public class SenhaServico {
    /**
     * ID único
     */
    private IntegerProperty id = new SimpleIntegerProperty();
    /**
     * Nome do serviço ou site
     */
    private StringProperty servico = new SimpleStringProperty();
    /**
     * Login do serviço ou site
     */
    private StringProperty login = new SimpleStringProperty();
    /**
     * Senha do serviço ou site
     */
    private StringProperty senha = new SimpleStringProperty();
    /**
     * Observações complementares
     */
    private StringProperty observacoes = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getServico() {
        return servico.get();
    }

    public StringProperty servicoProperty() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico.set(servico);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getSenha() {
        return senha.get();
    }

    public StringProperty senhaProperty() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public String getObservacoes() {
        return observacoes.get();
    }

    public StringProperty observacoesProperty() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes.set(observacoes);
    }

    /**
     * @see java.lang.Object#equals(Object) 
     */

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SenhaServico that = (SenhaServico) object;
        return Objects.equals(id, that.id);
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", SenhaServico.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("servico=" + servico)
                .add("login=" + login)
                .add("senha=" + senha)
                .add("observacoes=" + observacoes)
                .toString();
    }
}
