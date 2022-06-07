package com.ivan.springhotelsapp.controllers;

import com.ivan.springhotelsapp.service.HotelPartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hotelpart")
public class HotelPartController {
    private final HotelPartService hotelPartService;

    public HotelPartController(HotelPartService hotelPartService) {
        this.hotelPartService = hotelPartService;
    }
    @GetMapping
    public String getAllHotelParts(Model model){
        model.addAttribute("hotelParts",hotelPartService.getHotelPartList());
        return "hotel_parts_main";
    }

    @GetMapping("/{id}")
    public String getHotelPartById(@PathVariable Long id, Model model){
        model.addAttribute("hotelPart",hotelPartService.getHotelPartById(id));
        model.addAttribute("visitors",hotelPartService.getVisitorsListById(id));
        return "hotel_part";
    }

    @PostMapping
    public String addHotelPart(@RequestParam String address, @RequestParam Long id){
        if (id != null)
        hotelPartService.saveHotelPart(address,id);
        return "redirect:/hotelpart";
    }

    @PatchMapping
    public String updateHotelPartById(@RequestParam String address, Long id,Model model){
        hotelPartService.updateHotelPartAddressById(address, id);
        return "redirect:/hotelpart";
    }

    @DeleteMapping
    public String deleteHotelPartById(@RequestParam Long id){
        hotelPartService.deleteByHotelPartId(id);
        return "redirect:/hotelpart";
    }
}
