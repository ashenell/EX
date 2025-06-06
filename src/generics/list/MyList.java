package generics.list;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> {

    private final List<T> elements = new ArrayList<>();

    public void add(T element) {
        elements.add(element);
    }

    public void addAll(List<? extends T> elements) {
        for (T each : elements) {
            add(each);
        }
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
