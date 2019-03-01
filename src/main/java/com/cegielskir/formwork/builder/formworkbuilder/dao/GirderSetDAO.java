package com.cegielskir.formwork.builder.formworkbuilder.dao;

import com.cegielskir.formwork.builder.formworkbuilder.entity.GirderSet;

import java.util.List;

public interface GirderSetDAO {
    void add(GirderSet girderSet);
    List<GirderSet> getList(int formworkId);
    void delete(int id);
    GirderSet getById(int id);
}

