package com.cegielskir.formwork.builder.dao;

import com.cegielskir.formwork.builder.entity.Formwork;
import com.cegielskir.formwork.builder.entity.FormworkProject;

import java.util.List;

public interface FormworkProjectDAO {
    void add(FormworkProject formworkProject);
    void delete(int id);
    FormworkProject getById(int id);
}
