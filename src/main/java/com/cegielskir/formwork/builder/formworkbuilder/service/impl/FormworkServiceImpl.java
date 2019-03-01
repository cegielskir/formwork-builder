package com.cegielskir.formwork.builder.formworkbuilder.service.impl;

import com.cegielskir.formwork.builder.formworkbuilder.dao.FormworkDAO;
import com.cegielskir.formwork.builder.formworkbuilder.entity.Formwork;
import com.cegielskir.formwork.builder.formworkbuilder.service.FormworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FormworkServiceImpl implements FormworkService{

    @Autowired
    FormworkDAO formworkDAO;

    @Override
    @Transactional
    public void add(Formwork formwork) {
        formworkDAO.add(formwork);
    }

    @Override
    @Transactional
    public List<Formwork> getList() {
        return formworkDAO.getList();
    }

    @Override
    @Transactional
    public void delete(int id) {
        formworkDAO.delete(id);
    }

    @Override
    @Transactional
    public Formwork getById(int id) {
        return formworkDAO.getById(id);
    }
}
