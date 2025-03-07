package com.unitTestGenerator.persistence.repositories;

import com.unitTestGenerator.persistence.model.Data;

import java.util.List;

public interface IDaoService {

    public void update(Data data);
    public void save(Data data);
    public List<Data> findAll();
    public Data findById(Long id);
    public List<Data> findById2(Long id);
    public List<Data> findByName(String name);
    public void delete(Data data);

}
