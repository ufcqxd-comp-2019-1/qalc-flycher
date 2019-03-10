package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;

/**
 * Analisador Léxico da linguagem.
 * <p>
 * Funciona como uma fonte de tokens, de onde o Analisador Sintático
 * deverá ler.
 *
 * @see Source
 */
public class Scanner {

    /**
     * Último token reconhecido.
     */
    protected Token currentToken;
    /**
     * Fonte de caracteres de onde extrair os tokens.
     */
    protected Source source;

    /**
     * Constrói um Analisador Léxico a partir de uma fonte de caracteres.
     *
     * @param sourceStream Fonte a ser utilizada.
     */
    public Scanner(Source sourceStream) {
        // FIXME Lidar corretamente com as exceções.
        this.source = sourceStream;
    }

    /**
     * Consome caracteres da fonte até que eles componham um lexema de
     * um dos tokens da linguagem, coinstrói um objeto representando esse
     * token associado ao lexema lido e o retorna.
     *
     * @return Token reconhecido.
     * @throws IOException Caso haja problema na leitura da fonte.
     */
    public Token getNextToken() throws IOException {
        // TODO Reconhecimento de tokens

        if (source.getCurrentChar() == Source.EOF) { // EOF
            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());
        } else if (Character.isDigit(source.getCurrentChar())) { // NumberToken
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

                while (Character.isDigit(source.getCurrentChar())) {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                }
            }

            String stringValue = lexema.toString();

            return new NumberToken(currentLine, lexemeStart, stringValue);
        } else if(source.getCurrentChar() == '@') { // FunctionIdentifierToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()) || Character.isLetter(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new FunctionIdentifierToken(currentLine, lexemeStart, stringValue);
        } else if(source.getCurrentChar() == '$') { // VariableIdentifierToken or ResultIdentifierToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexema.append(source.getCurrentChar());
            source.advance();

            if(Character.isLetter(source.getCurrentChar())) { // VariableIdentifierToken
                do {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isLetter(source.getCurrentChar()));

                String stringValue = lexema.toString();

                return new VariableIdentifierToken(currentLine, lexemeStart, stringValue);

            } else if(source.getCurrentChar() == '?'){ // ResultIdentifierToken
                    lexema.append(source.getCurrentChar());
                    source.advance();

                    String stringValue = lexema.toString();

                    return new ResultIdentifierToken(currentLine, lexemeStart, stringValue);

            } else if(Character.isDigit(source.getCurrentChar())) { // ResultIdentifierToken
                do {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isDigit(source.getCurrentChar()));

                String stringValue = lexema.toString();

                return new ResultIdentifierToken(currentLine, lexemeStart, stringValue);

            }
        } else if(OperatorToken.isOperator(source.getCurrentChar())) {
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            String stringValue = lexema.toString();

            return new OperatorToken(currentLine, lexemeStart, stringValue);

        } else if(DelimiterToken.isDelimiter(source.getCurrentChar())) {
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            String stringValue = lexema.toString();

            return new DelimiterToken(currentLine, lexemeStart, stringValue);

        } else if(SpecialToken.isSpecial(source.getCurrentChar())) {
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            String stringValue = lexema.toString();

            return new SpecialToken(currentLine, lexemeStart, stringValue);

        } else if(Character.isWhitespace(source.getCurrentChar())) {
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());

            do {
                source.advance();
            } while (Character.isWhitespace(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new WhitespaceToken(currentLine, lexemeStart, stringValue);

        } else if(CommentToken.isComment(source.getCurrentChar())) {
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (source.getCurrentChar() != '\n' || source.getCurrentChar() != '\r');

            String stringValue = lexema.toString();

            return new CommentToken(currentLine, lexemeStart, stringValue);

        }
        // TODO Recuperação de erros.

        return null;
    }

    /**
     * Obtém o último token reconhecido.
     *
     * @return O último token reconhecido.
     */
    public Token getCurrentToken() {
        return currentToken;
    }
}
