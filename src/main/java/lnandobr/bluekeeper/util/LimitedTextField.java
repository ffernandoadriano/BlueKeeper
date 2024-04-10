package lnandobr.bluekeeper.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;

/**
 * TextField do JavaFX que permite limitação no número de caracteres
 */
public class LimitedTextField extends TextField {

    /**
     * Propriedade que define o limite máximo de caracteres
     */
    private final IntegerProperty limit = new SimpleIntegerProperty();

    /**
     * @see javafx.scene.control.TextInputControl#replaceText(int, int, String)
     */
    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    /**
     * @see javafx.scene.control.TextInputControl#replaceSelection(String)
     */
    @Override
    public void replaceSelection(String replacement) {
        if (validate(replacement)) {
            super.replaceSelection(replacement);
        }
    }

    /**
     * Valida se o número de caracteres ainda não ultrapassou o limite
     *
     * @return boolean
     */
    private boolean validate(String typed) {
        // permite excluir o elemento anterior ao atingir o tamanho máximo
        if (lengthProperty().get() == limit.get() && typed.isEmpty()) {
            return true;
        }

        return lengthProperty().lessThan(limit).get();
    }

    public int getLimit() {
        return limit.get();
    }

    public IntegerProperty limitProperty() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit.set(limit);
    }
}
