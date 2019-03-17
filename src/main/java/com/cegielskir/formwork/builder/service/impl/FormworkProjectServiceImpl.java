package com.cegielskir.formwork.builder.service.impl;

import com.cegielskir.formwork.builder.dao.FormworkProjectDAO;
import com.cegielskir.formwork.builder.entity.FormworkProject;
import com.cegielskir.formwork.builder.service.FormworkProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FormworkProjectServiceImpl implements FormworkProjectService {

    @Autowired
    private FormworkProjectDAO formworkProjectDAO;

    @Transactional
    @Override
    public void add(FormworkProject formworkProject) {
        formworkProjectDAO.add(formworkProject);
    }

    @Transactional
    @Override
    public void delete(int id) {
        formworkProjectDAO.delete(id);

    }

    @Transactional
    @Override
    public FormworkProject getById(int id) {
        return formworkProjectDAO.getById(id);
    }
}
