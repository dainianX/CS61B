package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> cmp;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (T n : this) {
            if (cmp.compare(maxItem, n) < 0) {
                maxItem = n;
            }
        }
        return maxItem;
    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (T n : this) {
            if (c.compare(maxItem, n) < 0) {
                maxItem = n;
            }
        }
        return maxItem;
    }

}
