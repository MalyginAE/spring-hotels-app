package com.ivan.springhotelsapp.controllers;

import com.ivan.springhotelsapp.entity.Visitor;
import com.ivan.springhotelsapp.service.VisitorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService service;

    public VisitorController(VisitorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String getVisitorById(@PathVariable Long id, Model model) {
        Visitor visitor = service.findVisitorById(id);
        model.addAttribute("visitor",visitor);
        return "visitor";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<Visitor> visitorsList = service.getVisitorsList();
        model.addAttribute("visitors",visitorsList);
        return "visitors_main";
    }

    @PatchMapping
    public String updateVisitorDataById(@RequestParam Long id, @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String room,
                                        @RequestParam(required = false) String surName) {
        if (name != null && !name.isEmpty())
            service.updateVisitorName(id, name);
        if (room != null && !room.isEmpty())
            service.updateVisitorRoom(id, room);
        if (surName != null && !surName.isEmpty())
            service.updateVisitorSurname(id, surName);
        return "redirect:/visitors";
    }

    @PostMapping
    public String saveVisitor(@RequestParam String name, @RequestParam String surname,
                              @RequestParam String room,@RequestParam Long id){
        service.addVisitor(name,surname,room,id);
        return "redirect:/visitors";
    }

    @DeleteMapping
    public String deleteVisitorById(@RequestParam Long id) {
        service.deleteVisitorById(id);
        return "redirect:/visitors";
    }
}
