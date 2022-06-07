package com.ivan.springhotelsapp.controllers;

import com.ivan.springhotelsapp.entity.Hotel;
import com.ivan.springhotelsapp.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    @GetMapping
    public String showAllHotels(Model model){
        List<Hotel> hotelsList = hotelService.getHotelsList();
        model.addAttribute("hotels",hotelsList);
        return "hotels_main";
    }


    @GetMapping("/{id}")
    public String showHotelById(@PathVariable Long id, Model model ){
        model.addAttribute("hotel",hotelService.getHotelInfoById(id));
        model.addAttribute("visitors", hotelService.getListVisitorsById(id));

        return "hotel";
    }

    @DeleteMapping()
    public String deleteHotel(@RequestParam(required = false) String name, @RequestParam(required = false) Long id, Model model) {
        hotelService.deleteHotelById(id);
        //model.addAttribute("")
        return "redirect:/hotels";
    }

    @PatchMapping()
    public String updateHotel(@RequestParam() String name, @RequestParam() Long id, Model model){
        hotelService.updateHotelNameById(id, name);
        return "redirect:/hotels";
    }


    @PostMapping()
    public String saveHotel(@RequestParam()String hotelName,Model model){
        hotelService.saveHotel(hotelName);
        return "redirect:/hotels";
    }







//    private Boolean extracted(String name, Long id) {
//        if (name != null && !name.isEmpty() && id != null) {
//            hotelService.deleteByHotelNameAndId(name, id);
//            return true;
//        }
//        else if (id == null && name != null && !name.isEmpty()) {
//            hotelService.deleteByHotelName(name);
//            return true;
//        }
//        else if (id != null){
//            hotelService.deleteHotelById(id);
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
}
