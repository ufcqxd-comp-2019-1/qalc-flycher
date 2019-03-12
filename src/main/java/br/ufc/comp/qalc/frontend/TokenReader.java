package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;

public class TokenReader {
    public boolean validToken(char value) {
        return (
                Character.isDigit(value)
                        || Character.isWhitespace(value)
                        || this.isOperator(value)
                        || this.isDelimiter(value)
                        || this.isSpecial(value)
                        || this.isComment(value)
                        || value == '$'
                        || value == '@'
        );
    }

    public Token createNUML(Source source) throws IOException {
        StringBuilder lexeme = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            lexeme.append(source.getCurrentChar());
            source.advance();
        } while (Character.isDigit(source.getCurrentChar()));

        if (source.getCurrentChar() == '.') {

            lexeme.append(source.getCurrentChar());
            source.advance();

            if (Character.isDigit(source.getCurrentChar())) {
                do {
                    lexeme.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isDigit(source.getCurrentChar()));
            }
        }

        String stringValue = lexeme.toString();

        return new NumberToken(currentLine, lexemeStart, stringValue);
    }

    public Token createFUNCID(Source source) throws IOException {
        StringBuilder lexeme = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            lexeme.append(source.getCurrentChar());
            source.advance();
        } while (Character.isDigit(source.getCurrentChar()) || Character.isLetter(source.getCurrentChar()));

        String stringValue = lexeme.toString();

        return new FunctionIdentifierToken(currentLine, lexemeStart, stringValue);
    }

    public Token createVARID(Source source) throws IOException {
        StringBuilder lexeme = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        lexeme.append('$');

        do {
            lexeme.append(source.getCurrentChar());
            source.advance();
        } while (Character.isLetter(source.getCurrentChar()));

        String stringValue = lexeme.toString();

        return new VariableIdentifierToken(currentLine, lexemeStart, stringValue);
    }

    public Token createRESID(Source source) throws IOException {
        StringBuilder lexeme = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        lexeme.append('$');

        if (source.getCurrentChar() == '?') { // ResultIdentifierToken
            lexeme.append('?');
            source.advance();

            String stringValue = lexeme.toString();

            return new ResultIdentifierToken(currentLine, lexemeStart, stringValue);

        } else { // ResultIdentifierToken
            do {
                lexeme.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexeme.toString();

            if (Long.parseLong(stringValue.substring(1)) != 0)
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

    private class SingleLexeme {
        private StringBuilder lexeme;
        private String stringValue;
        private long currentLine;
        private long lexemeStart;

        public SingleLexeme(Source source) throws IOException {
            lexeme = new StringBuilder();

            currentLine = source.getCurrentLine();
            lexemeStart = source.getCurrentColumn();

            lexeme.append(source.getCurrentChar());

            source.advance();

            stringValue = lexeme.toString();
        }

        public long getCurrentLine() { return currentLine; }
        public long getLexemeStart() { return lexemeStart; }
        public String getStringValue() { return stringValue; }
    }

    public Token createOP(Source source) throws IOException {

        SingleLexeme lexeme = new SingleLexeme(source);

        if (lexeme.getStringValue().equals("="))
            return new AtribToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
        else if (lexeme.getStringValue().equals("+"))
            return new PlusToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
        else if (lexeme.getStringValue().equals("-"))
            return new MinusToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
        else if (lexeme.getStringValue().equals("*"))
            return new TimesToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
        else if (lexeme.getStringValue().equals("/"))
            return new DivToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
        else if (lexeme.getStringValue().equals("%"))
            return new ModToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
        else
            return new PowToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
    }

    public boolean isSpecial(char value) {
        return value == ',' || value == ';';
    }

    public Token createSPECIAL(Source source) throws IOException {

        SingleLexeme lexeme = new SingleLexeme(source);

        if (lexeme.getStringValue().equals(","))
            return new CommaToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
        else
            return new SemiToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
    }

    public boolean isDelimiter(char value) {
        return value == '(' || value == ')';
    }

    public Token createDELIM(Source source) throws IOException {

        SingleLexeme lexeme = new SingleLexeme(source);

        if (lexeme.getStringValue().equals("("))
            return new LParenToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
        else
            return new RParenToken(lexeme.getCurrentLine(), lexeme.getLexemeStart(), lexeme.getStringValue());
    }

    public Token createWHITE(Source source) throws IOException {
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
        StringBuilder lexeme = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            lexeme.append(source.getCurrentChar());
            source.advance();
        } while (source.getCurrentChar() != '\n' && source.getCurrentChar() != '\r' && source.getCurrentChar() != '\0');

        String stringValue = lexeme.toString();

        return new CommentToken(currentLine, lexemeStart, stringValue);
    }

    public Token createERROR(Source source) throws IOException {
        StringBuilder lexeme = new StringBuilder();

        long currentLine = source.getCurrentLine();
        long lexemeStart = source.getCurrentColumn();

        do {
            lexeme.append(source.getCurrentChar());
            source.advance();
        } while (!this.validToken(source.getCurrentChar()));

        String stringValue = lexeme.toString();

        return new ErrorToken(currentLine, lexemeStart, stringValue);
    }

}
