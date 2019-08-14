package tk.jasonho.shortcuts.everything.iteration;

public interface Matcher<E> {
    /**
     * Do something with the element,
     * return true if it matches your condition
     * @param e Custom element
     * @return true for matches, false for doesn't match
     */
    boolean matches(E e);
}
