package com.cegielskir.formwork.builder.formworkbuilder.service.impl;

import com.cegielskir.formwork.builder.formworkbuilder.dao.GirderSetDAO;
import com.cegielskir.formwork.builder.formworkbuilder.entity.GirderSet;
import com.cegielskir.formwork.builder.formworkbuilder.service.GirderSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GirderSetServiceImpl implements GirderSetService{

    @Autowired
    GirderSetDAO girderSetDAO;

    @Override
    @Transactional
    public void add(GirderSet girderSet) {
        girderSetDAO.add(girderSet);
    }

    @Override
    @Transactional
    public List<GirderSet> getList(int formworkId) {
        return girderSetDAO.getList(formworkId);
    }

    @Override
    @Transactional
    public void delete(int id) {
        girderSetDAO.delete(id);
    }

    @Override
    @Transactional
    public GirderSet getById(int id) {
        return girderSetDAO.getById(id);
    }
}
