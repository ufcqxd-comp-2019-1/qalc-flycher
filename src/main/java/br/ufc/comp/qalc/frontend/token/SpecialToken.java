package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (special).
 */
public class SpecialToken extends Token {

    public SpecialToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    /**
     * Determina se o caractere na source e um separador especial
     */
    public static boolean isSpecial(char value) {
        return (value == ',' || value == ';');
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
        if(stringValue == ",")
            return "COMMA";
        else
            return "SEMI";
    }

}
