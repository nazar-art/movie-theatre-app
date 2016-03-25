package net.lelyak.edu.repository;

import net.lelyak.edu.dao.BaseDAO;
import net.lelyak.edu.entity.BaseEntity;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public abstract class BaseRepository<T extends BaseEntity, E extends BaseDAO<T>> {

    private E dao;

    public E getDao() {
        return dao;
    }

    public void setDao(E dao) {
        this.dao = dao;
    }

    public long put(T entity) {
        return dao.save(preSave(entity));
    }

    public void remove(T entity) {
        dao.delete(entity);
    }

    public T getById(long id) {
        return postLoad(dao.getById(id));
    }

    public T getByName(String name) {
        return postLoad(dao.getByName(name));
    }

    public Collection<T> getAll() {
        return dao.getAll().stream()
                .map(this::postLoad)
                .collect(toList());
    }

    public void putAll(Collection<T> entities) {
        dao.saveAll(entities.stream()
                .map(this::postLoad)
                .collect(toList()));
    }

    public int getTotalCount() {
        return dao.getTotalCount();
    }

    public abstract T preSave(T entity);

    public abstract T postLoad(T entity);
}
