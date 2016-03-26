package com.mahull.model.repositories;

import com.mahull.model.model.ModelObject;

import java.util.List;

/**
 * Created by Ugo on 05/03/2016.
 */
public interface BaseRepository<T extends ModelObject> {

    void save(T type);

    T get(T type, Class<T> clazz);

    List<T> listItems(Class<T> clazz, String status);

    Boolean contains(T type);
}
