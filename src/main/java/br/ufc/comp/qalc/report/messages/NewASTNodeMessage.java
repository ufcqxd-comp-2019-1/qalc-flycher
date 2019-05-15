package br.ufc.comp.qalc.report.messages;

import br.ufc.comp.qalc.frontend.ast.TreeNode;

/**
 * Mensagem específica para reportar o reconhecimento de um novo nó na AST.
 * <p>
 * Esta mensagem se origina obrigatoriamente da Análise Sintática.
 */
public class NewASTNodeMessage extends Message {

    protected final TreeNode node;

    /**
     * Constrói uma mensagem informando o nó que foi construído.
     * <p>
     * Informa à superclasse que a mensagem diz respeito à fase de Análise Sintática.
     *
     * @param newNode TreeNode reconhecido.
     */
    public NewASTNodeMessage(TreeNode newNode) {
        super(MessageCategory.PARSING, null);

        this.node = newNode;
    }

    /**
     * Obtém o nó reconhecido que essa mensagem carrega.
     *
     * @return TreeNode reconhecido.
     */
    public TreeNode getNode() {
        return node;
    }

}