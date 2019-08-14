package tk.jasonho.shortcuts.everything.iteration;

public interface Modifier<I, O> {
    O modify(I i);
}
