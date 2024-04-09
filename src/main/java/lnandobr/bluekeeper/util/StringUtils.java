package lnandobr.bluekeeper.util;

/**
 * Classe de utilitário de string
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Verifica se uma String está vazia ou é composta só por espaços em branco
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }

        return str.trim().isEmpty();
    }

    /**
     * Retorna o separador de linha do S.O.
     */
    public static String newLine() {
        return System.lineSeparator();
    }

}
