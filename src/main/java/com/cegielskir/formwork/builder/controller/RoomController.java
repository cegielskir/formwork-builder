package com.cegielskir.formwork.builder.controller;

import com.cegielskir.formwork.builder.entity.Formwork;
import com.cegielskir.formwork.builder.entity.Room;
import com.cegielskir.formwork.builder.service.FormworkService;
import com.cegielskir.formwork.builder.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    FormworkService formworkService;

    @Autowired
    RoomService roomService;



    @GetMapping("/add")
    public String addRoom(@RequestParam("formworkId") int formworkId, Model model){
        Room room = new Room();
        model.addAttribute("formwork",formworkService.getById(formworkId));
        model.addAttribute("room", room);
        return "room-form";
    }

    @PostMapping("/save")
    public String saveRoom(@Valid @ModelAttribute("room") Room room,
                               @RequestParam("formworkId") int formworkId,
                               BindingResult bindingResult){
        Room correctRoom = room;
        if(bindingResult.hasErrors()){
            return "room-form";
        } else {
            if(room.getLen() < room.getWid()){
                correctRoom = new Room(room.getWid(), room.getLen());
            }
            Formwork formwork = formworkService.getById(formworkId);
            formwork.addRoom(correctRoom);
            formworkService.add(formwork);
            roomService.add(correctRoom);
            return "redirect:/formwork/details/" + formworkId;
        }
    }

    @GetMapping("/delete")
    public String deleteRoom(@RequestParam("formworkId") int formworkId, @RequestParam("roomId") int roomId,
                             Model model){
        Formwork formwork = formworkService.getById(formworkId);
        Room room = roomService.getById(roomId);
        formwork.deleteRoom(room);
        formworkService.add(formwork);
        roomService.delete(roomId);
        return "redirect:/formwork/details/" + formworkId;
    }
}
