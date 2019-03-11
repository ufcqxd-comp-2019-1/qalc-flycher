package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (COMMA).
 */
public class CommaToken extends Token {

    public CommaToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }


    /**
     * Obtém o separador associado ao token.
     *
     * @return Separador associado.
     */
    public String getOperator() {
        interpretAttributes();

        return stringValue;
    }

    @Override
    public String getTokenIdentifier() {
        return "COMMA";
    }

}
