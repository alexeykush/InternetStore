package internetStore.dao;

import java.util.Collection;

public interface DAO<T> {

    void add(T item);
    void remove(T item);
    void remove(int id);
    T get (int id);
    Collection<T> getAll();

}
