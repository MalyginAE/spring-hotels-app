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
public class HotelPartService extends ServiceHelper{
    private final Logger logger = LoggerFactory.getLogger(HotelPartService.class);
    private final HotelPartRepository hotelPartRepository;
    private final HotelRepository hotelRepository;
    private final VisitorRepository visitorRepository;

    public HotelPartService(HotelPartRepository hotelPartRepository, HotelRepository hotelRepository, VisitorRepository visitorRepository) {
        this.hotelPartRepository = hotelPartRepository;
        this.hotelRepository = hotelRepository;
        this.visitorRepository = visitorRepository;
    }

    public List<HotelPart> getHotelPartList(){
        return hotelPartRepository.findAll();
    }


    public void updateHotelPartAddressById(String address, Long id){
        hotelPartRepository.updateAddress(id, address);

    }

    public boolean deleteByHotelPartId(Long id) {
        logger.info("deleteHotelById invoked");
        visitorRepository.deleteAllByHotelPartId(id);

        return hotelPartRepository.findById(id)
                .map(entity -> {
                    hotelPartRepository.delete(entity);
                    hotelPartRepository.flush();
                    return true;
                })
                .orElse(false);

    }


    public void saveHotelPart(String hotelName, Long hotelId) {
        logger.info("saveHotelPart invoked");
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();
        HotelPart hotelPart = new HotelPart(hotelName,hotel);
        hotelPartRepository.save(hotelPart);
    }



    public List<Visitor> getVisitorsListById(Long id){
        logger.info("{}.getVisitorsListById invoked()",this.getClass());
       return hotelPartRepository.findById(id).stream().map(HotelPart::getVisitors).reduce(listVisitorBiOperator).orElse(Collections.emptyList());
    }

    public void deleteAllByHotelId(Long id) {
        logger.info("{}.deleteAllByHotelId invoked()",this.getClass());
        hotelPartRepository.deleteAllByHotelId(id);
    }

    public void deleteByHotelName(String hotelName) {
        logger.info("{}.deleteByHotelName() invoked",this.getClass());
        hotelPartRepository.deleteByHotelName(hotelName);
    }

    public void deleteByHotelNameAndId(String name, Long id) {
        hotelPartRepository.deleteByHotelNameAndId(name,id);
    }

    public HotelPart getHotelPartById(Long id) {
        return hotelPartRepository.findById(id).orElseThrow();
    }
}
