package net.lelyak.edu.dao;

import java.util.List;

public interface IGenericDao<ENTITY, ID> {

    Integer save(ENTITY entity);

    ENTITY getById(ID id);

    void update(ENTITY entity);

    void delete(ID id);

    List<ENTITY> getAll();

    int getTotalCount();
}
