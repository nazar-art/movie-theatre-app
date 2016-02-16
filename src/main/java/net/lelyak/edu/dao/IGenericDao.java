package net.lelyak.edu.dao;

import java.util.List;

public interface IGenericDao<E, I> {

    Integer create(E entity);

    E read(I id);

    void update(E entity);

    void delete(E entity);

    List<E> getAll();
}
