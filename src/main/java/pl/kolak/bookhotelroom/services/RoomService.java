package pl.kolak.bookhotelroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.kolak.bookhotelroom.models.Booking;
import pl.kolak.bookhotelroom.models.Room;
import pl.kolak.bookhotelroom.repositories.BookingRepository;
import pl.kolak.bookhotelroom.repositories.RoomRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class RoomService {
    RoomRepository roomRepository;
    BookingRepository bookingRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
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

    public List<Map<String, LocalDate>> dateAvailableRoom(Long id){
        List<Map<String, LocalDate>> dateList = new ArrayList<>();
        List<Booking> bookingList = bookingRepository.findAll();
        List <Booking> filterRoomList = new ArrayList<>();
        for(Booking b:bookingList){
            List<Room> roomList = b.getRooms();
            for(Room r:roomList){
                if(r.getId()==id)
                    filterRoomList.add(b);
            }
        }
        filterRoomList.sort(Comparator.comparing(Booking::getBooking_from));
        Map<String, LocalDate> mapRoomList;
        for(int i=1; i<filterRoomList.size(); i++){
            if(!filterRoomList.get(i-1).getBooking_to().equals(filterRoomList.get(i).getBooking_from())){
                mapRoomList = new HashMap<>();
                mapRoomList.put("from:",filterRoomList.get(i-1).getBooking_to());
                mapRoomList.put("to:",filterRoomList.get(i).getBooking_from());
                dateList.add(mapRoomList);
            }
        }

        return dateList;
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void updateRoomAvailable(){
        /*List<Room> roomList = roomRepository.findAll();
        List<Booking> bookingList = bookingRepository.findAll();
        LocalDate now = LocalDate.now();

        for(Room r:roomList){
            if(r.getLeftDay()!=0){
                r.setLeftDay(r.getLeftDay() - 1);
            }else{
                r.setAvailable(true);
            }
        }
        roomRepository.saveAll(roomList);*/

        List<Room> roomList = roomRepository.findAll();
        List<Booking> bookingList = bookingRepository.findAll();
        bookingList.sort(Comparator.comparing(Booking::getBooking_from));
        LocalDate now = LocalDate.now();

        for(Booking b:bookingList){
            if(b.getBooking_to().equals(now)){
                roomList.forEach(r->r.setAvailable(true));
            }
            if(b.getBooking_from().equals(now)){
                roomList.forEach(r->r.setAvailable(false));
            }
        }
        roomRepository.saveAll(roomList);
    }
}
