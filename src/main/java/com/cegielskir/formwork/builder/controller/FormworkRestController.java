package com.cegielskir.formwork.builder.controller;

import com.cegielskir.formwork.builder.entity.Formwork;
import com.cegielskir.formwork.builder.service.FormworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/formwork")
public class FormworkRestController {

    @Autowired
    FormworkService formworkService;

    @GetMapping("/get_result/{formworkId}")
    public String getResultAsJSON(@PathVariable("formworkId") int id, Model model){
        Formwork formwork = formworkService.getById(id);
        return formwork.getFormworkProject().getResultJSON();

    }



}
