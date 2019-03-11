package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;

public class TokenReader {
    public boolean validToken(char value) {
        return (
                !Character.isDigit(value)
                        && this.isOperator(value)
                        && this.isDelimiter(value)
                        && this.isSpecial(value)
                        && !Character.isWhitespace(value)
                        && this.isComment(value)
        );
    }

    public Token createNUML(Source source) throws IOException {
        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            lexema.append(source.getCurrentChar());
            source.advance();
        } while (Character.isDigit(source.getCurrentChar()));

        if(source.getCurrentChar() == '.') {

            lexema.append(source.getCurrentChar());
            source.advance();


            if(Character.isDigit(source.getCurrentChar())) {
                do {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isDigit(source.getCurrentChar()));
            }
        }

        String stringValue = lexema.toString();

        return new NumberToken(currentLine, lexemeStart, stringValue);
    }

    public Token createFUNCID(Source source) throws IOException {
        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            lexema.append(source.getCurrentChar());
            source.advance();
        } while (Character.isDigit(source.getCurrentChar()) || Character.isLetter(source.getCurrentChar()));

        String stringValue = lexema.toString();

        return new FunctionIdentifierToken(currentLine, lexemeStart, stringValue);
    }

    public Token createVARID(Source source) throws IOException {
        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        lexema.append('$');

        do {
            lexema.append(source.getCurrentChar());
            source.advance();
        } while (Character.isLetter(source.getCurrentChar()));

        String stringValue = lexema.toString();

        return new VariableIdentifierToken(currentLine, lexemeStart, stringValue);
    }

    public Token createRESID(Source source) throws IOException {
        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        lexema.append('$');

        if (source.getCurrentChar() == '?') { // ResultIdentifierToken
            lexema.append('?');
            source.advance();

            String stringValue = lexema.toString();

            return new ResultIdentifierToken(currentLine, lexemeStart, stringValue);

        } else { // ResultIdentifierToken
            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();

            if(Long.parseLong(stringValue.substring(1)) != 0)
                return new ResultIdentifierToken(currentLine, lexemeStart, stringValue);
            return new ErrorToken(currentLine, lexemeStart, stringValue);
        }
    }

    public boolean isOperator(char value) {
        return (
                value == '='
                        || value == '+'
                        || value == '-'
                        || value == '*'
                        || value == '/'
                        || value == '%'
                        || value == '^'
        );
    }

    public Token createOP(Source source) throws IOException {

        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        lexema.append(source.getCurrentChar());

        source.advance();

        String stringValue = lexema.toString();

        if(stringValue == "=")
            return new AtribToken(currentLine, lexemeStart, stringValue);
        else if(stringValue == "+")
            return new PlusToken(currentLine, lexemeStart, stringValue);
        else if(stringValue == "-")
            return new MinusToken(currentLine, lexemeStart, stringValue);
        else if(stringValue == "*")
            return new TimesToken(currentLine, lexemeStart, stringValue);
        else if(stringValue == "/")
            return new DivToken(currentLine, lexemeStart, stringValue);
        else if(stringValue == "%")
            return new ModToken(currentLine, lexemeStart, stringValue);
        else
            return new PowToken(currentLine, lexemeStart, stringValue);
    }

    public boolean isSpecial(char value) {
        return value == ',' || value == ';';
    }

    public Token createSPECIAL(Source source) throws IOException {

        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        lexema.append(source.getCurrentChar());

        source.advance();

        String stringValue = lexema.toString();

        if(stringValue == ",")
            return new CommaToken(currentLine, lexemeStart, stringValue);
        else
            return  new SemiToken(currentLine, lexemeStart, stringValue);
    }

    public boolean isDelimiter(char value) {
        return value == '(' || value == ')';
    }

    public Token createDELIM(Source source) throws IOException {
        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        lexema.append(source.getCurrentChar());

        source.advance();

        String stringValue = lexema.toString();

        if(stringValue == "(")
            return new LParenToken(currentLine, lexemeStart, stringValue);
        else
            return  new RParenToken(currentLine, lexemeStart, stringValue);
    }

    public  Token createWHITE(Source source) throws IOException {
        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            source.advance();
        } while (Character.isWhitespace(source.getCurrentChar()));

        return new WhitespaceToken(currentLine, lexemeStart, " ");
    }

    public boolean isComment(char value) {
        return value == '#';
    }

    public Token createCOMMENT(Source source) throws IOException {
        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            lexema.append(source.getCurrentChar());
            source.advance();
        } while (source.getCurrentChar() != '\n' && source.getCurrentChar() != '\r');

        String stringValue = lexema.toString();

        return new CommentToken(currentLine, lexemeStart, stringValue);
    }

    public Token createERROR(Source source) throws IOException {
        StringBuilder lexema = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            lexema.append(source.getCurrentChar());
            source.advance();
        } while (this.validToken(source.getCurrentChar()));

        String stringValue = lexema.toString();

        return new ErrorToken(currentLine, lexemeStart, stringValue);

    }

}
