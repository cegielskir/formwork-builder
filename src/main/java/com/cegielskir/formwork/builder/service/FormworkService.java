package com.cegielskir.formwork.builder.service;

import com.cegielskir.formwork.builder.entity.Formwork;

import java.util.List;

public interface FormworkService {
    void add(Formwork formwork);
    List<Formwork> getList();
    void delete(int id);
    Formwork getById(int id);

}
