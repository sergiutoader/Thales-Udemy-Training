package treesetcomparator;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getYear() == o2.getYear()) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
        return o1.getYear() - o2.getYear();
    }
}
