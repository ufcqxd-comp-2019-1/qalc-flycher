package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.ast.TreeNode;

/**
 * Analisador Sintático da linguagem.
 * <p>
 * O algoritmo de reconhecimento deve ser escolhido com base na gramática
 * criada para a linguagem. <u>Uma das opções a seguir devem ser usadas</u>:
 * <ul>
 *     <li>Analisador Descendente Recursivo</li>
 *     <li>LL(1)</li>
 *     <li>SLR(1)</li>
 * </ul>
 */
public class Parser {

    /**
     * Scanner de onde obter os tokens para construção da árvore de sintaxe.
     */
    protected Scanner scanner;

    /**
     * Constrói um Analisador Sintático a partir de uma fonte de tokens.
     *
     * @param scan Fonte a ser utilizada.
     */
    public Parser(Scanner scan) {
        scanner = scan;
    }

    /**
     * Inicia a Análise Sintática e constrói uma AST para o fluxo de tokens
     * lido.
     *
     * @return Raiz da AST construída para o fluxo de tokens lido.
     */
    public TreeNode parse() {
        // TODO
        return null;
    }

}
