package com.cegielskir.formwork.builder.controller;

import com.cegielskir.formwork.builder.computing.ComputingManager;
import com.cegielskir.formwork.builder.entity.Formwork;
import com.cegielskir.formwork.builder.entity.FormworkProject;
import com.cegielskir.formwork.builder.entity.FormworkProjectDetails;
import com.cegielskir.formwork.builder.service.FormworkProjectDetailsService;
import com.cegielskir.formwork.builder.service.FormworkProjectService;
import com.cegielskir.formwork.builder.service.FormworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/formwork")
public class FormworkController {

    @Autowired
    FormworkService formworkService;

    @Autowired
    FormworkProjectService formworkProjectService;

    @Autowired
    FormworkProjectDetailsService formworkProjectDetailsService;


    @GetMapping("/list")
    public String getFormworks(Model model){

        List<Formwork> formworks = formworkService.getList();

        model.addAttribute("formworks", formworks);
        return "index";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model){

        Formwork formwork = new Formwork();

        model.addAttribute("formwork", formwork);

        return "formwork-form";
    }

    @PostMapping("/save")
    public String saveFormwork(@Valid @ModelAttribute("forwork") Formwork formwork,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "formwork-form";
        } else {
            System.out.println("In save Formwork");

            FormworkProject formworkProject = new FormworkProject();
            FormworkProjectDetails formworkProjectDetails = new FormworkProjectDetails();
            formworkProject.setFormworkProjectDetails(formworkProjectDetails);
            formwork.setFormworkProject(formworkProject);
            formworkProjectService.add(formworkProject);
            formworkProjectDetailsService.add(formworkProjectDetails);
            formworkService.add(formwork);
            return "redirect:/formwork/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("formworkId") int id, Model model){
        formworkService.delete(id);

        return "redirect:/formwork/list";
    }

    @GetMapping("/details/{formworkId}")
    public String getDetails(@PathVariable("formworkId") int id, Model model){
        Formwork formwork = formworkService.getById(id);
        model.addAttribute("formwork",formwork);
        model.addAttribute("rooms",formwork.getRooms());
        model.addAttribute("girderSets", formwork.getGirderSets());
        return "show-details";
    }

    @GetMapping("/solution/{formworkId}")
    public String getSolution(@PathVariable("formworkId") int id, Model model){
        Formwork formwork = formworkService.getById(id);
        return formwork.getFormworkProject().getResultJSON();
    }

    @GetMapping("/compute/{formworkId}")
    public String computeNewSolution(@PathVariable("formworkId") int id, Model model){
        Formwork formwork = formworkService.getById(id);
        formwork.getFormworkProject().setIsBetterSolutionCalculated(false);
        Thread thread = new Thread(new ComputingManager(formwork, formworkProjectService, formworkService));
        thread.start();
        return "redirect:/formwork/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, "createDate",
                new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), false, 10));
        binder.registerCustomEditor(List.class, "formworks", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                return formworkService.getById(Integer.valueOf((String) element));
            }
        });
    }
}
