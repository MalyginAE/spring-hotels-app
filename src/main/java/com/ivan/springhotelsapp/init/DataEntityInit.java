package com.ivan.springhotelsapp.init;

import com.ivan.springhotelsapp.entity.Hotel;
import com.ivan.springhotelsapp.entity.HotelPart;
import com.ivan.springhotelsapp.entity.Visitor;
import com.ivan.springhotelsapp.repository.HotelPartRepository;
import com.ivan.springhotelsapp.repository.HotelRepository;
import com.ivan.springhotelsapp.repository.VisitorRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataEntityInit {
    private final HotelPartRepository hotelPartRepository;
    private final VisitorRepository visitorRepository;
    private final HotelRepository hotelRepository;

    public DataEntityInit(HotelPartRepository hotelPartRepository, VisitorRepository visitorRepository, HotelRepository hotelRepository) {
        this.hotelPartRepository = hotelPartRepository;
        this.visitorRepository = visitorRepository;
        this.hotelRepository = hotelRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        Hotel hotel1 = new Hotel("Гостиница Санкт-Петербург");
        Hotel hotel2 = new Hotel("Гостиница Нижний-Новгород");
        Hotel hotel3 = new Hotel("Гостиница ПодМостом");

        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);
        hotelRepository.save(hotel3);

        HotelPart hotelPartOfHotel1_1 = new HotelPart("ул. Ленина д.1",hotel1);
        HotelPart hotelPartOfHotel1_2 = new HotelPart("ул. Гагарина д.2",hotel1);
        HotelPart hotelPartOfHotel1_3 = new HotelPart("ул. Советская д 3",hotel2);
        HotelPart hotelPartOfHotel2_1 = new HotelPart("ул. Горького",hotel2);
        HotelPart hotelPartOfHotel2_2 = new HotelPart("ул. ПодМостовая д.5",hotel3);
        HotelPart hotelPartOfHotel3 = new HotelPart("ул. ПодМостовая д.6",hotel3);

        hotelPartRepository.save(hotelPartOfHotel1_1);
        hotelPartRepository.save(hotelPartOfHotel1_2);
        hotelPartRepository.save(hotelPartOfHotel1_3);
        hotelPartRepository.save(hotelPartOfHotel2_1);
        hotelPartRepository.save(hotelPartOfHotel2_2);
        hotelPartRepository.save(hotelPartOfHotel3);

        Visitor serega = new Visitor("Сергей","Вантеев","0",hotelPartOfHotel3);
        Visitor anton = new Visitor("Антон","Антонов","3",hotelPartOfHotel1_3);
        Visitor dima = new Visitor("Дмитрий","Кудрин","8",hotelPartOfHotel2_1);
        Visitor vyanya = new Visitor("Иван","Иванов","7",hotelPartOfHotel2_2);
        Visitor oksana = new Visitor("Оксана","Девина","6",hotelPartOfHotel1_2);
        Visitor sveta = new Visitor("Света","Полева","5",hotelPartOfHotel1_1);
        Visitor andrey = new Visitor("Андрей","Андреев","4",hotelPartOfHotel1_2);
        Visitor artem = new Visitor("Артем","Куприн","5",hotelPartOfHotel1_3);
        Visitor nikita = new Visitor("Никита","Никитин","7",hotelPartOfHotel2_2);
        Visitor petya = new Visitor("Петя","Питин","8",hotelPartOfHotel3);
        Visitor sahcha = new Visitor("Саша","Сашин","9",hotelPartOfHotel1_2);
        Visitor grischa = new Visitor("Григорий","Рак","3",hotelPartOfHotel2_2);

        visitorRepository.save(serega);
        visitorRepository.save(anton);
        visitorRepository.save(dima);
        visitorRepository.save(vyanya);
        visitorRepository.save(oksana);
        visitorRepository.save(sveta);
        visitorRepository.save(andrey);
        visitorRepository.save(artem);
        visitorRepository.save(nikita);
        visitorRepository.save(petya);
        visitorRepository.save(sahcha);
        visitorRepository.save(grischa);

    }

}
