package tk.jasonho.shortcuts.everything.iteration;

import tk.jasonho.shortcuts.everything.util.Pair;

import java.util.ArrayList;
import java.util.Collection;

import static tk.jasonho.shortcuts.everything.iteration.Comparer.FavorableChoice.LEFT;

public class Stream<I> {
    private final Collection<I> collection;

    private Stream(Collection<I> collection) {
        this.collection = collection;
    }

    public static <E> Stream<E> from(Collection<E> stream) {
        return new Stream<E>(stream);
    }

    public Pair<Collection<I>, Collection<I>> filter(Matcher<I> matcher) {
        ArrayList<I> filteredIn = new ArrayList<>();
        ArrayList<I> filteredOut = new ArrayList<>();
        for (I i : collection) {
            if (matcher.matches(i)) {
                filteredIn.add(i);
            } else {
                filteredOut.add(i);
            }
        }

        return Pair.of(filteredIn, filteredOut);
    }

    public Stream<I> filterOut(Matcher<I> m) {
        return new Stream<I>(this.filter(m).getRight());
    }

    public Stream<I> filterIn(Matcher<I> m) {
        return new Stream<I>(this.filter(m).getLeft());
    }

    public <O> Stream<O> modify(Modifier<I, O> m) {
        ArrayList<O> modified = new ArrayList<>();
        for (I i : collection) {
            modified.add(m.modify(i));
        }
        return new Stream<O>(modified);
    }

    public Stream<I> order(Comparer<I, I> c) {
        ArrayList<I> buildNew = new ArrayList<>();

        while(buildNew.size() != this.collection.size()) {
            I mostFavorable = null;
            for (I i : this.collection) {
                if(buildNew.contains(i)) {
                    continue;
                }

                if(mostFavorable == null) {
                    mostFavorable = i;
                    continue;
                }

                I left = mostFavorable;
                I right = i;

                Comparer.FavorableChoice compare = c.compare(left, right);

                mostFavorable = compare == LEFT ? left : right;
            }
            buildNew.add(mostFavorable);
        }

        return new Stream<>(buildNew);
    }

    public Stream<I> forEach(Function<I> f) {
        for (I i : this.collection) {
            f.accept(i);
        }

        return this;
    }

    public Collection<I> toCollection() {
        return collection;
    }
}
