package com.cegielskir.formwork.builder.controller;

import com.cegielskir.formwork.builder.entity.Formwork;
import com.cegielskir.formwork.builder.entity.GirderSet;
import com.cegielskir.formwork.builder.entity.Room;
import com.cegielskir.formwork.builder.service.FormworkService;
import com.cegielskir.formwork.builder.service.GirderSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/girderSet")
public class GirderSetController {

    @Autowired
    FormworkService formworkService;

    @Autowired
    GirderSetService girderSetService;



    @GetMapping("/add")
    public String addGirderSet(@RequestParam("formworkId") int formworkId, Model model){
        GirderSet girderSet = new GirderSet();
        model.addAttribute("formwork",formworkService.getById(formworkId));
        model.addAttribute("girderSet", girderSet);
        return "girder-set-form";
    }

    @PostMapping("/save")
    public String saveGirderSet(@Valid @ModelAttribute("girderSet") GirderSet girderSet,
                           @RequestParam("formworkId") int formworkId,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "girder-set-form";
        } else {
            Formwork formwork = formworkService.getById(formworkId);
            formwork.addGirderSet(girderSet);
            formworkService.add(formwork);
            girderSetService.add(girderSet);
            return "redirect:/formwork/details/" + formworkId;
        }
    }

    @GetMapping("/delete")
    public String deleteGirderSet(@RequestParam("formworkId") int formworkId,
                                  @RequestParam("girderSetId") int girderSetId,
                             Model model){
        Formwork formwork = formworkService.getById(formworkId);
        GirderSet girderSet = girderSetService.getById(girderSetId);
        formwork.deleteGirderSet(girderSet);
        formworkService.add(formwork);
        girderSetService.delete(girderSetId);
        return "redirect:/formwork/details/" + formworkId;
    }
}
