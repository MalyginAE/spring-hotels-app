package com.ivan.springhotelsapp.service;

import com.ivan.springhotelsapp.entity.HotelPart;
import com.ivan.springhotelsapp.entity.Visitor;
import com.ivan.springhotelsapp.repository.HotelPartRepository;
import com.ivan.springhotelsapp.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitorService {
    private static final Logger logger = LoggerFactory.getLogger(VisitorService.class);
    private final VisitorRepository visitorRepository;
    private final HotelPartRepository hotelPartRepository;

    public VisitorService(VisitorRepository visitorRepository, HotelPartRepository hotelPartRepository) {
        this.visitorRepository = visitorRepository;
        this.hotelPartRepository = hotelPartRepository;
    }


    public List<Visitor> getVisitorsList(){
        return visitorRepository.findAll();
    }

    public Visitor findVisitorById(Long id){
        return visitorRepository.findById(id).orElseThrow();
    }

    public void updateVisitorName(Long id, String name){
        logger.info("updateVisitorName invoked");
        visitorRepository.updateName(id,name);
        visitorRepository.flush();
        logger.info("updateVisitorName ended");
    }

    public void updateVisitorSurname(Long id, String surname){
        logger.info("updateVisitorSurname invoked");
        visitorRepository.updateSurname(id,surname);
        visitorRepository.flush();
        logger.info("updateVisitorSurname ended");
    }


    public void updateVisitorRoom(Long id, String room){
        logger.info("updateVisitorRoom invoked");
        visitorRepository.updateRoom(id,room);
        visitorRepository.flush();
        logger.info("updateVisitorRoom ended");
    }


    public boolean deleteVisitorById(Long id) {
        logger.info("deleteHotelById invoked");
        return visitorRepository.findById(id)
                .map(entity -> {
                    visitorRepository.delete(entity);
                    visitorRepository.flush();
                    return true;
                })
                .orElse(false);

    }


    public Boolean addVisitor(String name,  String surname, String room, Long hotelPartId){
        logger.info("addVisitor invoked");
        HotelPart hotelPart = hotelPartRepository.findById(hotelPartId).orElseThrow();
        Visitor visitor = new Visitor(name,surname,room, hotelPart);
        visitorRepository.save(visitor);
        visitorRepository.flush();
        logger.info("addVisitor ended success");
        return true;
    }

    public void deleteAllByHotelId(Long id) {
        visitorRepository.deleteAllByHotelId(id);
    }

    public void deleteByHotelName(String hotelName) {
        visitorRepository.deleteByHotelName(hotelName);
    }

    public void deleteByHotelNameAndId(String name, Long id) {
        visitorRepository.deleteByHotelNameAndId( name,  id);
    }

//    public void saveVisitor(String){
//        Visitor visitor = new Visitor();
//        visitorRepository.save(visitor);
//    }
   // public <>
}
