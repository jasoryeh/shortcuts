package tk.jasonho.shortcuts.everything.iteration;

public interface Comparer<L, R> {

    FavorableChoice compare(L left, R right);

    public enum FavorableChoice {
        LEFT,
        RIGHT
    }
}
