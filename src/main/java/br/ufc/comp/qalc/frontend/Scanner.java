package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.EOFToken;
import br.ufc.comp.qalc.frontend.token.Token;

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
        TokenReader reader = new TokenReader();

        if (source.getCurrentChar() == Source.EOF) { // EOF

            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());

        } else if (Character.isDigit(source.getCurrentChar())) { // NumberToken

            return reader.createNUML(source);

        } else if (source.getCurrentChar() == '@') { // FunctionIdentifierToken

            return reader.createFUNCID(source);

        } else if (source.getCurrentChar() == '$') { // VariableIdentifierToken or ResultIdentifierToken

            source.advance();

            if (Character.isLetter(source.getCurrentChar())) { // VariableIdentifierToken

                return reader.createVARID(source);

            } else if (source.getCurrentChar() == '?' || Character.isDigit(source.getCurrentChar())) { // ResultIdentifierToken

                return reader.createRESID(source);

            }

        } else if (reader.isOperator(source.getCurrentChar())) { // OperatorToken

            return reader.createOP(source);

        } else if (reader.isSpecial(source.getCurrentChar())) { //  , ;

            return reader.createSPECIAL(source);

        } else if (reader.isDelimiter(source.getCurrentChar())) { //  ( )

            return reader.createDELIM(source);

        } else if (Character.isWhitespace(source.getCurrentChar())) { // WhitespaceToken

            return reader.createWHITE(source);

        } else if (reader.isComment(source.getCurrentChar())) { // CommentToken

            return reader.createCOMMENT(source);

        } else { // Nao combina com nenhum token.

            return reader.createERROR(source);

        }
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
