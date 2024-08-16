package dao;

import java.util.List;

public interface Repository<I, T> {
    T findById(I id);

    List<T> findAll();
}
