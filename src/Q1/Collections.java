package Q1;

import java.util.ArrayList;
import java.util.List;


class Collections<T extends Comparable> {
    public List<T> merge(List<T> list1, List<T> list2) {
        List<T> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).compareTo(list2.get(j)) <= 0) {
                res.add(list1.get(i++));
            } else {
                res.add(list2.get(j++));
            }
        }
        while (i < list1.size()) {
            res.add(list1.get(i++));
        }
        while (j < list2.size()) {
            res.add(list2.get(j++));
        }
        return res;
    }

}