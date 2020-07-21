package com.codecool.dao;

import com.codecool.customer.Customer;

import java.util.List;

public interface DAO<Obj> {
    public Obj getObj(String id);
    public List<Obj> getAllObj();
    public void update(Obj objToUpdate);
    public void add(Obj newObj);
    public void delete(String id);
}
