package br.ufc.comp.qalc.frontend;

public class SourceInterval {
    protected final SourceLocation begin;
    protected final SourceLocation end;

    public static SourceInterval makeInterval(long lineBegin, long columnBegin, long lineEnd, long columnEnd) {
        return new SourceInterval(new SourceLocation(lineBegin, columnBegin), new SourceLocation(lineEnd, columnEnd));
    }

    public SourceInterval(SourceLocation begin, SourceLocation end) {
        this.begin = begin;
        this.end = end;
    }

    public SourceLocation getBegin() {
        return begin;
    }

    public SourceLocation getEnd() {
        return end;
    }
}
