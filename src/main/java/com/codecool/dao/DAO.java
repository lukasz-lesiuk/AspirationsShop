package com.codecool.dao;

import com.codecool.customer.Customer;

import java.util.List;

public interface DAO<T> {
    public T getObj(String id);
    public List<T> getAllObj();
    public void update(T objToUpdate);
    public void add(T newObj);
    public void delete(String id);
}
