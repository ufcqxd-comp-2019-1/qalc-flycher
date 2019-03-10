package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (whitespace).
 */
public class WhitespaceToken extends Token {

    public WhitespaceToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    /**
     * Obtém o espaco em branco.
     *
     * @return Espaco em branco.
     */
    public String getSpace() {
        interpretAttributes();

        return stringValue;
    }

    @Override
    public String getTokenIdentifier() { return "WHITE"; }

}
