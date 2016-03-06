package com.mahull.repositories;

import com.mahull.model.ModelObject;

import java.util.List;

/**
 * Created by Ugo on 05/03/2016.
 */
public interface BaseRepository <T extends ModelObject> {

    void save(T t);
    T get(T t, Class<T> clazz);
    List<T> listItems(Class<T> clazz, String status);
    Boolean contains(T t);
}
