package com.cegielskir.formwork.builder.service.impl;

import com.cegielskir.formwork.builder.dao.FormworkProjectDetailsDAO;
import com.cegielskir.formwork.builder.entity.FormworkProjectDetails;
import com.cegielskir.formwork.builder.service.FormworkProjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FormworkProjectDetailsServiceImpl implements FormworkProjectDetailsService {

    @Autowired
    private FormworkProjectDetailsDAO formworkProjectDetailsDAO;

    @Transactional
    @Override
    public void add(FormworkProjectDetails formworkProjectDetails) {
        formworkProjectDetailsDAO.add(formworkProjectDetails);
    }

    @Transactional
    @Override
    public void delete(int id) {
        formworkProjectDetailsDAO.delete(id);
    }

    @Transactional
    @Override
    public FormworkProjectDetails getById(int id) {
        return formworkProjectDetailsDAO.getById(id);
    }
}
