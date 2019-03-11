package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (LPAREN).
 */
public class LParenToken extends Token {

    public LParenToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
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
        return "LPAREN";
    }

}
