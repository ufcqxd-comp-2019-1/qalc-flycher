package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (RPAREN).
 */
public class RParenToken extends Token {

    public RParenToken(long line, long start, String value) throws IllegalArgumentException {
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
    public String getTokenIdentifier() { return "RPAREN"; }

}
