package br.ufc.comp.qalc.report.messages;

/**
 * Mensagem específica para reportar o reconhecimento de um novo nó na AST.
 * <p>
 * Esta mensagem se origina obrigatoriamente da Análise Sintática.
 */
public class MatchingNonterminalMessage extends Message {

    /**
     * Constrói uma mensagem informando o não-terminal cujo reconhecimento foi iniciado.
     * <p>
     * Informa à superclasse que a mensagem diz respeito à fase de Análise Sintática.
     *
     * @param nonterminal Não-terminal a reconhecer.
     */
    public MatchingNonterminalMessage(String nonterminal) {
        super(MessageCategory.PARSING, nonterminal);
    }

}