package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (erro).
 */
public class ErrorToken extends Token {

    public ErrorToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    @Override
    public String getTokenIdentifier() {
        return "ERROR";
    }

}
