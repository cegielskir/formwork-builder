package com.cegielskir.formwork.builder.formworkbuilder.service;

import com.cegielskir.formwork.builder.formworkbuilder.entity.Formwork;
import com.cegielskir.formwork.builder.formworkbuilder.entity.GirderSet;

import java.util.List;

public interface GirderSetService {
    void add(GirderSet girderSet);
    List<GirderSet> getList(int formworkId);
    void delete(int id);
    GirderSet getById(int id);
}
