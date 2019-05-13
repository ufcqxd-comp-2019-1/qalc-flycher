package br.ufc.comp.qalc.frontend.ast;

import br.ufc.comp.qalc.frontend.SourceInterval;

/**
 * Tipo comum a todos os nós da AST da linguagem.
 * <p>
 * Os nós concretos da AST devem todos derivar deste, mesmo que indiretamente.
 */
public abstract class TreeNode {
    /**
     * Faixa dentro do código fonte que representa o nó atual.
     */
    protected SourceInterval position;
}
