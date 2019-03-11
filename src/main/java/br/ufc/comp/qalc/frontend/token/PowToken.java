package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (POW).
 */
public class PowToken extends Token {

    public PowToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    /**
     * Obtém o operador associado ao token.
     *
     * @return Operador associado.
     */
    public String getOperator() {
        interpretAttributes();

        return stringValue;
    }

    @Override
    public String getTokenIdentifier() {
        return "POW";
    }

}
