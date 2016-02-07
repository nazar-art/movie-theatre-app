package net.lelyak.edu.dao;

import java.util.List;

public interface GenericDao<E, I> {

    List<E> getAll();

    Integer create(E entity);

    E read(I id);

    void update(E entity);

    void delete(E entity);
}
