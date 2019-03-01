package com.cegielskir.formwork.builder.formworkbuilder.service;

import com.cegielskir.formwork.builder.formworkbuilder.entity.Formwork;

import java.util.List;

public interface FormworkService {
    void add(Formwork formwork);
    List<Formwork> getList();
    void delete(int id);
    Formwork getById(int id);

}
