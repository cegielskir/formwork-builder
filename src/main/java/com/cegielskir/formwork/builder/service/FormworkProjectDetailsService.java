package com.cegielskir.formwork.builder.service;

import com.cegielskir.formwork.builder.entity.FormworkProject;
import com.cegielskir.formwork.builder.entity.FormworkProjectDetails;

public interface FormworkProjectDetailsService {
    void add(FormworkProjectDetails formworkProjectDetails);
    void delete(int id);
    FormworkProjectDetails getById(int id);
}
