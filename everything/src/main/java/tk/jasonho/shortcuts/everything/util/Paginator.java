package tk.jasonho.shortcuts.everything.util;

import java.util.ArrayList;
import java.util.List;

public class Paginator {
    public static <O> List<List<O>> paginate(Iterable<O> obj, int resultsPerPage) {
        int count = 0;

        List<List<O>> pages = new ArrayList<>();

        List<O> page = new ArrayList<>();
        for (O o : obj) {
            if(count >= resultsPerPage) {
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

    public static <O> List<O> mergePaginate(List<List<O>> obj) {
        List<O> objs = new ArrayList<>();

        obj.forEach(objs::addAll);

        return objs;
    }
}
