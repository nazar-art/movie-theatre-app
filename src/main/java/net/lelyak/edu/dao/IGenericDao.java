package net.lelyak.edu.dao;

import net.lelyak.edu.entity.BaseEntity;

import java.util.Collection;

public interface IGenericDao<ENTITY extends BaseEntity> {

    Integer save(ENTITY entity);

    ENTITY getById(long id);

    ENTITY getByName(String name);

    void update(ENTITY entity);

    void delete(ENTITY entity);

    Collection<ENTITY> getAll();

    default void saveAll(Collection<ENTITY> entities) {
        entities.forEach(this::save);
    }

    int getTotalCount();
}
