package com.ivan.springhotelsapp.service;

import com.ivan.springhotelsapp.entity.Hotel;
import com.ivan.springhotelsapp.entity.HotelPart;
import com.ivan.springhotelsapp.entity.Visitor;
import com.ivan.springhotelsapp.repository.HotelPartRepository;
import com.ivan.springhotelsapp.repository.HotelRepository;
import com.ivan.springhotelsapp.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class HotelService extends ServiceHelper {
    private static final Logger logger = LoggerFactory.getLogger(HotelService.class);
    private final VisitorService visitorService;
    private final HotelPartService hotelPartService;
    private final HotelRepository hotelRepository;
    private final HotelPartRepository hotelPartRepository;
    private final VisitorRepository visitorRepository;

    public HotelService(VisitorService visitorService, HotelPartService hotelPartService,
                        HotelRepository hotelRepository, HotelPartRepository hotelPartRepository, VisitorRepository visitorRepository) {
        this.visitorService = visitorService;
        this.hotelPartService = hotelPartService;
        this.hotelRepository = hotelRepository;
        this.hotelPartRepository = hotelPartRepository;
        this.visitorRepository = visitorRepository;
    }


    public List<Visitor> getListVisitorsById(Long id) {
        logger.info("getListVisitorsById invoked");
        return hotelRepository.findById(id).orElseThrow().getHotelParts().stream().map(HotelPart::getVisitors)
                .reduce(listVisitorBiOperator).orElse(Collections.emptyList());
    }


    public void saveHotel(String hotelName) {
        logger.info("saveHotel invoked");
        Hotel hotel = new Hotel(hotelName);
        hotelRepository.save(hotel);
    }

    public void updateHotelNameById(Long id, String hotelName) {
        hotelRepository.updateName(id, hotelName);
    }

    public boolean deleteHotelById(Long id) {
        logger.info("deleteHotelById invoked");
        visitorService.deleteAllByHotelId(id);
        hotelPartService.deleteAllByHotelId(id);
        return hotelRepository.findById(id)
                .map(entity -> {
                    hotelRepository.delete(entity);
                    hotelRepository.flush();
                    return true;
                })
                .orElse(false);

    }

    public long deleteByHotelName(String hotelName) {
        logger.info("deleteByHotelName invoked");
        visitorService.deleteByHotelName(hotelName);
        hotelPartService.deleteByHotelName(hotelName);
        return hotelRepository.findHotelsByHotelName(hotelName)
                .stream().peek(entity -> {
                    logger.info(entity.getHotelName());
                    hotelRepository.delete(entity);
                    logger.info("deleted success");
                    hotelRepository.flush();
                }).count();
    }

    public List<Hotel> getHotelsList(){
        return hotelRepository.findAll();
    }
    public Hotel getHotelInfoById(Long id) {
        return hotelRepository.findById(id).orElseThrow();
    }

    public void deleteByHotelNameAndId(String name, Long id) {
        logger.info("{}.deleteByHotelNameAndId() invoked",this.getClass());
        visitorService.deleteByHotelNameAndId(name,id);
        hotelPartService.deleteByHotelNameAndId(name,id);
        hotelRepository.deleteByHotelNameAndId(name,id);
    }
}
