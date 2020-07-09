package pl.kolak.bookhotelroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kolak.bookhotelroom.models.Room;
import pl.kolak.bookhotelroom.repositories.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> rooms(){
        return roomRepository.findAll();
    }

    public List<Room> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Room> rooms = roomRepository.findAll(pageRequest);
        return rooms.getContent();
    }

    public Room roomById(Long id){
        return roomRepository.findById(id).get();
    }

    public void create(Room room){
        roomRepository.save(room);
    }

    public void delete(Long id){
        Optional<Room> room = roomRepository.findById(id);
        room.ifPresent(r -> roomRepository.delete(r));
    }

    public void modify(Long id,Room room){
        room.setId(id);
        roomRepository.save(room);
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void updateRoomAvailable(){
        List<Room> roomList = roomRepository.findAll();
        for(Room r:roomList){
            if(r.getLeftDay()!=0){
                r.setLeftDay(r.getLeftDay() - 1);
            }else{
                r.setAvailable(true);
            }
        }
        roomRepository.saveAll(roomList);
    }
}
