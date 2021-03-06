package com.cegielskir.formwork.builder.dao;

import com.cegielskir.formwork.builder.entity.Formwork;

import java.util.List;

public interface FormworkDAO {

    void add(Formwork formwork);
    List<Formwork> getList();
    void delete(int id);
    Formwork getById(int id);
}
