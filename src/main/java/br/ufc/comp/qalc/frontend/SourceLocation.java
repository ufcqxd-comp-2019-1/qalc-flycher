package br.ufc.comp.qalc.frontend;

public final class SourceLocation {
    protected final long line;

    protected final long column;

    public SourceLocation(long line, long column) {
        this.line = line;
        this.column = column;
    }

    public long getLine() {
        return line;
    }

    public long getColumn() {
        return column;
    }
}
