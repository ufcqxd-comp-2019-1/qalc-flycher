package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (delimiter).
 */
public class DelimiterToken extends Token {

    public DelimiterToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    /**
     * Determina se o caractere na source e um delimitador
     */
    public static boolean isDelimiter(char value) {
        return (value == '(' || value == ')');
    }

    /**
     * Obtém o delimitador associado ao token.
     *
     * @return Delimitador associado.
     */
    public String getOperator() {
        interpretAttributes();

        return stringValue;
    }

    @Override
    public String getTokenIdentifier() {
        if(stringValue == "(")
            return "LPAREN";
        else
            return "RPAREN";
    }

}
