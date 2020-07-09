package pl.kolak.bookhotelroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.kolak.bookhotelroom.models.Equipment;
import pl.kolak.bookhotelroom.models.Room;
import pl.kolak.bookhotelroom.repositories.EquipmentRepository;
import pl.kolak.bookhotelroom.repositories.RoomRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@SpringBootApplication
@EnableScheduling
public class BookHotelRoomApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BookHotelRoomApplication.class, args);

        //new BookHotelRoomApplication().add(ctx);
    }

    @Transactional
    public void add(ConfigurableApplicationContext ctx){
        Room room = new Room(2,true,100);
        Equipment equipment = new Equipment("Krzesłą",2);
        //Equipment equipment2 = new Equipment("Stoliki",2);
        EquipmentRepository equipmentRepository = ctx.getBean(EquipmentRepository.class);
        RoomRepository roomRepository = ctx.getBean(RoomRepository.class);
        equipmentRepository.save(equipment);
        room.getEquipment().add(equipment);
        //equipment.getRoomList().add(room);
        roomRepository.save(room);

        Room room2 = new Room(2,true,200);
        room2.getEquipment().add(equipment);
        //equipment.getRoomList().add(room2);
        roomRepository.save(room2);
    }

}
