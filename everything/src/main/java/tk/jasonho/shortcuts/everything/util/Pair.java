package tk.jasonho.shortcuts.everything.util;

/**
 * Simple pairing of values
 *
 * @param <L> Key/Left
 * @param <R> Value/Right
 */
public class Pair<L, R> {

    private final L l;
    private final R r;

    private Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getLeft() {
        return l;
    }

    public R getRight() {
        return r;
    }

    public L getKey() {
        return l;
    }

    public R getValue() {
        return r;
    }

    public static <L, R> Pair<L, R> of(L l, R r) {
        return new Pair<L, R>(l, r);
    }
}
