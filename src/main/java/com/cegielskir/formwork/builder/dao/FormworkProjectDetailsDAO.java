package com.cegielskir.formwork.builder.dao;

import com.cegielskir.formwork.builder.entity.Formwork;
import com.cegielskir.formwork.builder.entity.FormworkProjectDetails;

import java.util.List;

public interface FormworkProjectDetailsDAO {

    void add(FormworkProjectDetails formworkProjectDetails);
    void delete(int id);
    FormworkProjectDetails getById(int id);
}
