package com.example.shopr.repositories;

import java.util.List;

public interface Repository<T , ID extends Number> {

    public List<T> findAll();

    public T findById(ID id);

    public T save(T entity);

    public void remove(ID id);

}
