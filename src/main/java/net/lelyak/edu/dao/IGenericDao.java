package net.lelyak.edu.dao;

import java.util.List;

public interface IGenericDao<ENTITY, ID> {

    Integer create(ENTITY entity);

    ENTITY read(ID id);

    void update(ENTITY entity);

    void delete(ID id);

    List<ENTITY> getAll();
}
