package tk.jasonho.shortcuts.everything.util;

import java.util.ArrayList;
import java.util.List;

public class Paginator {

    /**
     * Given a collection, we build a list
     * that contains lists which each contain
     * the specified maximum results per page
     * @param obj The original collection/iterable
     * @param resultsPerPage Maximum results per page
     * @param <O> Object type (we don't really care)
     * @return List of lists of resultsPerPage size
     */
    public static <O> List<List<O>> paginate(Iterable<O> obj, int resultsPerPage) {
        int count = 0;

        List<List<O>> pages = new ArrayList<>();

        List<O> page = new ArrayList<>();
        for (O o : obj) {
            if (count >= resultsPerPage) {
                pages.add(page);
                page = new ArrayList<>();
                count = 0;
            }

            page.add(o);

            count++;
        }
        pages.add(page);

        return pages;
    }

    /**
     * Undos what paginate() just did.
     *
     * @param obj A already paginated list
     * @param <O> Object type
     * @return De-paginated list
     */
    public static <O> List<O> mergePaginate(List<List<O>> obj) {
        List<O> objs = new ArrayList<>();

        obj.forEach(objs::addAll);

        return objs;
    }
}
