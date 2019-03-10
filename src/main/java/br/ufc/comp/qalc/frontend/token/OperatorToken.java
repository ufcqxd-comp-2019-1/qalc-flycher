package br.ufc.comp.qalc.frontend.token;


/**
 * Classe que representa um token do tipo (operator).
 */
public class OperatorToken extends Token {

    public OperatorToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    /**
     * Determina se o caractere na source e um operador
     */
    public static boolean isOperator(char value) {
        return (value == '=' || value == '+' || value == '-' || value == '*' || value == '/' || value == '%' || value == '^');
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
        if(stringValue == "=")
            return "ATRIB";
        else if(stringValue == "+")
            return "PLUS";
        else if(stringValue == "-")
            return "MINUS";
        else if(stringValue == "*")
            return "TIMES";
        else if(stringValue == "/")
            return "DIV";
        else if(stringValue == "%")
            return "MOD";
        else
            return "POW";
    }

}
