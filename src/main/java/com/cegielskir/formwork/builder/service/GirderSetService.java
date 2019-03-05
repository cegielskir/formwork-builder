package com.cegielskir.formwork.builder.service;

import com.cegielskir.formwork.builder.entity.GirderSet;

import java.util.List;

public interface GirderSetService {
    void add(GirderSet girderSet);
    List<GirderSet> getList(int formworkId);
    void delete(int id);
    GirderSet getById(int id);
}
